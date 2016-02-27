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

import pl.naniewicz.wrocloveplaces.model.Place;
import pl.naniewicz.wrocloveplaces.ui.widget.adapter.RecyclerViewAdapter;
import pl.naniewicz.wrocloveplaces.util.PlacesGenerator;

import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Copyright (C) 2016  Rafał Naniewicz and Szymon Kozak
 * <p/>
 * This program is free software; you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation; either version 2 of the License, or
 * (at your option) any later version.
 * <p/>
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 * <p/>
 * You should have received a copy of the GNU General Public License along
 * with this program; if not, write to the Free Software Foundation, Inc.,
 * 51 Franklin Street, Fifth Floor, Boston, MA 02110-1301 USA.
 */

public class PlacesListFragment extends Fragment {

    @Bind(pl.naniewicz.wrocloveplaces.R.id.swipeRefreshLayout) SwipeRefreshLayout mSwipeRefreshLayout;
    @Bind(pl.naniewicz.wrocloveplaces.R.id.recyclerview) RecyclerView mRecyclerView;

    private RecyclerViewAdapter mRecyclerViewAdapter;

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        Log.i("Fragment", "OnAttach");
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(pl.naniewicz.wrocloveplaces.R.layout.fragment_places_list, container, false);
        ButterKnife.bind(this, view);
        setupSwipeToRefresh();
        setupRecyclerViewAdapter(mRecyclerView.getContext());
        setupRecyclerView();
        return view;
    }

    private void setupRecyclerView() {
        mRecyclerView.setLayoutManager(new LinearLayoutManager(mRecyclerView.getContext()));
        mRecyclerView.setAdapter(mRecyclerViewAdapter);
    }

    private void setupRecyclerViewAdapter(Context context) {
        mRecyclerViewAdapter = new RecyclerViewAdapter();
        mRecyclerViewAdapter.setPlaces(PlacesGenerator.getRandomPlaces(context, 30));
    }

    private void setupSwipeToRefresh() {
        mSwipeRefreshLayout.setColorSchemeResources(pl.naniewicz.wrocloveplaces.R.color.primary, pl.naniewicz.wrocloveplaces.R.color.accent);
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
        mRecyclerViewAdapter.setPlaces(places);
    }

    private class DummyBackgroundTask extends AsyncTask<Void, Void, List<Place>> {

        static final int TASK_DURATION_MILLISECONDS = 3 * 1000;

        private Context mApplicationContext;

        public DummyBackgroundTask(Context context) {
            mApplicationContext = context.getApplicationContext();
        }

        @Override
        protected List<Place> doInBackground(Void... params) {
            try {
                Thread.sleep(TASK_DURATION_MILLISECONDS);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return PlacesGenerator.getRandomPlaces(mApplicationContext, 30);
        }

        @Override
        protected void onPostExecute(List<Place> result) {
            onRefreshComplete(result);
        }
    }
}
