package com.example.numbertrivia;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.util.Log;

import java.util.List;

public class TriviaAdapter extends RecyclerView.Adapter<TriviaAdapter.TriviaViewholder> {

    private List<Trivia> mFacts;
    private static final String TAG = "TriviaAdapter";

    public TriviaAdapter(List<Trivia> mFacts) {
        this.mFacts = mFacts;
    }

    @NonNull
    @Override
    public TriviaViewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        LayoutInflater inflater= LayoutInflater.from(context);
        View view;
        if(viewType == 0) {
            view = inflater.inflate(R.layout.number_item, parent, false);
        } else {
            view = inflater.inflate(R.layout.number_item_2, parent, false);
        }
        return new TriviaAdapter.TriviaViewholder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull TriviaViewholder holder, int position) {
        Trivia trivia = mFacts.get(position);
        holder.vNumberTextView.setText(String.valueOf(trivia.getNumber()));
        holder.vFactTextView.setText(trivia.getText());
    }

    @Override
    public int getItemCount() {
        return mFacts.size();
    }

    //determine which layout to use for the row
    @Override
    public int getItemViewType(int position) {
        return position % 2;
    }

    public class TriviaViewholder extends RecyclerView.ViewHolder implements View.OnClickListener {

        private TextView vNumberTextView;
        private TextView vFactTextView;
        private View view;

        public TriviaViewholder(View v) {
            super(v);
            this.view = v;
            vNumberTextView = v.findViewById(R.id.numberTextView);
            vFactTextView = v.findViewById(R.id.factTextView);
            v.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
        }
    }
}
