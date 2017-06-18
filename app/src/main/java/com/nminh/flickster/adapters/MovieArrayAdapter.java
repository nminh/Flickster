package com.nminh.flickster.adapters;

import android.content.Context;
import android.content.res.Configuration;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.nminh.flickster.R;
import com.nminh.flickster.models.Movie;
import com.squareup.picasso.Picasso;

import java.util.List;

/**
 * Created by nminh on 6/17/17.
 */

public class MovieArrayAdapter extends ArrayAdapter<Movie> {

    public MovieArrayAdapter(Context context, List<Movie> movies) {
        super(context, android.R.layout.simple_list_item_1, movies);
    }

    // View lookup cache
    private static class ViewHolder {
        TextView tvTitle;
        TextView tvOverview;
        ImageView ivImage;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        int orientation = getContext().getResources().getConfiguration().orientation;

        // get the data item for position
        Movie movie = getItem(position);

        // check the existing view being reused
        ViewHolder viewHolder; // view lookup cache stored in tag
        if (convertView == null) {
            viewHolder = new ViewHolder();
            LayoutInflater inflater = LayoutInflater.from(getContext());
            convertView = inflater.inflate(R.layout.item_movie, parent, false);
            viewHolder.tvTitle = (TextView) convertView.findViewById(R.id.tvTitle);
            viewHolder.tvOverview = (TextView) convertView.findViewById(R.id.tvOverview);
            viewHolder.ivImage = (ImageView) convertView.findViewById(R.id.ivMovieItem);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }

        viewHolder.ivImage.setImageResource(0);

        // populate data
        viewHolder.tvTitle.setText(movie.getOriginalTitle());
        viewHolder.tvOverview.setText(movie.getOverview());

        if (orientation == Configuration.ORIENTATION_PORTRAIT) {
            Picasso.with(getContext()).load(movie.getPosterPath(orientation))
                    .fit().centerCrop()
                    .placeholder(R.drawable.flicker_w480_land)
                    .error(R.drawable.flicker_not_found_r2)
                    .into(viewHolder.ivImage);
        } else{ //LANDSCAPE
            Picasso.with(getContext()).load(movie.getPosterPath(orientation))
                    .fit().centerCrop()
                    .placeholder(R.drawable.flick_w320_portrait)
                    .error(R.drawable.flicker_not_found_r2)
                    .into(viewHolder.ivImage);

        }

        // return the view
        return convertView;
    }
}
