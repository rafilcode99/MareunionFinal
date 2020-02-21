package com.example.mareunion.Event;

import com.example.mareunion.Model.Reunion;

public class CreateReunionEvent {

    public Reunion mReunion;

    public CreateReunionEvent(Reunion reunion) {
        mReunion = reunion;
    }
}
