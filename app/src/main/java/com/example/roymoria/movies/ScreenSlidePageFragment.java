package com.example.roymoria.movies;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.res.Resources;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;


public class ScreenSlidePageFragment extends Fragment {

    private OnFragmentInteractionListener mListener;

    public ScreenSlidePageFragment() {
        // Required empty public constructor
    }

    // TODO: Rename and change types and number of parameters
    public static ScreenSlidePageFragment newInstance(int imageResourceIdLarge, int imageResourceId, int movieId) {
        ScreenSlidePageFragment fragment = new ScreenSlidePageFragment();

        Bundle args = new Bundle();
        args.putString("image", String.valueOf(imageResourceId));
        args.putString("imageLarge", String.valueOf(imageResourceIdLarge));
        args.putInt("title",movieId);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {

        }
    }

    @SuppressLint("ResourceAsColor")
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        ViewGroup rootView = (ViewGroup) inflater.inflate(R.layout.fragment_screen_slide_page, container, false);
        ImageView imageTop = rootView.findViewById(R.id.TopIV);
        ImageView imageCenter = rootView.findViewById(R.id.centerIV);
        Bundle args = getArguments();
        Resources res = getResources();
        String[] movie = res.getStringArray(args.getInt("title"));
        int imageId = Integer.parseInt(args.getString("image",""));
        imageCenter.setImageResource(imageId);
        int imageIdLarge = Integer.parseInt(args.getString("imageLarge",""));
        imageTop.setImageResource(imageIdLarge);
        TextView title = rootView.findViewById(R.id.title);
        title.setText(movie[0]);
        TextView context = rootView.findViewById(R.id.intro);;
        context.setText(movie[1]);
        TextView release = rootView.findViewById(R.id.released);
        release.setText(movie[3]);
        return rootView;
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
//        if (context instanceof OnFragmentInteractionListener) {
//            mListener = (OnFragmentInteractionListener) context;
//        } else {
//            throw new RuntimeException(context.toString()
//                    + " must implement OnFragmentInteractionListener");
//        }
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
