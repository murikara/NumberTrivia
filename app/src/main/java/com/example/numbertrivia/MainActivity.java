package com.example.numbertrivia;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    @BindView(R.id.triviaRecyclerView)
    RecyclerView triviaRecyclerView;

    private TriviaAdapter triviaAdapter;
    private List<Trivia> triviaList;
    private static final String TAG = "MainActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        LinearLayoutManager linearLayoutManager =
                new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        triviaList = new ArrayList<>();
        triviaAdapter = new TriviaAdapter(triviaList);
        triviaRecyclerView.setAdapter(triviaAdapter);
        triviaRecyclerView.setLayoutManager(linearLayoutManager);
    }

    @OnClick(R.id.addTriviaFloatingActionButton)
    public void addTrivia(){
        requestData();
    }

    private void requestData(){
        NumbersApiService service = NumbersApiService.retrofit.create(NumbersApiService.class);

        Call<Trivia> call = service.getRandomFact();

        call.enqueue(new Callback<Trivia>() {
            @Override
            public void onResponse(Call<Trivia> call, Response<Trivia> response) {
                if(response.body() != null && response.isSuccessful() && response != null) {
                    updateList(response.body());
                }
            }

            @Override
            public void onFailure(Call<Trivia> call, Throwable t) {
                t.printStackTrace();
            }
        });
    }

    private void updateList(Trivia trivia){
        triviaList.add(trivia);
        triviaAdapter.notifyDataSetChanged();
        triviaRecyclerView.scrollToPosition(triviaAdapter.getItemCount() - 1);
    }
}
