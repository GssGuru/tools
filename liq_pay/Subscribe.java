
public class Subscribe {
	
    void subscribe(){
	    
	String sum = "5" // PRICE
		
	HashMap<String, String> map = new HashMap<String, String>();
        map.put("public_key", "PUBLIC_KEY");
	map.put("version", "3");
	map.put("action", "subscribe");
        map.put("amount", sum);
        map.put("currency", "UAH");
        map.put("description", "Оплата за подписку");
        map.put("order_id", "some UUID");
        map.put("language", "ru");
        map.put("server_url", "https://gss.guru");
		
	map.put("subscribe", "2");
        map.put("subscribe_date_start", "yyyy-MM-dd HH:mm:ss");
        map.put("subscribe_periodicity", "month");

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
                    if ("subscribed".equals(object.optString("status"))) {
			String resOrderId = object.optString("liqpay_order_id"); // Нужен для отписки
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
