package com.xiefei.openmusicplayer.ui.SongLibrary.album;

import android.graphics.Color;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.view.View;

import com.xiefei.openmusicplayer.entity.Album;
import com.xiefei.openmusicplayer.ui.SongLibrary.BaseLayoutFragment;
import com.xiefei.openmusicplayer.ui.custom.DividerItemDecoration;
import com.xiefei.openmusicplayer.ui.custom.GradDividerItemDecoration;

import java.util.List;

/**
 * Created by xiefei on 16/7/10.
 */
public class AlbumListFragment extends BaseLayoutFragment<AlbumListPresenter,AlbumListView> implements AlbumListView {
    private AlbumListAdapter adapter;

//

    @Override
    public AlbumListPresenter createPresent() {
        return new AlbumListPresenter(getContext());
    }

    @Override
    protected boolean isRetainInstance() {
        return false;
    }

    @Override
    protected void bindData(View v) {
        super.bindData(v);
//        RecyclerView contentView = (RecyclerView) v.findViewById(R.id.content);
        adapter = new AlbumListAdapter(getContext());
        contentView.setLayoutManager(new GridLayoutManager(getContext(),2));
        contentView.addItemDecoration(new GradDividerItemDecoration(Color.TRANSPARENT,16,2));
        contentView.setAdapter(adapter);
        presenter.getData();
    }

    @Override
    public void showLoading(boolean isPullToRefresh) {

    }

    @Override
    public void showContent() {

    }

    @Override
    public void showError(Throwable throwable, boolean isPullToRefresh) {

    }

    @Override
    public void setData(List<Album> data) {
        adapter.addSongs(data);
    }

}
