package com.example.secureapi.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;

import com.example.secureapi.network.RetrofitNetwork;
import com.example.secureapi.network.data.*;
import com.example.secureapi.R;
import com.example.secureapi.activities.MainActivity;
import com.example.secureapi.network.services.IAuthService;
import com.example.secureapi.network.services.ITaskService;

import java.io.IOException;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class LoginActivity extends AppCompatActivity {

    private static final String userLogin = "CrkJohn";
    private static final String pswLogin = "12345";
    private final ExecutorService executorService = Executors.newFixedThreadPool(1);
    private  IAuthService authService;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://backieti.herokuapp.com/") //localhost for emulator
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        authService = retrofit.create(IAuthService.class);
    }

    public void login(View view) {
        final Intent intent = new Intent(this, MainActivity.class);
        EditText editUser = (EditText) findViewById(R.id.userText);
        EditText editPsw = (EditText) findViewById(R.id.userPassword);
        final String userS = editUser.getText().toString();
        final String pswS = editPsw.getText().toString();
        if(userS.equals(userLogin) && pswS.equals(pswLogin)){
            executorService.execute(new Runnable() {
                @Override
                public void run() {
                    try {
                        Response<Token> response =
                                authService.login(new LoginWrapper(userS,pswS)).execute();
                        final Token token = response.body();

                        /*
                            Testing if this's works
                            RetrofitNetwork retrofit1 = new RetrofitNetwork(token.getAccessToken());
                            ITaskService taskService =  retrofit1.getTaskService();
                            final Response<List<Task>> response1 =  taskService.getAllTask().execute();
                        */
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                //Log.e("TaskList " , response1.body().toString());
                                System.out.println(token.toString());
                                saveToken(token);

                            }
                        });

                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            });
            startActivity(intent);
        }
        if(!userS.equals(userLogin)){
            editUser.setError("Usuario incorrecto");
        }

        if(!pswS.equals(pswLogin)){
            editPsw.setError("Clave incorrecta");

        }
    }

    private void saveToken(Token token) {
        SharedPreferences sharedPref =
                getSharedPreferences(getString(R.string.preference_file_key), Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPref.edit();
        editor.remove("TOKEN_KEY");
        editor.putString("TOKEN_KEY", "Bearer "+token.getAccessToken());
        editor.apply();
    }
}
