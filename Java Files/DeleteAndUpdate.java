package com.techroid.notesapp;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class DeleteAndUpdate extends AppCompatActivity {
    EditText note;
    String oldNote;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_delete_and_update);
        note = findViewById(R.id.edtNewNote);
        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            String value = extras.getString("note");
            note.setText(value);
            oldNote = note.getText().toString();
        }
    }

    public void onUpdateNote(View view) {
        boolean flag;
        if(note.getText().toString().equals(""))
            Toast.makeText(getApplicationContext(),"Note is Empty",Toast.LENGTH_SHORT);
        else {
            DatabaseHelper mDb = new DatabaseHelper(this);
            flag =mDb.updateData(oldNote,note.getText().toString());
            if(flag)
            {
                Toast.makeText(getApplicationContext(),"Note is Updated",Toast.LENGTH_SHORT);
                Intent viewAll = new Intent(this,ViewAll.class);
                startActivity(viewAll);
                finish();
            }
            else
                Toast.makeText(getApplicationContext(),"Something went wrong",Toast.LENGTH_SHORT);

        }
    }

    public void onDeleteNote(View view) {
        DatabaseHelper mDb = new DatabaseHelper(this);
            AlertDialog.Builder dialog = new AlertDialog.Builder(this);
            dialog.setCancelable(false);
            dialog.setTitle("Deleting note!");
            dialog.setMessage("Do you want to delete note?");
            dialog.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int id) {

                    int i = mDb.deleteData(oldNote);
                    if(i==-1)
                        Toast.makeText(getApplicationContext(),"Something went wrong",Toast.LENGTH_SHORT);
                    else {
                        Toast.makeText(getApplicationContext(), "Note is Deleted", Toast.LENGTH_SHORT);
                        Intent viewAll = new Intent(getApplicationContext(), ViewAll.class);
                        startActivity(viewAll);
                        finish();
                    }
                }
            });

            dialog.setNegativeButton("No ", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    dialog.dismiss();
                }
            }).show();

        }

    @Override
    public void onBackPressed() {
        Intent main = new Intent(this,MainActivity.class);
        startActivity(main);
        finish();
    }





}