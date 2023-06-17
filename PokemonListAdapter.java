package mavericks.PokeDexDemo;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.List;

public class PokemonListAdapter extends BaseAdapter {

    private Context context;
    private List<PokemonStatsObj> pokemonList;

    public PokemonListAdapter(Context context, List<PokemonStatsObj> pokemonList) {
        this.context = context;
        this.pokemonList = pokemonList;
    }

    @Override
    public int getCount() {
        return pokemonList.size();
    }

    @Override
    public Object getItem(int position) {
        return pokemonList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder;

        if (convertView == null) {
            viewHolder = new ViewHolder();
            viewHolder.imageView = convertView.findViewById(R.id.pokemonImageView);
            viewHolder.nameTextView = convertView.findViewById(R.id.nameTextView);
            viewHolder.heightTextView = convertView.findViewById(R.id.heightTextView);
            viewHolder.weightTextView = convertView.findViewById(R.id.weightTextView);
            viewHolder.moveTextView = convertView.findViewById(R.id.moveTextView);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }

        PokemonStatsObj pokemon = pokemonList.get(position);

        // Set the data for each view
        viewHolder.nameTextView.setText("Name: " + pokemon.getName());
        viewHolder.heightTextView.setText("Height: " + pokemon.getHeight());
        viewHolder.weightTextView.setText("Weight: " + pokemon.getWeight());
        viewHolder.moveTextView.setText("Move: " + pokemon.getMove());
        Picasso.get().load(pokemon.getSpriteUrl()).into(viewHolder.imageView);

        return convertView;
    }

    private static class ViewHolder {
        ImageView imageView;
        TextView nameTextView;
        TextView heightTextView;
        TextView weightTextView;
        TextView moveTextView;
    }
}
