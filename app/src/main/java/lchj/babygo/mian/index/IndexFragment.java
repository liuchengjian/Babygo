package lchj.babygo.mian.index;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;


import org.xutils.view.annotation.ViewInject;
import org.xutils.x;

import java.util.WeakHashMap;

import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import lchj.babygo.R;
import lchj.babygo.net.RestCreator;
import lchj.babygo.net.rx.RxRestClient;
import lchj.babygo.recycler.BaseDecoration;
import lchj.babygo.refresh.RefreshHandler;
import lchj.babygo.utils.AllUrl;

/**
 * Created by XiMiTwo on 2017/11/17.
 */

public class IndexFragment extends Fragment {
    //    @BindView(R2.id.rv_index)
    @ViewInject(R.id.rv_index)
    RecyclerView mRecyclerView = null;
    //    @BindView(R2.id.srl_index)
    @ViewInject(R.id.srl_index)
    SwipeRefreshLayout mRefreshLayout = null;

    private RefreshHandler mRefreshHandler = null;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_index, null);
        x.view().inject(this, view);
        initRefreshLayout();
        initRecycleView();
        mRefreshHandler = RefreshHandler.create(mRefreshLayout,mRecyclerView,new IndexDataConverter(),getContext());
        mRefreshHandler.firstPage("http://39.108.117.228/BabyGoServer/api/index.php");
//        onCallRxGet();
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
                ContextCompat.getColor(getContext(), R.color.main_div),30));
        mRecyclerView.addOnItemTouchListener(IndexItemClickListener.create(getContext()));
    }

    //TODO:测试方法，没啥卵用
    void onCallRxGet() {

        final String url = AllUrl.INDEX_URL;
        final WeakHashMap<String, Object> params = new WeakHashMap<>();

        final Observable<String> observable = RestCreator.getRxRestService().get(url, params);
        observable.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<String>() {
                    @Override
                    public void onSubscribe(@io.reactivex.annotations.NonNull Disposable d) {

                    }

                    @Override
                    public void onNext(@io.reactivex.annotations.NonNull String s) {
                        Toast.makeText(getContext(), s, Toast.LENGTH_LONG).show();
                    }

                    @Override
                    public void onError(@io.reactivex.annotations.NonNull Throwable e) {

                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }

    //TODO:测试方法，没啥卵用X2
    private void onCallRxRestClient() {


        final String url = "index.php";
        RxRestClient.builder()
                .url(url)
                .build()
                .get()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<String>() {
                    @Override
                    public void onSubscribe(@io.reactivex.annotations.NonNull Disposable d) {

                    }

                    @Override
                    public void onNext(@io.reactivex.annotations.NonNull String s) {
                        Toast.makeText(getContext(), s, Toast.LENGTH_LONG).show();
                    }

                    @Override
                    public void onError(@io.reactivex.annotations.NonNull Throwable e) {

                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }
}
