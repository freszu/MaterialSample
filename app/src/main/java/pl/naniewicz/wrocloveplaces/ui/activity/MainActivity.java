package pl.naniewicz.wrocloveplaces.ui.activity;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.NavigationView;
import android.support.design.widget.Snackbar;
import android.support.design.widget.TabLayout;
import android.support.v4.view.GravityCompat;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import pl.naniewicz.wrocloveplaces.R;
import pl.naniewicz.wrocloveplaces.ui.adapter.ViewPagerAdapter;
import pl.naniewicz.wrocloveplaces.ui.fragment.PlacesListFragment;

/**
 *  Copyright (C) 2016  Rafał Naniewicz and Szymon Kozak

 *  This program is free software; you can redistribute it and/or modify
 *  it under the terms of the GNU General Public License as published by
 *  the Free Software Foundation; either version 2 of the License, or
 *  (at your option) any later version.

 *  This program is distributed in the hope that it will be useful,
 *  but WITHOUT ANY WARRANTY; without even the implied warranty of
 *  MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 *  GNU General Public License for more details.

 *  You should have received a copy of the GNU General Public License along
 *  with this program; if not, write to the Free Software Foundation, Inc.,
 *  51 Franklin Street, Fifth Floor, Boston, MA 02110-1301 USA.
 */

public class MainActivity extends AppCompatActivity {

    @Bind(R.id.drawer_layout) DrawerLayout mDrawerLayout;
    @Bind(R.id.toolbar) Toolbar mToolbar;
    @Bind(R.id.navigation_view) NavigationView mNavigationView;
    @Bind(R.id.viewpager) ViewPager mViewPager;
    @Bind(R.id.fab) FloatingActionButton mFloatingActionButton;
    @Bind(R.id.tab_layout) TabLayout mTabLayout;
    private ActionBarDrawerToggle mDrawerToggle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(pl.naniewicz.wrocloveplaces.R.layout.activity_main);
        ButterKnife.bind(this);
        setupToolbar();
        setupDrawerContent(mNavigationView);
        setupViewPager(mViewPager);
        mTabLayout.setupWithViewPager(mViewPager);
    }

    @Override
    protected void onPostCreate(final Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        mDrawerToggle.syncState();
    }

    private void setupToolbar() {
        setSupportActionBar(mToolbar);
        mDrawerToggle = new ActionBarDrawerToggle(this, mDrawerLayout, mToolbar,
                pl.naniewicz.wrocloveplaces.R.string.drawer_open, pl.naniewicz.wrocloveplaces.R.string.drawer_close);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(pl.naniewicz.wrocloveplaces.R.menu.menu_main_activity_actions, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                mDrawerLayout.openDrawer(GravityCompat.START);
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    private void setupViewPager(ViewPager viewPager) {
        ViewPagerAdapter adapter = new ViewPagerAdapter(getSupportFragmentManager(),6);
        adapter.addFragment(new PlacesListFragment(), "Category 1");
        adapter.addFragment(new PlacesListFragment(), "Category 2");
        adapter.addFragment(new PlacesListFragment(), "Category 3");
        adapter.addFragment(new PlacesListFragment(), "Category 4");
        adapter.addFragment(new PlacesListFragment(), "Category 5");
        adapter.addFragment(new PlacesListFragment(), "Category 6");
        viewPager.setAdapter(adapter);
        mViewPager.setOffscreenPageLimit(adapter.getCount() - 1);
    }

    private void setupDrawerContent(NavigationView navigationView) {
        navigationView.setNavigationItemSelectedListener(
                new NavigationView.OnNavigationItemSelectedListener() {
                    @Override
                    public boolean onNavigationItemSelected(MenuItem menuItem) {
                        menuItem.setChecked(true);
                        mDrawerLayout.closeDrawers();
                        return true;
                    }
                });
    }

    @SuppressWarnings("unused")
    @OnClick(R.id.fab)
    public void onFabClick() {
        Snackbar.make(mFloatingActionButton, "I'm snackbar!", Snackbar.LENGTH_LONG)
                .setAction("Action", null).show();
    }

}

