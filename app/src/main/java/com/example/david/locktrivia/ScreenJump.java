package com.meir.david.locktrivia;

import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.AssetManager;
import android.os.Handler;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import android.widget.TextView;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class ScreenJump extends AppCompatActivity {
    int wrongs;
    int rights;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        SharedPreferences SP = PreferenceManager.getDefaultSharedPreferences(getBaseContext());
        SharedPreferences.Editor editor = SP.edit();
        rights = SP.getInt("rights", 0);
        wrongs = SP.getInt("wrongs", 0);


        try {

            InputStream is = getOneFile();

            BufferedReader br = new BufferedReader(new InputStreamReader(is, "UTF-8"));
            String currentLine = br.readLine();

            int quesNum = Integer.parseInt(currentLine);

            //get random question
            Random rand = new Random();
            int randQues = rand.nextInt(quesNum);
            int i;
            for (i = 0; i < randQues * 6; i++) {
                br.readLine();
            }
            Question myQuestion = parseQuestion(br);

            setContentView(R.layout.activity_screen_jump);
            TextView stats = (TextView) findViewById(R.id.stats);
            stats.setText("Correct Answers                   Wrongs Answers \n                    " + rights + "                                      " + wrongs);
            final int mUIFlag = View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                    | View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
                    | View.SYSTEM_UI_FLAG_IMMERSIVE
                    | View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                    | View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY
                    | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN;


            //View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION;

            getWindow().getDecorView().setSystemUiVisibility(mUIFlag);
            //set the question
            TextView q = (TextView) findViewById(R.id.Thequestion);
            q.setText(myQuestion.getQuestion());

            //set the buttons for the answer
            for (i = 0; i < myQuestion.getOptions().size(); i++) {
                Button tempButton = new Button(this);
                switch (i) {
                    case 0:
                        tempButton = (Button) findViewById(R.id.radioButton1);
                        break;
                    case 1:
                        tempButton = (Button) findViewById(R.id.radioButton2);
                        break;
                    case 2:
                        tempButton = (Button) findViewById(R.id.radioButton3);
                        break;
                    case 3:
                        tempButton = (Button) findViewById(R.id.radioButton4);
                        break;
                }

                tempButton.setVisibility(View.VISIBLE);
                tempButton.setBackgroundResource(R.drawable.gradientbutton1);
                tempButton.setText(myQuestion.getOptions().get(i));
                tempButton.setOnClickListener(new MyOnClickListener(myQuestion.corAnswer));
            }

        } catch (IOException e) {
            e.printStackTrace();
        }



    }
    @Override
    protected void onPause() {
        super.onPause();
    }

    public Question parseQuestion(BufferedReader br) {
        try {
            List<String> options = new ArrayList();


            String q = br.readLine();
            String line = br.readLine();
            String[] splitted = line.split("\\s+");
            int optionNum = Integer.parseInt(splitted[0]);
            int corAns = Integer.parseInt(splitted[1]);
            int i;
            for (i = 0; i < optionNum; i++) {
                String tempLine = br.readLine();
                options.add(tempLine);
            }
            Question ques = null;

            //create question
            ques = new Question(q, corAns, options);

            return ques;

        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }
    public void terminate(View v)
    {
        Button ter = (Button)v;
        if(ter.isPressed())
        {
            ter.setBackgroundResource(R.drawable.afterpressrect);
            finish();
        }
    }
    public InputStream getOneFile() {
        try {
            AssetManager assetMgr = this.getAssets();
            ArrayList<InputStream> allFiles = new ArrayList<InputStream>();
            SharedPreferences SP = PreferenceManager.getDefaultSharedPreferences(getBaseContext());
            if (SP.getBoolean("food", true)) {
                allFiles.add(assetMgr.open("foodQuestions.txt"));
            }
            if (SP.getBoolean("sports", true)) {
                allFiles.add(assetMgr.open("sportQuestions.txt"));
            }
            if (SP.getBoolean("history", true)) {
                allFiles.add(assetMgr.open("historyQuestions.txt"));
            }
            if (SP.getBoolean("movies", true)) {
                allFiles.add(assetMgr.open("movieQuestions.txt"));
            }
            if (SP.getBoolean("science", true)) {
                allFiles.add(assetMgr.open("scienceQuestions.txt"));
            }
            Random rand = new Random();
            int randFile = rand.nextInt(allFiles.size());
            return allFiles.get(randFile);

        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }
    public class MyOnClickListener implements View.OnClickListener {

        String answer;

        public MyOnClickListener(String myString) {
            this.answer = myString;
        }

        @Override
        public void onClick(View v) {
            Button tempButton = (Button) v;
            int rightCounter, wrongCounter;

            SharedPreferences SP = PreferenceManager.getDefaultSharedPreferences(getBaseContext());
            SharedPreferences.Editor editor = SP.edit();
            rightCounter = SP.getInt("rightCounter", 0);
            wrongCounter = SP.getInt("wrongCounter", 0);


            int numQues = SP.getInt("counter", SP.getInt("quesnumber", 5));
            if (answer.equals(tempButton.getText())) {
                editor.putInt("rightCounter", rightCounter + 1);
                v.setBackgroundResource(R.drawable.right_answer);
                int def = SP.getInt("quesnumber", 5);
                editor.putInt("counter", def);
                editor.putInt("rights", rights+1);
                editor.commit();

                Handler handler = new Handler();
                handler.postDelayed(new Runnable() {
                    public void run() {
                        finish();
                    }
                }, 500);
            }
            else {
                v.setBackgroundResource(R.drawable.wrong_answer);
                editor.putInt("wrongCounter", wrongCounter + 1);
                editor.putInt("counter", (numQues - 1));
                editor.putInt("wrongs", wrongs + 1);
                editor.commit();


                if (SP.getInt("counter", 5) == 0) {
                    int def = SP.getInt("quesnumber", 5);
                    editor.putInt("counter", def);
                    editor.commit();
                    finish();
                } else {
                    finish();
                    Intent newQuestion = new Intent(v.getContext(), ScreenJump.class);
                    v.getContext().startActivity(newQuestion);
                }
            }
        }

    }

}
