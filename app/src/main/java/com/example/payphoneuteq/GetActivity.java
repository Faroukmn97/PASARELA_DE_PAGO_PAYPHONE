package com.example.payphoneuteq;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import java.util.HashMap;
import java.util.Map;

public class GetActivity extends AppCompatActivity {

    private String URL = "https://pay.payphonetodoesposible.com/";
    private String TOKEN = "THgalUhRUZfXgKoWMkHSWSSUT6uo3GGCTQwp4PvwF5qD53WPNknhYEgldeQWTZHKN3drwQhsdSdtRBL2ehGTyaLauPDCK1hjwympwkR03wTdo7dNA6xyCgFiHoRp8bszZ96J8ELUkBS6nBFFQ9hHlszWBTACRPB-O8jOYt5k5veoBRoGfh511NFM9RXv0vonT8k4aKWQtcKpJqN0skksrf9WKbXyMZNTyVzMQ4OePzlDSnwnDgYqJITvWz5nAnXF8PUzQ1oM0JDaMKtIXWyE8LOz1Jkr_QbwxV0P0-CUhUF2yhTBl23mdWQCXEpGR3VSpaXW3_Bc6u2hqgD6Si3Ira-b460";
    private RequestQueue requestQueue;

    private EditText clientid;
    private Button btnsearch;
    private TextView txtresponseget;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_get);
        init();

        btnsearch.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {

                if(!clientid.getText().toString().equals("")){
                    find(clientid.getText().toString());
                }else{
                    Toast.makeText(GetActivity.this, "Empty fields", Toast.LENGTH_LONG).show();
                }

            }
        });
    }

    private void init(){
        clientid =  (EditText) findViewById(R.id.clientid);
        txtresponseget =  (TextView) findViewById(R.id.txtresponseget);
        btnsearch =  (Button) findViewById(R.id.btnsearch);
    }

    private void find(String codigo){
        requestQueue = Volley.newRequestQueue(this);
        StringRequest request = new StringRequest(
                Request.Method.GET,
                URL +"api/Sale/" + codigo,
                new com.android.volley.Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        int size = response.length();
                        try {
                            txtresponseget.setText(response);

                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                },
                new com.android.volley.Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {

                    }
                }
        ) {
            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {
                Map<String, String>  params = new HashMap<String, String>();
                params.put("Content-Type", "application/json; charset=utf-8");
                params.put("Accept", "application/json");
                params.put("Authorization", "Bearer "+TOKEN);

                return params;
            }
        };
        requestQueue.add(request);
    }

    // Se controla la pulsación del botón atrás
    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == event.KEYCODE_BACK){
            AlertDialog.Builder builder = new AlertDialog.Builder(this);
            builder.setMessage("Do you want to go to the options menu?")
                    .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            goOptions();
                        }
                    })
                    .setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            dialog.dismiss();
                        }
                    });
            builder.show();
        }
        return super.onKeyDown(keyCode, event);
    }

    private void goOptions(){
        Intent i = new Intent(this, OptionActivity.class);
        // bandera para que no se creen nuevas actividades innecesarias
        i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP |
                Intent.FLAG_ACTIVITY_CLEAR_TASK |
                Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(i);
    }


}
