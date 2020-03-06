package com.example.mareunion.Controler;
import android.annotation.SuppressLint;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.SearchView;

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
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;


public class MainActivity extends AppCompatActivity implements TextWatcher{
    private RecyclerView myRecyclerView;
    private List<Reunion> mReunionsList;
    private ReunionApiService mApiService;

    private FloatingActionButton mAddButton;
    private ImageButton sortButton;

    private EditText mSearchBar;








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
        sortButton = findViewById(R.id.sortBy_btn);

        sortButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DialogFragment fragment;
                fragment = SortListFragment.newDialogInstance();
                fragment.show(getSupportFragmentManager(), "Sort list");


            }
        });

        mSearchBar = findViewById(R.id.search_input);
        mSearchBar.addTextChangedListener(this);





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
    public void beforeTextChanged(CharSequence s, int start, int count, int after) {

    }

    @Override
    public void onTextChanged(CharSequence s, int start, int before, int count) {
        ReunionsListRecyclerViewAdapter adapter = new ReunionsListRecyclerViewAdapter((ArrayList<Reunion>) mReunionsList);
        adapter.getFilter().filter(s);

    }

    @Override
    public void afterTextChanged(Editable s) {

    }
}
