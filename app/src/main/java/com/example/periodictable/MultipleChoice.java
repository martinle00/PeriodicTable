package com.example.periodictable;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.periodictable.asynctask.AsyncTaskFindDelegate;
import com.example.periodictable.asynctask.FindElementAsyncTask;
import com.example.periodictable.database.AppDatabase;

import java.util.Arrays;
import java.util.List;
import java.util.Random;
import java.util.concurrent.TimeUnit;

public class MultipleChoice extends AppCompatActivity implements AsyncTaskFindDelegate {

    // Initialising items on the page
    AppDatabase db = AppDatabase.getInstance(this);
    TextView answer;
    TextView result;
    Button buttonA;
    Button buttonB;
    Button buttonC;
    Button buttonD;
    int counter = 1;
    int difficulty;
    List<Integer> answers = Arrays.asList(R.id.buttonA, R.id.buttonB, R.id.buttonC,
            R.id.buttonD);

    // The code for this works by assigning buttons with a random number depending on the
    // difficulty chosen and ensures that one of the numbers is the same as the question asked.
    // The code then randomly orders the values of the buttons which is ensured
    // by the while loops which checks the obtained values
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.multiple_choice);

        Intent intent = getIntent();
        difficulty = intent.getIntExtra("difficulty",118);

        int keyElementAtomicNumber = randomNumberGenerator();
        answer = findViewById(R.id.question);
        result = findViewById(R.id.result);

        int designator1 = randomAnswerDesignator();
        buttonA = findViewById(answers.get(designator1));

        int designator2 = randomAnswerDesignator();
        while (designator2 == 0 || designator2 == designator1 - 1) {
            designator2 = randomAnswerDesignator();
        }
        buttonB = findViewById(answers.get(designator2));

        int designator3 = randomAnswerDesignator();
        while (designator3 == designator1 || designator3 == designator2) {
            designator3 = randomAnswerDesignator();
        }
        buttonC = findViewById(answers.get(designator3));

        int designator4 = randomAnswerDesignator();
        while (designator4 == designator1 || designator4 == designator2 || designator4 == designator3) {
            designator4 = randomAnswerDesignator();
        }
        buttonD = findViewById(answers.get(designator4));

        int answer1 = randomNumberGenerator();
        while (answer1 == keyElementAtomicNumber) {
            answer1 = randomNumberGenerator();
        }
        int answer2 = randomNumberGenerator();
        while (answer2 == keyElementAtomicNumber || answer2 == answer1) {
            answer2 = randomNumberGenerator();
        }
        int answer3 = randomNumberGenerator();
        {
            while (answer3 == keyElementAtomicNumber || answer3 == answer1 || answer3 == answer2) {
                answer3 = randomNumberGenerator();
            }
        }


        startAsyncTask(keyElementAtomicNumber);
        startAsyncTask(answer1);
        startAsyncTask(answer2);
        startAsyncTask(answer3);

        if (buttonA.getText().equals(null)){
            getIntent();
            finish();
            startActivity(intent);
        }
        // Case in which the user chooses the correct answer, text will pop up saying correct
        buttonA.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                result.setText(R.string.correct);
                result.setTextColor(getResources().getColor(R.color.correct));
                try {
                    TimeUnit.MICROSECONDS.sleep(100);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                Intent intent = getIntent();
                finish();
                startActivity(intent);
            }
        });
    // Cases in which the player chooses the incorrect answer, text will pop up saying incorrect
        buttonB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                result.setText(R.string.incorrect);
                result.setTextColor(getResources().getColor(R.color.incorrect));
            }
        });

        buttonC.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                result.setText(R.string.incorrect);
                result.setTextColor(getResources().getColor(R.color.incorrect));
            }
        });

        buttonD.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                result.setText(R.string.incorrect);
                result.setTextColor(getResources().getColor(R.color.incorrect));
            }
        });
    }

    public void handleTaskResult(Element element) {
        String name = element.getName();
        if (counter == 1) {
            answer.setText("Guess the element with atomic number " + (element.getAtomicNumber()));
            //Note that there is a bug that sometimes causes the box with the correct answer to be blank.
            //The bug also prevents the onClick method for the answer from working.
            // We're not exactly sure what the cause of this bug is.
            buttonA.setText(name);
        } else if (counter == 2) {
            buttonB.setText(name);
        } else if (counter == 3) {
            buttonC.setText(name);
        } else if (counter == 4) {
            buttonD.setText(name);
        }
        counter++;
    }
    // Method to assign a random answer to a button
    int randomAnswerDesignator() {
        Random random = new Random();
        int assignedNumber = random.nextInt(4);
        return assignedNumber;
    }
    // Method to randomly generate numbers based on difficulty
    int randomNumberGenerator() {
        Random random = new Random();
        int generatedNumber = random.nextInt(difficulty) + 1;
        return generatedNumber;
    }
    // Async task to obtain data
    public void startAsyncTask(int element) {
        FindElementAsyncTask findElementAsyncTask = new FindElementAsyncTask();
        findElementAsyncTask.setDatabase(db);
        findElementAsyncTask.setDelegate(MultipleChoice.this);
        findElementAsyncTask.execute(element);
    }
}