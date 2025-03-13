package com.java.towmandu;

import android.content.Intent;
import android.os.Bundle;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;

import java.util.concurrent.Executors;

public class FirstTimeActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.splash_screen);

        // Find views
        ImageView splashImage = findViewById(R.id.splash_screen_image);
        TextView splashText = findViewById(R.id.splash_screen_text);

        // Create fade-in animations
        Animation fadeIn = new AlphaAnimation(0.0f, 1.0f); // Start invisible (0) and fade to visible (1)
        fadeIn.setDuration(2000); // Duration in milliseconds

        // Apply animations
        splashImage.startAnimation(fadeIn);
        splashText.startAnimation(fadeIn);

        // Start the next activity after the animation
        Executors.newSingleThreadExecutor().execute(() -> {
            try {
                Thread.sleep(3000); // Wait for 3 seconds before starting the next activity
            } catch (InterruptedException ignored){

            }

            runOnUiThread(() -> {
                // If it's the first run, transition to SplashOneActivity
                if (getIntent().getBooleanExtra("isFirstRun", true)) {
                    Intent intent = new Intent(FirstTimeActivity.this, SplashOneActivity.class);
                    startActivity(intent);
                    overridePendingTransition(R.anim.slide_in_up, R.anim.slide_out_up);
                    finish();
                }else {
                    Intent intent = new Intent(FirstTimeActivity.this, MainActivity.class);
                    intent.putExtra("done", true);
                    startActivity(intent);
                    overridePendingTransition(R.anim.slide_in_up, R.anim.slide_out_up);
                    finish();
                }
            });
        });
    }

}
