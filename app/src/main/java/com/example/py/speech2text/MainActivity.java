package com.example.py.speech2text;

import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.speech.RecognizerIntent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Locale;

public class MainActivity extends AppCompatActivity {
    TextView res ;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        res = findViewById(R.id.textView);
    }

    public void go(View view){
        if (view.getId() == R.id.imageButton){
            promtSpeechInput();
        }

    }

    public void promtSpeechInput(){
        Intent intent = new Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH);
        intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE_MODEL, RecognizerIntent.LANGUAGE_MODEL_FREE_FORM);
        intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE, Locale.getDefault());
        intent.putExtra(RecognizerIntent.EXTRA_PROMPT, "Say Something");
        try
        {
        startActivityForResult(intent, 100);
        }
        catch (ActivityNotFoundException a){
            Toast.makeText(this, "sorry your devvice doesn't support speech language",Toast.LENGTH_SHORT).show();
        }

    }

    public void onActivityResult(int request_code,int result_code, Intent intent){
      super.onActivityResult(request_code, result_code, intent);
      switch (request_code){
          case 100:
              if (result_code == RESULT_OK && intent!=null){
                  ArrayList<String> result = intent.getStringArrayListExtra(RecognizerIntent.EXTRA_RESULTS);
                  res.setText(result.get(0));
              }
              break;
      }
    }
}
