package com.soft_sketch.job_a2z;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.ColorStateList;
import android.graphics.Color;
import android.os.CountDownTimer;
import android.support.annotation.ColorInt;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Locale;

public class QuizeActivity extends AppCompatActivity {

    private TextView modelQuesSerialTV;
    private TextView totalQuesTV;
    private TextView clockTV;
    private RecyclerView quesRecyclerView;
    private Button submitBtn;
    private ProgressBar probar;

    private FirebaseFirestore db = FirebaseFirestore.getInstance();
    private CollectionReference colRef = db.collection("Question");
    public List<Question> quesObj;

    private QuesAdapter adapter;
    private RecyclerView.LayoutManager layoutManager;

    public static final String SHARED_PREF_NAME = "SubjectCodeTransfered";
    public static final String SUBJECT_CODE = "SubjefctCode";
    private SharedPreferences preferences;

    private long totalTime = 0;
    public ColorStateList timerColor;
    private CountDownTimer countDownTimer;
    private long timeLeft;

    private int totalScore;
    private int currentQuesNo;
    private int totalQuesNo;

    private Question currentQues;
    private Boolean answered;

    List<Integer> rightAnsArray;
    List<Integer> seltedAnsArray;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quize);

        this.setTitle("Quiz Test");

        modelQuesSerialTV=findViewById(R.id.modeltest_serial_id);
        totalQuesTV=findViewById(R.id.totalQuesID);
        clockTV=findViewById(R.id.clockID);
        quesRecyclerView=findViewById(R.id.recyclerViewID);
        submitBtn = findViewById(R.id.submitBtnID);
        probar = findViewById(R.id.progress_bar_id);


        timerColor = clockTV.getTextColors();
        currentQues = new Question();

        int modelTestNo = getIntent().getIntExtra("modelTestNo",-1);
        preferences = getSharedPreferences(SHARED_PREF_NAME, Context.MODE_PRIVATE);
        final int subCode = preferences.getInt(SUBJECT_CODE,-1);

        modelQuesSerialTV.setText("মডেল টেস্ট : " + modelTestNo);

        quesObj = new ArrayList<Question>();

        colRef.get().addOnSuccessListener(new OnSuccessListener<QuerySnapshot>() {
            @SuppressLint("SetTextI18n")
            @Override
            public void onSuccess(QuerySnapshot queryDocumentSnapshots) {
                for (QueryDocumentSnapshot snapshots : queryDocumentSnapshots) {
                    Question question = snapshots.toObject(Question.class);
                    quesObj.add(question);
                    Collections.shuffle(quesObj);
                }

                ShowOnList(quesObj);
                probar.setVisibility(View.GONE);
                totalQuesTV.setText("মোট প্রশ্ন : "+ quesObj.size());
                totalTime = quesObj.size()*10000;
                timeLeft = totalTime;
                startCountdown();

            }
        }).addOnFailureListener(


                new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Toast.makeText(getApplicationContext(), e.getLocalizedMessage(), Toast.LENGTH_SHORT).show();
            }
        });

        submitBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
    }

    private void startCountdown() {
        countDownTimer = new CountDownTimer(timeLeft,1000) {
            @Override
            public void onTick(long millisUntilFinished) {
                timeLeft = millisUntilFinished;
                updateClock();
            }

            @Override
            public void onFinish() {
                timeLeft = 0;
                updateClock();
            }
        }.start();
    }

    private void updateClock() {
        int minute = (int) ((timeLeft/1000)/60);
        int second = (int)(timeLeft/1000)%60;

        String timeFormate = String.format(Locale.getDefault(),"%02d:%02d",minute,second);
        clockTV.setText(timeFormate);

        if (timeLeft <= 6000) {
            clockTV.setTextColor(Color.RED);
        }else {
            clockTV.setTextColor(timerColor);
        }
    }

    private void ShowOnList(List<Question> quesObj) {
        quesRecyclerView.setHasFixedSize(true);
        layoutManager = new LinearLayoutManager(this);
        quesRecyclerView.setLayoutManager(layoutManager);
        adapter = new QuesAdapter(this, quesObj);
        quesRecyclerView.setAdapter(adapter);
    }
}
