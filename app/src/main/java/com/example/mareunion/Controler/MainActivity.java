package com.example.mareunion.Controler;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
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
import java.util.List;


public class MainActivity extends AppCompatActivity {
    private RecyclerView myRecyclerView;
    private List<Reunion> mReunionsList;
    private ReunionApiService mApiService;

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
        myRecyclerView.setAdapter(new ReunionsListRecyclerViewAdapter((ArrayList<Reunion>) mReunionsList));
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
}
