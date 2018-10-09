package com.master.androidx.mzitu.image;

import android.arch.paging.PagedListAdapter;
import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.util.DiffUtil;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.master.androidx.R;
import com.master.androidx.mzitu.detail.ImageDetailActivity;

public class ImageAdapter extends PagedListAdapter<ImageObject, ImageAdapter.ImageHolder> {

    public static final DiffUtil.ItemCallback<ImageObject> DIFF_CALLBACK =
            new DiffUtil.ItemCallback<ImageObject>() {
                @Override
                public boolean areItemsTheSame(ImageObject oldItem, ImageObject newItem) {
                    return oldItem.id == newItem.id;
                }

                @Override
                public boolean areContentsTheSame(ImageObject oldItem, ImageObject newItem) {
                    return oldItem.equals(newItem);
                }
            };

    private Context mContext;

    public ImageAdapter(Context context) {
        super(DIFF_CALLBACK);
        mContext = context;
    }

    @NonNull
    @Override
    public ImageHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext)
                .inflate(R.layout.user_item_model, null);
        return new ImageHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ImageHolder holder, int position) {
        ImageObject object = getItem(position);
        if (object != null) {
            holder.bindData(object);
        } else {
            holder.clear();
        }
    }

    public class ImageHolder extends RecyclerView.ViewHolder {

        private ImageView mImageView;
        private TextView mIdView;
        private TextView mNameView;

        private ImageObject mImageObject;

        public ImageHolder(View itemView) {
            super(itemView);
            mImageView = itemView.findViewById(R.id.image_view);
            mIdView = itemView.findViewById(R.id.id_view);
            mNameView = itemView.findViewById(R.id.name_view);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (mImageObject != null) {
                        ImageDetailActivity.start(v.getContext(), mImageObject.id);
                    }
                }
            });
        }

        public void bindData(ImageObject object) {
            mImageObject = object;
            Glide.with(mContext)
                    .load(object.thumb_src_min)
                    .into(mImageView);
            mIdView.setText(object.title);
            mNameView.setText(object.title);
        }

        public void clear() {

        }

    }
}
