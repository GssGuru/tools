
public class Unsubscribe {
	
    void unsubscribe(String orderId){
		
		HashMap<String, String> map = new HashMap<String, String>();
        map.put("public_key", "PUBLIC_KEY");
        map.put("action", "unsubscribe");
        map.put("description", "Отписка");
        map.put("order_id", orderId);
        map.put("language", "ru");
        map.put("server_url", "https://gss.guru");

        LiqPay.checkout(getContext(), map, "PRIVATE_KEY", new LiqPayCallBack() {
            @Override
            public void onResponseSuccess(String s) {             
                JSONObject object = null;
                if (listener != null) {
                    try {
                        object = new JSONObject(s);
                    } catch (JSONException e) {
                        // Some Error
                    }
                    assert object != null;
                    if ("success".equals(object.optString("status"))) {
						// success Pay
                    } else {
						// Some Error
                    }
                }
            }

            @Override
            public void onResponceError(ErrorCode errorCode) {
                // Some Error
            }
        });
	}
}
