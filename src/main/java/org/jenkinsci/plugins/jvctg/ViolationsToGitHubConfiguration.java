package org.jenkinsci.plugins.jvctg;

import hudson.Extension;
import jenkins.model.GlobalConfiguration;
import net.sf.json.JSONObject;
import org.kohsuke.stapler.DataBoundSetter;
import org.kohsuke.stapler.StaplerRequest;

/**
 * Created by magnayn on 07/04/2016.
 */
@Extension
public class ViolationsToGitHubConfiguration extends GlobalConfiguration {


    public String username;
    public String password;
    public String oAuth2Token;
    public String gitHubUrl;
    public String repositoryOwner;

    /**
     * Returns this singleton instance.
     *
     * @return the singleton.
     */
    public static ViolationsToGitHubConfiguration get() {
        return GlobalConfiguration.all().get(ViolationsToGitHubConfiguration.class);
    }

    public ViolationsToGitHubConfiguration() {
        load();
    }

    @Override
    public boolean configure(StaplerRequest req, JSONObject json) throws FormException {
        req.bindJSON(this, json);
        return true;
    }

    public String getUsername() {
        return username;
    }

    @DataBoundSetter
    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    @DataBoundSetter
    public void setPassword(String password) {
        this.password = password;
    }

    public String getoAuth2Token() {
        return oAuth2Token;
    }

    @DataBoundSetter
    public void setoAuth2Token(String oAuth2Token) {
        this.oAuth2Token = oAuth2Token;
    }

    public String getGitHubUrl() {
        return gitHubUrl;
    }

    @DataBoundSetter
    public void setGitHubUrl(String gitHubUrl) {
        this.gitHubUrl = gitHubUrl;
    }

    public String getRepositoryOwner() {
        return repositoryOwner;
    }

    @DataBoundSetter
    public void setRepositoryOwner(String repositoryOwner) {
        this.repositoryOwner = repositoryOwner;
    }
}
