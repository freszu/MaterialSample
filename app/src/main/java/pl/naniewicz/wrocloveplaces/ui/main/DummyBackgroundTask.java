package pl.naniewicz.wrocloveplaces.ui.main;

import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;

import java.lang.ref.WeakReference;
import java.util.List;

import pl.naniewicz.wrocloveplaces.model.Place;
import pl.naniewicz.wrocloveplaces.util.PlacesGenerator;

class DummyBackgroundTask extends AsyncTask<Void, Void, List<Place>> {

    private static final String TAG = DummyBackgroundTask.class.getSimpleName();
    private static final int TASK_DURATION_MILLISECONDS =  1000;
    private static final int PLACES_AMOUNT = 30;

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
        return PlacesGenerator.getRandomPlaces(mApplicationContext, PLACES_AMOUNT);
    }

    @Override
    protected void onPostExecute(List<Place> result) {
        PlaceListFragment placeListFragment = mPlaceListFragmentWeakReference.get();
        if (placeListFragment != null) {
            placeListFragment.onRefreshComplete(result);
        }
    }
}