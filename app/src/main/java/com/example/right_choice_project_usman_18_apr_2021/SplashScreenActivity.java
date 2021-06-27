package com.example.right_choice_project_usman_18_apr_2021;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

public class SplashScreenActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);

        Thread splash_screen = new Thread()
        {
            public void run()
            {
                try
                {
                    sleep(3000);
                }
                catch (Exception e)
                {
                    e.printStackTrace();
                }
                finally
                {
                    //Intent is used to link one activity to another activity.
                    /*Intent is used to invoke components. It is mainly used to:
                    Start the service
                    Launch an activity
                    Display a web page
                    Display a list of contacts
                    Broadcast a message
                    Dial a phone call etc.
                    */
                    startActivity(new Intent(getApplicationContext(), LoginActivity.class));
                    finish();
                }
            }
        };
        splash_screen.start();
    }
}