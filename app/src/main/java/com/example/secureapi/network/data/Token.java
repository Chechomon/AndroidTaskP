package com.example.secureapi.network.services.data;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Token{

    private String accessToken;
    private String userName;
    private String password;

}
