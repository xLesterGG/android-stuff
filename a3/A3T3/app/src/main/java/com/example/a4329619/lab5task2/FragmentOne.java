package com.example.a4329619.lab5task2;

import android.content.Context;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;


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
        void onFragmentClick(String clicked);
    }

    public FragmentOne() {
        // Required empty public constructor
    }




    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_one,container,false);
        ListView lv = (ListView)view.findViewById(R.id.listview);
        final TextView tv = (TextView)view.findViewById(R.id.txtview);

        InputStream inputStream = getResources().openRawResource(R.raw.android_dev_agreement);
        Set<String> words = getWords(inputStream);


        ArrayList<String> newA = new ArrayList<String>();

        for (String s : words) {
            newA.add(s);
        }

        ArrayAdapter<String>adapter= new ArrayAdapter<String>(getContext(),android.R.layout.simple_list_item_1,newA);
        lv.setAdapter(adapter);

        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

                String input = (String)adapterView.getItemAtPosition(i);
                mListener.onFragmentClick(input); // pass to listener

                tv.setText(input);

                if(input.length()>5)
                {
                    tv.setTextColor(Color.parseColor("#FF0000"));
                }
                else
                {
                    tv.setTextColor(Color.parseColor("#00FF00"));

                }

            }
        });


        return view;
    }

    // TODO: Rename method, update argument and hook method into UI event
   /* public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
    }*/

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


    private Set<String> getWords(InputStream res)
    {
        BufferedReader br=new BufferedReader(new InputStreamReader(res));
        String line;
        Set<String> words = new HashSet<String>(2000); // to store unique words
        try
        {
            while ((line = br.readLine()) != null)
            {
                String[] wordsOnLine = line.split("[\\p{Punct}\\s}]");
                for (String w : wordsOnLine)
                {
                    if (w.trim().length() < 2)
                        continue; // ignore empty and single char
                    words.add(w.toLowerCase());
                }
            }
        } catch (IOException iox)
        {
            words = null; // this is evil
        }
        return words;
    }
}
