package mavericks.PokeDexDemo;

public class PokemonStatsObj {

    String name;
    private int id;
    private static final String IMAGE_BASE_URL = "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/";

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getHeight() {
        return height;
    }

    public void setHeight(String height) {
        this.height = height;
    }

    public String getWeight() {
        return weight;
    }

    public void setWeight(String weight) {
        this.weight = weight;
    }

    public String getMove() {
        return move;
    }

    public void setMove(String move) {
        this.move = move;
    }
    public int getId() {
        return id;
    }
    public String getSpriteUrl() {
        return IMAGE_BASE_URL + id + ".png";
    }

    String height;
    String weight;
    String move;

    public PokemonStatsObj(String name, String height, String weight, String move, int id) {
        this.name = name;
        this.height = height;
        this.weight = weight;
        this.move = move;
        this.id = id;
    }
}
