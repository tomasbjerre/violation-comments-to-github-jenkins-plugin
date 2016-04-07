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

import jenkins.tasks.SimpleBuildStep;
import org.jenkinsci.plugins.jvctg.config.ViolationsToGitHubConfig;

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

  jvctsPerform(combinedConfig, build, listener);
 }

 @Override
 public BuildStepDescriptor<Publisher> getDescriptor() {
  return DESCRIPTOR;
 }

 public ViolationsToGitHubRecorder() {
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
