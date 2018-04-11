package com.master.android.meitu.image;

import android.support.annotation.Nullable;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.master.android.R;
import com.master.android.meitu.ImageModel;

import java.util.List;

public class ImageSpaceAdapter extends BaseQuickAdapter<ImageModel, BaseViewHolder> {

    public ImageSpaceAdapter(@Nullable List<ImageModel> data) {
        super(R.layout.user_item_model, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, ImageModel item) {
        Glide.with(mContext)
                .load(item.avatar)
                .dontAnimate()
                .dontTransform()
                .into((ImageView) helper.getView(R.id.image_view));
        helper.setText(R.id.id_view, item.ID);
        helper.setText(R.id.name_view, item.title);
    }
}
