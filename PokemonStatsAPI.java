package mavericks.PokeDexDemo;

import android.os.AsyncTask;
import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import javax.net.ssl.HttpsURLConnection;

public class PokemonStatsAPI {

  static final String Base_URL = " https://pokeapi.co/api/v2/pokemon";


   // static final String API_KEY= ""; // bad practice


    static final String TAG = "PokemonAPI_Tag";

    MainActivity mainActivity;


        public PokemonStatsAPI(MainActivity mainActivity){


            this.mainActivity = mainActivity;
        }

        public void fetchPokemonStats() {

            //need for URL for the request
            String url = constructStatsList();
            url+= "/?offset=0&limit=1154" ;

            Log.d(TAG, "PokemonStats"+ url);


            //android will not let you do any network activity
            // on the main ui thread
            // define a subclass a AsyncTask
            //asynchronous  doesnt wait/block stream
        fetchPokemonStatsAPI asynTask= new fetchPokemonStatsAPI();
        asynTask.execute(url);




        }

public String constructStatsList() {
            String url = Base_URL;


            return url;
}

class fetchPokemonStatsAPI extends AsyncTask<String,Void, List<mavericks.PokeDexDemo.PokemonStatsObj>> {

//executes on background thread
    //cannot update UI thread
    //unless there is an appropiate AsyncTask method
    //1.open the url request
    //2.download the JSON RESPONSE
    // 3. pasrse the JSON RESPONSE in to the

    @Override
    protected List<mavericks.PokeDexDemo.PokemonStatsObj> doInBackground(String... strings) {
      // string.... is called var arg, treat like an area.
       String url = strings[0];

       List<mavericks.PokeDexDemo.PokemonStatsObj> pokemonStatsObjList = new ArrayList<>();
        try {
            URL urlob = new URL(url);
            HttpsURLConnection urlConnection= (HttpsURLConnection) urlob.openConnection();
            String jsonResult = "";
            InputStream in = urlConnection.getInputStream();
            InputStreamReader reader = new InputStreamReader(in);
            int data = reader.read();
            while( data != -1) {
                jsonResult += (char)data;
                data = reader.read();
            }
            Log.d(TAG, "doInBackground: " + jsonResult);

            JSONObject jsonObject = new JSONObject(jsonResult);

            //grab  the "Root" photos json Object
            JSONObject photosObject = jsonObject.getJSONObject("count");

            JSONArray photosArray = photosObject.getJSONArray("results");




        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        }


        return pokemonStatsObjList;
    }
}
}
