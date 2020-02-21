package com.example.mareunion.Service;

import com.example.mareunion.Model.Reunion;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public abstract class FakeReunionsGenerator  {

    public static List<Reunion> FAKE_REUNIONS = Arrays.asList(
            new Reunion("10/03/20, 10:00h", "Salle 1", "Gestion", new ArrayList(Arrays.asList("jhon@gmail.com","tommas@gmail.com)","sandrine@gmail.com")) ),
            new Reunion("01/03/20, 10:00h", "Salle 2", "Projet", new ArrayList(Arrays.asList("cloé@gmail.com","jane@gmail.com)", "jack@gmail.com")) ),
            new Reunion("05/03/20, 10:00h", "Salle 3", "Ouverture", new ArrayList(Arrays.asList("jhon@gmail.com","tommas@gmail.com)","sandrine@gmail.com")) ),
            new Reunion("20/02/20, 10:00h", "Salle 4", "Particulier", new ArrayList(Arrays.asList("cloé@gmail.com","jane@gmail.com)", "jack@gmail.com")) ),
            new Reunion("25/01/20, 10:00h", "Salle 5", "Urgent", new ArrayList(Arrays.asList("jhon@gmail.com","tommas@gmail.com)","sandrine@gmail.com")) ),
            new Reunion("30/03/20, 10:00h", "Salle 6", "Solution", new ArrayList(Arrays.asList("djil@gmail.com","sil@gmail.com)","cloé@gmail.com")) ),
            new Reunion("15/04/20, 10:00h", "Salle 2", "Instalation", new ArrayList(Arrays.asList("cloé@gmail.com","jane@gmail.com)", "jack@gmail.com")) ),
            new Reunion("10/03/20, 10:00h", "Salle 1", "Présentation", new ArrayList(Arrays.asList("jhon@gmail.com","tommas@gmail.com)","sandrine@gmail.com")) ),
            new Reunion("05/04/20, 10:00h", "Salle 4", "Facture", new ArrayList(Arrays.asList("djil@gmail.com","sil@gmail.com")) ),
            new Reunion("25/04/20, 10:00h", "Salle 5", "Réglement", new ArrayList(Arrays.asList("cloé@gmail.com","jane@gmail.com)", "jack@gmail.com")) ),
            new Reunion("20/04/20, 10:00h", "Salle 3", "Conflit", new ArrayList(Arrays.asList("jhon@gmail.com","tommas@gmail.com)","sandrine@gmail.com")) )
    );

static List<Reunion> generateFakeReunions(){return new ArrayList<>(FAKE_REUNIONS);}
}
