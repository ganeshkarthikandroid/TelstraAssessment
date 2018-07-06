package com.telstra.assessment.model;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;


public class GetData implements Serializable {

    @SerializedName("title")
    private String title;
    @SerializedName("description")
    private String description;
    @SerializedName("imageHref")
    private String imageHref;

    /**
     Empty constructor
     */
    public GetData() {
    }

    /*
     return title
     */
    public String getTitle() {
        return title;
    }

    /*
     return description
     */
    public String getDescription() {
        return description;
    }

    /*
     return image URL
     */
    public String getImageHref() {
        return imageHref;
    }
}
