package org.jenkinsci.plugins.jvctg.config;

import static com.google.common.base.Strings.isNullOrEmpty;
import static com.google.common.collect.Lists.newArrayList;

import java.io.Serializable;
import java.util.List;

import org.jenkinsci.plugins.jvctg.ViolationsToGitHubConfiguration;
import org.kohsuke.stapler.DataBoundConstructor;

public class ViolationsToGitHubConfig implements Serializable {
 private static final long serialVersionUID = 4851568645021422528L;

 private boolean commentOnlyChangedContent;
 private boolean createCommentWithAllSingleFileComments;
 private boolean createSingleFileComments;
 private String gitHubUrl;
 private String oAuth2Token;
 private String oAuth2TokenCredentialsId;
 private String password;
 private String pullRequestId;
 private String repositoryName;
 private String repositoryOwner;
 private boolean useOAuth2Token;
 private boolean useOAuth2TokenCredentials;
 private String username;
 private String usernamePasswordCredentialsId;
 private boolean useUsernamePassword;
 private boolean useUsernamePasswordCredentials;
 private List<ViolationConfig> violationConfigs = newArrayList();

 public ViolationsToGitHubConfig() {

 }

 @DataBoundConstructor
 public ViolationsToGitHubConfig(boolean createSingleFileComments, boolean createCommentWithAllSingleFileComments,
   String repositoryName, String repositoryOwner, String password, String username, String oAuth2Token,
   String pullRequestId, String gitHubUrl, boolean commentOnlyChangedContent, List<ViolationConfig> violationConfigs,
   String usernamePasswordCredentialsId, boolean useOAuth2Token, boolean useUsernamePasswordCredentials,
   boolean useUsernamePassword, String oAuth2TokenCredentialsId, boolean useOAuth2TokenCredentialsIdCredentials) {
  List<ViolationConfig> allViolationConfigs = includeAllReporters(violationConfigs);

  this.violationConfigs = allViolationConfigs;
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
  this.usernamePasswordCredentialsId = usernamePasswordCredentialsId;
  this.useOAuth2Token = useOAuth2Token;
  this.useUsernamePasswordCredentials = useUsernamePasswordCredentials;
  this.useUsernamePassword = useUsernamePassword;
  this.oAuth2TokenCredentialsId = oAuth2TokenCredentialsId;
  this.useOAuth2TokenCredentials = useOAuth2TokenCredentialsIdCredentials;
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
  this.usernamePasswordCredentialsId = rhs.usernamePasswordCredentialsId;
  this.useOAuth2Token = rhs.useOAuth2Token;
  this.useUsernamePasswordCredentials = rhs.useUsernamePasswordCredentials;
  this.useUsernamePassword = rhs.useUsernamePassword;
  this.oAuth2TokenCredentialsId = rhs.oAuth2TokenCredentialsId;
  this.useOAuth2TokenCredentials = rhs.useOAuth2TokenCredentials;
 }

 public void applyDefaults(ViolationsToGitHubConfiguration defaults) {
  if (isNullOrEmpty(this.gitHubUrl)) {
   this.gitHubUrl = defaults.getGitHubUrl();
  }

  if (isNullOrEmpty(this.usernamePasswordCredentialsId)) {
   this.usernamePasswordCredentialsId = defaults.getUsernamePasswordCredentialsId();
  }

  if (isNullOrEmpty(this.oAuth2TokenCredentialsId)) {
   this.oAuth2TokenCredentialsId = defaults.getoAuth2TokenCredentialsId();
  }

  if (isNullOrEmpty(this.username)) {
   this.username = defaults.getUsername();
  }

  if (isNullOrEmpty(this.password)) {
   this.password = defaults.getPassword();
  }

  if (isNullOrEmpty(this.oAuth2Token)) {
   this.oAuth2Token = defaults.getoAuth2Token();
  }

  if (isNullOrEmpty(this.repositoryOwner)) {
   this.repositoryOwner = defaults.getRepositoryOwner();
  }
 }

 @Override
 public boolean equals(Object obj) {
  if (this == obj) {
   return true;
  }
  if (obj == null) {
   return false;
  }
  if (getClass() != obj.getClass()) {
   return false;
  }
  ViolationsToGitHubConfig other = (ViolationsToGitHubConfig) obj;
  if (this.commentOnlyChangedContent != other.commentOnlyChangedContent) {
   return false;
  }
  if (this.createCommentWithAllSingleFileComments != other.createCommentWithAllSingleFileComments) {
   return false;
  }
  if (this.createSingleFileComments != other.createSingleFileComments) {
   return false;
  }
  if (this.gitHubUrl == null) {
   if (other.gitHubUrl != null) {
    return false;
   }
  } else if (!this.gitHubUrl.equals(other.gitHubUrl)) {
   return false;
  }
  if (this.oAuth2Token == null) {
   if (other.oAuth2Token != null) {
    return false;
   }
  } else if (!this.oAuth2Token.equals(other.oAuth2Token)) {
   return false;
  }
  if (this.oAuth2TokenCredentialsId == null) {
   if (other.oAuth2TokenCredentialsId != null) {
    return false;
   }
  } else if (!this.oAuth2TokenCredentialsId.equals(other.oAuth2TokenCredentialsId)) {
   return false;
  }
  if (this.password == null) {
   if (other.password != null) {
    return false;
   }
  } else if (!this.password.equals(other.password)) {
   return false;
  }
  if (this.pullRequestId == null) {
   if (other.pullRequestId != null) {
    return false;
   }
  } else if (!this.pullRequestId.equals(other.pullRequestId)) {
   return false;
  }
  if (this.repositoryName == null) {
   if (other.repositoryName != null) {
    return false;
   }
  } else if (!this.repositoryName.equals(other.repositoryName)) {
   return false;
  }
  if (this.repositoryOwner == null) {
   if (other.repositoryOwner != null) {
    return false;
   }
  } else if (!this.repositoryOwner.equals(other.repositoryOwner)) {
   return false;
  }
  if (this.useOAuth2Token != other.useOAuth2Token) {
   return false;
  }
  if (this.useOAuth2TokenCredentials != other.useOAuth2TokenCredentials) {
   return false;
  }
  if (this.useUsernamePassword != other.useUsernamePassword) {
   return false;
  }
  if (this.useUsernamePasswordCredentials != other.useUsernamePasswordCredentials) {
   return false;
  }
  if (this.username == null) {
   if (other.username != null) {
    return false;
   }
  } else if (!this.username.equals(other.username)) {
   return false;
  }
  if (this.usernamePasswordCredentialsId == null) {
   if (other.usernamePasswordCredentialsId != null) {
    return false;
   }
  } else if (!this.usernamePasswordCredentialsId.equals(other.usernamePasswordCredentialsId)) {
   return false;
  }
  if (this.violationConfigs == null) {
   if (other.violationConfigs != null) {
    return false;
   }
  } else if (!this.violationConfigs.equals(other.violationConfigs)) {
   return false;
  }
  return true;
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

 public String getoAuth2Token() {
  return this.oAuth2Token;
 }

 public String getOAuth2Token() {
  return this.oAuth2Token;
 }

 public String getoAuth2TokenCredentialsId() {
  return this.oAuth2TokenCredentialsId;
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

 public String getUsernamePasswordCredentialsId() {
  return this.usernamePasswordCredentialsId;
 }

 public List<ViolationConfig> getViolationConfigs() {
  return this.violationConfigs;
 }

 @Override
 public int hashCode() {
  final int prime = 31;
  int result = 1;
  result = prime * result + (this.commentOnlyChangedContent ? 1231 : 1237);
  result = prime * result + (this.createCommentWithAllSingleFileComments ? 1231 : 1237);
  result = prime * result + (this.createSingleFileComments ? 1231 : 1237);
  result = prime * result + ((this.gitHubUrl == null) ? 0 : this.gitHubUrl.hashCode());
  result = prime * result + ((this.oAuth2Token == null) ? 0 : this.oAuth2Token.hashCode());
  result = prime * result + ((this.oAuth2TokenCredentialsId == null) ? 0 : this.oAuth2TokenCredentialsId.hashCode());
  result = prime * result + ((this.password == null) ? 0 : this.password.hashCode());
  result = prime * result + ((this.pullRequestId == null) ? 0 : this.pullRequestId.hashCode());
  result = prime * result + ((this.repositoryName == null) ? 0 : this.repositoryName.hashCode());
  result = prime * result + ((this.repositoryOwner == null) ? 0 : this.repositoryOwner.hashCode());
  result = prime * result + (this.useOAuth2Token ? 1231 : 1237);
  result = prime * result + (this.useOAuth2TokenCredentials ? 1231 : 1237);
  result = prime * result + (this.useUsernamePassword ? 1231 : 1237);
  result = prime * result + (this.useUsernamePasswordCredentials ? 1231 : 1237);
  result = prime * result + ((this.username == null) ? 0 : this.username.hashCode());
  result = prime * result
    + ((this.usernamePasswordCredentialsId == null) ? 0 : this.usernamePasswordCredentialsId.hashCode());
  result = prime * result + ((this.violationConfigs == null) ? 0 : this.violationConfigs.hashCode());
  return result;
 }

 public boolean isUseOAuth2Token() {
  return this.useOAuth2Token;
 }

 public boolean isUseOAuth2TokenCredentials() {
  return this.useOAuth2TokenCredentials;
 }

 public boolean isUseUsernamePassword() {
  return this.useUsernamePassword;
 }

 public boolean isUseUsernamePasswordCredentials() {
  return this.useUsernamePasswordCredentials;
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

 public void setoAuth2TokenCredentialsId(String oAuth2TokenCredentialsId) {
  this.oAuth2TokenCredentialsId = oAuth2TokenCredentialsId;
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

 public void setUseOAuth2Token(boolean useOAuth2Token) {
  this.useOAuth2Token = useOAuth2Token;
 }

 public void setUseOAuth2TokenCredentials(boolean useOAuth2TokenCredentials) {
  this.useOAuth2TokenCredentials = useOAuth2TokenCredentials;
 }

 public void setUsername(String username) {
  this.username = username;
 }

 public void setUsernamePasswordCredentialsId(String usernamePasswordCredentialsId) {
  this.usernamePasswordCredentialsId = usernamePasswordCredentialsId;
 }

 public void setUseUsernamePassword(boolean useUsernamePassword) {
  this.useUsernamePassword = useUsernamePassword;
 }

 public void setUseUsernamePasswordCredentials(boolean useUsernamePasswordCredentials) {
  this.useUsernamePasswordCredentials = useUsernamePasswordCredentials;
 }

 public void setViolationConfigs(List<ViolationConfig> parsers) {
  this.violationConfigs = parsers;
 }

 @Override
 public String toString() {
  return "ViolationsToGitHubConfig [commentOnlyChangedContent=" + this.commentOnlyChangedContent
    + ", createCommentWithAllSingleFileComments=" + this.createCommentWithAllSingleFileComments
    + ", createSingleFileComments=" + this.createSingleFileComments + ", gitHubUrl=" + this.gitHubUrl
    + ", oAuth2Token=" + this.oAuth2Token + ", oAuth2TokenCredentialsId=" + this.oAuth2TokenCredentialsId
    + ", password=" + this.password + ", pullRequestId=" + this.pullRequestId + ", repositoryName="
    + this.repositoryName + ", repositoryOwner=" + this.repositoryOwner + ", useOAuth2Token=" + this.useOAuth2Token
    + ", useOAuth2TokenCredentialsIdCredentials=" + this.useOAuth2TokenCredentials + ", username=" + this.username
    + ", usernamePasswordCredentialsId=" + this.usernamePasswordCredentialsId + ", useUsernamePassword="
    + this.useUsernamePassword + ", useUsernamePasswordCredentials=" + this.useUsernamePasswordCredentials
    + ", violationConfigs=" + this.violationConfigs + "]";
 }

 private List<ViolationConfig> includeAllReporters(List<ViolationConfig> violationConfigs) {
  List<ViolationConfig> allViolationConfigs = ViolationsToGitHubConfigHelper.getAllViolationConfigs();
  for (ViolationConfig candidate : allViolationConfigs) {
   for (ViolationConfig input : violationConfigs) {
    if (candidate.getReporter() == input.getReporter()) {
     candidate.setPattern(input.getPattern());
    }
   }
  }
  return allViolationConfigs;
 }

}
