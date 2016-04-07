package org.jenkinsci.plugins.jvctg.config;

import org.jenkinsci.plugins.jvctg.ViolationsToGitHubConfiguration;

import static com.google.common.base.Strings.isNullOrEmpty;
import static com.google.common.collect.Lists.newArrayList;

import java.io.Serializable;
import java.util.List;

public class ViolationsToGitHubConfig implements Serializable {
 private static final long serialVersionUID = 4851568645021422528L;

 private List<ViolationConfig> violationConfigs = newArrayList();
 private boolean createSingleFileComments;
 private boolean createCommentWithAllSingleFileComments;
 private String repositoryName;
 private String repositoryOwner;
 private String password;
 private String username;
 private String oAuth2Token;
 private String pullRequestId;
 private String gitHubUrl;
 private boolean commentOnlyChangedContent;

 public ViolationsToGitHubConfig() {

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


 public ViolationsToGitHubConfig( boolean createSingleFileComments, boolean createCommentWithAllSingleFileComments, String repositoryName, String repositoryOwner, String password, String username, String oAuth2Token, String pullRequestId, String gitHubUrl, boolean commentOnlyChangedContent, List<ViolationConfig> violationConfigs) {
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

 public String getOAuth2Token() {
  return oAuth2Token;
 }

 public String getUsername() {
  return username;
 }

 public String getPassword() {
  return password;
 }

 public void setoAuth2Token(String oAuth2Token) {
  this.oAuth2Token = oAuth2Token;
 }

 public String getRepositoryOwner() {
  return repositoryOwner;
 }

 public String getRepositoryName() {
  return repositoryName;
 }

 public List<ViolationConfig> getViolationConfigs() {
  return violationConfigs;
 }

 public boolean getCreateCommentWithAllSingleFileComments() {
  return createCommentWithAllSingleFileComments;
 }

 public boolean getCreateSingleFileComments() {
  return createSingleFileComments;
 }

 public void setViolationConfigs(List<ViolationConfig> parsers) {
  this.violationConfigs = parsers;
 }

 public void setPullRequestId(String string) {
  this.pullRequestId = string;
 }

 public String getPullRequestId() {
  return pullRequestId;
 }

 public String getGitHubUrl() {
  return gitHubUrl;
 }

 public void setUsername(String username) {
  this.username = username;
 }

 public void setPassword(String password) {
  this.password = password;
 }

 public void setCreateCommentWithAllSingleFileComments(boolean createCommentWithAllSingleFileComments) {
  this.createCommentWithAllSingleFileComments = createCommentWithAllSingleFileComments;
 }

 public void setCreateSingleFileComments(boolean createSingleFileComments) {
  this.createSingleFileComments = createSingleFileComments;
 }

 public void setRepositoryName(String repositoryName) {
  this.repositoryName = repositoryName;
 }

 public void setRepositoryOwner(String repositoryOwner) {
  this.repositoryOwner = repositoryOwner;
 }

 public void setGitHubUrl(String gitHubUrl) {
  this.gitHubUrl = gitHubUrl;
 }

 public boolean getCommentOnlyChangedContent() {
  return commentOnlyChangedContent;
 }

 public void setCommentOnlyChangedContent(boolean commentOnlyChangedContent) {
  this.commentOnlyChangedContent = commentOnlyChangedContent;
 }

 public void applyDefaults(ViolationsToGitHubConfiguration defaults) {
  if( isNullOrEmpty(gitHubUrl) ) {
   gitHubUrl = defaults.getGitHubUrl();
  }
  if( isNullOrEmpty(username) ) {
   username = defaults.getUsername();
  }
  if( isNullOrEmpty(password) ) {
   password = defaults.getPassword();
  }
  if( isNullOrEmpty(repositoryOwner) ) {
   repositoryOwner = defaults.getRepositoryOwner();
  }
  if( isNullOrEmpty(oAuth2Token) ) {
   oAuth2Token = defaults.getoAuth2Token() ;
  }
 }
}
