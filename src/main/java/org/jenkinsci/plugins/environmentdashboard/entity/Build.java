package org.jenkinsci.plugins.environmentdashboard.entity;

/**
 * This class represents a build object.
 * 
 * @author Robert Northard
 */
public class Build {
    private String id;
    private String url;
    private String result;
    private String environment;

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
    public Build(String id, String url, String result, String environment) {
        this.id = id;
        this.setUrl(url);
        this.setResult(result);
        this.setEnvironment(environment);
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
}
