package com.example.mareunion.Controler;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.RecyclerView;

import com.amulyakhare.textdrawable.TextDrawable;
import com.amulyakhare.textdrawable.util.ColorGenerator;
import com.example.mareunion.R;

import com.example.mareunion.Model.Reunion;
import com.example.mareunion.Event.DeleteReunionEvent;


import com.example.mareunion.Service.ReunionApiService;

import org.greenrobot.eventbus.EventBus;

import java.util.ArrayList;

public class ReunionsListRecyclerViewAdapter extends RecyclerView.Adapter<ReunionsListRecyclerViewAdapter.ReunionsListViewHolder> {

    private ArrayList<Reunion> mReunionsList;
    private ReunionApiService mApiService;



    public ReunionsListRecyclerViewAdapter (ArrayList<Reunion> items) {
        mReunionsList = items;
    }

    @NonNull
    @Override
    public ReunionsListViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.reunion_item_list2, parent, false);
        ReunionsListViewHolder holder = new ReunionsListViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull ReunionsListViewHolder holder, int position) {

        final Reunion reunion = mReunionsList.get(position);
        holder.subject.setText(reunion.getSubject());
        holder.Date.setText(reunion.getDate());
        holder.Time.setText(reunion.getTime());
        holder.Location.setText(reunion.getLocation());


        String firstLetter = String.valueOf(reunion.getSubject().charAt(0));
        ColorGenerator generator = ColorGenerator.MATERIAL;
        int color = generator.getColor(reunion);

        TextDrawable drawable = TextDrawable.builder()
                .buildRound(firstLetter, color); // radius in px

        holder.picture.setImageDrawable(drawable);


        holder.Delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                EventBus.getDefault().post(new DeleteReunionEvent(reunion));




           }
        });
        ParticipantsListRecyclerViewAdapter participantsAdapter = new ParticipantsListRecyclerViewAdapter(reunion.getParticipants());
        holder.Participants.setAdapter(participantsAdapter);
        holder.Participants.addItemDecoration(new DividerItemDecoration(holder.Participants.getContext(), DividerItemDecoration.HORIZONTAL));
    }

    @Override
    public int getItemCount() {
        return mReunionsList.size();
    }


    public class ReunionsListViewHolder extends RecyclerView.ViewHolder {

        ImageView picture;
        TextView subject;
        TextView Location;
        TextView Date;
        ImageButton Delete;
        RecyclerView Participants;
        TextView Time;



        public ReunionsListViewHolder(@NonNull View itemView) {
            super(itemView);
            picture= itemView.findViewById(R.id.ItemImageView);
            subject= itemView.findViewById(R.id.subject_txt);
            Location= itemView.findViewById(R.id.location_txt);
            Date= itemView.findViewById(R.id.date_txt);
            Delete= itemView.findViewById(R.id.delete_btn);
            Participants= itemView.findViewById(R.id.emails_recyclerView);
            Time = itemView.findViewById(R.id.time_txt);



        }
    }





}
