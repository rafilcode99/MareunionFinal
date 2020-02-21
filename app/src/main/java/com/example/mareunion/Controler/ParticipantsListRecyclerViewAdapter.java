package com.example.mareunion.Controler;


import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;

import androidx.recyclerview.widget.RecyclerView;
import com.example.mareunion.Model.Reunion;
import com.example.mareunion.R;

import java.util.ArrayList;

public class ParticipantsListRecyclerViewAdapter extends RecyclerView.Adapter<ParticipantsListRecyclerViewAdapter.ParticipantsListViewHolder>{
  private ArrayList<String> myParticipants;

  public ParticipantsListRecyclerViewAdapter(ArrayList participants) {
    myParticipants = participants;
  }


  @NonNull
  @Override
  public ParticipantsListViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
    View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.participant_item_list, parent, false);
    ParticipantsListViewHolder holder = new ParticipantsListViewHolder(view);
    return holder;

  }

  @Override
  public void onBindViewHolder(@NonNull ParticipantsListViewHolder holder, int position) {
    String participant = myParticipants.get(position);
    holder.participants.setText(participant);

  }

  @Override
  public int getItemCount() {

    return myParticipants.size();
  }

  public class ParticipantsListViewHolder extends RecyclerView.ViewHolder{
    TextView participants;



    public ParticipantsListViewHolder(@NonNull View itemView) {
      super(itemView);
      participants = itemView.findViewById(R.id.participant_item_txt);




    }
  }
}
