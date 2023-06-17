package mavericks.PokeDexDemo;

import com.google.gson.annotations.SerializedName;

public  class Move {
    @SerializedName("move")
    private PokemonMove.MoveDetails moveDetails;

    // Getters and setters

    public PokemonMove.MoveDetails getMoveDetails() {
        return moveDetails;
    }

    public void setMoveDetails(PokemonMove.MoveDetails moveDetails) {
        this.moveDetails = moveDetails;
    }
}
