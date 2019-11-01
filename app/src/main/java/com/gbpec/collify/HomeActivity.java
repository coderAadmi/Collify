package com.gbpec.collify;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import butterknife.BindView;
import butterknife.ButterKnife;

public class HomeActivity extends AppCompatActivity implements NewsAdapter.OnCardClickListener {

    @BindView(R.id.toolbar)
    Toolbar toolbar;

    @BindView(R.id.profile_name)
    TextView mName;

    @BindView(R.id.profile_email)
    TextView mEmail;

    @BindView(R.id.drawer)
    DrawerLayout mDrawer;

    @BindView(R.id.news_list_view)
    RecyclerView mNewsListView;

    boolean isOpen = false;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        getWindow().setStatusBarColor(Color.BLACK);
        ButterKnife.bind(this);

        setSupportActionBar(toolbar);

        Intent intent = getIntent();
        mName.setText(intent.getStringExtra("NAME"));
        mEmail.setText(intent.getStringExtra("EMAIL"));
        ActionBar actionBar = getSupportActionBar();
        actionBar.setHomeAsUpIndicator(R.drawable.ic_home_icon);
        actionBar.setDisplayHomeAsUpEnabled(true);

        mDrawer.addDrawerListener(new DrawerLayout.DrawerListener() {
            @Override
            public void onDrawerSlide(@NonNull View drawerView, float slideOffset) {

            }

            @Override
            public void onDrawerOpened(@NonNull View drawerView) {
                isOpen = true;
            }

            @Override
            public void onDrawerClosed(@NonNull View drawerView) {
                isOpen = false;
            }

            @Override
            public void onDrawerStateChanged(int newState) {

            }
        });

        mNewsListView.setLayoutManager(new LinearLayoutManager(this));
        mNewsListView.setAdapter(new NewsAdapter(this));
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if(isOpen)
        {
            isOpen = false;
            mDrawer.closeDrawer(GravityCompat.START);
        }
        else
        {
            isOpen = true;
            mDrawer.openDrawer(GravityCompat.START);
        }
        return false;
    }

    @Override
    public void onCardClicked(int pos) {
        Intent intent = new Intent(HomeActivity.this,NewsDetailsActivity.class);
        intent.putExtra("TITLE","NEWS "+pos);
        startActivity(intent);
    }
}
