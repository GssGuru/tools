package guru.gss.loginsimple.utils.network;

import okhttp3.Response;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Header;
import retrofit2.http.Headers;
import retrofit2.http.POST;

public interface ApiClient {

    @Headers({"Content-Type: application/json"})
    @POST("authorization")
    Call<Response> authorization(@Header("Authorization") String contentRange, @Body String data);
	
    @GET("authorization")
    Call<Response> authorization(@Query("source") String source);
	
}
