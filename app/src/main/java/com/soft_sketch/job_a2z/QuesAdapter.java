package com.soft_sketch.job_a2z;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class QuesAdapter extends RecyclerView.Adapter<QuesAdapter.QuestionViewHolder> {

    private Context context;
    private List<Question> questionList;
    private int score = 0;
    private int ansid;

    public QuesAdapter(Context context, List<Question> questionList) {
        this.context = context;
        this.questionList = questionList;
    }



    @NonNull
    @Override
    public QuestionViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.single_ques_design, viewGroup, false);
        return new QuestionViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final QuestionViewHolder questionViewHolder, final int i) {
        questionViewHolder.quesTv.setText(questionList.get(i).getQuestion());
        questionViewHolder.opt1Btn.setText(questionList.get(i).getOpt1());
        questionViewHolder.opt2Btn.setText(questionList.get(i).getOpt2());
        questionViewHolder.opt3Btn.setText(questionList.get(i).getOpt3());
        questionViewHolder.opt4Btn.setText(questionList.get(i).getOpt4());

        final Question question = questionList.get(i);
        questionViewHolder.AnsOptGroup.check(question.getSelectedID());
        questionViewHolder.AnsOptGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {

            public void onCheckedChanged(RadioGroup group, int checkedId) {

                int radioButtonID = group.getCheckedRadioButtonId();
                View radioButton = group.findViewById(radioButtonID);
                ansid = group.indexOfChild(radioButton)+1;
                for (int i = 0; i < group.getChildCount(); i++) {
                    group.getChildAt(i).setEnabled(false);
                }
            }
        });
    }


    @Override
    public int getItemCount() {
        return questionList.size();
    }



    class QuestionViewHolder extends RecyclerView.ViewHolder {

        TextView quesTv;
        RadioGroup AnsOptGroup;
        RadioButton opt1Btn, opt2Btn, opt3Btn, opt4Btn;

        public QuestionViewHolder(@NonNull View itemView) {
            super(itemView);

            quesTv = itemView.findViewById(R.id.questionTV_id);
            AnsOptGroup = itemView.findViewById(R.id.optionGroup_id);
            opt1Btn = itemView.findViewById(R.id.opt1);
            opt2Btn = itemView.findViewById(R.id.opt2);
            opt3Btn = itemView.findViewById(R.id.opt3);
            opt4Btn = itemView.findViewById(R.id.opt4);

        }
    }
}
