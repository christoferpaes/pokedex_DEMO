package mavericks.PokeDexDemo;

import android.graphics.Color;
import android.os.Bundle;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import java.util.List;

public class SecondActivity extends AppCompatActivity {

    private LinearLayout pokemonLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);

        pokemonLayout = findViewById(R.id.pokemonLayout);

        // Retrieve the list of added Pokemon IDs from the Intent
        List<Integer> pokemonIds = getIntent().getIntegerArrayListExtra("pokemonIds");

        // Loop through each Pokemon ID and create a CardView for each
        for (Integer pokemonId : pokemonIds) {
            createPokemonCardView(pokemonId);
        }
    }

    private void createPokemonCardView(int pokemonId) {
        // Create a new CardView
        CardView cardView = new CardView(this);
        cardView.setLayoutParams(new CardView.LayoutParams(
                CardView.LayoutParams.MATCH_PARENT,
                CardView.LayoutParams.WRAP_CONTENT
        ));
        cardView.setCardBackgroundColor(Color.WHITE);
        cardView.setCardElevation(4);
        cardView.setRadius(8);

        // Create TextView to display Pokemon ID
        TextView textView = new TextView(this);
        textView.setLayoutParams(new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT,
                LinearLayout.LayoutParams.WRAP_CONTENT
        ));
        textView.setText("Pokemon ID: " + pokemonId);
        textView.setPadding(16, 16, 16, 16);

        // Add TextView to CardView
        cardView.addView(textView);

        // Add CardView to LinearLayout
        pokemonLayout.addView(cardView);
    }
}

