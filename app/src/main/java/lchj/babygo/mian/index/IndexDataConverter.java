package lchj.babygo.mian.index;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;

import java.util.ArrayList;

import lchj.babygo.recycler.DataConverter;
import lchj.babygo.recycler.MultipleItemEntity;


/**
 * Created by babygo on 2017/11/16.
 * 贝比狗首页数据
 */

public class IndexDataConverter extends DataConverter {
    @Override
    public ArrayList<MultipleItemEntity> convert() {
        final JSONArray dataArray = JSON.parseObject(getJsonData()).getJSONArray("data");
        final int size = dataArray.size();
        for (int i = 0; i < size; i++) {
            final JSONObject data = dataArray.getJSONObject(i);
            final String text = data.getString("text");
            final String title = data.getString("title");
            final String name = data.getString("name");
            final String time = data.getString("time");
            final String iconUrl = data.getString("iconUrl");
            final String headUrl = data.getString("headUrl");
            final String imageUrl = data.getString("imageUrl");

            final String icon_one = data.getString("icon_one");
            final String icon_two = data.getString("icon_two");
            final String icon_three = data.getString("icon_three");
            final String turnout = data.getString("turnout");

            final int spanSize = data.getInteger("spanSize");
            final int id = data.getInteger("id");
            final JSONArray banners = data.getJSONArray("banners");

            final ArrayList<String> bannerImages = new ArrayList<>();
            int type = 0;
            if (banners == null && headUrl == null && text != null) {
                type = IndexItemType.TEXT;
            } else if (banners == null && headUrl != null && text == null) {
                type = IndexItemType.ARTICLE;
            } else if (banners == null && headUrl == null && text == null) {
                type = IndexItemType.ACTIVITY;
            } else if (banners != null && headUrl == null && text == null) {
                type = IndexItemType.BANNER;
                //Banner的初始化
                final int bannerSize = banners.size();
                for (int j = 0; j < bannerSize; j++) {
                    final String banner = banners.getString(j);
                    bannerImages.add(banner);
                }
            }
            final MultipleItemEntity entity = MultipleItemEntity.builder()
                    .setField(IndexMultipleFields.ITEM_TYPE, type)
                    .setField(IndexMultipleFields.SPAN_SIZE, spanSize)
                    .setField(IndexMultipleFields.ID, id)
                    .setField(IndexMultipleFields.TEXT, text)
                    .setField(IndexMultipleFields.TITLE, title)
                    .setField(IndexMultipleFields.NAME, name)
                    .setField(IndexMultipleFields.TIME, time)
                    .setField(IndexMultipleFields.ICON_URL, iconUrl)
                    .setField(IndexMultipleFields.HEAD_URL, headUrl)
                    .setField(IndexMultipleFields.IMAGE_URL, imageUrl)
                    .setField(IndexMultipleFields.ICON_ONE, icon_one)
                    .setField(IndexMultipleFields.ICON_TWO, icon_two)
                    .setField(IndexMultipleFields.ICON_THREE, icon_three)
                    .setField(IndexMultipleFields.TURNOUT, turnout)
                    .setField(IndexMultipleFields.BANNERS, bannerImages)
                    .build();

            ENTITIES.add(entity);

        }

        return ENTITIES;
    }
}
