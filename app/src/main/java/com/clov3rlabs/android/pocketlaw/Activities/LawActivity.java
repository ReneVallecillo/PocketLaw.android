package com.clov3rlabs.android.pocketlaw.Activities;

import android.app.Activity;
import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.net.Uri;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import com.clov3rlabs.android.pocketlaw.Entities.Article;
import com.clov3rlabs.android.pocketlaw.Fragments.ArticleFragment;
import com.clov3rlabs.android.pocketlaw.Fragments.ArticleListFragment;
import com.clov3rlabs.android.pocketlaw.Fragments.LawListFragment;
import com.clov3rlabs.android.pocketlaw.R;

public class LawActivity extends Activity implements LawListFragment.OnLawSelectedListener, ArticleListFragment.OnArticleSelectedListener,
ArticleFragment.OnArticleInteractionListener{

    private Fragment mFragment;
    private Article mSelectedArticle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_law);
        if (savedInstanceState == null) {
            getFragmentManager().beginTransaction()
                    .add(R.id.container, new LawListFragment())
                    .commit();
        }
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.law, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    public void displayFragment(int type, int id){

        FragmentManager manager = getFragmentManager();
        FragmentTransaction ft = manager.beginTransaction();

        switch (type){
            case 0:
                mFragment = ArticleListFragment.newInstance(id);
                break;

            case 1:
                mFragment = ArticleFragment.newInstance(mSelectedArticle);
                break;
        }

        ft.replace(R.id.container, mFragment );
        ft.commit();
    }

    public void displayFragment(int type, int id, Article article){
        mSelectedArticle = article;
        displayFragment(type,id);
    }

    @Override
    public void onLawSelected(int lawId) {
        displayFragment(0,lawId);
    }

    @Override
    public void onArticleSelected(Article article) {
        displayFragment(1,article.getId(),article);
    }

    @Override
    public void onArticleInteration(int lawid) {

    }
}
