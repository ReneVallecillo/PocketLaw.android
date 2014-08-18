package com.clov3rlabs.android.pocketlaw.Adapters;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.clov3rlabs.android.pocketlaw.Entities.Article;
import com.clov3rlabs.android.pocketlaw.Entities.Law;
import com.clov3rlabs.android.pocketlaw.R;

import java.util.List;

/**
 * Created by reneval on 8/11/14.
 */
public class ArticleListAdapter extends BaseAdapter {

    private Activity mContext;
    private List<Article> mList;
    private LayoutInflater mLayoutInflater = null;

    public ArticleListAdapter(Activity context, List<Article> list){
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
            convertView = li.inflate(R.layout.card_list_layout,null);
            viewHolder = new ViewHolder();
            viewHolder.mTitle = (TextView)convertView.findViewById(R.id.article_title);
            viewHolder.mText = (TextView)convertView.findViewById(R.id.article_text);
            convertView.setTag(viewHolder);
        }else{
            viewHolder = (ViewHolder) convertView.getTag();
        }

        if(mList != null ){
            viewHolder.mTitle.setText(mList.get(position).getName());
            viewHolder.mText.setText(mList.get(position).getText());
        }

        return convertView;

    }

    public void newData(List<Article> list){
        mList = list;
    }

    static class ViewHolder {
        TextView mTitle;
        TextView mText;
    }
}
