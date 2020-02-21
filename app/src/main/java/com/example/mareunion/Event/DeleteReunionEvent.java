package com.example.mareunion.Event;

import com.example.mareunion.Model.Reunion;

public class DeleteReunionEvent {

    public Reunion mReunion;

    public DeleteReunionEvent(Reunion reunion) {
        mReunion = reunion;
    }
}
