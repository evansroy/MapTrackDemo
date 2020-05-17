package org.evansroy.maptrackdemo;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    //Initialize variable
    EditText etSource,etDestination;
    Button btTrack;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Assign Variable
        etSource = findViewById(R.id.et_source);
        etDestination = findViewById(R.id.et_destination);
        btTrack = findViewById(R.id.bt_track);

        btTrack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Get value from edit text
                String sSource = etSource.getText().toString().trim();
                String sDestination = etDestination.getText().toString().trim();

                //Check condition
                if (sSource.equals("") && sDestination.equals("")){
                    //When both value blank
                    Toast.makeText(MainActivity.this, "Enter both Location", Toast.LENGTH_SHORT).show();
                }else {
                    //When both value fill
                    //Display track
                    DisplayTrack(sSource,sDestination);
                }

            }

            private void DisplayTrack(String sSource, String sDestination) {
                //if the device does no have a map installed, then redirect it to play store
                try {
                    // when google map is installed
                    // Initialize uri
                    Uri uri = Uri.parse("https://www.google.co.in/maps/dir/" + sSource + "/" + sDestination);
                    //Initialize intent with action view
                    Intent intent = new Intent(Intent.ACTION_VIEW,uri);
                    //Set package
                    intent.setPackage("com.google.android.apps.maps");
                    //Set flag
                    intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    //Start activity
                    startActivity(intent);
                }catch (ActivityNotFoundException e){
                    //When google map in not Installed
                    //Initialize uri
                    Uri uri = Uri.parse("https://play.google.com/store/apps/details?id=com.google.android.apps.maps");
                    //Initialize intent with action view
                    Intent intent = new Intent(Intent.ACTION_VIEW,uri);
                    //Set flag
                    intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    //Start Activity
                    startActivity(intent);
                }
            }
        });

    }
}
