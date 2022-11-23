package org.example;

import java.util.List;

public class Transcript {

    private String audio_url;
    private String id;
    private String status;
    private String text;
    private List<Word> words;

    public List<Word> getWords() {
        return words;
    }

    public void setWords(List<Word> words) {
        this.words = words;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getAudio_url() {
        return audio_url;
    }

    public void setAudio_url(String audio_url) {
        this.audio_url = audio_url;
    }

    @Override
    public String toString() {
        return "Transcript{" +
                "audio_url='" + audio_url + '\'' +
                ", id='" + id + '\'' +
                ", status='" + status + '\'' +
                ", text='" + text + '\'' +
                ", words=" + words +
                '}';
    }
}
