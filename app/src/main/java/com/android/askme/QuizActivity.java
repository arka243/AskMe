package com.android.askme;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Random;


public class QuizActivity extends Activity {
    TextView txtQuestion;
    RadioButton rda, rdb, rdc, rdd;
    Button butNext, butEnd;
    int correct = 0;
//    int qnum = 1;
    String[] qsplit;
    static HashMap<Integer, String> qList = new HashMap<Integer, String>();

    public class CSVFile {

        InputStream inputStream;
        public CSVFile(InputStream inputStream){
            this.inputStream = inputStream;
        }

        public HashMap<Integer, String> read()
        {
            HashMap<Integer, String> resultList = new HashMap<Integer, String>();
            BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));
            try
            {
                String csvLine;
                int count = 0;
                while ((csvLine = reader.readLine()) != null)
                {
                    if(csvLine.contains("|"))
                    {
                        resultList.put(count, csvLine);
                        count++;
                    }
                }
            } catch (IOException ex)
            {
                throw new RuntimeException("Error in reading CSV file: "+ex);
            }
            finally
            {
                try
                {
                    inputStream.close();
                } catch (IOException e)
                {
                    throw new RuntimeException("Error while closing input stream: "+e);
                }
            }
            return resultList;
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz);
        InputStream inputstream = getResources().openRawResource(R.raw.questions);
        CSVFile csvfile = new CSVFile(inputstream);
        qList = csvfile.read();

        txtQuestion = (TextView) findViewById(R.id.textView1);
        rda = (RadioButton) findViewById(R.id.radio0);
        rdb = (RadioButton) findViewById(R.id.radio1);
        rdc = (RadioButton) findViewById(R.id.radio2);
        rdd = (RadioButton) findViewById(R.id.radio3);
        butNext = (Button) findViewById(R.id.button1);
        butEnd = (Button) findViewById(R.id.button2);
        Random num = new Random();
        int qnum = num.nextInt(qList.size()-1);
        String qs = qList.get(qnum);
        qsplit = qs.split("\\|");
        txtQuestion.setText(qsplit[0]);
        rda.setText(qsplit[1]);
        rdb.setText(qsplit[2]);
        rdc.setText(qsplit[3]);
        rdd.setText(qsplit[4]);

        butNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                RadioGroup grp = (RadioGroup) findViewById(R.id.radioGroup1);
                RadioButton answer = (RadioButton) findViewById(grp.getCheckedRadioButtonId());
                if(qsplit[5].equals(answer.getText().toString())) {
                    correct = 1;
                }
                Intent intent = new Intent(QuizActivity.this, ResultActivity.class);
                Bundle b = new Bundle();
                b.putInt("correct", correct);
                intent.putExtras(b);
                correct = 0;
                startActivity(intent);
                finish();
            }
        });

        butEnd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(QuizActivity.this, MainActivity.class);
                startActivity(intent);
                finish();
            }
        });
    }
}
