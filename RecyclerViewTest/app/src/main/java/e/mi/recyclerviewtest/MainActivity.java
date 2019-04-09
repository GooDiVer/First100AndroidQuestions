package e.mi.recyclerviewtest;

import android.os.Handler;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class MainActivity extends AppCompatActivity {
    TextView tv;
    SoApi mApi;
    MyAdapter adapter;
    List<Item> questionList;
    RecyclerView rv;
    SwipeRefreshLayout swipeRefreshLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        swipeRefreshLayout = findViewById(R.id.swipeRefresh);
        final NetworkService mService = NetworkService.getInstance();
        mApi = mService.getSoApi();

        questionList = new ArrayList<>();
        adapter = new MyAdapter(MainActivity.this, questionList);
        rv = findViewById(R.id.recyclerView);
        rv.setAdapter(adapter);
        rv.setLayoutManager(new LinearLayoutManager(this));
        loadData();


        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        loadData();
                        swipeRefreshLayout.setRefreshing(false);
                    }
                },2000);
            }
        });

    }

    public void loadData() {
        Toast.makeText(MainActivity.this,String.valueOf(adapter.questionList.size()),Toast.LENGTH_LONG).show();
        mApi.getQuestions().enqueue(new Callback<AndroidQuestions>() {
            @Override
            public void onResponse(Call<AndroidQuestions> call, Response<AndroidQuestions> response) {
                Toast.makeText(MainActivity.this,String.valueOf(adapter.questionList.size()),Toast.LENGTH_LONG).show();
                questionList = response.body().getItems();
                adapter.clearItems();
                adapter.addItems(questionList);
                Log.i("info", String.valueOf(adapter.getItemCount()));

            }
            @Override
            public void onFailure(Call<AndroidQuestions> call, Throwable t) {
                Log.i("infoooo", t.getMessage());
            }
        });
    }
}
