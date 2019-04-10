import java.util.concurrent.TimeUnit;
import guru.gss.loginsimple.Const;
import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.converter.scalars.ScalarsConverterFactory;

public class RetrofitClient {

    private static RetrofitClient instance;
    private final String BASE_URL = "https://gss.guru";
    private ApiClient apiclient;

    public static RetrofitClient getInstance() {
        if (instance == null) {
            instance = new RetrofitClient();
        }
        return instance;
    }

    public ApiClient getApiClient() {
        if (apiclient == null) {
            apiclient = buildApi(BASE_URL, ApiClient.class);
        }
        return apiclient;
    }

    private <T> T buildApi(String url, Class<T> aClass) {
        return getRetrofitBuilder(url, aClass)
                .client(getHttpClient(aClass)
                        .connectTimeout(20, TimeUnit.MINUTES)
                        .readTimeout(20, TimeUnit.MINUTES)
                        .writeTimeout(20, TimeUnit.MINUTES)
                        .build())
                .build()
                .create(aClass);
    }

    private <T> Retrofit.Builder getRetrofitBuilder(String url, Class<T> aClass) {
        return new Retrofit.Builder()
                .baseUrl(url)
                .client(getHttpClient(aClass).build())
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create());
    }

    @NonNull
    private <T> OkHttpClient.Builder getHttpClient(Class<T> aClass) {
		
        OkHttpClient.Builder okHttpBuilder = new OkHttpClient.Builder();
        okHttpBuilder.addInterceptor(chain -> {
            Request request = chain.request();
            request = request.newBuilder()
                    .addHeader("Accept", "application/json")
                    .build();
            return chain.proceed(request);
        });

        return okHttpBuilder;
    }

    public void clearRetrofit() {
        apiclient = null;
        instance = null;
    }
}
