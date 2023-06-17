package mavericks.PokeDexDemo;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Pokemon {
    @SerializedName("id")
    private int id;

    @SerializedName("name")
    private String name;

    @SerializedName("height")
    private int height;

    @SerializedName("weight")
    private int weight;

    @SerializedName("sprites")
    private Sprites sprites;

    @SerializedName("moves")
    private List<Move> moves;

    // Getters and setters

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

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

    public Sprites getSprites() {
        return sprites;
    }

    public void setSprites(Sprites sprites) {
        this.sprites = sprites;
    }

    public List<Move> getMoves() {
        return moves;
    }

    public void setMoves(List<Move> moves) {
        this.moves = moves;
    }

    public static class Sprites {
        @SerializedName("front_default")
        private String frontDefault;

        // Getters and setters

        public String getFrontDefault() {
            return frontDefault;
        }

        public void setFrontDefault(String frontDefault) {
            this.frontDefault = frontDefault;
        }
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

    public String getFirstMoveName() {
        if (moves != null && !moves.isEmpty()) {
            Move move = moves.get(0);
            if (move != null && move.getMoveDetails() != null) {
                return move.getMoveDetails().getName();
            }
        }
        return "";
    }
}
