package com.telstra.assessment.service;

import com.telstra.assessment.model.Response;

import retrofit2.Call;
import retrofit2.http.GET;


public interface ApiService {
    String BASE_URL = "https://dl.dropboxusercontent.com/";

    @GET("s/2iodh4vg0eortkl/facts.json")
    Call<Response> getData();
}