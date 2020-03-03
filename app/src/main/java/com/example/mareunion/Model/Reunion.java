package com.example.mareunion.Model;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Date;
import java.util.Locale;

public class Reunion {
  private String date;
  private String time;
  private String location;
  private String subject;
  private ArrayList<String> participants;


  public Reunion(String date, String time, String location, String subject, ArrayList<String> participants) {
    this.date = date;
    this.time = time;
    this.location = location;
    this.subject = subject;
    this.participants = participants;
  }

  public static final Comparator<Reunion> byAlpha = new Comparator<Reunion>() {
    @Override
    public int compare(Reunion r1, Reunion r2) {
      return r1.subject.compareTo(r2.subject);
    }
  };

  public static final Comparator<Reunion> byDate  = new Comparator<Reunion>() {
    @Override
    public int compare(Reunion r1, Reunion r2) {
      SimpleDateFormat mSimpleDateFormat = new SimpleDateFormat("dd/MM/yyyy hh:mm:ss", Locale.FRANCE);
      String dateStr1 = (r1.getDate().toString()+ " " + r1.getTime().toString()).toString() ;
      String dateStr2 = (r2.getDate().toString()+ " " + r2.getTime().toString()).toString() ;


      try {
        return mSimpleDateFormat.parse(dateStr1).compareTo(mSimpleDateFormat.parse(dateStr2));
      }catch (ParseException e){
        throw new IllegalArgumentException(e);
      }

    }
  };

  public String getDate() {
    return date;
  }

  public String getTime(){ return time; }

  public String getLocation() {
    return location;
  }

  public String getSubject() {
    return subject;
  }

  public ArrayList getParticipants() {
    return participants;
  }

  public void setDate(String date) {
    this.date = date;
  }

  public void setTime(String time){this.time = time;}

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