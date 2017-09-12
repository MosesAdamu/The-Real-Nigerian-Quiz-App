package com.example.android.finalapp;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

/**
 * Created by MOSES on 6/3/2017.
 */
public class Instruction extends Activity {

    Button btnPrev;
    Button btnStartQuiz;
    private QuizDriver quizDriver;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_instruction);


        // assign buttons/text fields
        btnPrev = (Button) findViewById(R.id.previous);
        btnStartQuiz = (Button) findViewById(R.id.startQuiz);
        quizDriver = (QuizDriver) getApplication();

        initListeners();

    }
    private void initListeners() {

        btnStartQuiz.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openFile("all_questions.txt", v.getContext());
                Intent intent = new Intent(v.getContext(), Question1.class);
                startActivityForResult(intent, 0);
            }
        });

        btnPrev.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(v.getContext(),MainActivity.class);
                startActivity(intent);
            }
        });
    }

    protected void openFile(String filename, Context context) {

        InputStream is = null;
        try {
            is = context.getAssets().open(filename);
            if ( is != null) { // check the file opened correctly from assets folder
                //Log.d("tag", "It worked!");
                quizDriver.parseQuestionData(new BufferedReader(new InputStreamReader(is, "UTF-8")));
                quizDriver.selectQuestions();
            }
        } catch (IOException e) {
            Log.d("main activity", "IOException thrown");
            //e.printStackTrace();
        }
    }

    protected void gotoPreviousQuestion(View v) {
        Intent intent = new Intent(v.getContext(), Question10.class);
        startActivityForResult(intent, 0);
    }



}
