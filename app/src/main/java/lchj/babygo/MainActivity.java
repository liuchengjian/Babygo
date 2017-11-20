package lchj.babygo;

import android.support.v4.content.ContextCompat;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.AppCompatEditText;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;

import com.joanzapata.iconify.widget.IconTextView;

import butterknife.BindView;
import lchj.babygo.mian.index.IndexDataConverter;
import lchj.babygo.recycler.BaseDecoration;
import lchj.babygo.refresh.RefreshHandler;
import lchj.babygo.utils.AllUrl;

public class MainActivity extends AppCompatActivity {
//    @BindView(R2.id.rv_index)
    RecyclerView mRecyclerView = null;
//    @BindView(R2.id.srl_index)
    SwipeRefreshLayout mRefreshLayout = null;

    private RefreshHandler mRefreshHandler = null;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mRecyclerView = findViewById(R.id.rv_index);
        mRefreshLayout = findViewById(R.id.srl_index);

        initRefreshLayout();
        initRecycleView();
        mRefreshHandler = RefreshHandler.create(mRefreshLayout,mRecyclerView,new IndexDataConverter());
        mRefreshHandler.firstPage("http://39.108.117.228/BabyGoServer/api/index.php");

    }
    private void initRefreshLayout(){
        mRefreshLayout.setColorSchemeResources(
                android.R.color.holo_blue_bright,
                android.R.color.holo_orange_light,
                android.R.color.holo_red_light);
        mRefreshLayout.setProgressViewOffset(true,120,200);
    }
    private void initRecycleView(){
        final GridLayoutManager manager = new GridLayoutManager(this,4);
        mRecyclerView.setLayoutManager(manager);
        mRecyclerView.addItemDecoration(BaseDecoration.create(
                ContextCompat.getColor(this, R.color.main_div),40));


    }
}
