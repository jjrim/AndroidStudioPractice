package ca.bcit.intentspractice;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.speech.tts.TextToSpeech;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.Locale;


public class SpeakActivity extends AppCompatActivity {

    Button backButton;
    Button speakButton;
    TextView textForSpeech;
    TextToSpeech textToSpeech;
    TextView errorMessage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_speak);

        backButton = (Button) findViewById(R.id.back_button);
        speakButton = (Button) findViewById(R.id.speak_button);
        textForSpeech = (TextView) findViewById(R.id.textForSpeech);
        errorMessage = (TextView) findViewById(R.id.errorMessage);

        textToSpeech = new TextToSpeech(getApplicationContext(), new TextToSpeech.OnInitListener() {
            @Override
            public void onInit(int status) {
                if (status != TextToSpeech.ERROR) {
                    textToSpeech.setLanguage(Locale.US);
                }
            }
        });


        speakButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (textForSpeech.getText().toString().isEmpty()){
                    showErrorMessage();
                } else {
                    clearErrorMessage();
                    String toSpeak = textForSpeech.getText().toString();
                    Toast.makeText(getApplicationContext(), toSpeak, Toast.LENGTH_SHORT).show();
                    textToSpeech.speak(toSpeak, TextToSpeech.QUEUE_FLUSH, null);
                }}
        });


        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                moveToMainActivity();
            }
        });


    }

    public void onPause() {
        if (textToSpeech != null) {
            textToSpeech.stop();
            textToSpeech.shutdown();
        }
        super.onPause();
    }

    private void moveToMainActivity() {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }

    public void showErrorMessage() {
            errorMessage.setText("Invalid Input");
            errorMessage.setTextColor(Color.RED);
        }

    public void clearErrorMessage() {
        errorMessage.setText("");
    }

    }

