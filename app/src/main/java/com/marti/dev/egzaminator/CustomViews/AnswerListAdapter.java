package com.marti.dev.egzaminator.CustomViews;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.marti.dev.egzaminator.R;

import java.util.List;



public class AnswerListAdapter extends RecyclerView.Adapter<AnswerListAdapter.AnswerViewHolder> {

    private List<String> mAnswers;

    class AnswerViewHolder extends RecyclerView.ViewHolder{

        TextView answerLetter, answerContent;

        public AnswerViewHolder(View itemView) {
            super(itemView);
            answerLetter =  itemView.findViewById(R.id.AnswerAdapter_text_answerLetter);
            answerContent = itemView.findViewById(R.id.AnswerAdapter_text_answerContent);
        }
    }


    @Override
    public AnswerViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.answer_list_adapter,parent,false);

        return new AnswerViewHolder(view);
    }

    public AnswerListAdapter(List<String> answers) {mAnswers = answers;}

    @Override
    public void onBindViewHolder(AnswerViewHolder holder, int position) {

        int ASCIIchar = 65 + position;
        String letter = Character.toString((char) ASCIIchar)+".";
        holder.answerLetter.setText(letter);
        holder.answerContent.setText(mAnswers.get(position));
    }

    @Override
    public int getItemCount() {
        return mAnswers.size();
    }



}
