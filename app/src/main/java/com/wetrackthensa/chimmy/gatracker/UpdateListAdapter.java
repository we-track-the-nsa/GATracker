package com.wetrackthensa.chimmy.gatracker;

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
//        viewHolder.nameText.setText(updatesList.get(i).getsourceh());
//        viewHolder.updateText.setText(updatesList.get(i).getbodyh());
//        viewHolder.sourceText.setText(updatesList.get(i).getorg());

        viewHolder.agencyText.setText(updatesList.get(i).getagencyh());
        viewHolder.sourceText.setText(updatesList.get(i).getsourceh());
        viewHolder.bodyText.setText(updatesList.get(i).getbodyh());
        viewHolder.timeText.setText(updatesList.get(i).gettimeh());
        viewHolder.titleText.setText(updatesList.get(i).gettitleh());

    }

    @Override
    public int getItemCount() {
        return updatesList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        View mView;
        public TextView agencyText;
        public TextView sourceText;
        public TextView bodyText;
        public TextView timeText;
        public TextView titleText;
        public ViewHolder(View itemView) {
            super(itemView);
            mView=itemView;
            agencyText=(TextView)mView.findViewById(R.id.agency_text);
            sourceText=(TextView)mView.findViewById((R.id.source_view));
            bodyText=(TextView)mView.findViewById((R.id.body_view));
            timeText=(TextView)mView.findViewById((R.id.time_view));
            titleText=(TextView)mView.findViewById((R.id.title_view));

        }
    }
}
