package com.hyemi.myapplication;

import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import androidx.appcompat.app.AppCompatActivity;
import java.util.Random;

public class MainActivity extends AppCompatActivity {

    private View rootView;
    private Random random;
    private Handler handler;
    private Runnable colorChanger;
    private boolean isColorChanging = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        rootView = findViewById(android.R.id.content);
        random = new Random();
        handler = new Handler();

        Button changeColorButton = findViewById(R.id.changeColorButton);

        colorChanger = new Runnable() {
            @Override
            public void run() {
                changeBackgroundColor();
                handler.postDelayed(this, 500); // 0.5초 간격으로 실행
            }
        };

        changeColorButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (isColorChanging) {
                    handler.removeCallbacks(colorChanger);
                    changeColorButton.setText("Start Color Change");
                } else {
                    handler.post(colorChanger);
                    changeColorButton.setText("Stop Color Change");
                }
                isColorChanging = !isColorChanging;
            }
        });
    }

    private void changeBackgroundColor() {
        int color = Color.rgb(random.nextInt(256), random.nextInt(256), random.nextInt(256));
        rootView.setBackgroundColor(color);
    }
}

