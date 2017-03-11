package pl.naniewicz.wrocloveplaces.ui.place;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import pl.naniewicz.wrocloveplaces.R;
import pl.naniewicz.wrocloveplaces.model.Place;

public class PlaceDetailActivity extends AppCompatActivity {

    public static final String EXTRA_PLACE = "EXTRA_PLACE";

    @BindView(R.id.toolbar)
    Toolbar mToolbar;
    @BindView(R.id.collapsing_toolbar)
    CollapsingToolbarLayout mCollapsingToolbarLayout;
    @BindView(R.id.backdrop)
    ImageView mImageViewBackdrop;
    @BindView(R.id.text_view_place_description)
    TextView mTextViewPlaceDescription;
    @BindView(R.id.text_view_place_review)
    TextView mTextViewPlaceReview;

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

    private void getPlaceFromIntent() {
        Intent intent = getIntent();
        if (intent != null) {
            mPlace = intent.getParcelableExtra(EXTRA_PLACE);
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_place_detail, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_delete:
                Toast.makeText(this, R.string.menu_delete, Toast.LENGTH_SHORT).show();
                return true;
            case R.id.action_share:
                Toast.makeText(this, R.string.menu_share, Toast.LENGTH_SHORT).show();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @OnClick(R.id.fab)
    public void onFabClick() {
        Toast.makeText(this, R.string.msg_details_fab, Toast.LENGTH_SHORT).show();
    }

    private void setupToolbar() {
        setSupportActionBar(mToolbar);
        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }
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
