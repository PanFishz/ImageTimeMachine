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
        Request request = buildRequest(word, option);
        try (Response response = httpClient.newCall(request).execute()) {
            if (!response.isSuccessful()) throw new IOException("Unexpected code " + response);
            return getReply(response, option);
        }
    }

    private Request buildRequest(String word, int option) {
        if (option == 1) {
            return new Request.Builder().url("https://17cranes.com/randomPeriod/").build();
        }
        else if (option == 2) {
            return new Request.Builder().url("https://historical-random-facts-api.herokuapp.com/").build();
        }
        else if (option == 3) {
            return new Request.Builder().url("https://image-service-cs361.herokuapp.com/image?searchTerm="
                    + word).build();
        }
        else {
            return new Request.Builder().url(getGoogleApi(word)).build();
        }
    }

    private String getGoogleApi(String word) {
        String key = "AIzaSyCg6HQGrzD5gR3vaHHzbtawpHmDO-wTyCE";
        String cx = "a05dcf97e5028dc74";
        return ("https://www.googleapis.com/customsearch/v1?key="
                + key + "&cx=" + cx + "&q=" + word + "&searchType=image");
    }

    private String getReply(Response response, int option) throws IOException {
        printResponseHeaders(response);
        String reply = response.body().string(); // Get response body
        if (option == 3) { //response is a plain url, return as it is
            return reply;
        }
        JSONObject jsonObject = new JSONObject(reply);
        if (option == 1) {
            return jsonObject.getString("timePeriod");
        }
        else if (option == 2)
        {
            return jsonObject.getString("fact");
        }
        else {
            printImageUrls(jsonObject);
            return jsonObject.get("items").toString();
        }
    }

    private void printResponseHeaders(Response response) {
        // Get response headers for diagnosis
        Headers responseHeaders = response.headers();
        for (int i = 0; i < responseHeaders.size(); i++) {
            System.out.println(responseHeaders.name(i) + ": " + responseHeaders.value(i));
        }
    }

    private void printImageUrls(JSONObject jo) {
        // Get search term and 10 urls
        String searchTerm = jo.getJSONObject("queries").getJSONArray("request")
                .getJSONObject(0).getString("searchTerms");
        System.out.println("searchTerm: " + searchTerm);
        JSONArray arr = jo.getJSONArray("items");
        for (int i = 0; i < arr.length(); i++)
        {
            String links = arr.getJSONObject(i).getString("link");
            System.out.println(links);
        }
    }

}

