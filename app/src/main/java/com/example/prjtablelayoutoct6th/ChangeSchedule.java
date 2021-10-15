package com.example.prjtablelayoutoct6th;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import javax.xml.transform.Result;

public class ChangeSchedule extends AppCompatActivity  implements View.OnClickListener {

    EditText edDescription;
    Button btnReturn;
    String data;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_change_schedule);
        initialize();
    }

    private void initialize() {
        edDescription = findViewById(R.id.edDescription);
        btnReturn = findViewById(R.id.btnReturn);
        data= getIntent().getStringExtra("schedule");
        edDescription.setText(data);
        btnReturn.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        String newData = edDescription.getText().toString();
       Intent intent = new Intent();

        if (data.equals(newData))
        {
            // No Change
            setResult(RESULT_CANCELED, intent);
        }
        else
            {
                // We have a new schedule
                intent.putExtra("new_schedule" ,newData);
                setResult(RESULT_OK, intent);
            }

        finish();
    }
}