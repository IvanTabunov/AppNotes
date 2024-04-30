package com.example.appnotes;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;

public class AddNoteActivity extends AppCompatActivity {

    private EditText editTextTitle;
    private EditText editTextdescription;
    private Spinner spinnerDayOfWeek;
    private RadioGroup radioGroupPriority;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_note);

        editTextTitle = findViewById(R.id.editTextTitle);
        editTextdescription = findViewById(R.id.editTextDescription);
        spinnerDayOfWeek = findViewById(R.id.spinnerDaysOfWeek);
        radioGroupPriority = findViewById(R.id.radioGroupPriority);
    }

    public void saveNote(View view) {
        String title = editTextTitle.getText().toString().trim();
        String description = editTextdescription.getText().toString().trim();
        String dayOfWeek = spinnerDayOfWeek.getSelectedItem().toString();
        int radioButtonId = radioGroupPriority.getCheckedRadioButtonId();
        RadioButton radioButton = findViewById(radioButtonId);
        int priority = Integer.parseInt(radioButton.getText().toString());
        Note note = new Note(title, description, dayOfWeek, priority);
        MainActivity.notes.add(note);
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }
}