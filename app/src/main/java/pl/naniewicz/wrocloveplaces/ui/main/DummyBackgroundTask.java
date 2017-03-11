package pl.naniewicz.wrocloveplaces.ui.main;

import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;

import java.lang.ref.WeakReference;
import java.util.List;

import pl.naniewicz.wrocloveplaces.model.Place;
import pl.naniewicz.wrocloveplaces.util.PlacesGenerator;

import static android.content.ContentValues.TAG;

/**
 * Created by Szymon Kozak on 11.03.2017.
 */

class DummyBackgroundTask extends AsyncTask<Void, Void, List<Place>> {

    static final int TASK_DURATION_MILLISECONDS = 3 * 1000;

    private WeakReference<PlaceListFragment> mPlaceListFragmentWeakReference;
    private Context mApplicationContext;

    DummyBackgroundTask(PlaceListFragment placeListFragment) {
        mPlaceListFragmentWeakReference = new WeakReference<>(placeListFragment);
        mApplicationContext = placeListFragment.getContext().getApplicationContext();
    }

    @Override
    protected List<Place> doInBackground(Void... params) {
        try {
            Thread.sleep(TASK_DURATION_MILLISECONDS);
        } catch (InterruptedException e) {
            Log.e(TAG, e.getMessage());
            Thread.currentThread().interrupt();
        }
        return PlacesGenerator.getRandomPlaces(mApplicationContext, 30);
    }

    @Override
    protected void onPostExecute(List<Place> result) {
        PlaceListFragment placeListFragment = mPlaceListFragmentWeakReference.get();
        if (placeListFragment != null) {
            placeListFragment.onRefreshComplete(result);
        }
    }
}