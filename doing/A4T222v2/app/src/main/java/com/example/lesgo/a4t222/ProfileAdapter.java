package com.example.lesgo.a4t222;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by lesgo on 10/25/2016.
 */
public class ProfileAdapter extends RecyclerView.Adapter<ProfileAdapter.CustomViewHolder>{

    private List<ProfileName> profile_list;

    public ProfileAdapter(List<ProfileName> list){
        profile_list = list;
    }

    private OnItemClickListener listener;

    public interface OnItemClickListener{
        void onItemClick(View itemView, int position);
    }

    public void setOnItemClickListener(OnItemClickListener listener) {
        this.listener = listener;
    }

    public class CustomViewHolder extends RecyclerView.ViewHolder{

        public TextView txtName,txtNationality;
        public CustomViewHolder(View itemView) {
            super(itemView);
            txtName = (TextView)itemView.findViewById(R.id.name);
            txtNationality = (TextView)itemView.findViewById(R.id.nationality);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Log.d("aaaa", txtName.getText().toString() + txtNationality.getText().toString());
                    String name = txtName.getText().toString();
                    String nat = txtNationality.getText().toString();


                    Intent intent = new Intent();
                    ArrayList<ProfileName> profilelist = new ArrayList<>();
                    ProfileName a = new ProfileName(name,nat);
                    profilelist.add(a);

                    intent.putParcelableArrayListExtra("profile",profilelist);
                    //setResult(RESULT_OK,intent);
                    //finish();

                }
            });
        }
    }

    @Override
    public ProfileAdapter.CustomViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        LayoutInflater inflator = LayoutInflater.from(context);
        View view = inflator.inflate(R.layout.row_view,parent,false);
        return new CustomViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ProfileAdapter.CustomViewHolder holder, int position) {
        ProfileName profile = profile_list.get(position);
        holder.txtName.setText(profile.getmName());
        holder.txtNationality.setText(profile.getmNationality());

    }

    @Override
    public int getItemCount() {
        return profile_list.size();
    }
}
