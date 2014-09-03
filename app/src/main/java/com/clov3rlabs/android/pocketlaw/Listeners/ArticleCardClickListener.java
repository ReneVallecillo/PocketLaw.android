package com.clov3rlabs.android.pocketlaw.Listeners;

import android.view.View;

import com.clov3rlabs.android.pocketlaw.Entities.Article;
import com.clov3rlabs.android.pocketlaw.Fragments.ArticleListFragment;

import it.gmariotti.cardslib.library.internal.Card;

/**
 * Created by reneval on 9/3/14.
 */
public class ArticleCardClickListener implements Card.OnCardClickListener {
    private Article mArticle;
    private ArticleListFragment.OnArticleSelectedListener mListener;

    @Override
    public void onClick(Card card, View view) {
        mListener.onArticleSelected(mArticle);
    }

    public ArticleCardClickListener(Article article, ArticleListFragment.OnArticleSelectedListener listener){
        mArticle = article;
        mListener = listener;
    }
}
