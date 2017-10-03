package com.marti.dev.egzaminator.CustomViews;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.marti.dev.egzaminator.R;
import com.marti.dev.egzaminator.core.TestModel;

import java.util.List;



public class AnswerListAdapter extends RecyclerView.Adapter<AnswerListAdapter.AnswerViewHolder> {

    public interface Listener{
        void onClick(int position);
    }

    private boolean mClickable;
    private List<TestModel.Question.Answers> mAnswers;
    private Listener mListener;
    private RecyclerView mRecyclerView;

    class AnswerViewHolder extends RecyclerView.ViewHolder{

        public TextView answerLetter, answerContent;
        public View answerAdapter;
        public AnswerViewHolder(View itemView) {
            super(itemView);
            answerLetter =  itemView.findViewById(R.id.AnswerAdapter_text_answerLetter);
            answerContent = itemView.findViewById(R.id.AnswerAdapter_text_answerContent);
            answerAdapter = itemView.findViewById(R.id.AnsweAdapter);
        }
    }


    @Override
    public AnswerViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.answer_list_adapter,parent,false);
        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View l) {
                if(mClickable)
                     mListener.onClick(mRecyclerView.getChildAdapterPosition(l));
            }
        });
        return new AnswerViewHolder(view);
    }

    public AnswerListAdapter(List<TestModel.Question.Answers> answers,RecyclerView recyclerView, Listener listener) {
        mRecyclerView = recyclerView;
        mAnswers = answers;
        mListener = listener;
    }

    public void setClickable(boolean clickable){
        mClickable = clickable;
    }

    public void changeItemColor(int position,int color,int textColor){
        View view = mRecyclerView.findViewHolderForAdapterPosition(position).itemView;
        view.setBackgroundColor(color);
        TextView answerT = view.findViewById(R.id.AnswerAdapter_text_answerContent);
        TextView answerL = view.findViewById(R.id.AnswerAdapter_text_answerLetter);

        answerL.setTextColor(textColor);
        answerT.setTextColor(textColor);

        notifyItemChanged(position);
    }

    @Override
    public void onBindViewHolder(AnswerViewHolder holder, int position) {

        int ASCIIchar = 65 + position;
        String letter = Character.toString((char) ASCIIchar)+".";
        holder.answerLetter.setText(letter);
        holder.answerContent.setText(mAnswers.get(position).answerText);
    }

    @Override
    public int getItemCount() {
        return mAnswers.size();
    }




}
