package com.example.firdt_assignment;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class choose_actvity extends AppCompatActivity {
    private ArrayAdapter<String> adapter;
    private String[] option = {"Learn", "Exame"};
    private ListView listView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_choose_actvity);
        listView = findViewById(R.id.listView);

        adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, option);

        // Set the adapter to the ListView
        listView.setAdapter(adapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String selectedItem = (String) parent.getItemAtPosition(position);
                if (selectedItem.equals("Learn") ){
                    Intent intent = new Intent(choose_actvity.this, learn_activity.class);
                    startActivity(intent);
                }
                else if (selectedItem.equals("Exame")){
                    Intent intent = new Intent(choose_actvity.this, exam_activity.class);
                    startActivity(intent);
                }
            }
        });
    }
}