package pl.naniewicz.wrocloveplaces.ui.main;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
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

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import pl.naniewicz.wrocloveplaces.R;
import pl.naniewicz.wrocloveplaces.ui.form.FormActivity;
import pl.naniewicz.wrocloveplaces.ui.widget.adapter.ViewPagerAdapter;

/**
 * Copyright (C) 2016  Rafał Naniewicz and Szymon Kozak
 * This program is free software; you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation; either version 2 of the License, or
 * (at your option) any later version.
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 * You should have received a copy of the GNU General Public License along
 * with this program; if not, write to the Free Software Foundation, Inc.,
 * 51 Franklin Street, Fifth Floor, Boston, MA 02110-1301 USA.
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
        setContentView(R.layout.activity_main);
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
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_settings:
                Toast.makeText(this, R.string.menu_settings, Toast.LENGTH_SHORT).show();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    private void setupViewPager(ViewPager viewPager) {
        ViewPagerAdapter adapter = new ViewPagerAdapter(getSupportFragmentManager(), 6);
        adapter.addFragment(new PlaceListFragment(), "Category 1");
        adapter.addFragment(new PlaceListFragment(), "Category 2");
        adapter.addFragment(new PlaceListFragment(), "Category 3");
        adapter.addFragment(new PlaceListFragment(), "Category 4");
        adapter.addFragment(new PlaceListFragment(), "Category 5");
        adapter.addFragment(new PlaceListFragment(), "Category 6");
        viewPager.setAdapter(adapter);
        mViewPager.setOffscreenPageLimit(adapter.getCount() - 1);
    }

    private void setupDrawerContent(NavigationView navigationView) {
        navigationView.setNavigationItemSelectedListener(
                new NavigationView.OnNavigationItemSelectedListener() {
                    @Override
                    public boolean onNavigationItemSelected(MenuItem menuItem) {
                        switch (menuItem.getItemId()) {
                            case R.id.activity_form:
                                goToFormActivity();
                                return true;
                        }
                        return false;
                    }
                });
    }

    private void goToFormActivity() {
        mDrawerLayout.closeDrawers();
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent intent = new Intent(MainActivity.this, FormActivity.class);
                startActivity(intent);
            }
        }, 250);
    }

    @SuppressWarnings("unused")
    @OnClick(R.id.fab)
    public void onFabClick() {
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