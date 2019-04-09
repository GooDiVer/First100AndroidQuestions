package e.mi.recyclerviewtest;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface SoApi {
    @GET("/2.2/questions?order=desc&sort=creation&tagged=android&site=stackoverflow&pagesize=100")
    Call<AndroidQuestions> getQuestions();
}
