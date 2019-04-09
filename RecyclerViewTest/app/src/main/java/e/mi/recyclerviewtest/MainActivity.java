package e.mi.recyclerviewtest;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.TextView;

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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        NetworkService mService = NetworkService.getInstance();
        mApi = mService.getSoApi();

        rv = findViewById(R.id.recyclerView);
        mApi.getQuestions().enqueue(new Callback<AndroidQuestions>() {
            @Override
            public void onResponse(Call<AndroidQuestions> call, Response<AndroidQuestions> response) {
                questionList = response.body().getItems();
                adapter = new MyAdapter(MainActivity.this,questionList);
                rv.setAdapter(adapter);
                rv.setLayoutManager(new LinearLayoutManager(MainActivity.this));

                Log.i("info", String.valueOf(adapter.getItemCount()));

            }
            @Override
            public void onFailure(Call<AndroidQuestions> call, Throwable t) {
                Log.i("info", t.getMessage());
            }
        });



    }
}
