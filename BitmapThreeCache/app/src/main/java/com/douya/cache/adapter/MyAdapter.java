package com.douya.cache.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.douya.cache.R;
import com.douya.cache.bean.Bean;
import com.douya.cache.utils.MyBitMapUtils;

import java.util.List;

/**
 * Created by 杨圆圆 on 2017/11/9.
 */

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.MyViewHolder>{
    private Context context;
    private List<Bean.DataBean.ComicsBean> comics;
    private final MyBitMapUtils myBitMapUtils;


    public MyAdapter(Context context, List<Bean.DataBean.ComicsBean> comics) {
        this.context=context;
        this.comics=comics;
        myBitMapUtils = new MyBitMapUtils();
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item, parent, false);
        MyViewHolder holder=new MyViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        Bean.DataBean.ComicsBean comicsBean = comics.get(position);
        myBitMapUtils.disPlay(holder.iv_tu,comicsBean.getCover_image_url());
        holder.tv_title.setText(comicsBean.getTitle());
    }

    @Override
    public int getItemCount() {
        return comics.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder{

        private final ImageView iv_tu;
        private final TextView tv_title;

        public MyViewHolder(View itemView) {
            super(itemView);
            iv_tu = (ImageView) itemView.findViewById(R.id.iv_tu);
            tv_title = (TextView) itemView.findViewById(R.id.tv_title);
        }
    }
}

