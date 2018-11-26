package com.wetrackthensa.chimmy.gatracker;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

public class UpdateListAdapter extends RecyclerView.Adapter<UpdateListAdapter.ViewHolder> {
public List<updates> updatesList;
public UpdateListAdapter(List <updates> updatesList)
{
    this.updatesList=updatesList;
}

    @Override
    public ViewHolder onCreateViewHolder( ViewGroup viewGroup, int i) {
        View view=LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.list_item,viewGroup,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder( ViewHolder viewHolder, int i) {
        viewHolder.nameText.setText(updatesList.get(i).getsourceh());
        viewHolder.updateText.setText(updatesList.get(i).getbodyh());

    }

    @Override
    public int getItemCount() {
        return updatesList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        View mView;
        public TextView nameText;
        public TextView updateText;
        public ViewHolder(View itemView) {
            super(itemView);
            mView=itemView;
            nameText=(TextView)mView.findViewById(R.id.agency_name);
            updateText=(TextView)mView.findViewById((R.id.agency_update));
        }
    }
}
