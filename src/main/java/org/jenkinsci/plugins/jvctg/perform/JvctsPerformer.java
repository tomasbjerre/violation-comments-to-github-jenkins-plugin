package org.jenkinsci.plugins.jvctg.perform;

import static com.google.common.base.Strings.emptyToNull;
import static com.google.common.base.Strings.isNullOrEmpty;
import static com.google.common.base.Throwables.propagate;
import static com.google.common.collect.Lists.newArrayList;
import static java.util.logging.Level.INFO;
import static java.util.logging.Level.SEVERE;
import static org.jenkinsci.plugins.jvctg.JvctsLogger.doLog;
import static org.jenkinsci.plugins.jvctg.config.ViolationsToGitHubConfigHelper.FIELD_COMMENTONLYCHANGEDCONTENT;
import static org.jenkinsci.plugins.jvctg.config.ViolationsToGitHubConfigHelper.FIELD_CREATECOMMENTWITHALLSINGLEFILECOMMENTS;
import static org.jenkinsci.plugins.jvctg.config.ViolationsToGitHubConfigHelper.FIELD_CREATESINGLEFILECOMMENTS;
import static org.jenkinsci.plugins.jvctg.config.ViolationsToGitHubConfigHelper.FIELD_GITHUBURL;
import static org.jenkinsci.plugins.jvctg.config.ViolationsToGitHubConfigHelper.FIELD_OAUTH2TOKEN;
import static org.jenkinsci.plugins.jvctg.config.ViolationsToGitHubConfigHelper.FIELD_PASSWORD;
import static org.jenkinsci.plugins.jvctg.config.ViolationsToGitHubConfigHelper.FIELD_PULLREQUESTID;
import static org.jenkinsci.plugins.jvctg.config.ViolationsToGitHubConfigHelper.FIELD_REPOSITORYNAME;
import static org.jenkinsci.plugins.jvctg.config.ViolationsToGitHubConfigHelper.FIELD_REPOSITORYOWNER;
import static org.jenkinsci.plugins.jvctg.config.ViolationsToGitHubConfigHelper.FIELD_USERNAME;
import static se.bjurr.violations.comments.github.lib.ViolationCommentsToGitHubApi.violationCommentsToGitHubApi;
import static se.bjurr.violations.lib.ViolationsReporterApi.violationsReporterApi;
import static se.bjurr.violations.lib.parsers.FindbugsParser.setFindbugsMessagesXml;
import hudson.EnvVars;
import hudson.model.BuildListener;
import hudson.model.AbstractBuild;

import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.util.List;

import org.jenkinsci.plugins.jvctg.config.ViolationConfig;
import org.jenkinsci.plugins.jvctg.config.ViolationsToGitHubConfig;

import se.bjurr.violations.lib.model.Violation;

import com.google.common.annotations.VisibleForTesting;
import com.google.common.io.CharStreams;

public class JvctsPerformer {

 public static void jvctsPerform(ViolationsToGitHubConfig config, AbstractBuild<?, ?> build, BuildListener listener) {
  try {
   EnvVars env = build.getEnvironment(listener);
   config = expand(config, env);
   listener.getLogger().println("---");
   listener.getLogger().println("--- Jenkins Violation Comments to GitHub ---");
   listener.getLogger().println("---");
   logConfiguration(config, build, listener);

   listener.getLogger().println("Running Jenkins Violation Comments To GitHub");
   listener.getLogger().println("Will comment " + config.getPullRequestId());

   File workspace = new File(build.getExecutor().getCurrentWorkspace().toURI());
   setupFindBugsMessages();
   doLog(INFO, "Workspace: " + workspace.getAbsolutePath());
   doPerform(config, workspace, listener);
  } catch (Exception e) {
   doLog(SEVERE, "", e);
   return;
  }
 }

 private static void setupFindBugsMessages() {
  try {
   String findbugsMessagesXml = CharStreams.toString(new InputStreamReader(JvctsPerformer.class
     .getResourceAsStream("findbugs-messages.xml")));
   setFindbugsMessagesXml(findbugsMessagesXml);
  } catch (IOException e) {
   propagate(e);
  }
 }

 @VisibleForTesting
 public static void doPerform(ViolationsToGitHubConfig config, File workspace, BuildListener listener)
   throws MalformedURLException {
  if (isNullOrEmpty(config.getPullRequestId())) {
   doLog(INFO, "No pull request id defined, will not send violation comments to GitHub.");
   return;
  }
  Integer pullRequestIdInt = Integer.valueOf(config.getPullRequestId());
  if (!isNullOrEmpty(config.getOAuth2Token())) {
   doLog(INFO, "Using OAuth2Token");
  } else if (!isNullOrEmpty(config.getUsername()) && !isNullOrEmpty(config.getPassword())) {
   doLog(INFO, "Using username/password: " + config.getUsername().substring(0, 1) + ".../*********");
  } else {
   doLog(INFO, "No OAuth2 token and no username/email specified. Will not comment any pull request.");
   return;
  }

  doLog(
    INFO,
    "Will comment PR " + config.getRepositoryOwner() + "/" + config.getRepositoryName() + "/"
      + config.getPullRequestId() + " on " + config.getGitHubUrl());

  List<Violation> allParsedViolations = newArrayList();
  for (ViolationConfig violationConfig : config.getViolationConfigs()) {
   List<Violation> parsedViolations = violationsReporterApi()//
     .findAll(violationConfig.getReporter())//
     .inFolder(workspace.getAbsolutePath())//
     .withPattern(violationConfig.getPattern())//
     .violations();
   allParsedViolations.addAll(parsedViolations);
  }

  try {
   violationCommentsToGitHubApi()//
     .withoAuth2Token(emptyToNull(config.getOAuth2Token()))//
     .withUsername(emptyToNull(config.getUsername()))//
     .withPassword(emptyToNull(config.getPassword()))//
     .withGitHubUrl(config.getGitHubUrl())//
     .withPullRequestId(pullRequestIdInt)//
     .withRepositoryName(config.getRepositoryName())//
     .withRepositoryOwner(config.getRepositoryOwner())//
     .withViolations(allParsedViolations)//
     .withCreateCommentWithAllSingleFileComments(config.getCreateCommentWithAllSingleFileComments())//
     .withCreateSingleFileComments(config.getCreateSingleFileComments())//
     .withCommentOnlyChangedContent(config.getCommentOnlyChangedContent())//
     .toPullRequest();
  } catch (Exception e) {
   doLog(SEVERE, "", e);
  }
 }

 /**
  * Makes sure any Jenkins variable, used in the configuration fields, are
  * evaluated.
  */
 private static ViolationsToGitHubConfig expand(ViolationsToGitHubConfig config, EnvVars environment) {
  ViolationsToGitHubConfig expanded = new ViolationsToGitHubConfig();
  expanded.setGitHubUrl(environment.expand(config.getGitHubUrl()));
  expanded.setUsername(environment.expand(config.getUsername()));
  expanded.setPassword(environment.expand(config.getPassword()));
  expanded.setoAuth2Token(environment.expand(config.getOAuth2Token()));
  expanded.setPullRequestId(environment.expand(config.getPullRequestId()));
  expanded.setRepositoryName(environment.expand(config.getRepositoryName()));
  expanded.setRepositoryOwner(environment.expand(config.getRepositoryOwner()));
  expanded.setCreateCommentWithAllSingleFileComments(config.getCreateCommentWithAllSingleFileComments());
  expanded.setCreateSingleFileComments(config.getCreateSingleFileComments());
  for (ViolationConfig violationConfig : config.getViolationConfigs()) {
   ViolationConfig p = new ViolationConfig();
   p.setPattern(environment.expand(violationConfig.getPattern()));
   p.setReporter(violationConfig.getReporter());
   expanded.getViolationConfigs().add(p);
  }
  return expanded;
 }

 private static void logConfiguration(ViolationsToGitHubConfig config, AbstractBuild<?, ?> build, BuildListener listener) {
  listener.getLogger().println(FIELD_GITHUBURL + ": " + config.getGitHubUrl());
  listener.getLogger().println(FIELD_REPOSITORYOWNER + ": " + config.getRepositoryOwner());
  listener.getLogger().println(FIELD_REPOSITORYNAME + ": " + config.getRepositoryName());
  listener.getLogger().println(FIELD_PULLREQUESTID + ": " + config.getPullRequestId());

  listener.getLogger().println(FIELD_USERNAME + ": " + config.getUsername().isEmpty());
  listener.getLogger().println(FIELD_PASSWORD + ": " + config.getPassword().isEmpty());
  listener.getLogger().println(FIELD_OAUTH2TOKEN + ": " + config.getOAuth2Token().isEmpty());

  listener.getLogger().println(FIELD_CREATESINGLEFILECOMMENTS + ": " + config.getCreateSingleFileComments());
  listener.getLogger().println(
    FIELD_CREATECOMMENTWITHALLSINGLEFILECOMMENTS + ": " + config.getCreateCommentWithAllSingleFileComments());
  listener.getLogger().println(FIELD_COMMENTONLYCHANGEDCONTENT + ": " + config.getCommentOnlyChangedContent());

  for (ViolationConfig violationConfig : config.getViolationConfigs()) {
   doLog(INFO, violationConfig.getReporter() + " with pattern " + violationConfig.getPattern());
  }
 }
}
