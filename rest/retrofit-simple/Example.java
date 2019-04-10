
public class Example {
	
    void doSome(){
		
		Call<Response> call = RetrofitClient.getInstance().getApiClient().authorization("some headers");
        call.enqueue(new Callback<Response>() {
            @Override
            public void onResponse(Call<okhttp3.Response> call, retrofit2.Response<Response> response) {
                if (response.isSuccessful()) {
                    if (listener != null) {
                        listener.isAuthorizationSuccess(true);
                    }
                } else {
                    if (listener != null) {
                        listener.isAuthorizationSuccess(false);
                    }
                }
            }

            @Override
            public void onFailure(Call<okhttp3.Response> call, Throwable t) {
                if (listener != null){
                    listener.isAuthorizationSuccess(false);
                }
            }
        });
		
	}
}
