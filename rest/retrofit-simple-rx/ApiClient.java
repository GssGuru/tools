package guru.gss.loginsimple.utils.network;

import okhttp3.Response;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Header;
import retrofit2.http.Headers;
import retrofit2.http.POST;

public interface ApiClient {

    @GET("auth")
    Single<Response> authorization(@Header("MOBILE-EMAIL") String email);
	
	
	@GET("shop-info/{objId}")
    Single<Response<Object>> authorization(@Path(value = "objId") Integer objId);
	
	@POST("/auth/main")
    Single<Object> authorization(@Query("name") String name, @Body Object objId);
			
}
