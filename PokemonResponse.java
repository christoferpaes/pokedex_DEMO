package mavericks.PokeDexDemo;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

public class PokemonResponse {
    @SerializedName("name")
    private String name;

    @SerializedName("height")
    private int height;

    @SerializedName("weight")
    private int weight;

    @SerializedName("moves")
    private List<Move> moves;

    // Getters and setters

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public int getWeight() {
        return weight;
    }

    public void setWeight(int weight) {
        this.weight = weight;
    }

    public List<Move> getMoves() {
        return moves;
    }

    public void setMoves(List<Move> moves) {
        this.moves = moves;
    }

    public mavericks.PokeDexDemo.Pokemon getPokemon() {
        mavericks.PokeDexDemo.Pokemon pokemon = new mavericks.PokeDexDemo.Pokemon();
        pokemon.setName(name);
        pokemon.setHeight(height);
        pokemon.setWeight(weight);

        List<mavericks.PokeDexDemo.Pokemon.Move> pokemonMoves = new ArrayList<>();
        for (Move move : moves) {
            mavericks.PokeDexDemo.Pokemon.Move pokemonMove = new mavericks.PokeDexDemo.Pokemon.Move();
            Pokemon.MoveDetails moveDetails = new Pokemon.MoveDetails();
            moveDetails.setName(move.getMoveDetails().getName());
            pokemonMove.setMoveDetails(moveDetails);
            pokemonMoves.add(pokemonMove);
        }
        pokemon.setMoves(pokemonMoves);

        return pokemon;
    }

    public static class Move {
        @SerializedName("move")
        private MoveDetails moveDetails;

        // Getters and setters

        public MoveDetails getMoveDetails() {
            return moveDetails;
        }

        public void setMoveDetails(MoveDetails moveDetails) {
            this.moveDetails = moveDetails;
        }
    }

    public static class MoveDetails {
        @SerializedName("name")
        private String name;

        // Getters and setters

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }
    }
}

