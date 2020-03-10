package com.example.mareunion.Controler;
import android.app.DatePickerDialog;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.DatePicker;
import android.widget.SearchView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.DialogFragment;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.example.mareunion.Event.CreateReunionEvent;
import com.example.mareunion.Event.DeleteReunionEvent;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.example.mareunion.R;
import com.example.mareunion.DI.DI;
import com.example.mareunion.Model.Reunion;
import com.example.mareunion.Service.ReunionApiService;
import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Date;
import java.util.List;



public class MainActivity extends AppCompatActivity{
    private RecyclerView myRecyclerView;
    private List<Reunion> mReunionsList;
    private ReunionApiService mApiService;

    private DatePickerDialog.OnDateSetListener mOnDateSetListener;

    private FloatingActionButton mAddButton;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        mApiService = DI.getReunionApiService();
        myRecyclerView = findViewById(R.id.reunion_recyclerview1);
        myRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        myRecyclerView.addItemDecoration(new DividerItemDecoration(this, DividerItemDecoration.VERTICAL));
        initList();

        mAddButton = findViewById(R.id.new_reunion_btn);
        mAddButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DialogFragment fragment;
                fragment = AddReunionFragment.newDialogInstance();
                fragment.show(getSupportFragmentManager(), "Add new reunion");
            }
        });

        Log.d("list size", "list size is" + mReunionsList.size());

    }
    public void initList(){
        mReunionsList = mApiService.getReunions();
        ReunionsListRecyclerViewAdapter adapter = new ReunionsListRecyclerViewAdapter((ArrayList<Reunion>) mReunionsList);
        myRecyclerView.setAdapter(adapter);

    }



    @Override
    protected void onStart() {
        super.onStart();
        initList();
        EventBus.getDefault().register(this);

    }

    @Override
    protected void onResume() {
        super.onResume();
        initList();
    }



    @Override
    protected void onStop() {
        super.onStop();
        EventBus.getDefault().unregister(this);
    }

    @Subscribe
    public void onDeleteNeighbour(DeleteReunionEvent event) {
        mApiService.deleteReunion(event.mReunion);
        initList();
    }
    @Subscribe
    public void onCreateNeighbour(CreateReunionEvent event) {
        mApiService.createReunion(event.mReunion);
        initList();
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        final MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.mymenu, menu);

        MenuItem searchBar = menu.findItem(R.id.search_by_location);
        SearchView searchView = (SearchView) searchBar.getActionView();

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                ReunionsListRecyclerViewAdapter adapter = new ReunionsListRecyclerViewAdapter((ArrayList<Reunion>) mReunionsList);
                myRecyclerView.setAdapter(adapter);
                adapter.getFilter().filter(newText);
                return false;
            }
        });

        MenuItem dateFilter = menu.findItem(R.id.Date_Filter_Item);
        dateFilter.setOnMenuItemClickListener(new MenuItem.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                final Calendar c = Calendar.getInstance();
                int year = c.get(Calendar.YEAR);
                int month = c.get(Calendar.MONTH + 1);
                int day = c.get(Calendar.DAY_OF_MONTH);


                DatePickerDialog dialog = new DatePickerDialog(MainActivity.this, R.style.Theme_AppCompat_Light_Dialog_MinWidth,
                        mOnDateSetListener, year, month, day);
                dialog.show();

                mOnDateSetListener = new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                        String date = dayOfMonth + "/" + (month+1) + "/" + year;
                        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yy");
                        String constraint = null;
                        try {
                            Date date1 = sdf.parse(date);
                            constraint = sdf.format(date1);
                        } catch (ParseException e) {
                            e.printStackTrace();
                        }
                        ReunionsListRecyclerViewAdapter adapter = new ReunionsListRecyclerViewAdapter((ArrayList<Reunion>) mReunionsList);
                        myRecyclerView.setAdapter(adapter);
                        adapter.getMyDateFilter().filter(constraint);

                    }
                };

                return false;
            }
        });

        MenuItem sortAlphaItem = menu.findItem(R.id.sortBy_Alpha_item);
        sortAlphaItem.setOnMenuItemClickListener(new MenuItem.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                sortAlphabetically();
                return false;
            }
        });

        final MenuItem sortByDatesItem = menu.findItem(R.id.sortBy_Date_item);
        sortByDatesItem.setOnMenuItemClickListener(new MenuItem.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                sortByDate();
                return false;
            }
        });


        return true;

    }


    public void sortAlphabetically(){
        Collections.sort(mApiService.getReunions(), Reunion.byAlpha);
        initList();

    }

    public void sortByDate(){
        Collections.sort(mApiService.getReunions(), Reunion.byDate);
        initList();

    }


}
