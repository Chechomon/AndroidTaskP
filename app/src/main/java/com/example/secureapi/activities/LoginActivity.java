package com.example.secureapi.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import com.example.secureapi.R;
import com.example.secureapi.activities.MainActivity;

public class LoginActivity extends AppCompatActivity {

    private static final String userLogin = "CrkJohn";
    private static final String pswLogin = "12345";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
    }

    public void login(View view) {
        Intent intent = new Intent(this, MainActivity.class);
        EditText editUser = (EditText) findViewById(R.id.userText);
        EditText editPsw = (EditText) findViewById(R.id.userPassword);
        String userS = editUser.getText().toString();
        String pswS = editPsw.getText().toString();
        if(userS.equals(userLogin) && pswS.equals(pswLogin)){
            startActivity(intent);
        }
        if(!userS.equals(userLogin)){
            editUser.setError("Usuario incorrecto");
        }

        if(!pswS.equals(pswLogin)){
            editPsw.setError("Clave incorrecta");

        }
    }
}
