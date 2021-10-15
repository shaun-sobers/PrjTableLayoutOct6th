package com.example.prjtablelayoutoct6th;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.example.prjtablelayoutoct6th.model.Schedule;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    TextView listOfTextViews[];
    int widgets [] = {R.id.tvMMorning, R.id.tvMAftEvening,
    R.id.tvMornAfter, R.id.tvTEvening};

    Schedule[] listOfSchedules;

    TextView clickedTv;
    // 1- Declare the activity launcher
    ActivityResultLauncher<Intent> activityResultLauncher;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initialize();
    }

    private void initialize() {

        listOfTextViews = new TextView[widgets.length];
        listOfSchedules = new Schedule[widgets.length];
        listOfSchedules[0]= new Schedule(0,"Android", Color.RED);
        listOfSchedules[1]= new Schedule(1,"ASP.NET", Color.BLUE);
        listOfSchedules[2]= new Schedule(2,"JAVA");
        listOfSchedules[3]= new Schedule(3,"SQL", Color.YELLOW);




        for (int i=0; i<widgets.length; i++)
        {
            listOfTextViews[i] = findViewById(widgets[i]);
            Schedule oneSchedule = listOfSchedules[i];
            listOfTextViews[i].setText(oneSchedule.toString());
            listOfTextViews[i].setTextColor(oneSchedule.getTxtColor());
            listOfTextViews[i].setOnClickListener(this);
        }

        //2- Register the activity result launcher
        activityResultLauncher = registerForActivityResult(new ActivityResultContracts.StartActivityForResult(), new ActivityResultCallback<ActivityResult>() {
            @Override
            public void onActivityResult(ActivityResult result) {

                if(result.getResultCode()== RESULT_OK&&result.getData()!=null){
                    String newData = result.getData().getStringExtra("new_schedule");
                    clickedTv.setText(newData);
                }
                else {
                    if(result.getResultCode()==RESULT_CANCELED)
                    {
                        processNoChangeResult();
                        Toast.makeText(MainActivity.this, "no change",Toast.LENGTH_LONG).show();
                    }
                }
            }
        });
    }

   private void processNoChangeResult(){
       Toast.makeText(MainActivity.this, "no change",Toast.LENGTH_LONG).show();
   }

    @Override
    public void onClick(View view) {

        /*
        TextView tv = (TextView) view;
        Toast.makeText(this,
                tv.getText().toString(),
                Toast.LENGTH_LONG).show();

         */


        // 3- Launch the intent
        clickedTv = (TextView)view;
        Intent intent = new Intent(this
        , ChangeSchedule.class);
        intent.putExtra("schedule", clickedTv.getText().toString());


        activityResultLauncher.launch(intent);
    }
}