package pl.naniewicz.wrocloveplaces.ui.widget.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

import pl.naniewicz.wrocloveplaces.model.Place;

public class PlacesRecyclerViewAdapter extends RecyclerView.Adapter<PlaceViewHolder> {

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
}
