package mavericks.PokeDexDemo;

import android.content.Context;
import android.widget.ImageView;

import com.squareup.picasso.Picasso;

public class PicassoUtil {

    public static void loadImage(Context context, String imageUrl, ImageView imageView) {
        Picasso.get().load(imageUrl).into(imageView);
    }

    public static void loadImageWithPlaceholder(Context context, String imageUrl, int placeholderResId, ImageView imageView) {
        Picasso.get().load(imageUrl).placeholder(placeholderResId).into(imageView);
    }

    // Add more utility methods as needed...

}