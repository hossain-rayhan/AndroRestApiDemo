package com.rayapplica.androrestapidemo;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.rayapplica.androrestapidemo.adapter.UserRVAdapter;
import com.rayapplica.androrestapidemo.fragments.QuickAddDialogFragment;
import com.rayapplica.androrestapidemo.model.Data;
import com.rayapplica.androrestapidemo.model.User;
import com.rayapplica.androrestapidemo.network.RetrofitClient;
import com.rayapplica.androrestapidemo.network.UserDataService;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {
    private UserRVAdapter adapter;
    private RecyclerView recyclerView;
    ProgressDialog progressDialog;
    UserDataService service;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                new QuickAddDialogFragment().show(getFragmentManager(), "QuickAddDialog");
            }
        });

        //create singleton instance of Retrofit Client and service
        service = RetrofitClient.getRetrofitInstance().create(UserDataService.class);

        //get user data and populate every time when the app launches
        getUserData();

        //postUserData(new PostUser("Neporia","Jobless"));
    }

    //method for getting user data from the REST API
    private void getUserData(){
        progressDialog = new ProgressDialog(MainActivity.this);
        progressDialog.setMessage("...Loading...");
        progressDialog.show();

        Call<Data> call = service.getData();
        call.enqueue(new Callback<Data>() {
            @Override
            public void onResponse(Call<Data> call, Response<Data> response) {
                progressDialog.dismiss();
                generateDataList(response.body());
            }

            @Override
            public void onFailure(Call<Data> call, Throwable t) {
                Log.e("On Failor Error", t.getMessage());
                progressDialog.dismiss();
                Toast.makeText(MainActivity.this, "Something went wrong...Please try later!", Toast.LENGTH_SHORT).show();
            }
        });
    }

    //extract and populate user data from http response
    private void generateDataList(Data data){
        for (User user : data.getUserList()){
            Log.e("User", user.getFirstName());
        }
        recyclerView = findViewById(R.id.customRecyclerView);
        adapter = new UserRVAdapter(this, data.getUserList());
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(MainActivity.this);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(adapter);
    }

}
