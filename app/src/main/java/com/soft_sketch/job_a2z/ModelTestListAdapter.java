package com.soft_sketch.job_a2z;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

public class ModelTestListAdapter extends RecyclerView.Adapter<ModelTestListAdapter.ListHolder> {

    private Context context;
    private List<ModelTest> modelTestList;

    public ModelTestListAdapter(Context context, List<ModelTest> modelTestList) {
        this.context = context;
        this.modelTestList = modelTestList;
    }

    @NonNull
    @Override
    public ListHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.single_exam_row, viewGroup, false);
        return new ListHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ListHolder listHolder, int i) {
        listHolder.nameTv.setText("মডেল টেস্ট : "+modelTestList.get(i).getTestNo());
    }

    @Override
    public int getItemCount() {
        return modelTestList.size();
    }


    class ListHolder extends RecyclerView.ViewHolder {
        TextView nameTv;
        public ListHolder(@NonNull View itemView) {
            super(itemView);
            context = itemView.getContext();
            nameTv = itemView.findViewById(R.id.model_testName_id);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int position = getAdapterPosition();
                    ModelTest test = modelTestList.get(position);
                    Intent intent = new Intent(context,QuizeActivity.class);
                    intent.putExtra("modelTestNo",test.getTestNo());
                    context.startActivity(intent);
                }
            });
        }
    }

}
