package pl.naniewicz.wrocloveplaces.ui.main;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.NavigationView;
import android.support.design.widget.Snackbar;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import pl.naniewicz.wrocloveplaces.R;
import pl.naniewicz.wrocloveplaces.ui.form.FormActivity;
import pl.naniewicz.wrocloveplaces.ui.widget.adapter.ViewPagerAdapter;

public class MainActivity extends AppCompatActivity {

    @BindView(R.id.drawer_layout)
    DrawerLayout mDrawerLayout;
    @BindView(R.id.toolbar)
    Toolbar mToolbar;
    @BindView(R.id.navigation_view)
    NavigationView mNavigationView;
    @BindView(R.id.viewpager)
    ViewPager mViewPager;
    @BindView(R.id.fab)
    FloatingActionButton mFloatingActionButton;
    @BindView(R.id.tab_layout)
    TabLayout mTabLayout;

    private ActionBarDrawerToggle mDrawerToggle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        setupToolbar();
        setupDrawerContent();
        setupViewPager();
        mTabLayout.setupWithViewPager(mViewPager);
    }

    @Override
    protected void onPostCreate(final Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        mDrawerToggle.syncState();
    }

    private void setupToolbar() {
        setSupportActionBar(mToolbar);
        mDrawerToggle = new ActionBarDrawerToggle(this, mDrawerLayout, mToolbar, R.string.drawer_open, R.string.drawer_close);
        mDrawerLayout.addDrawerListener(mDrawerToggle);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == R.id.action_settings) {
            Toast.makeText(this, R.string.menu_settings, Toast.LENGTH_SHORT).show();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    private void setupViewPager() {
        ViewPagerAdapter adapter = new ViewPagerAdapter(getSupportFragmentManager(), 6);
        adapter.addFragment(new PlaceListFragment(), "Category 1");
        adapter.addFragment(new PlaceListFragment(), "Category 2");
        adapter.addFragment(new PlaceListFragment(), "Category 3");
        adapter.addFragment(new PlaceListFragment(), "Category 4");
        adapter.addFragment(new PlaceListFragment(), "Category 5");
        adapter.addFragment(new PlaceListFragment(), "Category 6");
        mViewPager.setAdapter(adapter);
        mViewPager.setOffscreenPageLimit(adapter.getCount() - 1);
    }

    private void setupDrawerContent() {
        mNavigationView.setNavigationItemSelectedListener(
                new NavigationView.OnNavigationItemSelectedListener() {
                    @Override
                    public boolean onNavigationItemSelected(MenuItem menuItem) {
                        switch (menuItem.getItemId()) {
                            case R.id.activity_form:
                                mDrawerLayout.closeDrawers();
                                FormActivity.start(MainActivity.this);
                                break;
                            case R.id.activity_map:
                                Toast.makeText(MainActivity.this, R.string.map, Toast.LENGTH_SHORT).show();
                                break;
                            case R.id.activity_settings:
                                Toast.makeText(MainActivity.this, R.string.settings, Toast.LENGTH_SHORT).show();
                                break;
                        }
                        return false;
                    }
                });
    }

    @OnClick(R.id.fab)
    void onFabClick() {
        showSnackbar();
    }

    private void showSnackbar() {
        Snackbar.make(mFloatingActionButton, R.string.msg_snackbar, Snackbar.LENGTH_LONG)
                .setAction(R.string.action, getSnackbarAction()).show();
    }

    private View.OnClickListener getSnackbarAction() {
        return new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(v.getContext(), R.string.msg_snackbar_action_string, Toast.LENGTH_SHORT).show();
            }
        };
    }
}