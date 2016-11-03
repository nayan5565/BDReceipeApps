package com.example.nayan.newmybdreceipetest.activity;

import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.design.widget.TabLayout;
import android.support.v4.view.GravityCompat;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;

import com.example.nayan.newmybdreceipetest.MyPagerAdapter;
import com.example.nayan.newmybdreceipetest.R;
import com.example.nayan.newmybdreceipetest.fragment.CategoryFragment;
import com.example.nayan.newmybdreceipetest.fragment.FragDemo;
import com.example.nayan.newmybdreceipetest.fragment.FragmentMail;

public class MainActivity extends AppCompatActivity {
    Toolbar toolbar;
    NavigationView navigationView;
    ActionBar actionBar;
    DrawerLayout drawerLayout;
    ViewPager viewPager;
    TabLayout tabLayout;
    MyPagerAdapter adapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        init();
        setupViewPager();
        setupTablayout();

    }



    private void init() {
        drawerLayout = (DrawerLayout) findViewById(R.id.drawer);
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setTitle("বাংলাদেশী রেসিপি ");
        toolbar.setTitleTextColor(0xffffffff);
        navigationView = (NavigationView) findViewById(R.id.navigation);
        viewPager = (ViewPager) findViewById(R.id.viewPager);
        tabLayout = (TabLayout) findViewById(R.id.tabLayout);
        setSupportActionBar(toolbar);
        actionBar = getSupportActionBar();
        actionBar.setHomeAsUpIndicator(R.drawable.ic_menu);
        actionBar.setDisplayHomeAsUpEnabled(true);

        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(MenuItem menuItem) {
                menuItem.setChecked(true);
                drawerLayout.closeDrawers();
                return true;
            }
        });
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId()) {
            case android.R.id.home:
                drawerLayout.openDrawer(GravityCompat.START);
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    private void setupViewPager() {
        adapter = new MyPagerAdapter(getSupportFragmentManager());

        adapter.addFragment(CategoryFragment.getInstance(), "ক্যাটেগরি  ");
        adapter.addFragment(FragDemo.getInstance(2), "পছন্দনীয় ");
        adapter.addFragment(FragmentMail.getinstance(), "রেসিপি চাই");
        viewPager.setAdapter(adapter);
    }

    private void setupTablayout() {
        tabLayout.setupWithViewPager(viewPager);
        tabLayout.setOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                viewPager.setCurrentItem(tab.getPosition());
                adapter.notifyDataSetChanged();
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });
    }
}
