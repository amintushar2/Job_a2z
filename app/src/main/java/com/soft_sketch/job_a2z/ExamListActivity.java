package com.soft_sketch.job_a2z;

import android.annotation.SuppressLint;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Adapter;
import android.widget.ListAdapter;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.List;

public class ExamListActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private ModelTestListAdapter adapter;
    private RecyclerView.LayoutManager layoutManager;
    private ProgressBar bar;

    private FirebaseFirestore db = FirebaseFirestore.getInstance();
    private CollectionReference colRef= db.collection("Job_a2z");
    public List<ModelTest> modelTestList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_exam_list);

        this.setTitle("List of Model Test");


        recyclerView = findViewById(R.id.modelTestListID);
        bar = findViewById(R.id.progressBar_id);

        modelTestList = new ArrayList<ModelTest>();

        colRef.get().addOnSuccessListener(new OnSuccessListener<QuerySnapshot>() {
            @SuppressLint("SetTextI18n")
            @Override
            public void onSuccess(QuerySnapshot queryDocumentSnapshots) {
                for (QueryDocumentSnapshot snapshots : queryDocumentSnapshots){
                    ModelTest modelTest = snapshots.toObject(ModelTest.class);
                    modelTestList.add(modelTest);
                }
                ShowOnList(modelTestList);
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Toast.makeText(getApplicationContext(), e.getLocalizedMessage(), Toast.LENGTH_SHORT).show();
            }
        });

    }

    private void ShowOnList(List<ModelTest> modelTestList) {
        recyclerView.setHasFixedSize(true);
        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        adapter = new ModelTestListAdapter(this, modelTestList);
        recyclerView.setAdapter(adapter);
        bar.setVisibility(View.GONE);
    }


}
