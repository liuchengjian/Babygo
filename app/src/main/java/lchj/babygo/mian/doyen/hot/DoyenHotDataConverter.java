package lchj.babygo.mian.doyen.hot;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;

import java.util.ArrayList;

import lchj.babygo.mian.doyen.DoyenItemType;
import lchj.babygo.mian.doyen.DoyenMultipleFields;
import lchj.babygo.mian.index.IndexMultipleFields;
import lchj.babygo.recycler.DataConverter;
import lchj.babygo.recycler.MultipleItemEntity;

/**
 * Created by XiMiTwo on 2017/11/17.
 */

public class DoyenHotDataConverter extends DataConverter {
    @Override
    public ArrayList<MultipleItemEntity> convert() {
        final ArrayList<MultipleItemEntity>dataList = new ArrayList<>();
        final JSONArray dataArray = JSON.parseObject(getJsonData()).getJSONArray("data");
        final int size = dataArray.size();
        for (int i = 0; i < size; i++) {
            final JSONObject data = dataArray.getJSONObject(i);
            final int id = data.getInteger("id");
            final String name = data.getString("name");
            final String iconText = data.getString("iconText");
            final String title = data.getString("title");
            final String text = data.getString("text");
            final String from = data.getString("from");

            final int zf_count = data.getInteger("zf_count");
            final int pl_count = data.getInteger("pl_count");
            final int dz_count = data.getInteger("dz_count");
            final String head_Url = data.getString("head_Url");
            final String img_Url = data.getString("img_Url");

            final MultipleItemEntity entity = MultipleItemEntity.builder()
                    .setField(IndexMultipleFields.ITEM_TYPE , DoyenItemType.DOYEN_HOT_ITEM)
                    .setField(DoyenMultipleFields.ID, id)
                    .setField(DoyenMultipleFields.NAME, name)
                    .setField(DoyenMultipleFields.ICONTEXT, iconText)
                    .setField(DoyenMultipleFields.TITLE, title)
                    .setField(DoyenMultipleFields.TEXT, text)
                    .setField(DoyenMultipleFields.FROM, from)
                    .setField(DoyenMultipleFields.ZFCOUNT, zf_count)
                    .setField(DoyenMultipleFields.PLCOUNT, pl_count)
                    .setField(DoyenMultipleFields.DZCOUNT, dz_count)
                    .setField(DoyenMultipleFields.HEADURL, head_Url)
                    .setField(DoyenMultipleFields.IMGURL, img_Url)
                    .build();

            dataList.add(entity);
        }

        return dataList;
    }
}
