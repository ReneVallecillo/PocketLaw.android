package com.clov3rlabs.android.pocketlaw.Entities;

import android.content.Context;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.clov3rlabs.android.pocketlaw.R;

import it.gmariotti.cardslib.library.internal.Card;
import it.gmariotti.cardslib.library.internal.CardHeader;
import it.gmariotti.cardslib.library.internal.CardThumbnail;

/**
 * Created by reneval on 8/18/14.
 */
public class ArticleCard extends Card {

    protected TextView mTitle;
    protected TextView mText;

    protected String text;
    protected String title;

    protected int count;
    protected int resourceIdThumbnail;



    public ArticleCard(Context context) {
        super(context, R.layout.card_article_inner_content);
    }

    public ArticleCard(Context context,int innerLayout) {
        super(context, innerLayout);
    }


    public void init() {


        //Add thumbnail
        CardThumbnail cardThumbnail = new CardThumbnail(mContext);

        if (resourceIdThumbnail==0)
            cardThumbnail.setDrawableResource(R.drawable.ic_launcher);
        else{
            cardThumbnail.setDrawableResource(resourceIdThumbnail);
        }

        addCardThumbnail(cardThumbnail);

        //Only for test, some cards have different clickListeners
        if (count==12){

            setTitle(title + " No Click");
            setClickable(false);

        }else if (count==20){

            setTitle(title + " Partial Click");
            addPartialOnClickListener(Card.CLICK_LISTENER_CONTENT_VIEW,new OnCardClickListener() {
                @Override
                public void onClick(Card card, View view) {
                    Toast.makeText(getContext(), "Partial click Listener card=" + title, Toast.LENGTH_SHORT).show();
                }
            });

        }else{

            //Add ClickListener
            setOnClickListener(new OnCardClickListener() {
                @Override
                public void onClick(Card card, View view) {
                    Toast.makeText(getContext(), "Click Listener card=" + title, Toast.LENGTH_SHORT).show();
                }
            });

        }


        //Swipe
        if (count>5 && count<13){

            setTitle(title + " Swipe enabled");
            setSwipeable(true);
            setOnSwipeListener(new OnSwipeListener() {
                @Override
                public void onSwipe(Card card) {
                    Toast.makeText(getContext(), "Removed card=" + title, Toast.LENGTH_SHORT).show();
                }
            });
        }

    }

    @Override
    public void setupInnerViewElements(ViewGroup parent, View view) {

        Log.d(TAG, "Entro a inner view");
        //Retrieve elements
        mTitle = (TextView) parent.findViewById(R.id.card_article_title);
        mText = (TextView) parent.findViewById(R.id.card_article_text);

        if (mTitle != null){
            mTitle.setText(title);
            Log.d(TAG,"se encontro title");
        }

        if (mText != null)
            mText.setText(text);

    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}
