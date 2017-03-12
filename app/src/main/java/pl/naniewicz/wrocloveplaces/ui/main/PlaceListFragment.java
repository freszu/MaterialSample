package pl.naniewicz.wrocloveplaces.ui.main;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import pl.naniewicz.wrocloveplaces.R;
import pl.naniewicz.wrocloveplaces.model.Place;
import pl.naniewicz.wrocloveplaces.ui.widget.adapter.PlacesRecyclerViewAdapter;
import pl.naniewicz.wrocloveplaces.util.PlacesGenerator;

public class PlaceListFragment extends Fragment {

    @BindView(R.id.swipeRefreshLayout)
    SwipeRefreshLayout mSwipeRefreshLayout;
    @BindView(R.id.recycler_view)
    RecyclerView mRecyclerView;

    private PlacesRecyclerViewAdapter mPlacesRecyclerViewAdapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_places_list, container, false);
        ButterKnife.bind(this, view);
        setupSwipeToRefresh();
        setupRecyclerView();
        return view;
    }

    private void setupRecyclerView() {
        mPlacesRecyclerViewAdapter = new PlacesRecyclerViewAdapter();
        mPlacesRecyclerViewAdapter.setPlaces(PlacesGenerator.getRandomPlaces(getContext(), 30));

        mRecyclerView.setLayoutManager(new LinearLayoutManager(mRecyclerView.getContext()));
        mRecyclerView.setAdapter(mPlacesRecyclerViewAdapter);
    }

    private void setupSwipeToRefresh() {
        mSwipeRefreshLayout.setColorSchemeResources(R.color.primary, R.color.accent);
        mSwipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                refresh();
            }
        });
    }

    private void refresh() {
        mSwipeRefreshLayout.setRefreshing(true);
        new DummyBackgroundTask(this).execute();
    }

    public void onRefreshComplete(List<Place> places) {
        mSwipeRefreshLayout.setRefreshing(false);
        mPlacesRecyclerViewAdapter.setPlaces(places);
    }
}
