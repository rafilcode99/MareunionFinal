package com.example.mareunion.Model;

import java.util.ArrayList;

public class Reunion {
  private String time;
  private String location;
  private String subject;
  private ArrayList<String> participants;



  public Reunion(String time, String location, String subject, ArrayList participants) {
    this.time = time;
    this.location = location;
    this.subject = subject;
    this.participants = participants;
  }

  public String getTime() {
    return time;
  }

  public String getLocation() {
    return location;
  }

  public String getSubject() {
    return subject;
  }

  public ArrayList getParticipants() {
    return participants;
  }

  public void setTime(String time) {
    this.time = time;
  }

  public void setLocation(String location) {
    this.location = location;
  }

  public void setSubject(String subject) {
    this.subject = subject;
  }

  public void setParticipants(ArrayList participants) {
    this.participants = participants;
  }
}