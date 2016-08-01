package org.jenkinsci.plugins.jvctg.config;

import static com.google.common.base.Strings.isNullOrEmpty;
import static com.google.common.collect.Lists.newArrayList;

import java.io.Serializable;
import java.util.List;

import org.jenkinsci.plugins.jvctg.ViolationsToGitHubConfiguration;

public class ViolationsToGitHubConfig implements Serializable {
 private static final long serialVersionUID = 4851568645021422528L;

 private boolean commentOnlyChangedContent;
 private boolean createCommentWithAllSingleFileComments;
 private boolean createSingleFileComments;
 private String gitHubUrl;
 private String oAuth2Token;
 private String password;
 private String pullRequestId;
 private String repositoryName;
 private String repositoryOwner;
 private String username;
 private List<ViolationConfig> violationConfigs = newArrayList();

 public ViolationsToGitHubConfig() {

 }

 public ViolationsToGitHubConfig(boolean createSingleFileComments, boolean createCommentWithAllSingleFileComments,
   String repositoryName, String repositoryOwner, String password, String username, String oAuth2Token,
   String pullRequestId, String gitHubUrl, boolean commentOnlyChangedContent, List<ViolationConfig> violationConfigs) {
  this.violationConfigs = violationConfigs;
  this.createSingleFileComments = createSingleFileComments;
  this.createCommentWithAllSingleFileComments = createCommentWithAllSingleFileComments;
  this.repositoryName = repositoryName;
  this.repositoryOwner = repositoryOwner;
  this.password = password;
  this.username = username;
  this.oAuth2Token = oAuth2Token;
  this.pullRequestId = pullRequestId;
  this.gitHubUrl = gitHubUrl;
  this.commentOnlyChangedContent = commentOnlyChangedContent;
 }

 public ViolationsToGitHubConfig(ViolationsToGitHubConfig rhs) {
  this.violationConfigs = rhs.violationConfigs;
  this.createSingleFileComments = rhs.createSingleFileComments;
  this.createCommentWithAllSingleFileComments = rhs.createCommentWithAllSingleFileComments;
  this.repositoryName = rhs.repositoryName;
  this.repositoryOwner = rhs.repositoryOwner;
  this.password = rhs.password;
  this.username = rhs.username;
  this.oAuth2Token = rhs.oAuth2Token;
  this.pullRequestId = rhs.pullRequestId;
  this.gitHubUrl = rhs.gitHubUrl;
  this.commentOnlyChangedContent = rhs.commentOnlyChangedContent;
 }

 public void applyDefaults(ViolationsToGitHubConfiguration defaults) {
  if (isNullOrEmpty(this.gitHubUrl)) {
   String rhs = defaults.getGitHubUrl();
   if (isNullOrEmpty(rhs)) {
    rhs = "https://api.github.com/";
   }
   this.gitHubUrl = rhs;
  }
  if (isNullOrEmpty(this.username)) {
   this.username = defaults.getUsername();
  }
  if (isNullOrEmpty(this.password)) {
   this.password = defaults.getPassword();
  }
  if (isNullOrEmpty(this.repositoryOwner)) {
   this.repositoryOwner = defaults.getRepositoryOwner();
  }
  if (isNullOrEmpty(this.oAuth2Token)) {
   this.oAuth2Token = defaults.getoAuth2Token();
  }
 }

 public boolean getCommentOnlyChangedContent() {
  return this.commentOnlyChangedContent;
 }

 public boolean getCreateCommentWithAllSingleFileComments() {
  return this.createCommentWithAllSingleFileComments;
 }

 public boolean getCreateSingleFileComments() {
  return this.createSingleFileComments;
 }

 public String getGitHubUrl() {
  return this.gitHubUrl;
 }

 public String getOAuth2Token() {
  return this.oAuth2Token;
 }

 public String getPassword() {
  return this.password;
 }

 public String getPullRequestId() {
  return this.pullRequestId;
 }

 public String getRepositoryName() {
  return this.repositoryName;
 }

 public String getRepositoryOwner() {
  return this.repositoryOwner;
 }

 public String getUsername() {
  return this.username;
 }

 public List<ViolationConfig> getViolationConfigs() {
  return this.violationConfigs;
 }

 public void setCommentOnlyChangedContent(boolean commentOnlyChangedContent) {
  this.commentOnlyChangedContent = commentOnlyChangedContent;
 }

 public void setCreateCommentWithAllSingleFileComments(boolean createCommentWithAllSingleFileComments) {
  this.createCommentWithAllSingleFileComments = createCommentWithAllSingleFileComments;
 }

 public void setCreateSingleFileComments(boolean createSingleFileComments) {
  this.createSingleFileComments = createSingleFileComments;
 }

 public void setGitHubUrl(String gitHubUrl) {
  this.gitHubUrl = gitHubUrl;
 }

 public void setoAuth2Token(String oAuth2Token) {
  this.oAuth2Token = oAuth2Token;
 }

 public void setPassword(String password) {
  this.password = password;
 }

 public void setPullRequestId(String string) {
  this.pullRequestId = string;
 }

 public void setRepositoryName(String repositoryName) {
  this.repositoryName = repositoryName;
 }

 public void setRepositoryOwner(String repositoryOwner) {
  this.repositoryOwner = repositoryOwner;
 }

 public void setUsername(String username) {
  this.username = username;
 }

 public void setViolationConfigs(List<ViolationConfig> parsers) {
  this.violationConfigs = parsers;
 }
}
