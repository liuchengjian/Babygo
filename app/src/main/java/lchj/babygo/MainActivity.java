package lchj.babygo;

import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.support.v7.widget.Toolbar;

import org.xutils.view.annotation.ViewInject;
import org.xutils.x;

import java.util.List;

import lchj.babygo.common.ToolBarHelper;
import lchj.babygo.mian.index.IndexDataConverter;
import lchj.babygo.mian.index.profile.activity.trip.SectionBean;
import lchj.babygo.mian.index.profile.activity.trip.TripDataConverter;
import lchj.babygo.mian.index.profile.activity.trip.TripSectionAdapter;
import lchj.babygo.net.RestClient;
import lchj.babygo.net.callback.ISuccess;
import lchj.babygo.recycler.BaseDecoration;
import lchj.babygo.refresh.RefreshHandler;
import lchj.babygo.utils.AllUrl;
import lchj.babygo.utils.StatusBarUtils;

public class MainActivity extends AppCompatActivity{
    @ViewInject(R.id.rv_article_profile)
    RecyclerView mRecyclerView = null;
    private List<SectionBean> mData = null;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar =(Toolbar) findViewById(R.id.tb_article_profile);
//        toolbar.getBackground().setAlpha(0);//toolbar透明度初始化为0
        x.view().inject(this);
        /**
         *  沉浸状态栏
         */
        StatusBarUtils.from(this)
                .setTransparentStatusbar(true)
                //白底黑字状态栏
                .setLightStatusBar(true)
                .setTransparentStatusbar(true)
                .setTransparentNavigationbar(true)
                //设置toolbar,actionbar等view
//                .setActionbarView(mNavigationBar)
                .process();

        //瀑布流
        final StaggeredGridLayoutManager manager = new StaggeredGridLayoutManager(1,        StaggeredGridLayoutManager.VERTICAL);
        mRecyclerView.setLayoutManager(manager);

        initData();
    }
    private void initData() {

        RestClient.builder()
                .url(AllUrl.ACTIVITY_TRIP)
                .params("goods_id", 1)
                .loader(this)
                .success(new ISuccess() {
                    @Override
                    public void onSuccess(String response) {
                        mData = new TripDataConverter().convert(response);
                        final TripSectionAdapter adapter = new TripSectionAdapter
                                (R.layout.activity_trip_item_content,
                                        R.layout.activity_trip_item_header,mData);
                        mRecyclerView.setAdapter(adapter);
                    }
                })
                .build()
                .get();
    }
}
