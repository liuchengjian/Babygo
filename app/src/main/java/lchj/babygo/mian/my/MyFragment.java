package lchj.babygo.mian.my;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import org.xutils.view.annotation.ViewInject;
import org.xutils.x;

import java.util.ArrayList;
import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;
import lchj.babygo.MainActivity;
import lchj.babygo.R;
import lchj.babygo.common.BaseActivity;
import lchj.babygo.mian.my.list.ListAdapter;
import lchj.babygo.mian.my.list.ListBean;
import lchj.babygo.mian.my.list.ListItemType;
import lchj.babygo.mian.my.profile.UserProfileActivity;

/**
 * Created by XiMiTwo on 2017/11/17.
 */

public class MyFragment extends Fragment implements View.OnClickListener{
    private BaseActivity activity;
    @ViewInject(R.id.rv_my)
    RecyclerView mRecyclerView = null;

    @ViewInject(R.id.my_user_avatar)
    CircleImageView circleImageView;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        activity = (BaseActivity) getActivity();
        View view = inflater.inflate(R.layout.fragment_my, null);
        x.view().inject(this, view);

        init();
        Listener();
        return view;
    }

    private void Listener() {
        circleImageView.setOnClickListener(this);
    }

    private void init() {
        final ListBean news = new ListBean.Builder()
                .setItemType(ListItemType.ITEM_MY_ICON)
                .setId(1)
                .setImageResource(R.mipmap.icon_my_news)
//                .setDelegate(new AddressDelegate())
//                .setContext(new MainActivity())
                .setText("消息")
                .build();
        final ListBean activitys = new ListBean.Builder()
                .setItemType(ListItemType.ITEM_MY_ICON)
                .setId(2)
//                .setDelegate(new AddressDelegate())
                .setImageResource(R.mipmap.icon_my_activity)
                .setText("活动")
                .build();
        final ListBean praise = new ListBean.Builder()
                .setItemType(ListItemType.ITEM_MY_ICON)
                .setImageResource(R.mipmap.icon_my_praise)
                .setId(3)
//                .setDelegate(new AddressDelegate())
                .setText("点赞")
                .build();
        final ListBean collect = new ListBean.Builder()
                .setItemType(ListItemType.ITEM_MY_ICON)
                .setImageResource(R.mipmap.icon_my_collect)
                .setId(4)
//                .setDelegate(new AddressDelegate())
                .setText("收藏")
                .build();

        final ListBean settings = new ListBean.Builder()
                .setItemType(ListItemType.ITEM_MY_ICON)
                .setImageResource(R.mipmap.icon_my_set)
                .setId(5)
//                .setDelegate(new AddressDelegate())
                .setText("设置")
                .build();
        final List<ListBean> data = new ArrayList<>();
        data.add(news);
        data.add(activitys);
        data.add(praise);
        data.add(collect);
        data.add(settings);
        //设置RecyclerView
        final LinearLayoutManager manager = new LinearLayoutManager(getContext());
        mRecyclerView.setLayoutManager(manager);
        final ListAdapter adapter = new ListAdapter(data);
        mRecyclerView.setAdapter(adapter);
        mRecyclerView.addOnItemTouchListener(new MyClickListener(getContext()));
        /**
         * 解决ScrollView嵌套RecyclerView导致滑动不流畅的问题
         * 必须把ScrollView改为NestedScrollView，不然会出现显示不全的问题
         */
        mRecyclerView.setNestedScrollingEnabled(false);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.my_user_avatar:
                Intent intent = new Intent(getContext(), UserProfileActivity.class);
                getActivity().startActivity(intent);
                break;
                default:
                    break;
        }
    }
}
