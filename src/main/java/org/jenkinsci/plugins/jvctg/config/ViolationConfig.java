package org.jenkinsci.plugins.jvctg.config;

import se.bjurr.violations.lib.reports.Reporter;

import java.io.Serializable;

public class ViolationConfig implements Serializable {
 private static final long serialVersionUID = 9009372864417543781L;

 private Reporter reporter;
 private String pattern;

 public ViolationConfig() {

 }

 public ViolationConfig(Reporter reporter, String pattern) {
  this.reporter = reporter;
  this.pattern = pattern;
 }

 public String getPattern() {
  return pattern;
 }

 public Reporter getReporter() {
  return reporter;
 }

 public void setPattern(String pattern) {
  this.pattern = pattern;
 }

 public void setReporter(Reporter reporter) {
  this.reporter = reporter;
 }
}
