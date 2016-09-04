package com.example.david.locktrivia;


import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * Created by David on 11/2/2015.
 */
public class Question {

    Question(String q, int a, List<String> options) {
        this.question = q;
        this.answers = new ArrayList<String>();
        int i;
        this.corAnswer = options.get(a);

        //add the options randomly to the questions
        int runTimes = options.size();
        for (i = 0; i < runTimes; i++) {
            Random rand = new Random();
            int value = rand.nextInt(options.size());
            this.answers.add(options.get(value));
            options.remove(value);
        }
    }

    public String getQuestion() {
        return this.question;
    }

    public String getAnswer() {
        return this.corAnswer;
    }

    public List<String> getOptions() {
        return this.answers;
    }

    protected List<String> answers;
    protected String question;
    protected String corAnswer;

}
