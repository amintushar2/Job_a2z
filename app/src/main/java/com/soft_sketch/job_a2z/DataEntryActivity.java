package com.soft_sketch.job_a2z;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

public class DataEntryActivity extends AppCompatActivity {

    private EditText modelTestET;
    private EditText subCodeET;
    private EditText quesET;
    private EditText opt1ET;
    private EditText opt2ET;
    private EditText opt3ET;
    private EditText opt4ET;
    private EditText rightAnsET;
    private RadioGroup group;
    private RadioButton subBtn, testListBtn, quesBtn;

    private FirebaseFirestore firestore = FirebaseFirestore.getInstance();
    private CollectionReference DEmodeltestRef = firestore.collection("Job_a2z");
    private CollectionReference DEquesRef = firestore.collection("Question");


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_data_entry);


        modelTestET = findViewById(R.id.DEmodel_test_no_id);
        subCodeET = findViewById(R.id.DEsubject_code_id);
        quesET = findViewById(R.id.DEques_id);
        opt1ET = findViewById(R.id.DEop1_id);
        opt2ET = findViewById(R.id.DEop2_id);
        opt3ET = findViewById(R.id.DEop3_id);
        opt4ET = findViewById(R.id.DEop4_id);
        rightAnsET = findViewById(R.id.DEright_ans_id);
        group = findViewById(R.id.DEgroup_id);
        subBtn = findViewById(R.id.DE_sub_radio_id);
        testListBtn = findViewById(R.id.DE_model_radio_id);
        quesBtn = findViewById(R.id.DE_ques_radio_id);

        group.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                if (checkedId==R.id.DE_sub_radio_id){
                    Toast.makeText(DataEntryActivity.this, "Subject", Toast.LENGTH_SHORT).show();
                }else if (checkedId==R.id.DE_model_radio_id){
                    quesET.setVisibility(View.GONE);
                    opt1ET.setVisibility(View.GONE);
                    opt2ET.setVisibility(View.GONE);
                    opt3ET.setVisibility(View.GONE);
                    opt4ET.setVisibility(View.GONE);
                    rightAnsET.setVisibility(View.GONE);
                    subCodeET.setVisibility(View.GONE);


                }else if (checkedId == R.id.DE_ques_radio_id){
                    Toast.makeText(DataEntryActivity.this, "ques", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    public void onDataEnteryClicked(View view) {

        int modelNo = Integer.parseInt(modelTestET.getText().toString());
        int subCode = Integer.parseInt(subCodeET.getText().toString());
        int rightAnsNo = Integer.parseInt(rightAnsET.getText().toString());
        String ques = quesET.getText().toString();
        String opt1 = opt1ET.getText().toString();
        String opt2 = opt2ET.getText().toString();
        String opt3 = opt3ET.getText().toString();
        String opt4 = opt4ET.getText().toString();


        Question quesObj = new Question(modelNo,subCode,rightAnsNo,ques,opt1,opt2,opt3,opt4);
        ModelTest modelTest = new ModelTest(modelNo, "Model Test : " + modelNo);


        if (Varifiction(modelNo, subCode)) {
            DEmodeltestRef.add(modelTest).addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
                @Override
                public void onSuccess(DocumentReference documentReference) {
                    Toast.makeText(DataEntryActivity.this, "A new model Test has been added !", Toast.LENGTH_SHORT).show();
                }
            }).addOnFailureListener(new OnFailureListener() {
                @Override
                public void onFailure(@NonNull Exception e) {
                    Toast.makeText(DataEntryActivity.this, "MODEL : " + e.getLocalizedMessage(), Toast.LENGTH_SHORT).show();
                }
            });
        }


        if (Varifiction(modelNo, subCode)) {
            if (QuesVarification(ques,opt1,opt2,opt3,opt4,rightAnsNo)) {
                DEquesRef.add(quesObj).addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
                    @Override
                    public void onSuccess(DocumentReference documentReference) {
                        Toast.makeText(DataEntryActivity.this, "New Ques has been added!", Toast.LENGTH_SHORT).show();
                        fieldClean();
                    }
                }).addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Toast.makeText(DataEntryActivity.this, "QUES : " + e.getLocalizedMessage(), Toast.LENGTH_SHORT).show();
                    }
                });
            }
        }

    }

    @Override
    public void onBackPressed() {
        Intent intent = new Intent(this,SubjectActivity.class);
        startActivity(intent);
        super.onBackPressed();
        finish();
    }

    private void fieldClean() {
        modelTestET.setText("");
        subCodeET.setText("");
        quesET.setText("");
        opt1ET.setText("");
        opt2ET.setText("");
        opt3ET.setText("");
        opt4ET.setText("");
        rightAnsET.setText("");
    }

    public void SectorSectedClicked(View view) {
        int id = view.getId();
        switch (id) {
            case 1:
                Toast.makeText(this, "1 selected", Toast.LENGTH_SHORT).show();
                break;
            case 2:
                Toast.makeText(this, "2 selected", Toast.LENGTH_SHORT).show();
                break;
            case 3:
                Toast.makeText(this, "3 selected", Toast.LENGTH_SHORT).show();
                break;
        }
    }

    public boolean Varifiction(int modeltestNo, int subjectCode) {
     if (modeltestNo < 1) {
            modelTestET.setError("Enter Model Test No");
            return false;
        }
        if ((subjectCode!=101||subjectCode!=102)) {
            subCodeET.setError("Enter Subject Code");
            return false;
        }
        return true;
    }

    public boolean QuesVarification(String question,String option1,String option2,String option3,String option4,int ansPossition){
        if (question.isEmpty()){
            quesET.setError("Enter full ques with ? sign");
            return false;
        } if (option1.isEmpty()){
            opt1ET.setError("Enter first option");
            return false;
        }if (option2.isEmpty()){
            opt2ET.setError("Enter second option");
            return false;
        } if (option3.isEmpty()){
            opt3ET.setError("Enter third option");
            return false;
        } if (option4.isEmpty()){
            opt4ET.setError("Enter fourth option");
            return false;
        } if (ansPossition==0){
            return false;
        }
        return true;
    }



}
