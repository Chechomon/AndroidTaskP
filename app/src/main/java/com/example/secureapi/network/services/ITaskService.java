package com.example.secureapi.network.services;

import com.example.secureapi.network.data.Task;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface ITaskService {

    @GET("v1/task")
    Call<List<Task>> getAllTask();

}
