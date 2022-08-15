package com.example.dts_vsga_aplikasi_login_sederhana;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class MainActivity extends AppCompatActivity {

    EditText etUsernameMain, etPasswordMain;
    Button btLoginMain, btRegisterMain;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        etUsernameMain = findViewById(R.id.etUsernameMain);
        etPasswordMain = findViewById(R.id.etPasswordMain);
        btLoginMain = findViewById(R.id.btLoginMain);
        btRegisterMain = findViewById(R.id.btRegisterMain);

        btRegisterMain.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this, RegisterActivity.class));
            }
        });

        btLoginMain.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (checkData(etUsernameMain.getText().toString().trim(), etPasswordMain.getText().toString().trim())) {
                    startActivity(new Intent(MainActivity.this, HomeActivity.class));
                }
            }
        });


    }

    private boolean checkData(String username, String password) {

        File file = getApplicationContext().getFileStreamPath("Data.txt");

        String lineFromFile;

        if (file.exists()) {
            try {
                BufferedReader reader = new BufferedReader(new InputStreamReader(openFileInput("Data.txt")));

                while ((lineFromFile = reader.readLine()) != null) {
                    StringTokenizer tokens = new StringTokenizer(lineFromFile, ",");

                    if (username.equals(tokens.nextToken()) && password.equals(tokens.nextToken())) {
                        return true;
                    }
                }
                reader.close();
                return false;
            } catch (IOException e) {
                Toast.makeText(this, e.getMessage(), Toast.LENGTH_SHORT).show();
                return false;
            }
        } else {
            Toast.makeText(this, "Data tidak ada", Toast.LENGTH_SHORT).show();
            return false;
        }
    }
}