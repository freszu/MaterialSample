package pl.naniewicz.wrocloveplaces.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import butterknife.Bind;
import butterknife.ButterKnife;
import pl.naniewicz.wrocloveplaces.R;
import pl.naniewicz.wrocloveplaces.model.Place;

public class PlaceDetailActivity extends AppCompatActivity {

    public static final String EXTRA_PLACE = "EXTRA_PLACE";

    @Bind(R.id.toolbar) Toolbar mToolbar;
    @Bind(R.id.collapsing_toolbar) CollapsingToolbarLayout mCollapsingToolbarLayout;
    @Bind(R.id.backdrop) ImageView mImageViewBackdrop;
    @Bind(R.id.text_view_place_description) TextView mTextViewPlaceDescription;
    @Bind(R.id.text_view_place_review) TextView mTextViewPlaceReview;

    private Place mPlace;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_place_detail);
        ButterKnife.bind(this);
        getPlaceFromIntent();
        setupToolbar();
        setContent();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main_activity_actions, menu);
        return true;
    }

    private void getPlaceFromIntent() {
        Intent intent = getIntent();
        if (intent != null) {
            mPlace = intent.getParcelableExtra(EXTRA_PLACE);
        }
    }

    private void setupToolbar() {
        setSupportActionBar(mToolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        mCollapsingToolbarLayout.setTitle(mPlace.getPlaceName());
        loadBackdrop();
    }

    private void loadBackdrop() {
        Picasso.with(this).load(mPlace.getDrawableRes()).centerCrop().fit().into(mImageViewBackdrop);
    }

    private void setContent() {
        mTextViewPlaceDescription.setText(mPlace.getDescription());
        mTextViewPlaceReview.setText(mPlace.getReview());
    }

}
