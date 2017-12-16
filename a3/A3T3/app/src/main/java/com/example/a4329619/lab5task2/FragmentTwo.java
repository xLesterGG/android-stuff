package com.example.a4329619.lab5task2;

import android.content.Context;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.renderscript.ScriptGroup;
import android.support.v4.app.Fragment;
import android.text.Spannable;
import android.text.SpannableStringBuilder;
import android.text.style.ForegroundColorSpan;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link FragmentTwo.OnFragmentInteractionListener} interface
 * to handle interaction events.
 */
public class FragmentTwo extends Fragment {

    private OnFragmentInteractionListener mListener;

    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void OnLoad(String msg);
    }

    public FragmentTwo() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        String textfile="";
        String abc ="aaaaa";
        Integer count = 0;
        Bundle bundle = getArguments();
        String word = bundle.getString("cValue");
        Log.d("at create view 2",word);

        View view = inflater.inflate(R.layout.fragment_two, container, false);

        TextView tvWord = (TextView)view.findViewById(R.id.tvWord);
        TextView tvFound1 = (TextView)view.findViewById(R.id.tvFound1);



        InputStream inputStream = getResources().openRawResource(R.raw.android_dev_agreement);

        Scanner in = null;
        try {
            in = new Scanner(inputStream);
            while(in.hasNext())
            {
                String line=in.nextLine().toLowerCase();
                if(line.contains(word))
                {
                    count+=1;

                    SpannableStringBuilder sb = new SpannableStringBuilder(line); // for coloring text
                    Pattern p = Pattern.compile(word, Pattern.CASE_INSENSITIVE);
                    Matcher m = p.matcher(line);
                    while (m.find()){
                        sb.setSpan(new ForegroundColorSpan(Color.rgb(0, 0, 255)), m.start(), m.end(), Spannable.SPAN_INCLUSIVE_INCLUSIVE);
                    }

                    tvFound1.setText(sb);
                }
            }
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        if(count>5)
            tvWord.setTextColor(Color.parseColor("#FF0000"));
        else
            tvWord.setTextColor(Color.parseColor("#00FF00"));





        tvWord.setText(word);

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
     * <p>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */

}
