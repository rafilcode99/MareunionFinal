package com.example.mareunion.Controler;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;

import com.example.mareunion.DI.DI;
import com.example.mareunion.Model.Reunion;
import com.example.mareunion.R;
import com.example.mareunion.Service.ReunionApiService;

import java.util.ArrayList;
import java.util.Collections;

public class SortListFragment extends DialogFragment {

    ReunionApiService mApiService;
    Button mSortByAlphaBtn;
    Button mSortByDateBtn;

    public static SortListFragment newDialogInstance(){
        SortListFragment fragment = new SortListFragment();
        return fragment;
    }


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.sortby_layout, container, false);
        mSortByAlphaBtn= view.findViewById(R.id.sortBy_Alpha_btn);
        mSortByDateBtn = view.findViewById(R.id.sortBy_Date_btn);

        mSortByAlphaBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sortByAlphabitical();
                dismiss();


            }
        });
        mSortByDateBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sortByDate();
                dismiss();
            }
        });



        return view;

    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mApiService= DI.getReunionApiService();


    }

    public void sortByAlphabitical(){
        Collections.sort(mApiService.getReunions(), Reunion.byAlpha);
        ((MainActivity) getContext()).initList();

    }

    public void sortByDate(){
        Collections.sort(mApiService.getReunions(), Reunion.byDate);
        ((MainActivity) getContext()).initList();

    }
}
