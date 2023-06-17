package mavericks.PokeDexDemo;

import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.target.CustomTarget;
import com.bumptech.glide.request.transition.Transition;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class PokemonFragment extends Fragment {

    private static final String ARG_POKEMON_NAME = "pokemonName";
    private static final String IMAGE_BASE_URL = "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/";

    private ImageView pokemonImageView;
    private TextView nameTextView;
    private TextView heightTextView;
    private TextView weightTextView;
    private TextView moveTextView;

    private String pokemonName;

    public static PokemonFragment newInstance(String pokemonName) {
        PokemonFragment fragment = new PokemonFragment();
        Bundle args = new Bundle();
        args.putString(ARG_POKEMON_NAME, pokemonName);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            pokemonName = getArguments().getString(ARG_POKEMON_NAME);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_pokemon, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        pokemonImageView = view.findViewById(R.id.pokemonImageView);
        nameTextView = view.findViewById(R.id.nameTextView);
        heightTextView = view.findViewById(R.id.heightTextView);
        weightTextView = view.findViewById(R.id.weightTextView);
        moveTextView = view.findViewById(R.id.moveTextView);

        // Set the Pokemon details
        setPokemonDetails();
    }

    private void setPokemonDetails() {
        // Fetch Pokemon details based on the name and populate the views
        mavericks.PokeDexDemo.PokeApiService pokeApiService =mavericks.PokeDexDemo.PokeApiService.retrofit.create(mavericks.PokeDexDemo.PokeApiService.class);
        Call<mavericks.PokeDexDemo.PokemonResponse> call = pokeApiService.getPokemonByName(pokemonName);
        call.enqueue(new Callback< mavericks.PokeDexDemo.PokemonResponse>() {
            @Override
            public void onResponse(Call<mavericks.PokeDexDemo.PokemonResponse> call, Response<mavericks.PokeDexDemo.PokemonResponse> response) {
                if (response.isSuccessful()) {
                    mavericks.PokeDexDemo.PokemonResponse pokemonResponse = response.body();
                    if (pokemonResponse != null) {
                        mavericks.PokeDexDemo.Pokemon pokemon = pokemonResponse.getPokemon();

                        String name = pokemon.getName();
                        String height = "Height: " + pokemon.getHeight();
                        String weight = "Weight: " + pokemon.getWeight();
                        String move = "Move: " + getFirstMoveName(pokemon.getMoves());

                        nameTextView.setText(name);
                        heightTextView.setText(height);
                        weightTextView.setText(weight);
                        moveTextView.setText(move);

                        // Load and set the Pokemon image
                        mavericks.PokeDexDemo.Pokemon.Sprites sprites = pokemon.getSprites();
                        if (sprites != null) {
                            String imageUrl = sprites.getFrontDefault();
                            if (imageUrl != null) {
                                Glide.with(requireContext())
                                        .asBitmap()
                                        .load(imageUrl)
                                        .into(new CustomTarget<Bitmap>() {
                                            @Override
                                            public void onResourceReady(@NonNull Bitmap resource, @Nullable Transition<? super Bitmap> transition) {
                                                pokemonImageView.setImageBitmap(resource);
                                            }

                                            @Override
                                            public void onLoadCleared(@Nullable Drawable placeholder) {
                                                // Optional: Perform additional cleanup here if needed.
                                            }
                                        });
                            }
                        }
                    }
                } else {
                    // Handle the case when the response is not successful
                }
            }

            @Override
            public void onFailure(Call<mavericks.PokeDexDemo.PokemonResponse> call, Throwable t) {
                // Handle error
            }
        });
    }

    private String getFirstMoveName(List<mavericks.PokeDexDemo.Pokemon.Move> moves) {
        if (moves != null && !moves.isEmpty()) {
            mavericks.PokeDexDemo.Pokemon.Move move = moves.get(0);
            if (move != null && move.getMoveDetails() != null) {
                return move.getMoveDetails().getName();
            }
        }
        return "";
    }
}