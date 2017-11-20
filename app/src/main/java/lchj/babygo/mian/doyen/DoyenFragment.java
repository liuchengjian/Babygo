package lchj.babygo.mian.doyen;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import org.xutils.view.annotation.ViewInject;
import org.xutils.x;

import java.util.ArrayList;
import java.util.List;

import lchj.babygo.R;
import lchj.babygo.mian.doyen.hot.HotFragment;
import lchj.babygo.mian.my.MyFragment;

/**
 * Created by XiMiTwo on 2017/11/17.
 */

public class DoyenFragment extends Fragment {
    @ViewInject(R.id.tabLayout)
    private TabLayout mtablayout;
    @ViewInject(R.id.viewpager)
    private ViewPager mViewpager;
    private List<Fragment> list;
    private MyAdapter adapter;
    private String[] titles = {"热门", "育儿","旅游", "美食", "学习", "运动"};
    private int images[] = {R.drawable.selector_doyen_hot, R.drawable.selector_doyen_child, R.drawable.selector_doyen_tour,R.drawable.selector_doyen_food,R.drawable.selector_doyen_study, R.drawable.selector_doyen_sports};
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_doyen, null);
        x.view().inject(this, view);
        //页面，数据源
        list = new ArrayList<>();
        list.add(new HotFragment());
        list.add(new HotFragment());
        list.add(new MyFragment());
        list.add(new HotFragment());
        list.add(new HotFragment());
        list.add(new HotFragment());
        //ViewPager的适配器
        adapter = new MyAdapter(getChildFragmentManager(), getActivity());
        mViewpager.setAdapter(adapter);
        //绑定
        mtablayout.setupWithViewPager(mViewpager);
        //MODE_SCROLLABLE可滑动的展示
        //MODE_FIXED固定展示
        mtablayout.setTabMode(TabLayout.MODE_SCROLLABLE);
        //设置自定义视图
        for (int i = 0; i < mtablayout.getTabCount(); i++) {
            TabLayout.Tab tab = mtablayout.getTabAt(i);
            tab.setCustomView(adapter.getTabView(i));
        }
        return view;
    }
    class MyAdapter extends FragmentPagerAdapter {

        private Context context;

        public MyAdapter(FragmentManager fm, Context context) {
            super(fm);
            this.context = context;
        }

        @Override
        public Fragment getItem(int position) {
            return list.get(position);
        }

        @Override
        public int getCount() {
            return list.size();
        }

        /**
         * 自定义方法，提供自定义Tab
         *
         * @param position 位置
         * @return 返回Tab的View
         */
        public View getTabView(int position) {
            View v = LayoutInflater.from(context).inflate(R.layout.doyen_navigation_bar, null);
            TextView textView = (TextView) v.findViewById(R.id.tv_doyen_navigation_bar);
            ImageView imageView = (ImageView) v.findViewById(R.id.iv_doyen_navigation_bar);
            textView.setText(titles[position]);
            imageView.setImageResource(images[position]);
            //添加一行，设置颜色
            textView.setTextColor(mtablayout.getTabTextColors());//
            return v;
        }
    }
}
