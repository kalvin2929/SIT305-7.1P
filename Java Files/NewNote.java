package com.techroid.notesapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class NewNote extends AppCompatActivity {
EditText note;
DatabaseHelper mDb;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_note);
        note = findViewById(R.id.edtNewNote);
        mDb = new DatabaseHelper(this);
    }

    @Override
    public void onBackPressed() {
        Intent main = new Intent(this,MainActivity.class);
        startActivity(main);
        finish();
    }

    public void onSaveNote(View view) {
        String note_Txt = note.getText().toString();
        if(note_Txt.equals(""))
            Toast.makeText(getApplicationContext(),"Please enter some text",Toast.LENGTH_SHORT).show();
        else {
            boolean isInserted = mDb.insertData(note_Txt);
            if(isInserted==true) {
                Toast.makeText(getApplicationContext(), "Note is Saved", Toast.LENGTH_SHORT).show();
                Intent main = new Intent(this, MainActivity.class);
                startActivity(main);
                finish();
            }
            else
                Toast.makeText(getApplicationContext(),"Some thing went wrong",Toast.LENGTH_SHORT).show();


        }

    }
}