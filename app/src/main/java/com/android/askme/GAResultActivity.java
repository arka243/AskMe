package com.android.askme;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;


public class GAResultActivity extends Activity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_caresult);
        //get rating bar object
        TextView t=(TextView)findViewById(R.id.textResult);
        //get score
        Bundle b = getIntent().getExtras();
        int correct= b.getInt("correct");
        if(correct == 1)
            t.setText("Correct Answer!!");
        else
            t.setText("Wrong Answer!!");
        Button btn = (Button)findViewById(R.id.btn);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(GAResultActivity.this, GAQuizActivity.class);
                startActivity(intent);
                finish();
            }
        });
    }
}