package lchj.babygo.mian.doyen.hot;

import android.content.Context;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.request.RequestOptions;

import java.util.List;

import lchj.babygo.MultipleRecyclerAdapter;
import lchj.babygo.R;
import lchj.babygo.mian.doyen.DoyenItemType;
import lchj.babygo.mian.doyen.DoyenMultipleFields;
import lchj.babygo.recycler.MultipleItemEntity;
import lchj.babygo.recycler.MultipleViewHolder;

/**
 * Created by XiMiTwo on 2017/11/17.
 */

public class DoyenHotAdapter extends MultipleRecyclerAdapter {

    private static final RequestOptions OPTIONS = new RequestOptions()
            .diskCacheStrategy(DiskCacheStrategy.ALL)
            .centerCrop()
            .dontAnimate();
    Context context;

    protected DoyenHotAdapter(List<MultipleItemEntity> data,Context context) {
        super(data, context);
        this.context = context;
        //添加hot item布局
        addItemType(DoyenItemType.DOYEN_HOT_ITEM, R.layout.item_doyen_hoy);

    }

    @Override
    protected void convert(MultipleViewHolder holder, MultipleItemEntity entity) {
        super.convert(holder, entity);
        switch (holder.getItemViewType()) {
            case DoyenItemType.DOYEN_HOT_ITEM:
                //先取出所有值
                final int id = entity.getField(DoyenMultipleFields.ID);
                final String name = entity.getField(DoyenMultipleFields.NAME);
                final String iconText= entity.getField(DoyenMultipleFields.ICONTEXT);
                final String title = entity.getField(DoyenMultipleFields.TITLE);
                final String text = entity.getField(DoyenMultipleFields.TEXT);
                final String from = entity.getField(DoyenMultipleFields.FROM);
                final int zf_count = entity.getField(DoyenMultipleFields.ZFCOUNT);
                final int pl_count = entity.getField(DoyenMultipleFields.PLCOUNT);
                final int dz_count = entity.getField(DoyenMultipleFields.DZCOUNT);
                final String img_Url = entity.getField(DoyenMultipleFields.IMGURL);
                final String head_Url = entity.getField(DoyenMultipleFields.HEADURL);

                //取出所以控件
                final TextView tvName = holder.getView(R.id.list_doyen_hot_name);

                final TextView tvIconText = holder.getView(R.id.list_doyen_hot_iconText);
                final TextView tvTitle = holder.getView(R.id.list_doyen_hot_title);
                final TextView tvText = holder.getView(R.id.tv_doyen_hot_text);
                final TextView tvFrom = holder.getView(R.id.tv_doyen_hot_from);
                final TextView tvZfcount = holder.getView(R.id.tv_doyen_hot_transmit);
                final TextView tvPlcount = holder.getView(R.id.tv_doyen_hot_comment);
                final TextView tvDzcount = holder.getView(R.id.tv_doyen_hot_praise);
                final ImageView imgHead = holder.getView(R.id.iv_doyen_hot_Photo);
                final ImageView img = holder.getView(R.id.iv_doyen_hot_background);

                //赋值
                tvName.setText(name);
                tvIconText.setText(iconText);
                tvTitle.setText(title);
                tvText.setText(text);
                tvFrom.setText(from);
                tvZfcount.setText(String.valueOf(zf_count));
                tvPlcount.setText(String.valueOf(pl_count));
                tvDzcount.setText(String.valueOf(dz_count));
                Glide.with(mContext)
                        .load(img_Url)
                        .apply(OPTIONS)
                        .into(img);
                Glide.with(mContext)
                        .load(head_Url)
                        .apply(OPTIONS)
                        .into(imgHead);
                break;
            default:
                break;
        }
    }
}
