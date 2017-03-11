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

import butterknife.BindView;
import butterknife.ButterKnife;
import pl.naniewicz.wrocloveplaces.R;
import pl.naniewicz.wrocloveplaces.model.Place;
import pl.naniewicz.wrocloveplaces.ui.place.PlaceDetailActivity;

public class PlacesRecyclerViewAdapter extends RecyclerView.Adapter {

    private List<Place> mPlaces;

    public PlacesRecyclerViewAdapter() {
        mPlaces = new ArrayList<>();
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
        placeViewHolder.mItemView.setOnClickListener(getOnViewHolderClickListener(position));
    }

    private View.OnClickListener getOnViewHolderClickListener(final int position) {
        return new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(v.getContext(), PlaceDetailActivity.class);
                intent.putExtra(PlaceDetailActivity.EXTRA_PLACE, mPlaces.get(position));
                v.getContext().startActivity(intent);
            }
        };
    }

    @Override
    public int getItemCount() {
        return mPlaces.size();
    }

    public void setPlaces(List<Place> places) {
        mPlaces = places;
        notifyDataSetChanged();
    }

    static class PlaceViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.place_photo)
        ImageView mImageViewPlacePhoto;
        @BindView(R.id.place_place_name)
        TextView mTextViewPlaceName;
        View mItemView;

        PlaceViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
            mItemView = itemView;
        }
    }
}
