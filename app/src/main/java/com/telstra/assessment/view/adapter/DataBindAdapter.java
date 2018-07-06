package com.telstra.assessment.view.adapter;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;
import com.telstra.assessment.R;
import com.telstra.assessment.model.GetData;

import java.util.List;

public class DataBindAdapter extends RecyclerView.Adapter<DataBindAdapter.DataViewHolder> {
    private final List<GetData> mDataList;

    public DataBindAdapter(List<GetData> mDataList) {
        this.mDataList = mDataList;
    }

    @NonNull
    @Override
    public DataViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_data
                , parent, false);
        return new DataViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull DataViewHolder holder, int position) {
        GetData data = mDataList.get(position);
        if (null != data) {
            holder.mTextViewTitle.setText(data.getTitle());
            holder.mTextViewDescription.setText(data.getDescription());
            Picasso.get().load(data.getImageHref()).into(holder.mImageViewdata);
        }
    }

    @Override
    public int getItemCount() {
        return mDataList.size();
    }

    class DataViewHolder extends RecyclerView.ViewHolder {
        private final TextView mTextViewTitle;
        private final TextView mTextViewDescription;
        private final ImageView mImageViewdata;

        DataViewHolder(View itemView) {
            super(itemView);
            mTextViewTitle = itemView.findViewById(R.id.text_item_title);
            mTextViewDescription = itemView.findViewById(R.id.text_item_description);
            mImageViewdata = itemView.findViewById(R.id.image_item_data);

        }
    }
}
