package org.jenkinsci.plugins.jvctg;

import hudson.Extension;
import hudson.util.ListBoxModel;

import java.io.Serializable;

import jenkins.model.GlobalConfiguration;
import net.sf.json.JSONObject;

import org.jenkinsci.plugins.jvctg.config.CredentialsHelper;
import org.kohsuke.stapler.DataBoundSetter;
import org.kohsuke.stapler.StaplerRequest;

/**
 * Created by magnayn on 07/04/2016.
 */
@Extension
public class ViolationsToGitHubConfiguration extends GlobalConfiguration implements Serializable {

 private static final long serialVersionUID = -2832851253933848205L;

 /**
  * Returns this singleton instance.
  *
  * @return the singleton.
  */
 public static ViolationsToGitHubConfiguration get() {
  return GlobalConfiguration.all().get(ViolationsToGitHubConfiguration.class);
 }

 public String gitHubUrl;
 public String oAuth2Token;
 public String password;
 public String repositoryOwner;
 public String username;
 private String oAuth2TokenCredentialsId;
 private String usernamePasswordCredentialsId;

 public ViolationsToGitHubConfiguration() {
  load();
 }

 @Override
 public boolean configure(StaplerRequest req, JSONObject json) throws FormException {
  req.bindJSON(this, json);
  save();
  return true;
 }

 public ListBoxModel doFillOAuth2TokenCredentialsIdItems() {
  return CredentialsHelper.doFillOAuth2TokenCredentialsIdItems();
 }

 public ListBoxModel doFillUsernamePasswordCredentialsIdItems() {
  return CredentialsHelper.doFillUsernamePasswordCredentialsIdItems();
 }

 public String getGitHubUrl() {
  return this.gitHubUrl;
 }

 public String getoAuth2Token() {
  return this.oAuth2Token;
 }

 public String getoAuth2TokenCredentialsId() {
  return this.oAuth2TokenCredentialsId;
 }

 public String getPassword() {
  return this.password;
 }

 public String getRepositoryOwner() {
  return this.repositoryOwner;
 }

 public String getUsername() {
  return this.username;
 }

 public String getUsernamePasswordCredentialsId() {
  return this.usernamePasswordCredentialsId;
 }

 @DataBoundSetter
 public void setGitHubUrl(String gitHubUrl) {
  this.gitHubUrl = gitHubUrl;
 }

 @DataBoundSetter
 public void setoAuth2Token(String oAuth2Token) {
  this.oAuth2Token = oAuth2Token;
 }

 @DataBoundSetter
 public void setoAuth2TokenCredentialsId(String oAuth2TokenCredentialsId) {
  this.oAuth2TokenCredentialsId = oAuth2TokenCredentialsId;
 }

 @DataBoundSetter
 public void setPassword(String password) {
  this.password = password;
 }

 @DataBoundSetter
 public void setRepositoryOwner(String repositoryOwner) {
  this.repositoryOwner = repositoryOwner;
 }

 @DataBoundSetter
 public void setUsername(String username) {
  this.username = username;
 }

 @DataBoundSetter
 public void setUsernamePasswordCredentialsId(String usernamePasswordCredentialsId) {
  this.usernamePasswordCredentialsId = usernamePasswordCredentialsId;
 }

 @Override
 public String toString() {
  return "ViolationsToGitHubConfiguration [gitHubUrl=" + this.gitHubUrl + ", oAuth2Token=" + this.oAuth2Token
    + ", password=" + this.password + ", repositoryOwner=" + this.repositoryOwner + ", username=" + this.username
    + ", usernamePasswordCredentialsId=" + this.usernamePasswordCredentialsId + ", oAuth2TokenCredentialsId="
    + this.oAuth2TokenCredentialsId + "]";
 }
}
