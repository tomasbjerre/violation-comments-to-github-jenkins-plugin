# Violation Comments to GitHub Jenkins Plugin

Travis CI: [![Build Status](https://travis-ci.org/tomasbjerre/violation-comments-to-github-jenkins-plugin.svg?branch=master)](https://travis-ci.org/tomasbjerre/violation-comments-to-github-jenkins-plugin)

CloudBees: [![Build Status](https://jenkins.ci.cloudbees.com/job/plugins/job/violation-comments-to-github-plugin/badge/icon)](https://jenkins.ci.cloudbees.com/job/plugins/job/violation-comments-to-github-plugin/)

This is a Jenkins plugin for [Violation Comments to GitHub Lib](https://github.com/tomasbjerre/violation-comments-to-github-lib). This plugin will find report files from static code analysis and comment GitHub pull requests with the content.

You can have a look at [violations-test](https://github.com/tomasbjerre/violations-test/pull/2) to see what the result may look like.

It supports:
 * [_Checkstyle_](http://checkstyle.sourceforge.net/)
 * [_CPPLint_](https://github.com/theandrewdavis/cpplint)
 * [_CPPCheck_](http://cppcheck.sourceforge.net/)
 * [_CSSLint_](https://github.com/CSSLint/csslint)
 * [_Findbugs_](http://findbugs.sourceforge.net/)
 * [_Flake8_](http://flake8.readthedocs.org/en/latest/) ([_PyLint_](https://www.pylint.org/), [_Pep8_](https://github.com/PyCQA/pycodestyle), [_Mccabe_](https://pypi.python.org/pypi/mccabe), [_PyFlakes_](https://pypi.python.org/pypi/pyflakes))
 * [_JSHint_](http://jshint.com/)
 * _Lint_ A common XML format, used by different linters.
 * [_PerlCritic_](https://github.com/Perl-Critic)
 * [_PMD_](https://pmd.github.io/)
 * [_ReSharper_](https://www.jetbrains.com/resharper/)
 * [_XMLLint_](http://xmlsoft.org/xmllint.html)

There is also:
 * A [Gradle plugin](https://github.com/tomasbjerre/violation-comments-to-github-gradle-plugin).
 * A [Maven plugin](https://github.com/tomasbjerre/violation-comments-to-github-maven-plugin).

Available in Jenkins [here](https://wiki.jenkins-ci.org/display/JENKINS/Violation+Comments+to+GitHub+Plugin).

You will need to the **pull request id** for the pull request that was built. You may want to have a look at [GitHub Pull Request Builder Plugin](https://wiki.jenkins-ci.org/display/JENKINS/GitHub+pull+request+builder+plugin), it provides the environment variable `ghprbPullId`.

# Screenshots

When installed, a post build action will be available.

![Post build action menu](https://github.com/tomasbjerre/violation-comments-to-github-jenkins-plugin/blob/master/sandbox/jenkins-postbuildmenu.png)

The configuration looks like this.

![Post build action](https://github.com/tomasbjerre/violation-comments-to-github-jenkins-plugin/blob/master/sandbox/jenkins-postbuildaction.png)

The pull request will be commented like this.

![Post build action](https://github.com/tomasbjerre/violation-comments-to-github-jenkins-plugin/blob/master/sandbox/findbugs-github-pr-file-comment.png)


# Plugin development
More details on Jenkins plugin development is available [here](https://wiki.jenkins-ci.org/display/JENKINS/Plugin+tutorial).

There is a ```/build.sh``` that will perform a full build and test the plugin. You may have a look at sandbox/settings.xml on how to configure your Maven settings.

A release is created like this. You need to clone from jenkinsci-repo, with https and have username/password in settings.xml.
```
mvn release:prepare release:perform
```
