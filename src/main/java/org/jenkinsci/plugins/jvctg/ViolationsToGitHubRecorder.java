package org.jenkinsci.plugins.jvctg;

import static hudson.tasks.BuildStepMonitor.NONE;
import static java.lang.Boolean.TRUE;
import static org.jenkinsci.plugins.jvctg.perform.JvctsPerformer.jvctsPerform;
import hudson.Extension;
import hudson.Launcher;
import hudson.model.BuildListener;
import hudson.model.AbstractBuild;
import hudson.tasks.BuildStepDescriptor;
import hudson.tasks.BuildStepMonitor;
import hudson.tasks.Publisher;
import hudson.tasks.Recorder;

import java.io.IOException;

import org.jenkinsci.plugins.jvctg.config.ViolationsToGitHubConfig;

public class ViolationsToGitHubRecorder extends Recorder {
 @Extension
 public static final BuildStepDescriptor<Publisher> DESCRIPTOR = new ViolationsToGitHubDescriptor();
 private ViolationsToGitHubConfig config;

 @Override
 public boolean perform(AbstractBuild<?, ?> build, Launcher launcher, BuildListener listener)
   throws InterruptedException, IOException {
  jvctsPerform(config, build, listener);
  return TRUE;
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
