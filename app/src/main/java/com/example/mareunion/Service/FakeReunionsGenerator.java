package com.example.mareunion.Service;

import com.example.mareunion.Model.Reunion;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public abstract class FakeReunionsGenerator  {

    public static List<Reunion> FAKE_REUNIONS = Arrays.asList(
            new Reunion("10/03/20", "11:00", "djil", "Gestion", new ArrayList(Arrays.asList("jhon@gmail.com","tommas@gmail.com)","sandrine@gmail.com")) ),
            new Reunion("10/06/20", "10:00", "Selma", "Projet", new ArrayList(Arrays.asList("cloé@gmail.com","jane@gmail.com)", "jack@gmail.com")) ),
            new Reunion("10/03/20", "07:00", "djil", "Ouverture", new ArrayList(Arrays.asList("jhon@gmail.com","tommas@gmail.com)","sandrine@gmail.com")) ),
            new Reunion("10/10/20", "13:00", "djill", "Particulier", new ArrayList(Arrays.asList("cloé@gmail.com","jane@gmail.com)", "jack@gmail.com")) ),
            new Reunion("10/03/20", "09:00", "Salle", "Urgent", new ArrayList(Arrays.asList("jhon@gmail.com","tommas@gmail.com)","sandrine@gmail.com")) ),
            new Reunion("30/03/20", "10:00", "sjill", "Solution", new ArrayList(Arrays.asList("djil@gmail.com","sil@gmail.com)","cloé@gmail.com")) ),
            new Reunion("15/04/20", "10:00", "Salle", "Instalation", new ArrayList(Arrays.asList("cloé@gmail.com","jane@gmail.com)", "jack@gmail.com")) ),
            new Reunion("10/03/20", "10:00", "djil", "Présentation", new ArrayList(Arrays.asList("jhon@gmail.com","tommas@gmail.com)","sandrine@gmail.com")) ),
            new Reunion("05/04/20", "10:00", "Salle", "Facture", new ArrayList(Arrays.asList("djil@gmail.com","sil@gmail.com")) ),
            new Reunion("10/03/20", "09:30", "djil", "Réglement", new ArrayList(Arrays.asList("cloé@gmail.com","jane@gmail.com)", "jack@gmail.com")) ),
            new Reunion("20/04/20", "10:00", "Salle", "Conflit", new ArrayList(Arrays.asList("jhon@gmail.com","tommas@gmail.com)","sandrine@gmail.com")) )
    );

static List<Reunion> generateFakeReunions(){return new ArrayList<>(FAKE_REUNIONS);}
}
