package com.rayapplica.androrestapidemo.fragments;

import android.app.Dialog;
import android.app.DialogFragment;
import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.rayapplica.androrestapidemo.R;
import com.rayapplica.androrestapidemo.model.PostUser;
import com.rayapplica.androrestapidemo.network.RetrofitClient;
import com.rayapplica.androrestapidemo.network.UserDataService;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class QuickAddDialogFragment extends DialogFragment {
    EditText etName, etJob;
    UserDataService service;

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        //create singleton instance of Retrofit Client and service
        service = RetrofitClient.getRetrofitInstance().create(UserDataService.class);
    }

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        final AlertDialog quickAddDialog = new AlertDialog.Builder(getActivity()).create();
        LayoutInflater inflater = getActivity().getLayoutInflater();
        View customView = (View) inflater.inflate(R.layout.dialog_quick_add, null);
        quickAddDialog.setView(customView);

        etName = customView.findViewById(R.id.etName);
        etJob = customView.findViewById(R.id.etJob);
        Button submitButton = customView.findViewById(R.id.btSubmit);

        submitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (etName.getText().toString().isEmpty()|| etJob.getText().toString().isEmpty()) {
                    Toast toast = Toast.makeText(getActivity().getApplicationContext(), "Name or Job Cannot be empty", Toast.LENGTH_LONG);
                    toast.setGravity(Gravity.CENTER, 0, 0);

                    toast.show();
                } else {
                    postUserData(new PostUser(etName.getText().toString(), etJob.getText().toString()));
                    Toast toast = Toast.makeText(getActivity().getApplicationContext(), "User added successfully!!!", Toast.LENGTH_LONG);
                    quickAddDialog.dismiss();
                }
            }
        });

        return quickAddDialog;
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
                Toast toast = Toast.makeText(getActivity().getApplicationContext(), "Ops! something went wrong.", Toast.LENGTH_LONG);
            }
        });
    }
}
