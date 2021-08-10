package com.example.payphoneuteq;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
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

import org.json.JSONException;
import org.json.JSONObject;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

public class PostActivity extends AppCompatActivity {

    private String URL = "https://pay.payphonetodoesposible.com/";
    private String TOKEN = "THgalUhRUZfXgKoWMkHSWSSUT6uo3GGCTQwp4PvwF5qD53WPNknhYEgldeQWTZHKN3drwQhsdSdtRBL2ehGTyaLauPDCK1hjwympwkR03wTdo7dNA6xyCgFiHoRp8bszZ96J8ELUkBS6nBFFQ9hHlszWBTACRPB-O8jOYt5k5veoBRoGfh511NFM9RXv0vonT8k4aKWQtcKpJqN0skksrf9WKbXyMZNTyVzMQ4OePzlDSnwnDgYqJITvWz5nAnXF8PUzQ1oM0JDaMKtIXWyE8LOz1Jkr_QbwxV0P0-CUhUF2yhTBl23mdWQCXEpGR3VSpaXW3_Bc6u2hqgD6Si3Ira-b460";
    private RequestQueue requestQueue;

    private EditText phoneNumber, countryCode, clientUserId, reference, responseUrl, amount,
            amountWithTax, amountWithoutTax, tax, clientTransactionId;
    private TextView txtresponse;
    public   Button btnnSell;

     private String stringamount,stringamountWithTax, stringamountWithoutTax, stringTax, stringservice, stringtip;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_post);
        init();

        btnnSell.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {

                if(!phoneNumber.getText().toString().equals("") &&
                        !countryCode.getText().toString().equals("") &&
                        !clientUserId.getText().toString().equals("") &&
                      /*   !reference.getText().toString().equals("") &&
                       !responseUrl.getText().toString().equals("") && */
                        !amount.getText().toString().equals("") &&
                      /*   !amountWithTax.getText().toString().equals("") &&
                        !amountWithoutTax.getText().toString().equals("") &&*/
                        !tax.getText().toString().equals("")
                     /*     !clientTransactionId.getText().toString().equals("")*/)
                {
                    String responseUrl = "http://paystoreCZ.com/confirm.php";
                    stringamount = amount.getText().toString(); ;
                    stringamountWithTax = String.valueOf(Integer.parseInt(amount.getText().toString()) - Integer.parseInt(tax.getText().toString()));
                    // num ale
                    Random r = new Random(); int i1 = r.nextInt(100300 - 300) + 300;

                    String idaleatorio = String.valueOf(i1) + randonnumletra() + randonnumletra()
                            + randonnumletra() + randonnumletra() + randonnumletra() +
                            randonnumletra() + randonnumletra();

                    Log.d("IDALEATORIO",idaleatorio);
                    String jsonpost = "{\n" +
                            "   \"phoneNumber\":\""+phoneNumber.getText()+"\",\n" +
                            "   \"countryCode\":\""+countryCode.getText()+"\",\n" +
                            "   \"clientUserId\":\""+clientUserId.getText()+"\",\n" +
                            "   \"reference\":\""+"none"+"\",\n" +
                            "   \"responseUrl\":\""+responseUrl+"\",\n" +
                            "   \"amount\":\""+stringamount+"\",\n" +
                           "   \"amountWithTax\":\""+stringamountWithTax+"\",\n" +
                            "   \"amountWithoutTax\":\""+"0"+"\",\n" +
                            "   \"tax\":\""+tax.getText()+"\",\n" +
                            "   \"clientTransactionId\":\""+idaleatorio+"\"\n" +
                            "}";
                    Log.d("JSONUSER",jsonpost);
                    Sell(jsonpost);
                }else{
                    Toast.makeText(PostActivity.this, "Empty fields", Toast.LENGTH_LONG).show();
                }

            }
        });
    }

    private void init(){
        phoneNumber =  (EditText) findViewById(R.id.phoneNumber);
        countryCode =  (EditText) findViewById(R.id.countryCode);
        clientUserId =  (EditText) findViewById(R.id.clientUserId);
        amount =  (EditText) findViewById(R.id.amount);
        tax =  (EditText) findViewById(R.id.tax);
        txtresponse = (TextView) findViewById(R.id.txtresponse);
       /* reference =  (EditText) findViewById(R.id.reference);
        responseUrl =  (EditText) findViewById(R.id.responseUrl);

        amountWithTax =  (EditText) findViewById(R.id.amountWithTax);
        amountWithoutTax =  (EditText) findViewById(R.id.amountWithoutTax);

        clientTransactionId =  (EditText) findViewById(R.id.clientTransactionId); */
        btnnSell =  (Button) findViewById(R.id.btnSell);
    }

    private char randonnumletra(){
        String alphabet = "0123456789abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ?/.,";
        Random rnd = new Random();
        char c = alphabet.charAt(rnd.nextInt(alphabet.length()));
        return  c;
    }

    private void Sell(String datajson){

        //Obtención de datos del web service utilzando Volley
        requestQueue = Volley.newRequestQueue(this);
        StringRequest request = new StringRequest(
                Request.Method.POST,URL+"api/Sale",
                new com.android.volley.Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        int size = response.length();
                        JSONObject json_transform = null;
                        try {
                            if (size > 0)
                            {
                                json_transform = new JSONObject(response);
                                Log.d("response",response);
                                txtresponse.setText(response);
                                //  json_transform.getString("flag");
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                },
                new com.android.volley.Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Log.d("Error.Response", String.valueOf(error));
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
            @Override
            public byte[] getBody() throws AuthFailureError {
                try {
                    return datajson == null ? "{}".getBytes("utf-8") : datajson.getBytes("utf-8");
                } catch (UnsupportedEncodingException uee) {

                    return null;
                }
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
