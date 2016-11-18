package br.com.radio.entity;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by David on 10/08/2016.
 */

public class Person implements Parcelable {
    private String name;
    private String description;
    private int idImagem;
    private String facebookLink;
    private String linkedinLink;
    private String gitHubLink;


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Person(String name, String description, int idImagem) {
        this.name = name;
        this.description = description;
        this.idImagem = idImagem;
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

    public int getIdImagem() {
        return idImagem;
    }

    public void setIdImagem(int idImagem) {
        this.idImagem = idImagem;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.name);
        dest.writeString(this.description);
        dest.writeInt(this.idImagem);
        dest.writeString(this.facebookLink);
        dest.writeString(this.linkedinLink);
        dest.writeString(this.gitHubLink);
    }

    public Person() {
    }

    private Person(Parcel in) {
        this.name = in.readString();
        this.description = in.readString();
        this.idImagem = in.readInt();
        this.facebookLink = in.readString();
        this.linkedinLink = in.readString();
        this.gitHubLink = in.readString();
    }

    public static final Parcelable.Creator<Person> CREATOR = new Parcelable.Creator<Person>() {
        @Override
        public Person createFromParcel(Parcel source) {
            return new Person(source);
        }

        @Override
        public Person[] newArray(int size) {
            return new Person[size];
        }
    };
}


