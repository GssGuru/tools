import java.util.concurrent.TimeUnit;
import guru.gss.loginsimple.Const;
import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.converter.scalars.ScalarsConverterFactory;

public class RetrofitClient {

    private static final int RESPONSE_TIMEOUT = 120;
    private static RetrofitClient instance = null;
    private ApiClient apiClient;

    private RetrofitClient() {
        Retrofit retrofit = new Retrofit.Builder().baseUrl("gss.guru.auth")
                .addConverterFactory(ScalarsConverterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .client(initHttpClient())
                .build();
        apiClient = retrofit.create(ApiClient.class);
    }

    public static RetrofitClient getInstance() {
        if (instance == null) {
            instance = new RetrofitClient();
        }
        return instance;
    }

    private OkHttpClient initHttpClient() {

        OkHttpClient.Builder okHttpBuilder = new OkHttpClient.Builder();
        okHttpBuilder
                .connectTimeout(RESPONSE_TIMEOUT, TimeUnit.SECONDS)
                .writeTimeout(RESPONSE_TIMEOUT, TimeUnit.SECONDS)
                .readTimeout(RESPONSE_TIMEOUT, TimeUnit.SECONDS);

        return okHttpBuilder.build();
    }

    public static void recreateRetrofit() {
        instance = null;
    }

    public ApiClient getApiClient() {
        return apiClient;
    }
}
