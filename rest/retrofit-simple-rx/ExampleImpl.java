
public class ExampleImpl {
	
    RetrofitClient restClient;

    public NetworkImpl(Context context) {
        restClient = RetrofitClient.
    }

    @Override
    public Single<AuthUser> authorization(String email, String passwd) {
        return restClient.getApiService().authorization(email, passwd);
    }
}
