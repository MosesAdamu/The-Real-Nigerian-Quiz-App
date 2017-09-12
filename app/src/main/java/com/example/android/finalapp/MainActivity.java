package com.example.android.finalapp;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

public class MainActivity extends Activity {

    Button btnStartQuiz;
    Button btnInstruction;
    TextView txtTitle;
    private QuizDriver quizDriver;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        quizDriver = (QuizDriver) getApplication();

        btnStartQuiz = (Button) findViewById(R.id.startQuiz);
        txtTitle = (TextView) findViewById(R.id.title);

        btnInstruction = (Button) findViewById(R.id.instruction);



        btnStartQuiz.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openFile("all_questions.txt", v.getContext());
                Intent intent = new Intent(v.getContext(), Question1.class);
                startActivityForResult(intent, 0);
            }
        });
        btnInstruction.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(v.getContext(), Instruction.class);
                startActivity(intent);
            }
        });



    }



    protected void openFile(String filename, Context context) {

        InputStream is = null;
        try {
            is = context.getAssets().open(filename);
            if (is != null) { // check the file opened correctly from assets folder
                //Log.d("tag", "It worked!");
                quizDriver.parseQuestionData(new BufferedReader(new InputStreamReader(is, "UTF-8")));
                quizDriver.selectQuestions();
            }
        } catch (IOException e) {
            Log.d("main activity", "IOException thrown");
            //e.printStackTrace();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }



}
