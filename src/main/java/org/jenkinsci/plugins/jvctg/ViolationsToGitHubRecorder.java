package org.jenkinsci.plugins.jvctg;

import static com.google.common.base.Strings.isNullOrEmpty;
import static hudson.tasks.BuildStepMonitor.NONE;
import static java.lang.Boolean.TRUE;
import static org.jenkinsci.plugins.jvctg.perform.JvctsPerformer.jvctsPerform;
import hudson.Extension;
import hudson.FilePath;
import hudson.Launcher;
import hudson.model.BuildListener;
import hudson.model.AbstractBuild;
import hudson.model.Run;
import hudson.model.TaskListener;
import hudson.tasks.BuildStepDescriptor;
import hudson.tasks.BuildStepMonitor;
import hudson.tasks.Publisher;
import hudson.tasks.Recorder;

import java.io.IOException;
import java.util.List;

import jenkins.tasks.SimpleBuildStep;
import org.jenkinsci.plugins.jvctg.config.ViolationConfig;
import org.jenkinsci.plugins.jvctg.config.ViolationsToGitHubConfig;
import org.kohsuke.stapler.DataBoundConstructor;

import javax.annotation.Nonnull;

public class ViolationsToGitHubRecorder extends Recorder implements SimpleBuildStep {
 @Extension
 public static final BuildStepDescriptor<Publisher> DESCRIPTOR = new ViolationsToGitHubDescriptor();
 private ViolationsToGitHubConfig config;

 @Override
 public void perform(@Nonnull Run<?, ?> build, @Nonnull FilePath filePath, @Nonnull Launcher launcher, @Nonnull TaskListener listener)
   throws InterruptedException, IOException {

  ViolationsToGitHubConfig combinedConfig = new ViolationsToGitHubConfig(config);
  ViolationsToGitHubConfiguration defaults = ViolationsToGitHubConfiguration.get();

  combinedConfig.applyDefaults(defaults);

  jvctsPerform(combinedConfig, filePath, build, listener);
 }

 @Override
 public BuildStepDescriptor<Publisher> getDescriptor() {
  return DESCRIPTOR;
 }


 public ViolationsToGitHubRecorder() {
 }

 @DataBoundConstructor
  public ViolationsToGitHubRecorder( boolean createSingleFileComments, boolean createCommentWithAllSingleFileComments, String repositoryName, String repositoryOwner, String password, String username, String oAuth2Token, String pullRequestId, String gitHubUrl, boolean commentOnlyChangedContent, List<
    ViolationConfig > violationConfigs) {

   config = new ViolationsToGitHubConfig(
    createSingleFileComments ,
    createCommentWithAllSingleFileComments ,
    repositoryName ,
    repositoryOwner ,
    password ,
    username ,
    oAuth2Token ,
    pullRequestId ,
    gitHubUrl ,
    commentOnlyChangedContent, violationConfigs);
  }



 @Override
 public BuildStepMonitor getRequiredMonitorService() {
  return NONE;
 }

 public void setConfig(ViolationsToGitHubConfig config) {
  this.config = config;
 }

 public ViolationsToGitHubConfig getConfig() {
  return config;
 }

}
