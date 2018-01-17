package com.magpiehunt.magpie.Fragments;

import android.app.Fragment;
import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.magpiehunt.magpie.Database.MagpieDatabase;
import com.magpiehunt.magpie.R;
import com.magpiehunt.magpie.WebClient.JSONParser;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link SearchCollectionsFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link SearchCollectionsFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class SearchCollectionsFragment extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    private static final String TAG = "SearchCollectionsFragment";


    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private OnFragmentInteractionListener mListener;

    public SearchCollectionsFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     *
     * @return A new instance of fragment SearchCollectionsFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static SearchCollectionsFragment newInstance() {
        SearchCollectionsFragment fragment = new SearchCollectionsFragment();
        Bundle args = new Bundle();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }

        MagpieDatabase db = MagpieDatabase.getMagpieDatabase(this.getActivity());
        JSONParser jsonParser = new JSONParser(this.getActivity());
        //jsonParser.getLandmarks();
       // jsonParser.getLandmarks();
      //  Collection c = db.collectionDao().getCollection(1);



    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        View view = inflater.inflate(R.layout.fragment_search_collections, container, false);


       // MagpieDatabase db = MagpieDatabase.getMagpieDatabase(this.getActivity());
      //  JSONParser jsonParser = new JSONParser(this.getActivity());
      /*  jsonParser.getCollections();
        //jsonParser.getLandmarks(1);
        //log.e(TAG, db.collectionDao().getCollection(1).getName());

        // jsonParser.getLandmarks(1);
        //Collection c = db.collectionDao().getCollection(1);
        TextView tc = (TextView)view.findViewById(R.id.cname);
        tc.setText( db.collectionDao().getCollection(1).getName());
        TextView tl = (TextView)view.findViewById(R.id.lname);
        //List<Landmark> landmarks = db.landmarkDao().getLandmarks(1);
        //tl.setText( db.landmarkDao().getLandmarks(1).get(0).getLandmarkName());

*/
        return view;
    }

    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnFragmentInteractionListener) {
            mListener = (OnFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnFragmentInteractionListener");
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
    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }
}
