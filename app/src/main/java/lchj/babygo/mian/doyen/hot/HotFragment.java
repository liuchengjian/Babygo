package lchj.babygo.mian.doyen.hot;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
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
import lchj.babygo.net.RestClient;
import lchj.babygo.net.callback.ISuccess;
import lchj.babygo.recycler.MultipleItemEntity;

/**
 * Created by XiMiTwo on 2017/11/17.
 */

public class HotFragment extends Fragment {
    private DoyenHotAdapter mAdapter;
    @ViewInject(R.id.rv_doyen_hot)
    private RecyclerView mRecyclerView;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_hot, null);
        x.view().inject(this, view);

        RestClient.builder()
                .url("http://39.108.117.228/BabyGoServer/api/daren_hot.php")
                .loader(getContext())
                .success(new ISuccess() {
                    @Override
                    public void onSuccess(String response) {
                        Log.e("lchj",response);
                        final ArrayList<MultipleItemEntity> data =
                                new DoyenHotDataConverter()
                                        .setJsonData(response)
                                        .convert();
                        mAdapter = new DoyenHotAdapter(data);
                        final LinearLayoutManager manager = new LinearLayoutManager(getContext());
                        mRecyclerView.setLayoutManager(manager);
                        mRecyclerView.setAdapter(mAdapter);
                    }
                })
                .build()
                .get();
        return view;
    }
}
