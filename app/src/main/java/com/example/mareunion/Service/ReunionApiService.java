package com.example.mareunion.Service;

import com.example.mareunion.Model.Reunion;

import java.util.List;

public interface ReunionApiService {

    List<Reunion> getReunions();

    void createReunion(Reunion reunion);

    void deleteReunion(Reunion mReunion);





}
