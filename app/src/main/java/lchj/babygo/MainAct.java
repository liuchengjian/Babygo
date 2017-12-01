package lchj.babygo;

import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.Toast;


import org.xutils.view.annotation.ViewInject;
import org.xutils.x;

import lchj.babygo.common.BaseActivity;
import lchj.babygo.mian.doyen.DoyenFragment;
import lchj.babygo.mian.index.IndexFragment;
import lchj.babygo.mian.my.MyFragment;
import lchj.babygo.mian.topic.TopicFragment;

public class MainAct extends BaseActivity implements View.OnClickListener{
    @ViewInject(R.id.rb_tab_menu_header)
    private RadioButton mRB_tab_header;
    @ViewInject(R.id.rb_tab_menu_topic)
    private RadioButton mRB_tab_topic;
    @ViewInject(R.id.rb_tab_menu_doyenO)
    private RadioButton mRB_tab_doyenO;
    @ViewInject(R.id.rb_tab_menu_my)
    private RadioButton mRB_tab_my;
    @ViewInject(R.id.iv_tab_map_Photo)
    private ImageView iv_tab_map_Photo;
    /**
     * 4个Fragment碎片界面
//     */
    private IndexFragment If;
    private DoyenFragment Df;
    private TopicFragment Tf;
    private MyFragment myf;
//    private GDMapFragment gdmapf;
    /**
     * FragmentTransaction fragment碎片管理器
     */
    private FragmentTransaction ft;
    @ViewInject(R.id.fl_mian)
    private FrameLayout fl_mian = null;
    FragmentManager fm;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        if (savedInstanceState != null) {
            If = (IndexFragment) fm.findFragmentByTag("If");
            Df = (DoyenFragment) fm.findFragmentByTag("Df");
//            gdmapf = (GDMapFragment) fm.findFragmentByTag("gdmapf");
            Tf = (TopicFragment) fm.findFragmentByTag("Tf");
            myf = (MyFragment) fm.findFragmentByTag("myf");
        }
        super.onCreate(savedInstanceState);
        setContentView(R.layout.act_main);
        x.view().inject(this);
        listener();
        select(0);

    }

    private void listener() {
        mRB_tab_header.setOnClickListener(this);
        mRB_tab_doyenO.setOnClickListener(this);
        mRB_tab_my.setOnClickListener(this);
        mRB_tab_topic.setOnClickListener(this);
        iv_tab_map_Photo.setOnClickListener(this);
//        iv_tab_public_Photo.setOnClickListener(this);
    }
    private void select(int i) {
        fm = getSupportFragmentManager(); // 获得Fragment管理器
        // FragmentManager fm = getChildFragmentManager(); // 获得Fragment管理器
        FragmentTransaction ft = fm.beginTransaction(); // 开启一个事务
        hidtFragment(ft); // 先隐藏 Fragment
        switch (i) {
            case 0:
                if (If == null) {
                    If = new IndexFragment();
                    ft.add(R.id.fl_mian, If);
                } else {
                    ft.show(If);
                }
                break;
            case 1:
                if (Df == null) {
                    Df = new DoyenFragment();
                    ft.add(R.id.fl_mian, Df);
                } else {
                    ft.show(Df);
                }
                break;
            case 2:
//                if (gdmapf == null) {
//                    gdmapf = new GDMapFragment();
//                    ft.add(R.id.fl_mian, gdmapf);
//                } else {
//                    ft.show(gdmapf);
//                }
                break;
            case 3:
                if (Tf == null) {
                    Tf = new TopicFragment();
                    ft.add(R.id.fl_mian, Tf);
                } else {
                    ft.show(Tf);
                }
                break;
            case 4:
                if (myf == null) {
                    myf = new MyFragment();
                    ft.add(R.id.fl_mian, myf);
                } else {
                    ft.show(myf);

                }
                break;
        }
        ft.commit(); // 提交事务
    }
    // 隐藏所有Fragment
    private void hidtFragment(FragmentTransaction fragmentTransaction) {
        if (If != null) {
            fragmentTransaction.hide(If);
        }
        if (Df != null) {
            fragmentTransaction.hide(Df);
        }
        if (Tf != null) {
            fragmentTransaction.hide(Tf);
        }
        if (myf != null) {
            fragmentTransaction.hide(myf);
        }
    }
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.rb_tab_menu_header:
                mRB_tab_header.setChecked(true);
                mRB_tab_doyenO.setChecked(false);
                mRB_tab_topic.setChecked(false);
                mRB_tab_my.setChecked(false);
//                iv_tab_public_Photo.setVisibility(View.GONE);
                select(0);
                break;
            case R.id.rb_tab_menu_doyenO:
                mRB_tab_doyenO.setChecked(true);
                mRB_tab_header.setChecked(false);
                mRB_tab_topic.setChecked(false);
                mRB_tab_my.setChecked(false);
//                iv_tab_public_Photo.setVisibility(View.GONE);
                select(1);
                break;
            case R.id.iv_tab_map_Photo:
//                select(2);
                Toast.makeText(this,"地图",Toast.LENGTH_SHORT).show();
//                mRB_tab_my.setChecked(false);
//                mRB_tab_topic.setChecked(false);
//                mRB_tab_doyenO.setChecked(false);
//                mRB_tab_header.setChecked(false);
//                iv_tab_public_Photo.setVisibility(View.VISIBLE);
                break;
            case R.id.rb_tab_menu_topic:
                select(3);
                mRB_tab_topic.setChecked(true);
                mRB_tab_doyenO.setChecked(false);
                mRB_tab_header.setChecked(false);
                mRB_tab_my.setChecked(false);
                break;
            case R.id.rb_tab_menu_my:
                select(4);
                mRB_tab_my.setChecked(true);
                mRB_tab_topic.setChecked(false);
                mRB_tab_doyenO.setChecked(false);
                mRB_tab_header.setChecked(false);
                break;
            default:
                break;
        }
    }
    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
    }
}
