package com.techroid.notesapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void createNewNote(View view) {
        Intent newNote = new Intent(this,NewNote.class);
        startActivity(newNote);
        finish();
    }

    public void onViewAll(View view) {
        Intent viewAll = new Intent(this,ViewAll.class);
        startActivity(viewAll);
        finish();
    }
}