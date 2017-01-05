package com.wgl.android.library.baseadapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.wgl.android.library.viewholder.RecyclerViewHolder;

import java.util.List;

/**
 * Created by Ganlin.Wu on 2016/10/10.
 */
public abstract class BaseRecyclerAdapter<T> extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private static final String TAG = "RecyclerAdapter";
    private Context mContext;
    private List<T> mData;
    private int mLayoutId;
    private LayoutInflater mInflater;

    public BaseRecyclerAdapter(Context context, List<T> data, int layoutId) {
        mContext = context;
        mData = data;
        mLayoutId = layoutId;
        mInflater = LayoutInflater.from(mContext);
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new RecyclerViewHolder(mInflater.inflate(mLayoutId, parent, false));
    }

    @Override
    public void onBindViewHolder(final RecyclerView.ViewHolder holder, int position) {
        if (holder instanceof RecyclerViewHolder) {
            convert((RecyclerViewHolder) holder, mData.get(position), position);
        }
    }

    public abstract void convert(RecyclerViewHolder holder, T data, int position);

    @Override
    public int getItemCount() {
        return mData.size();
    }

    public T getItem(int position) {
        return mData.get(position);
    }

    public void add(T object) {
        mData.add(object);
        notifyDataSetChanged();
    }

    public void addAll(List<T> objects) {
        mData.addAll(objects);
        notifyDataSetChanged();
    }

    public Context getContext() {
        return mContext;
    }

    public int getLayoutId() {
        return mLayoutId;
    }

}
