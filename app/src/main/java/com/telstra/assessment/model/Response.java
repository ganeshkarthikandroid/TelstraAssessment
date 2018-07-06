package com.telstra.assessment.model;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Response {

    @SerializedName("title")
    private String title;
    @SerializedName("rows")
    private List<GetData> dataList;

    /*
     Empty constructor
     */
    public Response() {
    }

    /*
     return Main title
     */
    public String getTitle() {
        return title;
    }

    /*
     return list of data
     */
    public List<GetData> getDataList() {
        return dataList;
    }
}
