package br.com.radio.entity;

/**
 * Created by David on 10/08/2016.
 */

public class Person {
    private String name;
    private String description;
    private String urlImage;
    private String facebookLink;
    private String linkedinLink;
    private String gitHubLink;


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUrlImage() {
        return urlImage;
    }

    public void setUrlImage(String urlImage) {
        this.urlImage = urlImage;
    }

    public String getFacebookLink() {
        return facebookLink;
    }

    public void setFacebookLink(String facebookLink) {
        this.facebookLink = facebookLink;
    }

    public String getLinkedinLink() {
        return linkedinLink;
    }

    public void setLinkedinLink(String linkedinLink) {
        this.linkedinLink = linkedinLink;
    }

    public String getGitHubLink() {
        return gitHubLink;
    }

    public void setGitHubLink(String gitHubLink) {
        this.gitHubLink = gitHubLink;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}


