package pl.naniewicz.wrocloveplaces.ui.widget.adapter;

import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import pl.naniewicz.wrocloveplaces.R;
import pl.naniewicz.wrocloveplaces.model.Place;
import pl.naniewicz.wrocloveplaces.ui.place.PlaceDetailActivity;

/**
 * Copyright (C) 2016  Rafał Naniewicz and Szymon Kozak
 * This program is free software; you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation; either version 2 of the License, or
 * (at your option) any later version.
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 * You should have received a copy of the GNU General Public License along
 * with this program; if not, write to the Free Software Foundation, Inc.,
 * 51 Franklin Street, Fifth Floor, Boston, MA 02110-1301 USA.
 */

public class PlacesRecyclerViewAdapter extends RecyclerView.Adapter {

    private List<Place> mPlaces;

    public PlacesRecyclerViewAdapter() {
        mPlaces = new ArrayList<>();
    }

    public static class PlaceViewHolder extends RecyclerView.ViewHolder {

        @Bind(R.id.place_photo) ImageView mImageViewPlacePhoto;
        @Bind(R.id.place_place_name) TextView mTextViewPlaceName;
        View mItemView;

        public PlaceViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
            mItemView = itemView;
        }
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.recycler_view_item_place, parent, false);
        return new PlaceViewHolder(view);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, final int position) {
        final PlaceViewHolder placeViewHolder = (PlaceViewHolder) holder;
        placeViewHolder.mTextViewPlaceName.setText(mPlaces.get(position).getPlaceName());
        Picasso.with(placeViewHolder.mImageViewPlacePhoto.getContext())
                .load(mPlaces.get(position).getDrawableRes())
                .fit()
                .centerCrop()
                .into(placeViewHolder.mImageViewPlacePhoto);
        placeViewHolder.mItemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(v.getContext(), PlaceDetailActivity.class);
                intent.putExtra("EXTRA_PLACE", mPlaces.get(position));
                v.getContext().startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return mPlaces.size();
    }

    public void setPlaces(List<Place> places) {
        mPlaces = places;
        notifyDataSetChanged();
    }
}
