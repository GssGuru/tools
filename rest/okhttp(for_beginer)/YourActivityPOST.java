

public class YourActivity extends AppCompatActivity {

    private final String URL = "https://gss.guru/auth";

    private final MediaType REQWEST_HEADERS = MediaType.get("application/json; charset=utf-8");

    private OkHttpClient client = new OkHttpClient();
    private RequestAsyncTask mAuthTask = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_simple);

        String requestBody= "body"; // TODO Сюда положите тело для запроса
        mAuthTask = new RequestAsyncTask(requestBody);
        mAuthTask.execute((Void) null);
    }

    public class RequestAsyncTask extends AsyncTask<Void, Void, String> {

        private String requestBody;

        private RequestAsyncTask(String json) {
            this.requestBody = json;
        }

        @Override
        protected String doInBackground(Void... params) {
            RequestBody body = RequestBody.create(REQWEST_HEADERS, requestBody);
            Request request = new Request.Builder()
                    .url(URL)
                    .post(body)
                    .build();
            try (Response response = client.newCall(request).execute()) {
                if (response != null && response.body() != null){
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
                    if(status.equals("saccess")){
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
