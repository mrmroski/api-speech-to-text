package org.example;

import com.google.gson.Gson;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.OptionalDouble;

public class Main {
    public static void main(String[] args) throws URISyntaxException, IOException, InterruptedException {

        Constants constants = new Constants();

        String uri = "https://api.assemblyai.com/v2/transcript";

        Transcript transcript = new Transcript();
        transcript.setAudio_url("https://bit.ly/3yxKEIY");

        Gson gson = new Gson();
        String jsonRequest = gson.toJson(transcript);

        System.out.println("JSON Request:");
        System.out.println(jsonRequest);
        System.out.println();

        HttpRequest postRequest = HttpRequest.newBuilder()
                .uri(new URI(uri))
                .header("Authorization", Constants.getAPI())
                .POST(HttpRequest.BodyPublishers.ofString(jsonRequest))
                .build();

        HttpClient httpClient = HttpClient.newHttpClient();

        HttpResponse<String> postResponse = httpClient
                .send(postRequest, HttpResponse.BodyHandlers.ofString());

        System.out.println("Response body:");
        System.out.println(postResponse.body());
        System.out.println();

        transcript = gson.fromJson(postResponse.body(), Transcript.class);

        System.out.println("AssemblyAI id:");
        System.out.println(transcript.getId());
        System.out.println();

        HttpRequest getRequest = HttpRequest.newBuilder()
                .uri(new URI(uri + "/" + transcript.getId()))
                .header("Authorization", Constants.getAPI())
                .build();

        HttpResponse<String> getResponse;

        while (true) {
            getResponse = httpClient
                    .send(getRequest, HttpResponse.BodyHandlers.ofString());
            transcript = gson.fromJson(getResponse.body(), Transcript.class);

            System.out.println(transcript.getStatus());

            if ("completed".equals(transcript.getStatus()) || "error".equals(transcript.getStatus())) {
                break;
            }

            Thread.sleep(1000);
        }
        System.out.println();

        System.out.println("Transcription completed!");
        System.out.println();

        System.out.println("Response body:");
        System.out.println(getResponse.body());
        System.out.println();

        transcript = gson.fromJson(getResponse.body(), Transcript.class);

        double overallConfidence = transcript.getWords().stream()
                .mapToDouble(Word::getConfidence)
                .average()
                .orElseThrow(RuntimeException::new);

        System.out.println("Overall confidence is: " + overallConfidence);
        System.out.println();

        System.out.println("List of words:");
        for (Word word : transcript.getWords()){
            System.out.println(word);
        }
    }
}