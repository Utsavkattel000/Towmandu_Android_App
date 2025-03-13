package com.java.towmandu;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;

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




        }
    };


}
