package org.jenkinsci.plugins.environmentdashboard.entity;

/**
 * This class represents a build object.
 * 
 * @author Robert Northard
 */
public class Build {
    private String id; // Build number
    private String url; // URL for build
    private String result; // result of the build
    private String environment; // environment build belongs to
    private String jobUrl; // Build job URL
    private String componantName; // Component build belongs to

    /**
     * Create an object of type Build.
     * 
     * @param id
     *            unique id of the build.
     * @param url
     *            the build url.
     * @param result
     *            the result of the build
     * @param environment
     *            the environment the build was deployed/built for.
     */
    public Build(String id, String url, String result, String environment, String componantName, String jobUrl) {
        this.id = id;
        this.setUrl(url);
        this.setResult(result);
        this.setEnvironment(environment);
        this.setComponantName(componantName);
        this.setJobUrl(jobUrl);
    }

    /**
     * @return the id
     */
    public String getId() {
        return id;
    }

    /**
     * @return the result
     */
    public String getResult() {
        return result;
    }

    /**
     * @param result
     *            the result to set
     */
    public void setResult(String result) {
        this.result = result;
    }

    /**
     * @return the url
     */
    public String getUrl() {
        return url;
    }

    /**
     * @param url
     *            the url to set
     */
    public void setUrl(String url) {
        this.url = url;
    }

    /**
     * @return the environment
     */
    public String getEnvironment() {
        return environment;
    }

    /**
     * @param environment
     *            the environment to set
     */
    public void setEnvironment(String environment) {
        this.environment = environment;
    }

    /**
     * @return the jobUrl
     */
    public String getJobUrl() {
        return jobUrl;
    }

    /**
     * @param jobUrl the jobUrl to set
     */
    public void setJobUrl(String jobUrl) {
        this.jobUrl = jobUrl;
    }

    /**
     * @return the componantName
     */
    public String getComponantName() {
        return componantName;
    }

    /**
     * @param componantName the componantName to set
     */
    public void setComponantName(String componantName) {
        this.componantName = componantName;
    }
}
