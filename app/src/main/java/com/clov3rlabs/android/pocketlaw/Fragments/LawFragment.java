package com.clov3rlabs.android.pocketlaw.Fragments;

import android.app.Activity;
import android.app.ListFragment;
import android.app.LoaderManager;
import android.content.Loader;
import android.net.Uri;
import android.os.Bundle;
import android.app.Fragment;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.ProgressBar;

import com.clov3rlabs.android.pocketlaw.Adapters.LawListAdapter;
import com.clov3rlabs.android.pocketlaw.Entities.Law;
import com.clov3rlabs.android.pocketlaw.Loaders.LawListLoader;
import com.clov3rlabs.android.pocketlaw.R;

import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link LawFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link LawFragment#newInstance} factory method to
 * create an instance of this fragment.
 *
 */
public class LawFragment extends ListFragment implements LoaderManager.LoaderCallbacks<List<Law>> {


    private OnFragmentInteractionListener mListener;
    private LawListAdapter lawListAdapter;

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.

     * @return A new instance of fragment LawFragment.
     */

    public static LawFragment newInstance() {
        LawFragment fragment = new LawFragment();

        return fragment;
    }
    public LawFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {

        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_law, container, false);

        // Create a progress bar to display while the list loads
        ProgressBar progressBar = new ProgressBar(getActivity());
        progressBar.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT,
                LinearLayout.LayoutParams.WRAP_CONTENT, Gravity.CENTER));
        progressBar.setIndeterminate(true);
        getListView().setEmptyView(progressBar);

        // Must add the progress bar to the root of the layout
        ViewGroup root = (ViewGroup) view.findViewById(android.R.id.content);
        root.addView(progressBar);

        //Create Adapter with empty data
        lawListAdapter = new LawListAdapter(getActivity(),null);
        setListAdapter(lawListAdapter);

        return view;
    }

    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
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

    //LoaderManager Methods

    @Override
    public Loader<List<Law>> onCreateLoader(int i, Bundle bundle) {
        return new LawListLoader(getActivity().getApplicationContext());
    }

    @Override
    public void onLoadFinished(Loader<List<Law>> listLoader, List<Law> laws) {

        lawListAdapter.newData(laws);
        lawListAdapter.notifyDataSetChanged();

    }

    @Override
    public void onLoaderReset(Loader<List<Law>> listLoader) {

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
        public void onFragmentInteraction(Uri uri);
    }

    //TODO implement loader

}
