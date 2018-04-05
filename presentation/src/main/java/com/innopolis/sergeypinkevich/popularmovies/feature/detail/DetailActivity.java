package com.innopolis.sergeypinkevich.popularmovies.feature.detail;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.widget.Toast;

import com.arellomobile.mvp.presenter.InjectPresenter;
import com.innopolis.sergeypinkevich.popularmovies.R;
import com.innopolis.sergeypinkevich.popularmovies.feature.main.MainActivity;
import com.innopolis.sergeypinkevich.popularmovies.feature.review.ReviewFragment;

import java.util.ArrayList;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import es.dmoral.toasty.Toasty;
import internal.di.BaseApp;

public class DetailActivity extends AppCompatActivity implements DetailView {

    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.view_pager)
    ViewPager viewPager;
    @BindView(R.id.tabs)
    TabLayout tabLayout;
    @BindView(R.id.is_favourite_button)
    FloatingActionButton favouriteButton;

    @Inject
    @InjectPresenter
    DetailPresenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        BaseApp.component.inject(this);

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        ButterKnife.bind(this);

        presenter.attachView(this);

        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        setupViewPager();
        long movieId = getIntent().getLongExtra(MainActivity.MOVIE_DETAIL_EXTRA, -1);
        favouriteButton.setOnClickListener(listener -> presenter.changeMovieIsFavourite(movieId));
    }

    private void setupViewPager() {
        ViewPagerAdapter adapter = new ViewPagerAdapter(getSupportFragmentManager());
        adapter.addFragment(new DetailFragmentFragment(), getString(R.string.details_fragment));
        adapter.addFragment(new ReviewFragment(), getString(R.string.reviews_fragment));
        viewPager.setAdapter(adapter);
        tabLayout.setupWithViewPager(viewPager);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            onBackPressed();
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void showMovieIsFavourite() {
        favouriteButton.setImageResource(R.drawable.ic_favourite_on);
    }

    @Override
    public void showMovieIsNotFavourite() {
        favouriteButton.setImageResource(R.drawable.ic_favourite_off);
    }

    @Override
    public void showErrorMessage() {
        Toasty.error(this, getString(R.string.error_message), Toast.LENGTH_SHORT).show();
    }

    @Override
    public void finishView() {
        onBackPressed();
    }

    class ViewPagerAdapter extends FragmentPagerAdapter {

        private ArrayList<Fragment> fragmentArrayList = new ArrayList<>();
        private ArrayList<String> tabsTitles = new ArrayList<>();

        public ViewPagerAdapter(FragmentManager fragmentManager) {
            super(fragmentManager);
        }

        public void addFragment(Fragment fragment, String title) {
            fragmentArrayList.add(fragment);
            tabsTitles.add(title);
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return tabsTitles.get(position);
        }

        @Override
        public Fragment getItem(int position) {
            return fragmentArrayList.get(position);
        }

        @Override
        public int getCount() {
            return fragmentArrayList.size();
        }
    }
}
