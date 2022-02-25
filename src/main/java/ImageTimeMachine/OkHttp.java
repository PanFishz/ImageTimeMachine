package ImageTimeMachine;

import okhttp3.Headers;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

import java.io.IOException;
import org.json.*;


public class OkHttp {

    // only one client, singleton, better puts it in a factory,
    // multiple instances will create more memory.
    private final OkHttpClient httpClient = new OkHttpClient();

    public static void main(String[] args) throws IOException {
        OkHttp obj = new OkHttp();
        obj.sendGETSync(" ", 1);
    }

    public String sendGETSync(String word, int option) throws IOException {
        String key = "AIzaSyCg6HQGrzD5gR3vaHHzbtawpHmDO-wTyCE";
        String cx = "a05dcf97e5028dc74";
        Request request = null;
        if (option == 1) {
            request = new Request.Builder().url("https://17cranes.com/randomPeriod/").build();
        }
        else {
            request = new Request.Builder(). url("https://historical-random-facts-api.herokuapp.com/").build();
                    //.url("https://www.googleapis.com/customsearch/v1?key=" + key + "&cx=" + cx + "&q=" + word
                    //+ "&searchType=image")
                    //.addHeader("custom-key", "mkyong")  // add request headers
                    //.addHeader("User-Agent", "OkHttp Bot")
                    //.build();
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
            else
            {
                reply = jo.getString("fact");
                /*
                reply = jo.get("items").toString();
                String pageName = jo.getJSONObject("queries").getJSONArray("request").getJSONObject(0).getString("searchTerms");
                System.out.println(pageName);
                JSONArray arr = jo.getJSONArray("items"); // notice that `"posts": [...]`
                for (int i = 0; i < arr.length(); i++)
                {
                    String links = arr.getJSONObject(i).getString("link");
                    System.out.println(links);
                }*/

            }

            return reply;
        }

    }

}

