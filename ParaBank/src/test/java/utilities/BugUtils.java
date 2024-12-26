package utilities;

//import okhttp3.*;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;

public class BugUtils {

    private static final String API_KEY = "sk-proj-xV685abjM89e3GVBiV4IT3BlbkFJkkdmfrmQ0HLMD45pEtuv"; // Replace with your actual API key
    @SuppressWarnings("unused")
	private static final String ENDPOINT_URL = "https://api.openai.com/v1/chat/completions";

    public static String generateBugDetails(String prompt) {
        String url = "https://api.openai.com/v1/chat/completions";
        String apiKey = API_KEY;
        String model = "gpt-4o";
        try {
            URL obj = new URL(url);
            HttpURLConnection connection = (HttpURLConnection) obj.openConnection();
            connection.setRequestMethod("POST");
            connection.setRequestProperty("Authorization", "Bearer " + apiKey);
            connection.setRequestProperty("Content-Type", "application/json");
            // The request body
            String body = "{\"model\": \"" + model + "\", \"messages\": [{\"role\": \"user\", \"content\": \"" + prompt + "\"}]}";
            connection.setDoOutput(true);
            OutputStreamWriter writer = new OutputStreamWriter(connection.getOutputStream());
            writer.write(body);
            writer.flush();
            writer.close();
            // Response from ChatGPT
            BufferedReader br = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            String line;
            StringBuffer response = new StringBuffer();
            while ((line = br.readLine()) != null) {
                response.append(line);
            }
            br.close();
            // calls the method to extract the message.
           // return response.toString();
            JSONObject jsonResponse = new JSONObject(response.toString());
            String bugReport = jsonResponse.getJSONArray("choices").getJSONObject(0).getJSONObject("message").getString("content");
            return bugReport;

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

}

