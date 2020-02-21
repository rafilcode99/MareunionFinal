package com.example.mareunion.DI;

import com.example.mareunion.*;
import com.example.mareunion.Service.ReunionApiService;
import com.example.mareunion.Service.FakeReunionApiService;

public class DI {
  public static ReunionApiService myApiService = new FakeReunionApiService();

  public static ReunionApiService getReunionApiService(){return myApiService;}

  public static ReunionApiService getNewInstanceApiService(){
    return new FakeReunionApiService();
  }
}