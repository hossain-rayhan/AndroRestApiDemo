package com.rayapplica.androrestapidemo;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.rayapplica.androrestapidemo.model.Data;
import com.rayapplica.androrestapidemo.model.PostUser;
import com.rayapplica.androrestapidemo.model.User;
import com.rayapplica.androrestapidemo.network.RetrofitClient;
import com.rayapplica.androrestapidemo.network.UserDataService;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

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
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });



        service = RetrofitClient.getRetrofitInstance().create(UserDataService.class);
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
    }

    //method for posting a user to the server using REST api
    private void postUserData(PostUser postUser){
        Call<PostUser> postUserCall = service.savePost(postUser);
        postUserCall.enqueue(new Callback<PostUser>() {
            @Override
            public void onResponse(Call<PostUser> call, Response<PostUser> response) {
                PostUser user = response.body();
                Log.e("Post Successful", user.getName() + " - " + user.getJob());
            }

            @Override
            public void onFailure(Call<PostUser> call, Throwable t) {
                Log.e("Post Request Error", t.getMessage());
            }
        });
    }
}
