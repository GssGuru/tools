
public class SinglePay {

    void pay(){	
	    
	String sum = "5" // PRICE
		
	HashMap<String, String> map = new HashMap<String, String>();
        map.put("public_key", "PUBLIC_KEY");
        map.put("action", "auth");
        map.put("amount", sum);
        map.put("currency", "UAH");
        map.put("description", "Оплата за пользование программой");
        map.put("order_id", "some UUID");
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
