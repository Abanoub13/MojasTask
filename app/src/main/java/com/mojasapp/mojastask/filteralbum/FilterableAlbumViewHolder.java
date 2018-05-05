package com.mojasapp.mojastask.filteralbum;

import android.support.v7.widget.RecyclerView.ViewHolder;
import android.view.View;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.mojasapp.mojastask.CustomView.CheckLimitedCheckBox;
import com.mojasapp.mojastask.R;
import com.squareup.picasso.Picasso;

import static com.mojasapp.mojastask.CustomView.CheckLimitedCheckBox.CheckLimit;

public class FilterableAlbumViewHolder extends ViewHolder {

    private TextView titleTextView;
    private TextView idTextView;
    private ImageView albumImageView;
    private CheckLimitedCheckBox filterCheckBox;
    private FilterListener listener;
    private CompoundButton.OnCheckedChangeListener checkListener;

    public FilterableAlbumViewHolder(View itemView, CheckLimit checkLimit) {
        super(itemView);
        titleTextView = itemView.findViewById(R.id.titleTextView);
        idTextView = itemView.findViewById(R.id.idTextView);
        albumImageView = itemView.findViewById(R.id.albumImageView);
        filterCheckBox = itemView.findViewById(R.id.filterCheckBox);
        filterCheckBox.setCheckLimit(checkLimit);
        checkListener = new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if (listener != null) {
                    listener.onIndexFiltered(getAdapterPosition(), b);
                }
            }
        };
    }

    public void setFilterListener(FilterListener listener) {
        this.listener = listener;
    }

    public void bindAlbum(FilterableAlbum album) {
        titleTextView.setText(album.getTitle());
        idTextView.setText(String.valueOf(album.getId()));
        filterCheckBox.setOnCheckedChangeListener(null);
        filterCheckBox.setChecked(album.isSelected());
        filterCheckBox.setOnCheckedChangeListener(checkListener);
        albumImageView.setImageResource(0);
        Picasso.get().
                load(album.getImageUrl()).
                into(albumImageView);
    }

    public interface FilterListener {
        void onIndexFiltered(int index, boolean isChecked);
    }
}
