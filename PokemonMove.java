package mavericks.PokeDexDemo;

import com.google.gson.annotations.SerializedName;

public class PokemonMove {
    @SerializedName("move")
    private MoveDetails moveDetails;

    // Getters and setters


    public void setMoveDetails(MoveDetails moveDetails) {
        this.moveDetails = moveDetails;
    }

    public String getName() {
        return moveDetails != null ? moveDetails.getName() : null;
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

    public static class Move {
        private MoveDetails moveDetails;

        public MoveDetails getMoveDetails() {
            return moveDetails;
        }

        public void setMoveDetails(MoveDetails moveDetails) {
            this.moveDetails = moveDetails;
        }
    }

}

