package lchj.babygo.mian.topic;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import org.xutils.view.annotation.ViewInject;
import org.xutils.x;

import java.util.ArrayList;

import lchj.babygo.R;
import lchj.babygo.mian.doyen.hot.DoyenHotAdapter;
import lchj.babygo.mian.doyen.hot.DoyenHotDataConverter;
import lchj.babygo.mian.index.IndexDataConverter;
import lchj.babygo.net.RestClient;
import lchj.babygo.net.callback.ISuccess;
import lchj.babygo.recycler.BaseDecoration;
import lchj.babygo.recycler.MultipleItemEntity;
import lchj.babygo.refresh.RefreshHandler;

/**
 * Created by XiMiTwo on 2017/11/17.
 */

public class TopicFragment extends Fragment {
    @ViewInject(R.id.rv_topic)
    RecyclerView mRecyclerView = null;
    @ViewInject(R.id.srl_topic)
    SwipeRefreshLayout mRefreshLayout = null;
    private RefreshHandler mRefreshHandler = null;
    private TopicRecyclerAdapter mAdapter;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_topic, null);
        x.view().inject(this, view);
        RestClient.builder()
                .url("http://39.108.117.228/BabyGoServer/api/topic.php")
                .loader(getContext())
                .success(new ISuccess() {
                    @Override
                    public void onSuccess(String response) {
                        Log.e("lchj",response);
                        final ArrayList<MultipleItemEntity> data =
                                new TopicDataConverter()
                                        .setJsonData(response)
                                        .convert();
                        mAdapter = new TopicRecyclerAdapter(data);
                        final LinearLayoutManager manager = new LinearLayoutManager(getContext());
                        mRecyclerView.setLayoutManager(manager);
                        mRecyclerView.setAdapter(mAdapter);
                    }
                })
                .build()
                .get();
        return view;
    }
    private void initRefreshLayout(){
        mRefreshLayout.setColorSchemeResources(
                android.R.color.holo_blue_bright,
                android.R.color.holo_orange_light,
                android.R.color.holo_red_light);
        mRefreshLayout.setProgressViewOffset(true,120,200);
    }
    private void initRecycleView(){
        final GridLayoutManager manager = new GridLayoutManager(getContext(),4);
        mRecyclerView.setLayoutManager(manager);
        mRecyclerView.addItemDecoration(BaseDecoration.create(
                ContextCompat.getColor(getContext(), R.color.main_div),1));
    }
}
