package mavericks.PokeDexDemo;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.os.AsyncTask;
import android.os.Bundle;

import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import mavericks.PokeDexDemo.databinding.ActivityMainBinding;
import com.squareup.picasso.Picasso;
import com.squareup.picasso.Target;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private ActivityMainBinding binding;
    private EditText searchEditText;
    private Button searchButton, backButton, forwardButton, addToListButton, openListButton;
    private TextView nameTextView, heightTextView, weightTextView, moveTextView;
    private ImageView spriteImageView;
    private List<ImageView> pokemonImageViews;
    private static final String API_BASE_URL = "https://pokeapi.co/api/v2/pokemon/";
    private static final String IMAGE_BASE_URL = "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/";
    private int currentOffset = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        searchEditText = binding.searchEditText;
        searchButton = binding.searchButton;
        backButton = binding.backButton;
        forwardButton = binding.forwardButton;
        addToListButton = binding.Add;
        openListButton = binding.listButton;
        nameTextView = binding.nameTextView;
        heightTextView = binding.heightTextView;
        weightTextView = binding.weightTextView;
        moveTextView = binding.moveTextView;
        spriteImageView = binding.spriteImageView;

        pokemonImageViews = new ArrayList<>();
        pokemonImageViews.add(binding.pokemonImageView1);
        pokemonImageViews.add(binding.pokemonImageView2);
        pokemonImageViews.add(binding.pokemonImageView3);
        // Add more ImageView tiles as needed

        searchButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String searchTerm = searchEditText.getText().toString().trim();
                if (!searchTerm.isEmpty()) {
                    String url = API_BASE_URL + searchTerm.toLowerCase();
                    new FetchPokemonDataTask().execute(url);
                }
            }
        });

        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                currentOffset -= 15;
                if (currentOffset < 0) {
                    currentOffset = 0;
                }
                loadPokemonImages();
            }
        });

        forwardButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                currentOffset += 15;
                loadPokemonImages();
            }
        });

        addToListButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addPokemonToList();
            }
        });

        openListButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, SecondActivity.class);
                startActivity(intent);
            }
        });

        loadPokemonImages();
    }

    private void loadPokemonImages() {
        for (int i = 0; i < pokemonImageViews.size(); i++) {
            int pokemonId = currentOffset + i + 1;
            String imageUrl = IMAGE_BASE_URL + pokemonId + ".png";
            ImageView imageView = pokemonImageViews.get(i);
            imageView.setTag(pokemonId); // Set the tag value as the Pokemon ID
            Picasso.get().load(imageUrl).into(imageView);
            imageView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int selectedPokemonId = (int) v.getTag();
                    String url = API_BASE_URL + selectedPokemonId;
                    new FetchPokemonDataTask().execute(url);
                }
            });
        }
    }

    private void displayPokemonStats(PokemonStatsObj pokemonStats) {
        nameTextView.setText("Name: " + pokemonStats.getName());
        heightTextView.setText("Height: " + pokemonStats.getHeight());
        weightTextView.setText("Weight: " + pokemonStats.getWeight());
        moveTextView.setText("Move: " + pokemonStats.getMove());

        // Load and display the sprite image
        String spriteUrl = IMAGE_BASE_URL + pokemonStats.getId() + ".png";
        Picasso.get().load(spriteUrl).into(new Target() {
            @Override
            public void onBitmapLoaded(Bitmap bitmap, Picasso.LoadedFrom from) {
                spriteImageView.setImageBitmap(bitmap);
            }

            @Override
            public void onBitmapFailed(Exception e, Drawable errorDrawable) {
                // Handle the failure to load the sprite image
            }

            @Override
            public void onPrepareLoad(Drawable placeHolderDrawable) {
                // Handle the placeholder or loading state of the sprite image
            }
        });
    }

    private void addPokemonToList() {
        Intent intent = new Intent(MainActivity.this, SecondActivity.class);
        Integer selectedPokemonId = (Integer) spriteImageView.getTag();
        if (selectedPokemonId != null) {
            intent.putExtra("pokemonId", selectedPokemonId);
            startActivity(intent);
        } else {
            Log.e("MainActivity", "Selected Pokemon ID is null");
        }
    }

    class FetchPokemonDataTask extends AsyncTask<String, Void, PokemonStatsObj> {

        @Override
        protected PokemonStatsObj doInBackground(String... urls) {
            String urlString = urls[0];
            try {
                URL url = new URL(urlString);
                HttpURLConnection connection = (HttpURLConnection) url.openConnection();
                connection.setRequestMethod("GET");
                connection.connect();

                int responseCode = connection.getResponseCode();
                if (responseCode == HttpURLConnection.HTTP_OK) {
                    BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
                    StringBuilder response = new StringBuilder();
                    String line;
                    while ((line = reader.readLine()) != null) {
                        response.append(line);
                    }
                    reader.close();
                    return parsePokemonStats(response.toString());
                } else {
                    Log.e("FetchPokemonDataTask", "HTTP error code: " + responseCode);
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
            return null;
        }

        @Override
        protected void onPostExecute(PokemonStatsObj pokemonStats) {
            if (pokemonStats != null) {
                displayPokemonStats(pokemonStats);
            } else {
                Log.e("FetchPokemonDataTask", "Failed to fetch Pokemon data");
            }
        }

        private PokemonStatsObj parsePokemonStats(String json) {
            try {
                JSONObject jsonObject = new JSONObject(json);
                String name = jsonObject.getString("name");
                int height = jsonObject.getInt("height");
                int weight = jsonObject.getInt("weight");
                JSONArray movesArray = jsonObject.getJSONArray("moves");
                String move = movesArray.getJSONObject(0).getJSONObject("move").getString("name");
                int id = jsonObject.getInt("id");
                return new PokemonStatsObj(name, String.valueOf(height), String.valueOf(weight), move, id);
            } catch (JSONException e) {
                e.printStackTrace();
            }
            return null;
        }
    }
}
