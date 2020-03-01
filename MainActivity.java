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
import android.view.Menu;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.appcompat.widget.Toolbar;

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
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        NavigationView navigationView = findViewById(R.id.nav_view);
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        mAppBarConfiguration = new AppBarConfiguration.Builder(
                R.id.nav_home, R.id.nav_gallery, R.id.nav_slideshow)
                .setDrawerLayout(drawer)
                .build();
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment);
        NavigationUI.setupActionBarWithNavController(this, navController, mAppBarConfiguration);
        NavigationUI.setupWithNavController(navigationView, navController);
        Button button1 = (Button) findViewById(R.id.search);
        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setContentView(R.layout.results);
                createnew();
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
    public void createnew(){
        Button add_button = (Button) findViewById(R.id.add_button);
        add_button.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                name = "Bain and Company Info Session";
                startTime = "2:00 pm";
                endTime = "4:00 pm";
                location = "SSB 4.102";
                description = "Coffee Chats with Amanda Moehnke, PhD (Manager in Bain's Houston office) and Rina Rotunno.";
                Event temp = new Event(startTime, endTime, name, location, description);
                events.get((2)*31+2).set(0, temp);
                setContentView(R.layout.activity_main);
                DialogFragment datePicker = new DatePickerFragment();
                datePicker.show(getSupportFragmentManager(), "date picker");
            }

        });
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onSupportNavigateUp() {
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment);
        return NavigationUI.navigateUp(navController, mAppBarConfiguration)
                || super.onSupportNavigateUp();
    }
}
