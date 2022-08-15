package com.example.dts_vsga_aplikasi_login_sederhana;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class RegisterActivity extends AppCompatActivity {

    EditText etUsername, etPassword;
    Button btRegister, btBack;
    TextView tvHasil;

    ArrayList<Account> accounts;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        btRegister = findViewById(R.id.btRegister);
        etUsername = findViewById(R.id.etUsername);
        etPassword = findViewById(R.id.etPassword);
        tvHasil = findViewById(R.id.tvHasil);
        btBack = findViewById(R.id.btBack);

        accounts = new ArrayList<Account>();

        loadData();

        btRegister.setOnClickListener(view -> {
            if (etUsername.getText().toString().isEmpty() || etPassword.getText().toString().isEmpty()) {
                Toast.makeText(RegisterActivity.this, "Masukkan Nama Anda", Toast.LENGTH_SHORT).show();
            } else {
                Account account = new Account(etUsername.getText().toString().trim(), etPassword.getText().toString().trim());
                accounts.add(account);
            }

            try {
                FileOutputStream file = openFileOutput("Data.txt",MODE_PRIVATE);
                OutputStreamWriter outputFile = new OutputStreamWriter(file);

                for (int i = 0; i < accounts.size(); i++) {
                    outputFile.write(accounts.get(i).getUsername() + "," + accounts.get(i).getPaswword() + "\n");
                }

                outputFile.flush();
                outputFile.close();

                Toast.makeText(RegisterActivity.this, "Berhasil menyimpan", Toast.LENGTH_SHORT).show();
            } catch (IOException e) {
                Toast.makeText(RegisterActivity.this, e.getMessage(), Toast.LENGTH_SHORT).show();
            }

            loadData();


//            startActivity(new Intent(RegisterActivity.this, MainActivity.class));
        });

        btBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(RegisterActivity.this, MainActivity.class));
            }
        });




    }
    private void loadData() {
        accounts.clear();

        File file = getApplicationContext().getFileStreamPath("Data.txt");

        String lineFromFile;

        if (file.exists()) {
            try {
                BufferedReader reader = new BufferedReader(new InputStreamReader(openFileInput("Data.txt")));

                while ((lineFromFile = reader.readLine()) != null) {
                    StringTokenizer tokens = new StringTokenizer(lineFromFile, ",");

                    Account person = new Account(tokens.nextToken(), tokens.nextToken());
                    accounts.add(person);
                }
                reader.close();

                setTextToTextView();
            } catch (IOException e) {
                Toast.makeText(this, e.getMessage(), Toast.LENGTH_SHORT).show();
            }
        } else {
            Toast.makeText(this, "Data tidak ada", Toast.LENGTH_SHORT).show();
        }
    }

    private void setTextToTextView() {
        String text = "";
        for (int i = 0; i < accounts.size(); i++) {
            text = text + accounts.get(i).getUsername() + " " + accounts.get(i).getPaswword() + "\n";
        }

        tvHasil.setText(text);
    }
}