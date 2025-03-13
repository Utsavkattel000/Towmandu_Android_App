package com.java.towmandu;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;


public class SplashOneActivity extends AppCompatActivity {
    ImageButton nextButtonObj;
    Button skipButtonObj;
    ImageButton splashBackObj;
    TextView lastTextObj;
    ImageView splashTrackObj;
    ImageView splashIconObj;
    RelativeLayout relativeLayout;

    TextView splashTitleObj;
    TextView splashSubTitleObj;

    int pageCount=1;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_splash_one);

        nextButtonObj = findViewById(R.id.nextsplash);
        skipButtonObj = findViewById(R.id.skipSplash);
        lastTextObj = findViewById(R.id.lastText);
        splashTrackObj = findViewById(R.id.splashtrack);
        splashIconObj = findViewById(R.id.splashIcon);
        splashBackObj= findViewById(R.id.splashBack);
        splashTitleObj= findViewById(R.id.splashTitle);
        splashSubTitleObj=findViewById(R.id.splashSubtitle);
        relativeLayout = findViewById(R.id.activitysplashone);

        nextButtonObj.setOnClickListener(click);
        skipButtonObj.setOnClickListener(click);
        splashBackObj.setOnClickListener(click);

    }

    private final View.OnClickListener click = new View.OnClickListener() {
        @SuppressLint("SetTextI18n")
        @Override
        public void onClick(View view) {
            int id = view.getId();
            if (id == R.id.nextsplash) {
                if(pageCount==1){
                    pageCount=2;
                    lastTextObj.setText("Getting fast and reliable towing assistance is now just a few taps away!");
                    splashTrackObj.setImageResource(R.drawable.pagetracksplashtwo);
                    splashIconObj.setImageResource(R.drawable.secondsplashscreenicon);
                    splashTitleObj.setText("Welcome to");
                    splashSubTitleObj.setText("Towmandu");
                    RelativeLayout.LayoutParams splashscreeniconParam = (RelativeLayout.LayoutParams) splashIconObj.getLayoutParams();
                    splashscreeniconParam.topMargin=dpToPx(87);
                    splashIconObj.setLayoutParams(splashscreeniconParam);
                } else if(pageCount==2){
                    pageCount=3;
                    lastTextObj.setText("Our digital payment options make paying for your tow quick, easy, and secure—no hassle!");
                    splashTitleObj.setText("Effortless");
                    splashSubTitleObj.setText("Payment");
                    splashTrackObj.setImageResource(R.drawable.pagetracksplashthree);
                    splashIconObj.setImageResource(R.drawable.thirdsplashscreenicon);
                    RelativeLayout.LayoutParams splashscreeniconParam = (RelativeLayout.LayoutParams) splashIconObj.getLayoutParams();
                    lastTextObj.setTextSize(18);
                    splashscreeniconParam.topMargin=dpToPx(36);
                    splashIconObj.setLayoutParams(splashscreeniconParam);
                }
                else if(pageCount==3){
                    SharedPreferences prefs = getSharedPreferences("AppPrefs", MODE_PRIVATE);
                    SharedPreferences.Editor editor = prefs.edit();
                    editor.putBoolean("isFirstRun", false);
                    editor.apply();
                    Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                    intent.putExtra("done", true);
                    startActivity(intent);
                }

            } else if (id == R.id.skipSplash) {
                SharedPreferences prefs = getSharedPreferences("AppPrefs", MODE_PRIVATE);
                SharedPreferences.Editor editor = prefs.edit();
                editor.putBoolean("isFirstRun", false);
                editor.apply();
                Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                intent.putExtra("done", true);
                startActivity(intent);
            } else if(id==R.id.splashBack){
                if(pageCount==2){
                    pageCount=1;
                    lastTextObj.setText("Fast, hassle-free towing with real-time tracking—whenever you need it!");
                    splashTrackObj.setImageResource(R.drawable.pagetracksplashone);
                    splashIconObj.setImageResource(R.drawable.firstsplashscreenicon);
                    splashTitleObj.setText("Quick and Reliable");
                    splashSubTitleObj.setText("Tow Service");
                    RelativeLayout.LayoutParams splashscreeniconParam = (RelativeLayout.LayoutParams) splashIconObj.getLayoutParams();
                    int px= dpToPx(100);
                    splashscreeniconParam.topMargin=px;
                    splashIconObj.setLayoutParams(splashscreeniconParam);
                }
                else if(pageCount==3){
                    pageCount=2;
                    lastTextObj.setText("Getting fast and reliable towing assistance is now just a few taps away!");
                    splashTitleObj.setText("Welcome to");
                    splashSubTitleObj.setText("Towmandu!");
                    splashTrackObj.setImageResource(R.drawable.pagetracksplashtwo);
                    splashIconObj.setImageResource(R.drawable.secondsplashscreenicon);
                    RelativeLayout.LayoutParams splashscreeniconParam = (RelativeLayout.LayoutParams) splashIconObj.getLayoutParams();
                    splashscreeniconParam.topMargin=dpToPx(88);
                    splashIconObj.setLayoutParams(splashscreeniconParam);
                }
            }

        }
    };
    public int dpToPx(int dp) {
        float density = getResources().getDisplayMetrics().density;
        return (int) (dp * density);
    }



}