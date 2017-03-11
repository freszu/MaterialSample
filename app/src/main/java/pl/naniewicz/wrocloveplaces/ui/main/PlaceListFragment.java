package pl.naniewicz.wrocloveplaces.ui.main;

import android.content.Context;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
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

    private static final String TAG = PlaceListFragment.class.getSimpleName();

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
        setupRecyclerViewAdapter(mRecyclerView.getContext());
        setupRecyclerView();
        return view;
    }

    private void setupRecyclerView() {
        mRecyclerView.setLayoutManager(new LinearLayoutManager(mRecyclerView.getContext()));
        mRecyclerView.setAdapter(mPlacesRecyclerViewAdapter);
    }

    private void setupRecyclerViewAdapter(Context context) {
        mPlacesRecyclerViewAdapter = new PlacesRecyclerViewAdapter();
        mPlacesRecyclerViewAdapter.setPlaces(PlacesGenerator.getRandomPlaces(context, 30));
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
        new DummyBackgroundTask(mRecyclerView.getContext()).execute();
    }

    private void onRefreshComplete(List<Place> places) {
        mSwipeRefreshLayout.setRefreshing(false);
        mPlacesRecyclerViewAdapter.setPlaces(places);
    }

    private class DummyBackgroundTask extends AsyncTask<Void, Void, List<Place>> {

        static final int TASK_DURATION_MILLISECONDS = 3 * 1000;

        private Context mApplicationContext;

        DummyBackgroundTask(Context context) {
            mApplicationContext = context.getApplicationContext();
        }

        @Override
        protected List<Place> doInBackground(Void... params) {
            try {
                Thread.sleep(TASK_DURATION_MILLISECONDS);
            } catch (InterruptedException e) {
                Log.e(TAG, e.getLocalizedMessage());
            }
            return PlacesGenerator.getRandomPlaces(mApplicationContext, 30);
        }

        @Override
        protected void onPostExecute(List<Place> result) {
            onRefreshComplete(result);
        }
    }
}
