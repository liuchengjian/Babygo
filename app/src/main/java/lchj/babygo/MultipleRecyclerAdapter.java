package lchj.babygo;

import android.support.v7.widget.GridLayoutManager;
import android.view.View;
import android.widget.ImageView;

import com.bigkoo.convenientbanner.ConvenientBanner;
import com.bigkoo.convenientbanner.listener.OnItemClickListener;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.request.RequestOptions;
import com.chad.library.adapter.base.BaseMultiItemQuickAdapter;
import com.chad.library.adapter.base.BaseQuickAdapter;

import java.util.ArrayList;
import java.util.List;

import lchj.babygo.banner.BannerCreator;
import lchj.babygo.mian.index.IndexItemType;
import lchj.babygo.mian.index.IndexMultipleFields;
import lchj.babygo.recycler.DataConverter;
import lchj.babygo.recycler.ItemType;
import lchj.babygo.recycler.MultipleFields;
import lchj.babygo.recycler.MultipleItemEntity;
import lchj.babygo.recycler.MultipleViewHolder;


/**
 * Created by lchj
 */

public class MultipleRecyclerAdapter extends
        BaseMultiItemQuickAdapter<MultipleItemEntity, MultipleViewHolder>
        implements
        BaseQuickAdapter.SpanSizeLookup,
        OnItemClickListener {

    //确保初始化一次Banner，防止重复Item加载
    private boolean mIsInitBanner = false;
    //设置图片加载策略
    private static final RequestOptions RECYCLER_OPTIONS =
            new RequestOptions()
                    .centerCrop()
                    .diskCacheStrategy(DiskCacheStrategy.ALL)
                    .dontAnimate();

    protected MultipleRecyclerAdapter(List<MultipleItemEntity> data) {
        super(data);
        init();
    }

    public static MultipleRecyclerAdapter create(List<MultipleItemEntity> data) {
        return new MultipleRecyclerAdapter(data);
    }

    public static MultipleRecyclerAdapter create(DataConverter converter) {
        return new MultipleRecyclerAdapter(converter.convert());
    }

    public void refresh(List<MultipleItemEntity> data) {
        getData().clear();
        setNewData(data);
        notifyDataSetChanged();
    }

    private void init() {
        //设置不同的item布局
        addItemType(IndexItemType.TEXT, R.layout.item_index_text);
        addItemType(IndexItemType.ARTICLE, R.layout.item_index_article);
        addItemType(IndexItemType.ACTIVITY, R.layout.item_inde_activity);
        addItemType(IndexItemType.BANNER, R.layout.item_multiple_banner);
        //设置宽度监听
        setSpanSizeLookup(this);
        openLoadAnimation();
        //多次执行动画
        isFirstOnly(false);
    }

    @Override
    protected MultipleViewHolder createBaseViewHolder(View view) {
        return MultipleViewHolder.create(view);
    }

    @Override
    protected void convert(MultipleViewHolder holder, MultipleItemEntity entity) {
        final String text;
        final String title;
        final String name;
        final String time;
        final String turnout;
        final String iconUrl;
        final String headUrl;
        final String imageUrl;
        final String icon_one;
        final String icon_two;
        final String icon_three;
        final ArrayList<String> bannerImages;
        switch (holder.getItemViewType()) {
            case IndexItemType.TEXT:
                text = entity.getField(IndexMultipleFields.TEXT);
                holder.setText(R.id.index_single_text, text);
                break;
            case IndexItemType.ARTICLE:
                title = entity.getField(IndexMultipleFields.TITLE);
                holder.setText(R.id.tv_acticler_title, title);
                name = entity.getField(IndexMultipleFields.NAME);
                holder.setText(R.id.tv_acticler_name, name);
                time = entity.getField(IndexMultipleFields.TIME);
                holder.setText(R.id.tv_acticler_date, time);

                iconUrl = entity.getField(IndexMultipleFields.ICON_URL);
                Glide.with(mContext)
                        .load(iconUrl)
                        .apply(RECYCLER_OPTIONS)
                        .into((ImageView) holder.getView(R.id.iv_icon));
                headUrl = entity.getField(IndexMultipleFields.HEAD_URL);
                Glide.with(mContext)
                        .load(headUrl)
                        .apply(RECYCLER_OPTIONS)
                        .into((ImageView) holder.getView(R.id.iv_head_Photo));
                imageUrl = entity.getField(IndexMultipleFields.IMAGE_URL);
                Glide.with(mContext)
                        .load(imageUrl)
                        .apply(RECYCLER_OPTIONS)
                        .into((ImageView) holder.getView(R.id.iv_background_image));
                break;
            case IndexItemType.ACTIVITY:
                title = entity.getField(IndexMultipleFields.TITLE);
                holder.setText(R.id.tv_activity_title, title);
                name = entity.getField(IndexMultipleFields.NAME);
                holder.setText(R.id.tv_activity_name, name);
                turnout = entity.getField(IndexMultipleFields.TURNOUT);
                holder.setText(R.id.tv_activity_person, turnout);
                icon_one = entity.getField(IndexMultipleFields.ICON_ONE);
                holder.setText(R.id. tv_age, icon_one);
                icon_two = entity.getField(IndexMultipleFields.ICON_TWO);
                holder.setText(R.id.tv_tributeone, icon_two);
                icon_three = entity.getField(IndexMultipleFields.ICON_THREE);
                holder.setText(R.id.tv_tributetwo, icon_three);
                iconUrl = entity.getField(IndexMultipleFields.ICON_URL);
                Glide.with(mContext)
                        .load(iconUrl)
                        .apply(RECYCLER_OPTIONS)
                        .into((ImageView) holder.getView(R.id.iv_icon_activity));
                imageUrl = entity.getField(IndexMultipleFields.IMAGE_URL);

                Glide.with(mContext)
                        .load(imageUrl)
                        .apply(RECYCLER_OPTIONS)
                        .into((ImageView) holder.getView(R.id.iv_activity_background_image));
                break;
            case IndexItemType.BANNER:
                if (!mIsInitBanner) {
                    bannerImages = entity.getField(IndexMultipleFields.BANNERS);
                    final ConvenientBanner<String> convenientBanner = holder.getView(R.id.banner_recycler_item);
                    BannerCreator.setDefault(convenientBanner, bannerImages, this);
                    mIsInitBanner = true;
                }
                break;
            default:
                break;
        }
    }

    @Override
    public int getSpanSize(GridLayoutManager gridLayoutManager, int position) {
        return getData().get(position).getField(IndexMultipleFields.SPAN_SIZE);
    }

    @Override
    public void onItemClick(int position) {

    }
}
