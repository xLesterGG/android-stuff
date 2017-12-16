package com.example.lesgo.a4t222;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;

import java.util.ArrayList;
import java.util.List;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link FragmentOne.OnFragmentInteractionListener} interface
 * to handle interaction events.
 */
public class FragmentOne extends Fragment {

    private OnFragmentInteractionListener mListener;

    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onItemClick(String a,String b);
    }

    public FragmentOne() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_one,container,false);


        List<ProfileName> profile_list = populate_list();

        ProfileAdapter adapter = new ProfileAdapter(profile_list);
        adapter.setOnItemClickListener(new ProfileAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(String a, String b) {
                mListener.onItemClick(a,b);
            }
        });

        RecyclerView recycle_view = (RecyclerView)view.findViewById(R.id.recycle_view1);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getContext());
        recycle_view.setLayoutManager(layoutManager);
        recycle_view.setItemAnimator(new DefaultItemAnimator());
        recycle_view.setAdapter(adapter);


        return view;
    }

    // TODO: Rename method, update argument and hook method into UI event

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
     * <p/>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */

    private List<ProfileName> populate_list(){
        List<ProfileName> list = new ArrayList<>();
        list.add(new ProfileName("Marlene", "Malaysian"));
        list.add(new ProfileName("Emily", "Malaysian"));
        list.add(new ProfileName("Ambrose", "Bangladesh"));
        list.add(new ProfileName("Marlene", "Malaysian"));
        list.add(new ProfileName("Emily", "Malaysian"));
        list.add(new ProfileName("Ambrose", "Bangladesh"));
        list.add(new ProfileName("Marlene", "Malaysian"));
        list.add(new ProfileName("Emily", "Malaysian"));
        list.add(new ProfileName("Ambrose", "Bangladesh"));
        list.add(new ProfileName("Marlene", "Malaysian"));
        list.add(new ProfileName("Emily", "Malaysian"));
        list.add(new ProfileName("Ambrose", "Bangladesh"));



        return list;
    }
}
