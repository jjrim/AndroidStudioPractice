package ca.bcit.intentspractice;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.provider.Settings;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;
import java.util.Random;


public class MainActivity extends AppCompatActivity {

    View view;
    Button randomColorButton;
    Button serialNumberButton;
    Button apiVersionButton;
    Button speakButton;
    TextView result;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        view = findViewById(R.id.view);
        randomColorButton = (Button) findViewById(R.id.random_color_button);
        serialNumberButton = (Button) findViewById(R.id.serial_number_button);
        apiVersionButton = (Button) findViewById(R.id.api_version_button);
        speakButton = (Button) findViewById(R.id.speak_button);
        result = findViewById(R.id.result);

        randomColorButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Random random = new Random();

                int randomColor =  Color.rgb(
                        random.nextInt(256),
                        random.nextInt(256),
                        random.nextInt(256));

                view.setBackgroundColor(randomColor);
            }
        });

        serialNumberButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String deviceId = Settings.System.getString(getContentResolver(), Settings.Secure.ANDROID_ID);
                Intent intent = new Intent(Intent.ACTION_SEND);
                intent.setType("text/plain");
                intent.putExtra(intent.EXTRA_TEXT, deviceId);
                startActivity(intent);
            }
        });

        speakButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                moveToSpeakActivity();
            }
        });

        apiVersionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String manufacturer = android.os.Build.MANUFACTURER;
                String model = android.os.Build.MODEL;
                int version = android.os.Build.VERSION.SDK_INT;
                String versionRelease = android.os.Build.VERSION.RELEASE;

                String messageText = " manufacturer " + manufacturer
                        + " \n model " + model
                        + " \n version " + version
                        + " \n versionRelease " + versionRelease;

                Toast.makeText(MainActivity.this, messageText, Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void moveToSpeakActivity() {
        Intent intent = new Intent(this, SpeakActivity.class);
        startActivity(intent);
    }
}