package com.example.secureapi.network.data;


import com.example.secureapi.network.data.util.State;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Task {

    private String _id;
    private State state;
    private String expirationDate;
    private int priority;
    private String name;
    private String description;
    private String id;

    @Override
    public String toString() {
        return "Task{" +
                "_id='" + _id + '\'' +
                ", state=" + state +
                ", expirationDate='" + expirationDate + '\'' +
                ", priority=" + priority +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", id='" + id + '\'' +
                '}';
    }
	
}
