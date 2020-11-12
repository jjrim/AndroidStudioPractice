package ca.bcit.howtoreverseatext;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.os.Bundle;
import android.provider.Settings;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import java.lang.*;


public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }


    public void ReverseTextButton(View v) {

        EditText input = findViewById(R.id.editTextTextPersonName);
        TextView result = findViewById(R.id.textView);

        if (input.getText().toString().isEmpty()) {
            result.setText("Invalid Input");
            result.setTextColor(Color.RED);
        } else {
            String deviceId = Settings.System.getString(getContentResolver(), Settings.Secure.ANDROID_ID);

            result.setText(deviceId);
        }
    }


}