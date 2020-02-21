package com.example.mareunion.Controler;

import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.DialogFragment;

import com.example.mareunion.Event.CreateReunionEvent;
import com.example.mareunion.R;
import com.example.mareunion.Service.ReunionApiService;
import com.example.mareunion.Model.Reunion;
import com.example.mareunion.DI.DI;

import org.greenrobot.eventbus.EventBus;

import java.util.ArrayList;
import java.util.Arrays;


public class AddReunionFragment extends DialogFragment {

    private ReunionApiService mApiService;
    private ArrayList<Reunion> mReunions;

    public static AddReunionFragment newDialogInstance(){
        AddReunionFragment dialog = new AddReunionFragment();
        return dialog;
    }



    private Toolbar mToolbar;
    private ImageButton mReturnButton;
    private TextView mTextView;

    private EditText mSubjectInput;
    private EditText mDateInput;
    private EditText mLocationInput;
    private EditText mParticipantsInput;

    private Button mAddButton;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.new_reunion_layout, container, false);
        mToolbar = view.findViewById(R.id.toolbar_new);
        mReturnButton = view.findViewById(R.id.return_btn);
        mTextView = view.findViewById(R.id.toolbar_txt);

        mSubjectInput = view.findViewById(R.id.subject_input);
        mDateInput = view.findViewById(R.id.date_input);
        mLocationInput = view.findViewById(R.id.location_input);
        mParticipantsInput = view.findViewById(R.id.participants_input);

        mAddButton = view.findViewById(R.id.add_btn);

        mApiService = DI.getNewInstanceApiService();



        mReturnButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                exit();
            }
        });

        mAddButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Reunion newReunion = new Reunion(mDateInput.getText().toString(), mLocationInput.getText().toString(), mSubjectInput.getText().toString(), new ArrayList(Arrays.asList("djil", "silé")));
                EventBus.getDefault().post(new CreateReunionEvent(newReunion));

                dismiss();




            }
        });


        return view;

    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setStyle(DialogFragment.STYLE_NORMAL, R.style.AppTheme_Full_screen_dialogue);
    }

    @Override
    public void onStart() {
        super.onStart();
        setStyle(DialogFragment.STYLE_NORMAL, R.style.AppTheme_Full_screen_dialogue);
        Dialog dialog = getDialog();

        //if (dialog != null){
        //int width = ViewGroup.LayoutParams.MATCH_PARENT;
        //int height = ViewGroup.LayoutParams.MATCH_PARENT;
        //dialog.getWindow().setLayout(, 3000);

        //}

    }



    @Override
    public void onDismiss(DialogInterface dialog) {
        super.onDismiss(dialog);
        ((MainActivity) getContext()).initList();
    }

    public void exit(){
        getDialog().cancel();
    }
}
