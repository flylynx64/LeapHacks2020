package com.example.leaphacks;

import java.util.ArrayList;
import android.app.DatePickerDialog;
import java.util.Calendar;
import androidx.fragment.app.DialogFragment;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;
import java.text.DateFormat;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity implements DatePickerDialog.OnDateSetListener {
    String name, startTime, endTime, location, description;

    EditText nameInput;
    EditText timeInput1;
    EditText timeInput2;
    EditText locationInput;
    EditText descriptionInput;

    ArrayList<ArrayList<Event>> events = new ArrayList<ArrayList<Event>>(372);

    Button submitButton;
    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        for (int i = 0; i < 372; i++) {
            ArrayList<Event> temp = new ArrayList<>();
            events.add(temp);
            Event a = new Event("", "", "", "", "");
            events.get(i).add(a);
        }
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        DialogFragment datePicker = new DatePickerFragment();
        datePicker.show(getSupportFragmentManager(), "date picker");
        Button button1 = (Button) findViewById(R.id.enter);
        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setContentView(R.layout.activity_main);
                DialogFragment datePicker = new DatePickerFragment();
                datePicker.show(getSupportFragmentManager(), "date picker");
            }
        });
    }

    @Override
    public void onDateSet(DatePicker view, int year, final int month, final int dayOfMonth) {
        Calendar c = Calendar.getInstance();
        c.set(Calendar.YEAR, year);
        c.set(Calendar.MONTH, month);
        c.set(Calendar.DAY_OF_MONTH, dayOfMonth);
        String currentDateString = DateFormat.getDateInstance(DateFormat.FULL).format(c.getTime());
        setContentView(R.layout.frag1);
        TextView textView1 = (TextView) findViewById(R.id.textView);
        TextView textView2 = (TextView) findViewById(R.id.Name);
        TextView textView3 = (TextView) findViewById(R.id.Time);
        TextView textView4 = (TextView) findViewById(R.id.Location);
        TextView textView5 = (TextView) findViewById(R.id.Description);
        textView1.setText(currentDateString);
        if(events.get((month)*31+dayOfMonth).get(0).getName() != "") {
                textView2.setText(events.get((month) * 31 + dayOfMonth).get(0).getName());
                String a = events.get((month) * 31 + dayOfMonth).get(0).getStartTime() + " - " + events.get((month) * 31 + dayOfMonth).get(0).getEndTime();
                textView3.setText(a);
                textView4.setText(events.get((month) * 31 + dayOfMonth).get(0).getLocation());
                textView5.setText(events.get((month) * 31 + dayOfMonth).get(0).getDescription());
        }
        Button button = (Button) findViewById(R.id.button);
        Button button1 = (Button) findViewById(R.id.button1);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setContentView(R.layout.activity_main);
                DialogFragment datePicker = new DatePickerFragment();
                datePicker.show(getSupportFragmentManager(), "date picker");
            }
        });
        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setContentView(R.layout.create);

                nameInput = (EditText) findViewById(R.id.nameInput);
                timeInput1 = (EditText) findViewById(R.id.timeInput1);
                timeInput2 = (EditText) findViewById(R.id.timeInput2);
                locationInput = (EditText) findViewById(R.id.locationInput);
                descriptionInput = (EditText) findViewById(R.id.descriptionInput);

                submitButton = (Button) findViewById(R.id.submitButton);
                submitButton.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        name = nameInput.getText().toString();
                        startTime = timeInput1.getText().toString();
                        endTime = timeInput2.getText().toString();
                        location = locationInput.getText().toString();
                        description = descriptionInput.getText().toString();
                        Event temp = new Event(startTime, endTime, name, location, description);
                        events.get((month)*31+dayOfMonth).set(0, temp);
                        setContentView(R.layout.activity_main);
                        DialogFragment datePicker = new DatePickerFragment();
                        datePicker.show(getSupportFragmentManager(), "date picker");
                    }
                });
            }
        });
    }
}
