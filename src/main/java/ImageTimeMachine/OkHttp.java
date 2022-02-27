package ImageTimeMachine;

import okhttp3.Headers;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

import java.io.IOException;
import org.json.*;


public class OkHttp {

    private final OkHttpClient httpClient = new OkHttpClient();

    public String sendGETSync(String word, int option) throws IOException {
        String key = "AIzaSyCg6HQGrzD5gR3vaHHzbtawpHmDO-wTyCE";
        String cx = "a05dcf97e5028dc74";
        Request request;
        if (option == 1) {
            request = new Request.Builder().url("https://17cranes.com/randomPeriod/").build();
        }
        else if (option == 2) {
            request = new Request.Builder().url("https://historical-random-facts-api.herokuapp.com/").build();
        }
        else {
            request = new Request.Builder().url("https://www.googleapis.com/customsearch/v1?key="
                    + key + "&cx=" + cx + "&q=" + word + "&searchType=image").build();
        }
        try (Response response = httpClient.newCall(request).execute()) {
            if (!response.isSuccessful()) throw new IOException("Unexpected code " + response);
            System.out.println(response);
            // Get response headers
            Headers responseHeaders = response.headers();
            for (int i = 0; i < responseHeaders.size(); i++) {
                System.out.println(responseHeaders.name(i) + ": " + responseHeaders.value(i));
            }

            // Get response body
            String reply = response.body().string();
            JSONObject jo = new JSONObject(reply);
            if (option == 1) {
                reply = jo.getString("timePeriod");
            }
            else if (option == 2)
            {
                reply = jo.getString("fact");
            }
            else {
                reply = jo.get("items").toString();
                String pageName = jo.getJSONObject("queries").getJSONArray("request").getJSONObject(0).getString("searchTerms");
                System.out.println(pageName);
                JSONArray arr = jo.getJSONArray("items");
                for (int i = 0; i < arr.length(); i++)
                {
                    String links = arr.getJSONObject(i).getString("link");
                    System.out.println(links);
                }
            }
            return reply;
        }

    }

}

