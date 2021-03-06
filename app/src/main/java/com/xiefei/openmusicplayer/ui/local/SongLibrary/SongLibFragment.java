package com.xiefei.openmusicplayer.ui.local.SongLibrary;

import android.os.Bundle;
import android.os.Parcelable;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.xiefei.openmusicplayer.R;
import com.xiefei.openmusicplayer.ui.MainActivity;
import com.xiefei.openmusicplayer.ui.local.BaseFragment;
import com.xiefei.openmusicplayer.ui.local.SongLibrary.album.AlbumListFragment;
import com.xiefei.openmusicplayer.ui.local.SongLibrary.artists.ArtistListFragment;
import com.xiefei.openmusicplayer.ui.local.SongLibrary.songs.SongListFragment;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by xiefei on 16/7/10.
 */
public class SongLibFragment extends BaseFragment {

    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.tab)
    TabLayout tabLayout;
    @BindView(R.id.view_pager)
    ViewPager viewPager;


    private static final int TAB_COUNT = 3;
    private static final int TAB_INDEX_SONG = 0;
    private static final int TAB_INDEX_ALBUM = 1;
    private static final int TAB_INDEX_ARTIST = 2;

    private String[] mTabTitles;
    private Fragment[] mTabFragments;
    private OpenViewPager openViewPager;
    private View view;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        super.onCreateView(inflater,container,savedInstanceState);
        if(view==null)
            view = inflater.inflate(R.layout.song_lib_layout,container,false);
        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        if(savedInstanceState==null) {
            ButterKnife.bind(this, view);
            bindToolbar();
            mTabTitles = new String[TAB_COUNT];
            mTabTitles[TAB_INDEX_SONG] = getResources().getString(R.string.song);
            mTabTitles[TAB_INDEX_ALBUM] = getResources().getString(R.string.album);
            mTabTitles[TAB_INDEX_ARTIST] = getResources().getString(R.string.artist);
            mTabFragments = new Fragment[TAB_COUNT];
            mTabFragments[TAB_INDEX_SONG] = new SongListFragment();
            mTabFragments[TAB_INDEX_ALBUM] = new AlbumListFragment();
            mTabFragments[TAB_INDEX_ARTIST] = new ArtistListFragment();
            openViewPager = new OpenViewPager(getFragmentManager());
            viewPager.setAdapter(openViewPager);
            viewPager.setOffscreenPageLimit(TAB_COUNT);
            tabLayout.setupWithViewPager(viewPager);
        }
    }

    private void bindToolbar() {
        MainActivity activity =  ((MainActivity)getActivity());
        activity.setSupportActionBar(toolbar);
        ActionBarDrawerToggle drawerToggle = new ActionBarDrawerToggle(activity,activity.drawerLayout
                ,toolbar, R.string.drawer_open,R.string.drawer_close);
        drawerToggle.syncState();
        activity.getSupportActionBar().setTitle(getResources().getString(R.string.local_song_lib));
        activity.drawerLayout.addDrawerListener(drawerToggle);
    }

    class OpenViewPager extends FragmentPagerAdapter {

        public OpenViewPager(FragmentManager fm) {
            super(fm);
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return mTabTitles[position];
        }

        @Override
        public Fragment getItem(int position) {
            return mTabFragments[position];
        }

        @Override
        public int getCount() {
            return TAB_COUNT;
        }

        @Override
        public Parcelable saveState() {
            return super.saveState();
        }

        @Override
        public void restoreState(Parcelable state, ClassLoader loader) {
            super.restoreState(state, loader);
        }
    }
}
