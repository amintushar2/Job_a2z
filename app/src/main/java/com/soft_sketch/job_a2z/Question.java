package com.soft_sketch.job_a2z;

import java.util.ArrayList;
import java.util.List;

public class Question {
    private int modelTestNo,subjectCode,selectedID,rightAnsNo;
    private String question,opt1,opt2,opt3,opt4;
    private List<Integer> selectedAns = new ArrayList<>();


    public Question() {
    }

    public Question(int modelTestNo, int subjectCode, int rightAnsNo, String question, String opt1, String opt2, String opt3, String opt4) {
        this.modelTestNo = modelTestNo;
        this.subjectCode = subjectCode;
        this.rightAnsNo = rightAnsNo;
        this.question = question;
        this.opt1 = opt1;
        this.opt2 = opt2;
        this.opt3 = opt3;
        this.opt4 = opt4;
    }

    public int getModelTestNo() {
        return modelTestNo;
    }

    public String getQuestion() {
        return question;
    }

    public String getOpt1() {
        return opt1;
    }

    public String getOpt2() {
        return opt2;
    }

    public String getOpt3() {
        return opt3;
    }

    public String getOpt4() {
        return opt4;
    }

    public int getRightAnsNo() {
        return rightAnsNo;
    }

    public int getSubjectCode() {
        return subjectCode;
    }

    public int getSelectedID() {
        return selectedID;
    }

    public void setSelectedID(int selectedID) {
        this.selectedID = selectedID;
    }
}
