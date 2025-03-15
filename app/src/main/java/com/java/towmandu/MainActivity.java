package com.java.towmandu;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;

import com.java.towmandu.api.ApiClient;
import com.java.towmandu.model.User;
import com.java.towmandu.service.ApiService;

import java.io.IOException;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    EditText emailObj;
    EditText passwordObj;
    AppCompatButton signInButtonObj;
    AppCompatButton signUpButtonObj;





    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        SharedPreferences prefs = getSharedPreferences("AppPrefs", MODE_PRIVATE);
        boolean isFirstRun = prefs.getBoolean("isFirstRun", true);

        if (isFirstRun) {
            Intent intent = new Intent(MainActivity.this, FirstTimeActivity.class);
            intent.putExtra("isFirstRun", true);
            startActivity(intent);
        } else  {
            if(getIntent().getBooleanExtra("done",false)){
                mainActivityLauncher();
            } else {
                Intent intent = new Intent(MainActivity.this, FirstTimeActivity.class);
                intent.putExtra("isFirstRun", false);
                startActivity(intent);
            }

        }
    }


    private void mainActivityLauncher() {
        setContentView(R.layout.activity_main);
        emailObj = findViewById(R.id.email);
        passwordObj = findViewById(R.id.password);
        signInButtonObj = findViewById(R.id.signIn);
        signUpButtonObj = findViewById(R.id.signUp);

        signInButtonObj.setOnClickListener(click);
        signUpButtonObj.setOnClickListener(click);
    }

    private final View.OnClickListener click = new View.OnClickListener(){

        @Override
        public void onClick(View view) {
          if(view.getId()==R.id.signUp){
               Intent intent = new Intent(MainActivity.this, SignupActivity.class);
               startActivity(intent);
              overridePendingTransition(R.anim.slide_in_up, R.anim.slide_out_up);

            }
          if(view.getId()==R.id.signIn){
              String email = emailObj.getText().toString();
              String password = passwordObj.getText().toString();
              if(verify(email,password)){
                  User user = new User(email,password);
                  ApiService apiService = ApiClient.getClient().create(ApiService.class);
                  Call<String> call = apiService.login(user);
                  call.enqueue(new Callback<String>() {
                      @Override
                      public void onResponse(@NonNull retrofit2.Call<String> call, @NonNull Response<String> response) {
                          if (response.isSuccessful() && "success".equals(response.body())) {
                              Intent intent = new Intent(MainActivity.this, HomeActivity.class);
                              startActivity(intent);
                          } else {
                              String errorMessage;
                              if (response.errorBody() != null) {
                                  try {
                                      errorMessage = response.errorBody().string(); // Get the error body as a string
                                  } catch (IOException e) {
                                      errorMessage = "Unable to parse error response";
                                  }
                              } else {
                                  errorMessage = "Unknown error";
                              }
                              Toast.makeText(MainActivity.this, "Error: " + errorMessage, Toast.LENGTH_SHORT).show();
                          }
                      }

                      @Override
                      public void onFailure(@NonNull retrofit2.Call<String> call, Throwable t) {
                          Toast.makeText(MainActivity.this, "Error: " + t.getMessage(), Toast.LENGTH_SHORT).show();
                      }
                  });

              }
          }




        }
    };

    public boolean verify(String email, String password){
        return email != null && password != null;
    }


}
