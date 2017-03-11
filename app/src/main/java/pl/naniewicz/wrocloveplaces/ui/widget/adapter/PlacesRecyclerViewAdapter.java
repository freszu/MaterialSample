package pl.naniewicz.wrocloveplaces.ui.widget.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import pl.naniewicz.wrocloveplaces.R;
import pl.naniewicz.wrocloveplaces.model.Place;
import pl.naniewicz.wrocloveplaces.ui.place.PlaceDetailActivity;

public class PlacesRecyclerViewAdapter extends RecyclerView.Adapter<PlacesRecyclerViewAdapter.PlaceViewHolder> {

    private final List<Place> mPlaces;

    public PlacesRecyclerViewAdapter() {
        mPlaces = new ArrayList<>();
    }

    @Override
    public PlaceViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new PlaceViewHolder(parent);
    }

    @Override
    public void onBindViewHolder(PlaceViewHolder holder, int position) {
        holder.bind(mPlaces.get(position));
    }

    @Override
    public int getItemCount() {
        return mPlaces.size();
    }

    public void setPlaces(List<Place> places) {
        mPlaces.clear();
        mPlaces.addAll(places);
        notifyDataSetChanged();
    }

    static class PlaceViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.place_photo)
        ImageView mImageViewPlacePhoto;
        @BindView(R.id.place_place_name)
        TextView mTextViewPlaceName;

        private Place mPlace;

        PlaceViewHolder(ViewGroup parent) {
            super(LayoutInflater.from(parent.getContext()).inflate(R.layout.recycler_view_item_place, parent, false));
            ButterKnife.bind(this, itemView);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    PlaceDetailActivity.start(itemView.getContext(), mPlace);
                }
            });
        }

        void bind(Place place) {
            mPlace = place;
            mTextViewPlaceName.setText(place.getPlaceName());
            Picasso.with(mImageViewPlacePhoto.getContext())
                    .load(place.getDrawableRes())
                    .fit()
                    .centerCrop()
                    .into(mImageViewPlacePhoto);
        }

    }
}
