package com.example.firdt_assignment;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Switch;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    private Switch switchExample;
    private RadioGroup radioGroup;
    private EditText name;
    private Button saveButton;
    private ArrayList<User> array;
    private static String selectedOption;
    private SharedPreferences sharedPreferences;
    private static final String GENDER = "GENDER";
    public static final String NAME = "NAME";
    public static final String FLAG = "FLAG";
    public boolean flag = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setupViews();
        array = new ArrayList<>();

        sharedPreferences = getSharedPreferences("user_data", Context.MODE_PRIVATE); // Initialize sharedPreferences

        saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!switchExample.isChecked() || radioGroup.getCheckedRadioButtonId() == -1 || name.getText().toString().isEmpty()) {
                    Toast.makeText(MainActivity.this, "There's missing information", Toast.LENGTH_SHORT).show();
                } else {
                    GetSelectedRadioButton();
                    GetSharedPreferences();
                    String UName = name.getText().toString();
                    array.add(new User(selectedOption, UName));
                    Intent intent = new Intent(MainActivity.this, choose_actvity.class);
                    startActivity(intent);
                }
            }
        });
    }

    private void setupViews() {
        switchExample = findViewById(R.id.switch1);
        radioGroup = findViewById(R.id.radioGroupGender);
        name = findViewById(R.id.Name);
        saveButton = findViewById(R.id.button);
    }

    private void GetSelectedRadioButton() {
        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                RadioButton selectedRadioButton = findViewById(checkedId);
                if (selectedRadioButton != null) {
                    selectedOption = selectedRadioButton.getText().toString();
                    Log.d("SelectedOption", "Selected: " + selectedOption);
                }
            }
        });
    }

    private void GetSharedPreferences() {
        flag = sharedPreferences.getBoolean(FLAG, false);

        if (flag) {
            String Name = sharedPreferences.getString(NAME, "");
            String gender = sharedPreferences.getString(GENDER, "");
            name.setText(Name);
            if (gender.equals("Male")) {
                RadioButton maleRadioButton = findViewById(R.id.Male); // Replace with your actual IDs
                maleRadioButton.setChecked(true);
            } else if (gender.equals("Female")) {
                RadioButton femaleRadioButton = findViewById(R.id.Female); // Replace with your actual IDs
                femaleRadioButton.setChecked(true);
            }
        }
    }
}
