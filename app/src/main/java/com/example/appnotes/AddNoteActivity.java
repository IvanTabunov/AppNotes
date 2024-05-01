package com.example.appnotes;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.Toast;

public class AddNoteActivity extends AppCompatActivity {

    private EditText editTextTitle;
    private EditText editTextdescription;
    private Spinner spinnerDayOfWeek;
    private RadioGroup radioGroupPriority;

    private NotesDBHelper dbHelper;
    private SQLiteDatabase database;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_note);
        dbHelper = new NotesDBHelper(this);
        database = dbHelper.getWritableDatabase();
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
        if (isFilled(title, description)){
            ContentValues contentValues = new ContentValues();
            contentValues.put(NotesContract.NotesEntry.COLUMN_TITLE, title);
            contentValues.put(NotesContract.NotesEntry.COLUMN_DESCRIPTION, description);
            contentValues.put(NotesContract.NotesEntry.COLUMN_DAY_OF_WEEK, dayOfWeek);
            contentValues.put(NotesContract.NotesEntry.COLUMN_PRIORITY, priority);
            database.insert(NotesContract.NotesEntry.TABLE_NAME, null, contentValues);
            Intent intent = new Intent(this, MainActivity.class);
            startActivity(intent);
        } else{
            Toast.makeText(this, R.string.warning_fill, Toast.LENGTH_SHORT).show();
        }

    }

    private boolean isFilled(String title, String description){
        return !title.isEmpty() && !description.isEmpty();
    }
}