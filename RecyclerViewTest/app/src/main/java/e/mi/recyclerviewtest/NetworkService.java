package e.mi.recyclerviewtest;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class NetworkService {
    private static final String BASE_URL = "https://api.stackexchange.com";
    private static Retrofit mRetrofit;
    public static NetworkService mService;

    public NetworkService() {
          mRetrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
    }

    public static NetworkService getInstance() {
        if(mService == null)
            mService = new NetworkService();
        return mService;
    }

    public SoApi getSoApi() {
        return mRetrofit.create(SoApi.class);
    }

}
