

public class YourActivity extends AppCompatActivity {

    private final String URL = "https://gss.guru/auth";

    private OkHttpClient client = new OkHttpClient();
    private RequestAsyncTask mAuthTask = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_simple);

        mAuthTask = new RequestAsyncTask();
        mAuthTask.execute((Void) null);
    }

    public class RequestAsyncTask extends AsyncTask<Void, Void, String> {

        @Override
        protected String doInBackground(Void... params) {
            Request request = new Request.Builder()
                    .url(URL)
                    .build();
            try (Response response = client.newCall(request).execute()) {
                if (response != null && response.body() != null) {
                    return response.body().string();
                }
                return null;
            } catch (IOException e) {
                return null;
            }
        }

        @Override
        protected void onPostExecute(final String requestResult) {
            mAuthTask = null;
            if (requestResult != null) {
                try {
                    JSONObject argJSON = new JSONObject(requestResult);
                    String response = argJSON.getString("response");
                    JSONObject responseJSON = new JSONObject(response);
                    String status = responseJSON.getString("status");
                    if (status.equals("saccess")) {
                        // TODO
                        // Поздровляю))) Ми залогинелись
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }

        }

        @Override
        protected void onCancelled() {
            mAuthTask = null;
        }
    }
}
