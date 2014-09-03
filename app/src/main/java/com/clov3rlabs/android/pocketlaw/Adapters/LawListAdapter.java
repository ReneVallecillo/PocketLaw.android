package com.clov3rlabs.android.pocketlaw.Adapters;

import android.app.Activity;
import android.content.Context;
import android.media.Image;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.clov3rlabs.android.pocketlaw.Entities.Law;
import com.clov3rlabs.android.pocketlaw.R;

import java.util.List;

/**
 * Created by reneval on 8/11/14.
 */
public class LawListAdapter extends BaseAdapter {

    private Activity mContext;
    private List<Law> mList;
    private LayoutInflater mLayoutInflater = null;

    public LawListAdapter(Activity context, List<Law> list){
        mContext = context;
        mList = list;
        mLayoutInflater = (LayoutInflater) mContext
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public int getCount() {
        return mList.size();
    }

    @Override
    public Object getItem(int i) {
        return mList.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup viewGroup) {
        ViewHolder viewHolder;

        if (convertView == null){
            LayoutInflater li = (LayoutInflater) mContext
                    .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = li.inflate(R.layout.list_layout,null);
            viewHolder = new ViewHolder();
            viewHolder.mTextView = (TextView)convertView.findViewById(R.id.list_row);
            convertView.setTag(viewHolder);
        }else{
            viewHolder = (ViewHolder) convertView.getTag();
        }

        if(mList != null ){
            viewHolder.mTextView.setText(mList.get(position).getName());
            viewHolder.articleId = mList.get(position).getId();

        }

        return convertView;

    }

    public void newData(List<Law> list){
        mList = list;
    }

    public static class ViewHolder {
        public TextView mTextView;
        public int articleId;
    }
}
