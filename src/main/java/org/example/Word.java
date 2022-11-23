package org.example;

public class Word {

    private String text;
    private int start;
    private int end;
    private double confidence;

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public int getStart() {
        return start;
    }

    public void setStart(int start) {
        this.start = start;
    }

    public int getEnd() {
        return end;
    }

    public void setEnd(int end) {
        this.end = end;
    }

    public double getConfidence() {
        return confidence;
    }

    public void setConfidence(double confidence) {
        this.confidence = confidence;
    }

    @Override
    public String toString() {
        return "Word{" +
                "text='" + text + '\'' +
                ", start=" + start +
                ", end=" + end +
                ", confidence=" + confidence +
                '}';
    }
}
