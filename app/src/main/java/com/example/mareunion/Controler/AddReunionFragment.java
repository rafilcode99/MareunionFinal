package com.example.mareunion.Controler;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.app.TimePickerDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.util.Patterns;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.TimePicker;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.DialogFragment;

import com.example.mareunion.Event.CreateReunionEvent;
import com.example.mareunion.R;
import com.example.mareunion.Service.ReunionApiService;
import com.example.mareunion.Model.Reunion;
import com.example.mareunion.DI.DI;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;

import org.greenrobot.eventbus.EventBus;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;


public class AddReunionFragment extends DialogFragment {
    private DatePickerDialog.OnDateSetListener mOnDateSetListener;
    private TimePickerDialog.OnTimeSetListener mOnTimeSetListener;
    private ReunionApiService mApiService;
    private ArrayList<Reunion> mReunions;
    private ArrayList<String> mParticipants;


    public static AddReunionFragment newDialogInstance(){
        AddReunionFragment dialog = new AddReunionFragment();
        return dialog;
    }



    private Toolbar mToolbar;
    private ImageButton mReturnButton;
    private TextView mTextView;

    private TextInputLayout mSubjectInput;
    private TextInputLayout mDateLayout;
    private TextInputEditText mDateInput;
    private TextInputLayout mTimeLayout;
    private TextInputEditText mTimeInput;
    private TextInputLayout mLocationInput;
    private TextInputLayout mParticipantsInput;
    private ImageButton mAddParticipantsButton;


    private Button mAddButton;

    @Nullable
    @Override
    public View onCreateView(@NonNull final LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.new_reunion_layout, container, false);
        mToolbar = view.findViewById(R.id.toolbar_new);
        mReturnButton = view.findViewById(R.id.return_btn);
        mTextView = view.findViewById(R.id.toolbar_txt);

        mSubjectInput = view.findViewById(R.id.subject_input);
        mDateLayout = view.findViewById(R.id.date_layout);
        mDateInput = view.findViewById(R.id.date_input);
        mTimeInput = view.findViewById(R.id.time_input);
        mTimeLayout = view.findViewById(R.id.time_layout);
        mLocationInput = view.findViewById(R.id.location_input);
        mParticipantsInput = view.findViewById(R.id.participant_input);
        mAddParticipantsButton = view.findViewById(R.id.add_participatns_btn);



        mAddButton = view.findViewById(R.id.add_btn);

        mApiService = DI.getNewInstanceApiService();



        mDateInput.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final Calendar c = Calendar.getInstance();
                int year = c.get(Calendar.YEAR);
                int month = c.get(Calendar.MONTH);
                int day = c.get(Calendar.DAY_OF_MONTH);


                DatePickerDialog dialog = new DatePickerDialog(getDialog().getContext(), R.style.Theme_AppCompat_Light_Dialog_MinWidth,
                        mOnDateSetListener,year, month, day );

                dialog.show();

            }
        });
        mOnDateSetListener = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                String date = dayOfMonth + "/" + month + "/" + year ;
                mDateInput.setText(date);
            }

        };

        mTimeInput.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Calendar calendar = Calendar.getInstance();
                int hour = calendar.get(Calendar.HOUR_OF_DAY);
                int min = calendar.get(Calendar.MINUTE);
                TimePickerDialog mDialog = new TimePickerDialog(getDialog().getContext(), R.style.Theme_AppCompat_Dialog_MinWidth,
                        mOnTimeSetListener, hour, min, true);
                mDialog.show();
            }
        });


        mOnTimeSetListener = new TimePickerDialog.OnTimeSetListener() {
            @Override
            public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                String time = hourOfDay + ":" + minute;
                mTimeInput.setText(time);
            }
        };

        mParticipants = new ArrayList<String >();






            mAddParticipantsButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    String email = mParticipantsInput.getEditText().getText().toString();
                    if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()){
                        mParticipantsInput.setError("invalid email");
                    }else{
                        mParticipantsInput.setErrorEnabled(false);
                        mParticipants.add(email);
                        mParticipantsInput.getEditText().setText("");
                    }


                }
            });


        mReturnButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                exit();
            }
        });

        mAddButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                confirm(view);

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

    }



    @Override
    public void onDismiss(DialogInterface dialog) {
        super.onDismiss(dialog);

    }

    public void exit(){
        getDialog().cancel();
    }

    private Boolean validateSubject(){
        String subject = mSubjectInput.getEditText().getText().toString();

        if (subject.isEmpty()){
            mSubjectInput.setError("subject can't be empty");
            return false;
        }else if (subject.length() >15){
            mSubjectInput.setError("subject too long");
            return false;

        }else {
            mSubjectInput.setErrorEnabled(false);
            return true;
        }
    }
    private Boolean validateEmails(){


        if (mParticipants.size() <2){

          mParticipantsInput.setError("please, add at least" + " " + (2-mParticipants.size())+ " " + "participants");
            return false;
        }else {
           mParticipantsInput.setErrorEnabled(false);
            return true;
       }
    }
    private Boolean validateLocation(){
        String location = mLocationInput.getEditText().getText().toString();
        if (location.isEmpty()){
            mLocationInput.setError("location can't be empty");
            return false;
        }else if (location.length() >8){
            mLocationInput.setError("location too long");
            return false;

        }else {
            mLocationInput.setErrorEnabled(false);
            return true;
        }
    }


    private Boolean validateDate(){
        String date = mDateInput.getText().toString();
        if (date.isEmpty()){
            mDateLayout.setError("Date can't be empty");
            return false;
        }else {
            mDateLayout.setErrorEnabled(false);
            return true;
        }

    }
    private Boolean validateTime(){
        String time = mTimeInput.getText().toString();
        if (time.isEmpty()){
            mTimeLayout.setError("Time can't be empty");
            return false;
        }else {
            mTimeLayout.setErrorEnabled(false);
            return true;
        }
    }

    public void confirm(View v){
        if (!validateSubject() | !validateDate()|!validateTime() | !validateEmails() |!validateLocation()){
            return;
        }else {
            Reunion newReunion = new Reunion(mDateInput.getText().toString(), mTimeInput.getText().toString() ,mLocationInput.getEditText().getText().toString(), mSubjectInput.getEditText().getText().toString(), mParticipants);
            EventBus.getDefault().post(new CreateReunionEvent(newReunion));

            dismiss();
        }
    }


}
