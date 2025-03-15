package com.java.towmandu;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.java.towmandu.api.ApiClient;
import com.java.towmandu.model.User;
import com.java.towmandu.service.ApiService;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SignupActivity extends AppCompatActivity {
    EditText emailObj;
    EditText passwordObj;

    EditText fullNameObj;

    EditText phoneObj;
    AppCompatButton signInButtonObj;
    AppCompatButton signUpButtonObj;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_signup);
        emailObj = findViewById(R.id.email);
        passwordObj = findViewById(R.id.password);
        signInButtonObj = findViewById(R.id.signIn);
        signUpButtonObj = findViewById(R.id.signUp);
        fullNameObj = findViewById(R.id.fullName);
        phoneObj = findViewById(R.id.phone);

        signInButtonObj.setOnClickListener(click);
        signUpButtonObj.setOnClickListener(click);

    }

    private final View.OnClickListener click = new View.OnClickListener() {

        @Override
        public void onClick(View view) {
            if (view.getId() == R.id.signIn) {
                Intent intent = new Intent(SignupActivity.this, MainActivity.class);
                intent.putExtra("done", true);
                startActivity(intent);
                overridePendingTransition(R.anim.slide_in_up, R.anim.slide_out_up);
            }
            if (view.getId() == R.id.signUp) {
                if (validateFields()) {
                    signUp();
                }
            }
        }
    };

    void signUp() {
        String email = emailObj.getText().toString();
        String password = passwordObj.getText().toString();
        String phone = phoneObj.getText().toString();
        String fullName = fullNameObj.getText().toString();
        User user = new User(fullName, email, password, phone);
        ApiService apiService = ApiClient.getClient().create(ApiService.class);
        Call<String> call = apiService.signup(user);
        call.enqueue(new Callback<String>() {
            @Override
            public void onResponse(@NonNull Call<String> call, @NonNull Response<String> response) {
                if (response.isSuccessful() && "success".equals(response.body())) {
                    Toast.makeText(SignupActivity.this, "Signup successful!", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(SignupActivity.this, "", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<String> call, Throwable t) {
                Toast.makeText(SignupActivity.this, "Error: " + t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });


    }

    private boolean validateFields() {
        boolean isValid = true;

        // Check Full Name
        if (fullNameObj.getText().toString().trim().isEmpty()) {
            fullNameObj.setError("Full Name is required");
            isValid = false;
        }

        // Check Phone
        if (phoneObj.getText().toString().trim().isEmpty()) {
            phoneObj.setError("Phone Number is required");
            isValid = false;
        }

        // Check Email
        if (emailObj.getText().toString().trim().isEmpty()) {
            emailObj.setError("Email is required");
            isValid = false;
        } else if (!android.util.Patterns.EMAIL_ADDRESS.matcher(emailObj.getText().toString().trim()).matches()) {
            emailObj.setError("Enter a valid email address");
            isValid = false;
        }

        // Check Password
        if (passwordObj.getText().toString().trim().isEmpty()) {
            passwordObj.setError("Password is required");
            isValid = false;
        } else if (passwordObj.getText().toString().length() < 6) {
            passwordObj.setError("Password must be at least 6 characters");
            isValid = false;
        }

        return isValid;
    }
}