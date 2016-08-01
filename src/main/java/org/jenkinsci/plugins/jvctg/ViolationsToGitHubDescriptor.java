package org.jenkinsci.plugins.jvctg;

import static com.google.common.base.Strings.isNullOrEmpty;
import static org.jenkinsci.plugins.jvctg.config.ViolationsToGitHubConfigHelper.FIELD_COMMENTONLYCHANGEDCONTENT;
import static org.jenkinsci.plugins.jvctg.config.ViolationsToGitHubConfigHelper.FIELD_CREATECOMMENTWITHALLSINGLEFILECOMMENTS;
import static org.jenkinsci.plugins.jvctg.config.ViolationsToGitHubConfigHelper.FIELD_CREATESINGLEFILECOMMENTS;
import static org.jenkinsci.plugins.jvctg.config.ViolationsToGitHubConfigHelper.FIELD_GITHUBURL;
import static org.jenkinsci.plugins.jvctg.config.ViolationsToGitHubConfigHelper.FIELD_OAUTH2TOKEN;
import static org.jenkinsci.plugins.jvctg.config.ViolationsToGitHubConfigHelper.FIELD_PASSWORD;
import static org.jenkinsci.plugins.jvctg.config.ViolationsToGitHubConfigHelper.FIELD_PATTERN;
import static org.jenkinsci.plugins.jvctg.config.ViolationsToGitHubConfigHelper.FIELD_PULLREQUESTID;
import static org.jenkinsci.plugins.jvctg.config.ViolationsToGitHubConfigHelper.FIELD_REPOSITORYNAME;
import static org.jenkinsci.plugins.jvctg.config.ViolationsToGitHubConfigHelper.FIELD_REPOSITORYOWNER;
import static org.jenkinsci.plugins.jvctg.config.ViolationsToGitHubConfigHelper.FIELD_USERNAME;
import static org.jenkinsci.plugins.jvctg.config.ViolationsToGitHubConfigHelper.createNewConfig;
import hudson.model.AbstractProject;
import hudson.tasks.BuildStepDescriptor;
import hudson.tasks.Publisher;

import java.util.List;

import net.sf.json.JSONObject;

import org.jenkinsci.plugins.jvctg.config.ViolationsToGitHubConfig;
import org.kohsuke.stapler.StaplerRequest;

public final class ViolationsToGitHubDescriptor extends BuildStepDescriptor<Publisher> {
 private ViolationsToGitHubConfig config;

 public ViolationsToGitHubDescriptor() {
  super(ViolationsToGitHubRecorder.class);
  load();
  if (this.config == null || this.config.getViolationConfigs().size() != createNewConfig().getViolationConfigs().size()) {
   this.config = createNewConfig();
  }
 }

 @Override
 public String getDisplayName() {
  return "Report Violations to GitHub";
 }

 @Override
 public String getHelpFile() {
  return super.getHelpFile();
 }

 /**
  * Create new blank configuration. Used when job is created.
  */
 public ViolationsToGitHubConfig getNewConfig() {
  return createNewConfig();
 }

 @Override
 public boolean isApplicable(@SuppressWarnings("rawtypes") final Class<? extends AbstractProject> jobType) {
  return true;
 }

 @SuppressWarnings("unchecked")
 @Override
 public Publisher newInstance(StaplerRequest req, JSONObject formData) throws hudson.model.Descriptor.FormException {
  ViolationsToGitHubConfig config = createNewConfig();
  if (isNullOrEmpty(formData.getString(FIELD_GITHUBURL))) {
   config.setGitHubUrl("https://api.github.com/");
  } else {
   config.setGitHubUrl(formData.getString(FIELD_GITHUBURL));
  }
  config.setRepositoryOwner(formData.getString(FIELD_REPOSITORYOWNER));
  config.setRepositoryName(formData.getString(FIELD_REPOSITORYNAME));
  config.setPullRequestId(formData.getString(FIELD_PULLREQUESTID));
  config.setUsername(formData.getString(FIELD_USERNAME));
  config.setPassword(formData.getString(FIELD_PASSWORD));
  config.setoAuth2Token(formData.getString(FIELD_OAUTH2TOKEN));
  config.setCreateCommentWithAllSingleFileComments(formData.getString(FIELD_CREATECOMMENTWITHALLSINGLEFILECOMMENTS)
    .equalsIgnoreCase("true"));
  config.setCreateSingleFileComments(formData.getString(FIELD_CREATESINGLEFILECOMMENTS).equalsIgnoreCase("true"));
  config.setCommentOnlyChangedContent(formData.getString(FIELD_COMMENTONLYCHANGEDCONTENT).equalsIgnoreCase("true"));
  int i = 0;
  for (String pattern : (List<String>) formData.get(FIELD_PATTERN)) {
   config.getViolationConfigs().get(i++).setPattern(pattern);
  }
  ViolationsToGitHubRecorder publisher = new ViolationsToGitHubRecorder();
  publisher.setConfig(config);
  return publisher;
 }
}
