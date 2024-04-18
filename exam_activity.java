package com.example.firdt_assignment;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

public class exam_activity extends AppCompatActivity {

    private RadioGroup Radio1;
    private RadioGroup Radio2;
    private RadioGroup Radio3;
    private RadioGroup Radio4;
    private RadioGroup Radio5;
    private Button check;
    private int secound ;
    private boolean running = true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_exam);
        Radio1 = findViewById(R.id.radio1);
        Radio2 = findViewById(R.id.radio2);
        Radio3 = findViewById(R.id.radio3);
        Radio4 = findViewById(R.id.radio4);
        Radio5 = findViewById(R.id.radio5);
        check=findViewById(R.id.submitButton);
        checkInstance(savedInstanceState);

        Timer();


        check.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                running=false;
                checked ();
            }
        });




    }


    public boolean checkAnswer1 (){
        int selectedId = Radio1.getCheckedRadioButtonId();

        boolean answer1 = findViewById(selectedId) != null && selectedId == R.id.unhuppy;

        return answer1;
    }
public boolean checkAnswer2(){

        int selectedId=Radio2.getCheckedRadioButtonId();

        boolean answer2 =findViewById(selectedId)!= null && selectedId==R.id.wealthy;

    return answer2;
}
    public boolean checkAnswer3(){

        int selectedId =Radio3.getCheckedRadioButtonId();

        boolean answer3=findViewById(selectedId)!=null && selectedId==R.id.calm;

        return answer3;
    }
    public boolean checkAnswer4(){

        int selectedId=Radio4.getCheckedRadioButtonId();

        boolean answer4 =findViewById(selectedId)!= null && selectedId==R.id.powerful;

        return answer4;
    }
    public boolean checkAnswer5(){

        int selectedId=Radio5.getCheckedRadioButtonId();

        boolean answer5 =findViewById(selectedId)!= null && selectedId==R.id.larg;

        return answer5;
    }


    private void Timer(){
        final TextView TxtTime = findViewById(R.id.txtTime);
        final Handler handler = new Handler();
        secound = 60; // initialize the seconds to 60

        handler.post(new Runnable() {
            @Override
            public void run() {
                int mint = secound / 60; // calculate minutes
                int sec = secound % 60; // calculate remaining seconds

                String text = "0 : " + mint + " : " + sec;
                TxtTime.setText(text);

                if (running && secound > 0) { // check if the timer is running and there are seconds left
                    secound--;
                    handler.postDelayed(this, 1000);
                }
              if (secound==0){
                  checked ();
                  running=false;
              }


            }
        });
    }


    public void checked (){

                boolean answer1 = checkAnswer1();
                boolean answer2 = checkAnswer2();
                boolean answer3 = checkAnswer3();
                boolean answer4 = checkAnswer4();
                boolean answer5 = checkAnswer5();


                if (answer1 && answer2 && answer3 && answer4 && answer5) {

                    Toast.makeText(exam_activity.this, "successful", Toast.LENGTH_LONG).show();

                }

                else{

                    if ( !answer1  ){
                        RadioButton correctRadioButton = findViewById(R.id.unhuppy); // Update with the correct ID
                        correctRadioButton.setTextColor(Color.RED);
                    }
                    if ( !answer2 ){
                        RadioButton correctRadioButton = findViewById(R.id.wealthy); // Update with the correct ID
                        correctRadioButton.setTextColor(Color.RED);
                    }
                    if ( !answer3 ){
                        RadioButton correctRadioButton = findViewById(R.id.calm); // Update with the correct ID
                        correctRadioButton.setTextColor(Color.RED);
                    }
                    if ( !answer4 ){
                        RadioButton correctRadioButton = findViewById(R.id.powerful); // Update with the correct ID
                        correctRadioButton.setTextColor(Color.RED);
                    }if ( !answer5 ){
                        RadioButton correctRadioButton = findViewById(R.id.larg); // Update with the correct ID
                        correctRadioButton.setTextColor(Color.RED);
                    }
                    Toast.makeText(exam_activity.this, "you dont pass the exame and the correct answer in readcolor ", Toast.LENGTH_LONG).show();

                }


    }


    private void checkInstance(Bundle savedInstanceState) {
        if(savedInstanceState != null){
            secound = savedInstanceState.getInt("SECONDS");
            running = savedInstanceState.getBoolean("RUNNING");
        }
    }

    @Override
    protected void onSaveInstanceState(Bundle outState){
        super.onSaveInstanceState(outState);
        outState.putInt("SECONDS", secound);
        outState.putBoolean("RUNNING", running);


    }


}