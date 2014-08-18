package com.clov3rlabs.android.pocketlaw.Fragments;

import android.app.Activity;
import android.app.ListFragment;
import android.app.LoaderManager;
import android.content.Loader;
import android.os.Bundle;
import android.app.Fragment;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.LinearLayout;
import android.widget.ListAdapter;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.clov3rlabs.android.pocketlaw.Adapters.ArticleListAdapter;
import com.clov3rlabs.android.pocketlaw.Adapters.LawListAdapter;
import com.clov3rlabs.android.pocketlaw.Entities.Article;
import com.clov3rlabs.android.pocketlaw.Entities.Law;
import com.clov3rlabs.android.pocketlaw.Loaders.ArticlesLoaderLoader;
import com.clov3rlabs.android.pocketlaw.Loaders.LawListLoader;
import com.clov3rlabs.android.pocketlaw.R;

import com.clov3rlabs.android.pocketlaw.Fragments.dummy.DummyContent;

import java.util.List;

/**
 * A fragment representing a list of Items.
 * <p />
 * Large screen devices (such as tablets) are supported by replacing the ListView
 * with a GridView.
 * <p />
 * Activities containing this fragment MUST implement the CallBacks
 * interface.
 */
public class ArticleListFragment extends ListFragment implements LoaderManager.LoaderCallbacks<List<Article>> {


    private static final String ARG_LAW_ID = "law_id";
    private int mLawID;
    private OnFragmentInteractionListener mListener;

    /**
     * The fragment's ListView/GridView.
     */
    private AbsListView mListView;

    /**
     * The Adapter which will be used to populate the ListView/GridView with
     * Views.
     */
    private ArticleListAdapter mArticleListAdapter;

    private LoaderManager.LoaderCallbacks<List<Article>> mCallbacks;

    // The loader's unique id. Loader ids are specific to the Activity or
    // Fragment in which they reside.
    private static final int LOADER_ID = 2;

    // TODO: Rename and change types of parameters
    public static ArticleListFragment newInstance(int law_id) {
        ArticleListFragment fragment = new ArticleListFragment();
        Bundle args = new Bundle();
        args.putInt(ARG_LAW_ID, law_id);
        fragment.setArguments(args);
        return fragment;
    }

    /**
     * Mandatory empty constructor for the fragment manager to instantiate the
     * fragment (e.g. upon screen orientation changes).
     */
    public ArticleListFragment() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (getArguments() != null) {
            mLawID = getArguments().getInt(ARG_LAW_ID);
        }

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_article, container, false);


        mCallbacks = this;
        LoaderManager lm = getLoaderManager();
        lm.initLoader(LOADER_ID, null, mCallbacks);

        return view;
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        // Create a progress bar to display while the list loads
        ProgressBar progressBar = new ProgressBar(getActivity());
        progressBar.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT,
                LinearLayout.LayoutParams.WRAP_CONTENT, Gravity.CENTER));
        progressBar.setIndeterminate(true);
        getListView().setEmptyView(progressBar);

        // Must add the progress bar to the root of the layout
        ViewGroup root = (ViewGroup) getActivity().findViewById(android.R.id.content);
        root.addView(progressBar);
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        try {
            mListener = (OnFragmentInteractionListener) activity;
        } catch (ClassCastException e) {
            throw new ClassCastException(activity.toString()
                + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }


    /**
     * The default content for this Fragment has a TextView that is shown when
     * the list is empty. If you would like to change the text, call this method
     * to supply the text it should use.
     */
    public void setEmptyText(CharSequence emptyText) {
        View emptyView = mListView.getEmptyView();

        if (emptyText instanceof TextView) {
            ((TextView) emptyView).setText(emptyText);
        }
    }

    @Override
    public Loader<List<Article>> onCreateLoader(int i, Bundle bundle) {
        return new ArticlesLoaderLoader(getActivity().getApplicationContext(),mLawID);
    }

    @Override
    public void onLoadFinished(Loader<List<Article>> listLoader, List<Article> articles) {
        if(articles != null) {
            //Create Adapter with empty data
            mArticleListAdapter= new ArticleListAdapter(getActivity(), articles);
            setListAdapter(mArticleListAdapter);
            mArticleListAdapter.notifyDataSetChanged();
        }else{
            Toast.makeText(getActivity(), "No se retorno data", Toast.LENGTH_LONG);
        }
    }

    @Override
    public void onLoaderReset(Loader<List<Article>> listLoader) {

    }

    /**
    * This interface must be implemented by activities that contain this
    * fragment to allow an interaction in this fragment to be communicated
    * to the activity and potentially other fragments contained in that
    * activity.
    * <p>
    * See the Android Training lesson <a href=
    * "http://developer.android.com/training/basics/fragments/communicating.html"
    * >Communicating with Other Fragments</a> for more information.
    */
    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        public void onFragmentInteraction(String id);
    }

}
