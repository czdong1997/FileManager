package com.example.cslab.filemanager;


import android.provider.ContactsContract;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.io.File;
import java.util.List;
import java.util.Map;

import static android.content.ContentValues.TAG;

/**
 * Created by z.z on 2017/7/14.
 */

public class FileAdapter extends RecyclerView.Adapter<FileAdapter.ViewHolder> implements View.OnClickListener{

    private List<Map<String,Object>> list;
    private OnItemClickListener onItemClickListener = null;

    public FileAdapter(List<Map<String,Object>> list){
            this.list = list;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.file_item,parent,false);
        final ViewHolder holder = new ViewHolder(view);
        holder.fileView.setOnClickListener(this); //注册点击函数
        return holder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        Map<String,Object> map = list.get(position);
        int Image = Globals.imgsMap.get(map.get("extName").toString());
        holder.fileImage.setImageResource(Image);
        holder.fileName.setText((String)map.get("name"));
        holder.fileView.setTag(position);
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    @Override
    public void onClick(View view) {                   //点击的时候会调用这个函数
        if (onItemClickListener!=null){
            onItemClickListener.onItemClick(view, (int)view.getTag()); //2.设置调用onItemClick函数
        }
    }

    public void setOnItemClickListener(OnItemClickListener listener){
        this.onItemClickListener = listener;//1.初始化的时候传进来的linsenter,主要是把函数体onItemClick传进来
    }

    static class ViewHolder extends RecyclerView.ViewHolder{

        ImageView fileImage;
        TextView fileName;
        View fileView;

        public ViewHolder(View itemView) {
            super(itemView);
            fileView = itemView;
            fileImage = (ImageView)itemView.findViewById(R.id.file_image);
            fileName = (TextView)itemView.findViewById(R.id.file_name);
        }
    }

    public static interface OnItemClickListener{
        void onItemClick(View view, int position);
    }
}
