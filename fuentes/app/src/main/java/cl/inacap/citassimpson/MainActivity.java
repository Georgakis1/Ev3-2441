package cl.inacap.citassimpson;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Spinner;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.google.gson.Gson;

import org.json.JSONObject;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import cl.inacap.citassimpson.adapters.FrasesAdapter;
import cl.inacap.citassimpson.dto.Frase;

public class MainActivity extends AppCompatActivity {

    Spinner cantidadSpn;
    private List<Frase> frases = new ArrayList<>();
    private ListView frasesListView;
    private FrasesAdapter frasesAdapter;
    private RequestQueue  queue;
    private Spinner CantidadSpn;
    private String url;

    @Override
    protected void onResume() {
        String cantidad= this.CantidadSpn.getSelectedItem().toString();
        this.url = "https://thesimpsonsquoteapi.glitch.me/quotes?count="+cantidad;

        super.onResume();
        queue = Volley.newRequestQueue(this);
        this.frasesListView = findViewById(R.id.fraseslv);
        this.frasesAdapter =new FrasesAdapter(this,R.layout.fraseslv,this.frases);
        frasesListView.setAdapter(frasesAdapter);
        JsonObjectRequest jsonReq = new JsonObjectRequest(Request.Method.GET, url
                , null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                try{
                    frases.clear();
                    Frase[] arreglo = new Gson().fromJson(response.toString(),Frase[].class);
                    frases.addAll(Arrays.asList(arreglo));
                }catch (Exception ex){
                    frases.clear();
                    Log.e("Frases","error de peticion");
                }finally {
                    frasesAdapter.notifyDataSetChanged();
                }

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                frases.clear();
                Log.e("Frases","Error Respues");
                frasesAdapter.notifyDataSetChanged();

            }
        });
        queue.add(jsonReq);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        this.CantidadSpn = findViewById(R.id.cantidadSpn);


        cantidadSpn = (Spinner) findViewById(R.id.cantidadSpn);
        ArrayAdapter<CharSequence> adapter= ArrayAdapter.createFromResource(this,R.array.cantidadArray
                                                                            ,android.R.layout.simple_spinner_item);
        cantidadSpn.setAdapter(adapter);

    }
    public void pedirConsejo(){

    }

}