package com.aad.cn47.gadsleaderboard2;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.aad.cn47.gadsleaderboard2.models.SubmitProject;
import com.aad.cn47.gadsleaderboard2.services.ProjectSubmitService;
import com.aad.cn47.gadsleaderboard2.services.ServiceBuilder;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ProjectSubmission extends AppCompatActivity {

    private EditText firstName, lastName, email, projectLink;
    private Context context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_project_submission);

        Toolbar toolbar = (Toolbar) findViewById(R.id.detail_toolbar);
        setSupportActionBar(toolbar);
        context = this;

        // Show the Up button in the action bar.
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);
        }

        Button projectSubmitButton = (Button) findViewById(R.id.project_submit_button);

        firstName = (EditText) findViewById(R.id.first_name);
        lastName = (EditText) findViewById(R.id.last_name);
        email = (EditText) findViewById(R.id.email);
        projectLink = (EditText) findViewById(R.id.project_link);

        projectSubmitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                showConfirmDialog();
            }
        });
    }

    private void showConfirmDialog(){
        AlertDialog.Builder builder = new AlertDialog.Builder(ProjectSubmission.this, R.style.AlertDialogTheme);
        View view = LayoutInflater.from(ProjectSubmission.this).inflate(
                R.layout.layout_confirm_dialog,
                (ConstraintLayout) findViewById(R.id.layoutDialogContainer)
        );
        builder.setView(view);
        ((TextView) view.findViewById(R.id.textTitle)).setText(getResources().getString(R.string.message_title));
        ((TextView) view.findViewById(R.id.textMessage)).setText(getResources().getString(R.string.message_text));
        ((Button) view.findViewById(R.id.buttonYes)).setText(getResources().getString(R.string.yes));
        ((ImageView) view.findViewById(R.id.imageIcon)).setImageResource(R.drawable.ic_cancel);

        final AlertDialog alertDialog = builder.create();

        view.findViewById(R.id.buttonYes).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                alertDialog.dismiss();

                /*Toast.makeText(context,
                        firstName.getText().toString() + " <--> " +
                                lastName.getText().toString() + " <--> " +
                                email.getText().toString() + " <--> " +
                                projectLink.getText().toString(), Toast.LENGTH_LONG).show();*/

                //submitProjectOnline();
                //showSuccessDialog();
                showErrorDialog();
            }
        });

        if(alertDialog.getWindow() != null){
            alertDialog.getWindow().setBackgroundDrawable(new ColorDrawable(0));
        }

        alertDialog.show();
    }

    private void submitProjectOnline(){
        ProjectSubmitService projectSubmitServiceObject = ServiceBuilder.buildService2(ProjectSubmitService.class);
        Call<SubmitProject> projectSubmitRequest = projectSubmitServiceObject.createSubmission(
                firstName.getText().toString(),
                lastName.getText().toString(),
                email.getText().toString(),
                projectLink.getText().toString()
        );

        projectSubmitRequest.enqueue(new Callback<SubmitProject>() {
            @Override
            public void onResponse(Call<SubmitProject> call, Response<SubmitProject> response) {
                showSuccessDialog();
                Log.d("ProjectSubmitResponse", response.toString());
            }

            @Override
            public void onFailure(Call<SubmitProject> call, Throwable t) {
                showErrorDialog();
                Log.d("ProjectSubmitError", t.toString());
            }
        });
    }

    private void showSuccessDialog(){
        AlertDialog.Builder builder = new AlertDialog.Builder(ProjectSubmission.this, R.style.AlertDialogTheme);
        View view = LayoutInflater.from(ProjectSubmission.this).inflate(
                R.layout.layout_success_dialog,
                (ConstraintLayout) findViewById(R.id.layoutDialogContainer)
        );
        builder.setView(view);
        ((TextView) view.findViewById(R.id.textTitle)).setText(getResources().getString(R.string.message_title));
        ((TextView) view.findViewById(R.id.textMessage)).setText(getResources().getString(R.string.message_success));
        ((ImageView) view.findViewById(R.id.imageIcon)).setImageResource(R.drawable.ic_success);

        final AlertDialog alertDialog = builder.create();

        if(alertDialog.getWindow() != null){
            alertDialog.getWindow().setBackgroundDrawable(new ColorDrawable(0));
        }

        alertDialog.show();
    }

    private void showErrorDialog(){
        AlertDialog.Builder builder = new AlertDialog.Builder(ProjectSubmission.this, R.style.AlertDialogTheme);
        View view = LayoutInflater.from(ProjectSubmission.this).inflate(
                R.layout.layout_error_dialog,
                (ConstraintLayout) findViewById(R.id.layoutDialogContainer)
        );
        builder.setView(view);
        ((TextView) view.findViewById(R.id.textTitle)).setText(getResources().getString(R.string.message_title));
        ((TextView) view.findViewById(R.id.textMessage)).setText(getResources().getString(R.string.message_error));
        ((ImageView) view.findViewById(R.id.imageIcon)).setImageResource(R.drawable.ic_error);

        final AlertDialog alertDialog = builder.create();

        if(alertDialog.getWindow() != null){
            alertDialog.getWindow().setBackgroundDrawable(new ColorDrawable(0));
        }

        alertDialog.show();
    }
}
