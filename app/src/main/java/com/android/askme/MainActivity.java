package com.android.askme;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;


public class MainActivity extends Activity {

    private Button startcabutton;
    private Button startgabutton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        startcabutton = (Button) findViewById(R.id.startbtnca);
        startcabutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, CAQuizActivity.class);
                startActivity(intent);
                finish();
            }
        });

        startgabutton = (Button) findViewById(R.id.startbtnga);
        startgabutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, GAQuizActivity.class);
                startActivity(intent);
                finish();
            }
        });
    }

}
