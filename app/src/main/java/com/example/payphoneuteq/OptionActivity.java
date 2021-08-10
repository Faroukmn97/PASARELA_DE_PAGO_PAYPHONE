package com.example.payphoneuteq;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.UnsupportedEncodingException;

public class OptionActivity extends AppCompatActivity {
    private CardView btnverify, btnSellp, btnsearchAll;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_options);
        init();

        // boton para verificar por ID de usuario
        btnverify.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                goGetactivity();
            }
        });

        // boton para vender
        btnSellp.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                goPostactivity();
            }
        });

        // boton para verificar todo
        btnsearchAll.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                Toast.makeText(OptionActivity.this, "In process :v", Toast.LENGTH_LONG).show();
            }
        });
    }

    private void init(){
        btnverify =  (CardView) findViewById(R.id.btnverify);
        btnSellp =  (CardView) findViewById(R.id.btnSellp);
        btnsearchAll =  (CardView) findViewById(R.id.btnsearchAll);
    }

    private void goGetactivity(){
        Intent i = new Intent(this, GetActivity.class);
        // bandera para que no se creen nuevas actividades innecesarias
        i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP |
                Intent.FLAG_ACTIVITY_CLEAR_TASK |
                Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(i);
    }

    private void goPostactivity(){
        Intent i = new Intent(this, PostActivity.class);
        // bandera para que no se creen nuevas actividades innecesarias
        i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP |
                Intent.FLAG_ACTIVITY_CLEAR_TASK |
                Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(i);
    }




}
