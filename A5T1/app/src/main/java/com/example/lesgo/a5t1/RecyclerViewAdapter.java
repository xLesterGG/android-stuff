package com.example.lesgo.a5t1;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.TextView;

import java.util.List;

/**
 * Created by lesgo on 11/1/2016.
 */
public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.CustomViewHolder> {


    private List<Contact> contact_list;
    public RecyclerViewAdapter(List<Contact> list) {
        contact_list = list;
    }

    public class CustomViewHolder extends RecyclerView.ViewHolder{

        public TextView txtName,txtContact,txtID;
        public CustomViewHolder(View itemView){
            super(itemView);
            txtName = (TextView)itemView.findViewById(R.id.txtName);
            txtContact = (TextView)itemView.findViewById(R.id.txtContact);
            txtID = (TextView)itemView.findViewById(R.id.id);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    String name = txtName.getText().toString();
                    String contact = txtContact.getText().toString();
                    String id = txtID.getText().toString();

                    Log.d("testlog",name+contact+id);
                    listener.onItemClick(name,contact,id);
                    //do something
                }
            });

            itemView.setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View v) {
                    String id = txtID.getText().toString();
                    Llistener.OnLongItemClick(id);
                    return true;
                }
            });
        }

    }

    public OnItemClickListener listener;

    public interface OnItemClickListener{
        void onItemClick(String name , String contact,String id);
    }

    public void setOnItemClickListener(OnItemClickListener listener) {
        this.listener = listener;
    }


    public OnLongClickListener Llistener;

    public interface OnLongClickListener{
        void OnLongItemClick(String id);
    }

    public void setOnLongClickListener(OnLongClickListener listener)
    {
        this.Llistener = listener;
    }


    @Override
    public RecyclerViewAdapter.CustomViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        LayoutInflater inflator = LayoutInflater.from(context);
        View v = inflator.inflate(R.layout.row_view,parent,false);
        return new CustomViewHolder(v);

    }

    @Override
    public void onBindViewHolder(RecyclerViewAdapter.CustomViewHolder holder, int position) {
        Contact c = contact_list.get(position);
        holder.txtName.setText(c.getName());
        holder.txtContact.setText(c.getContact());
        holder.txtID.setText(String.valueOf(c.getId()));
    }

    @Override
    public int getItemCount() {
       if(contact_list!=null)
        {
            return contact_list.size();
        }
        else
       {
            return 0;
       }
    }
}
