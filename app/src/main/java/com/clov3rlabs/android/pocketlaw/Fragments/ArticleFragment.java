package com.clov3rlabs.android.pocketlaw.Fragments;

import android.app.Activity;
import android.net.Uri;
import android.os.Bundle;
import android.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ScrollView;

import com.clov3rlabs.android.pocketlaw.Entities.Article;
import com.clov3rlabs.android.pocketlaw.Entities.ArticleCard;
import com.clov3rlabs.android.pocketlaw.Entities.ArticleFullCard;
import com.clov3rlabs.android.pocketlaw.R;

import it.gmariotti.cardslib.library.view.CardView;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link com.clov3rlabs.android.pocketlaw.Fragments.ArticleFragment.OnArticleInteractionListener} interface
 * to handle interaction events.
 * Use the {@link ArticleFragment#newInstance} factory method to
 * create an instance of this fragment.
 *
 */
public class ArticleFragment extends Fragment {
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_ARTICLE_TEXT = "articleText";
    private static final String ARG_ARTICLE_TITLE = "articleTitle";

    private String mArticleTitle;
    private String mArticleText;

    private OnArticleInteractionListener mListener;

    protected ScrollView mScrollView;
    private CardView mCardview;
    private ArticleFullCard mArticleCard;


    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param article Parameter 1.
     * @return A new instance of fragment ArticleFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static ArticleFragment newInstance(Article article) {
        ArticleFragment fragment = new ArticleFragment();
        Bundle args = new Bundle();
        args.putString(ARG_ARTICLE_TEXT,article.getText());
        args.putString(ARG_ARTICLE_TITLE, article.getName());
        fragment.setArguments(args);
        return fragment;
    }

    public ArticleFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mArticleText = getArguments().getString(ARG_ARTICLE_TEXT);
            mArticleTitle = getArguments().getString(ARG_ARTICLE_TITLE);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Infla1te the layout for this fragment
        return inflater.inflate(R.layout.fragment_article, container, false);

    }

    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(int id) {
        if (mListener != null) {
            mListener.onArticleInteration(id);
        }
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        mScrollView = (ScrollView) getActivity().findViewById(R.id.card_scrollview);

        //Create a Card
        mArticleCard = new ArticleFullCard(getActivity());
        mArticleCard.setId("myId");
        mArticleCard.setTitle(mArticleTitle);
        mArticleCard.setText(mArticleText);

        //Set card in the cardView
        mCardview = (CardView) getActivity().findViewById(R.id.carddemo_cardBirth);
        mCardview.setCard(mArticleCard);

    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        try {
            mListener = (OnArticleInteractionListener) activity;
        } catch (ClassCastException e) {
            throw new ClassCastException(activity.toString()
                    + " must implement OnListArticleSelectedListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
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
    public interface OnArticleInteractionListener {
        // TODO: Update argument type and name
        public void onArticleInteration(int lawid);
    }

}
