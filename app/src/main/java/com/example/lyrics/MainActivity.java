package com.example.lyrics;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;

public class MainActivity extends AppCompatActivity {
    EditText ArtistName , SongName ;
    TextView textView ;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ArtistName = findViewById(R.id.ArtistName) ;
        SongName = findViewById(R.id.SongName) ;
        textView = findViewById(R.id.textView) ;

        findViewById(R.id.button3).setOnClickListener(view -> {
            String url = "https://api.lyrics.ovh/v1/" + ArtistName.getText().toString() + "/" + SongName.getText().toString() ;
            url.replace(" ","20%") ;

            RequestQueue requestQueue = Volley.newRequestQueue(getApplicationContext());
            JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.GET, url, null, response -> {

                try {
                    textView.setText(response.getString("lyrics"));
                } catch (JSONException e) {
                    e.printStackTrace();
                }


            }, error -> Toast.makeText(getApplicationContext(),error.getMessage(),Toast.LENGTH_SHORT).show());

            requestQueue.add(jsonObjectRequest);


        });

    }
}