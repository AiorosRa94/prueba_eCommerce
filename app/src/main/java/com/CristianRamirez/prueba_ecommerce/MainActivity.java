package com.CristianRamirez.prueba_ecommerce;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import static xdroid.toaster.Toaster.toast;

public class MainActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private adaptador madaptador;
    private ArrayList<generadorProductos> generadorProd;
    private RequestQueue requestQueue;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerView = findViewById(R.id.contendorProductos);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        final TextView txtBusqueda = findViewById(R.id.tfProductoBusqueda);
        generadorProd = new ArrayList<>();
        requestQueue = Volley.newRequestQueue(this);

        txtBusqueda.setOnKeyListener(new View.OnKeyListener() {
            public boolean onKey(View v, int keyCode, KeyEvent event) {
                // If the event is a key-down event on the "enter" button
                if ((event.getAction() == KeyEvent.ACTION_DOWN) &&
                        (keyCode == KeyEvent.KEYCODE_ENTER)) {
                    extraerInformacion("https://00672285.us-south.apigw.appdomain.cloud/demo-gapsi/search?&query="+txtBusqueda.getText());
                    return true;
                }
                return false;
            }
        });
    }


    private void extraerInformacion( String url) {
        JsonObjectRequest request=new JsonObjectRequest(Request.Method.GET, url, null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        try {
                            System.out.println("aqui");
                            System.out.println(response);
                            JSONArray jsonArray = response.getJSONArray("items");
                            for(int i=0;i<jsonArray.length();i++) {
                                JSONObject jsonobjdatos = jsonArray.getJSONObject(i);
                                String nombreProducto = jsonobjdatos.getString("title");
                                String imgProducto = jsonobjdatos.getString("image");
                                String precio = jsonobjdatos.getString("price");


                                generadorProd.add(new generadorProductos(nombreProducto,imgProducto,Float.parseFloat(precio)));
                            }

                            madaptador = new adaptador(MainActivity.this,generadorProd);
                            recyclerView.setAdapter(madaptador);
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                error.printStackTrace();
            }
        }){

            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {
                HashMap<String, String> headers = new HashMap<String, String>();
                //headers.put("Content-Type", "application/json");
                headers.put("X-IBM-Client-Id", "adb8204d-d574-4394-8c1a-53226a40876e");
                return headers;
            }
        };


        requestQueue.add(request);
    }
}