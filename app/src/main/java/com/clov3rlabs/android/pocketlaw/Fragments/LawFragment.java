package com.clov3rlabs.android.pocketlaw.Fragments;

import android.app.Activity;
import android.app.ListFragment;
import android.app.LoaderManager;
import android.content.Loader;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.app.Fragment;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.Toast;

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
    // The loader's unique id. Loader ids are specific to the Activity or
    // Fragment in which they reside.
    private static final int LOADER_ID = 1;
    // The callbacks through which we will interact with the LoaderManager.
    private LoaderManager.LoaderCallbacks<List<Law>> mCallbacks;


    private static String TAG = "LawFragment";
    private static boolean DEBUG = true;
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
        Log.d(TAG,"onCreateLoader Called");
        return new LawListLoader(getActivity().getApplicationContext());
    }

    @Override
    public void onLoadFinished(Loader<List<Law>> listLoader, List<Law> laws) {
        Log.d(TAG,"onLoadFinished");

        if(laws != null) {
            //Create Adapter with empty data
            lawListAdapter = new LawListAdapter(getActivity(), laws);
            setListAdapter(lawListAdapter);
            lawListAdapter.notifyDataSetChanged();
        }else{
            Toast.makeText(getActivity(),"No se retorno data",Toast.LENGTH_LONG);
        }

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
