package com.example.demo;

public class SampleRessponse {

    private int liczbaMalychLiter;
    private int liczbaDuzychLiter;
    private int liczbaLiczb;
    private int liczbaZnakowSpecjalnych;
    private String message;

    public SampleRessponse() {
        this.liczbaDuzychLiter = 0;
        this.liczbaMalychLiter = 0;
        this.liczbaLiczb = 0;
        this.liczbaZnakowSpecjalnych = 0;
    }

    public int getLiczbaMalychLiter() {
        return liczbaMalychLiter;
    }

    public void setLiczbaMalychLiter(int liczbaMalychLiter) {
        this.liczbaMalychLiter = liczbaMalychLiter;
    }

    public int getLiczbaDuzychLiter() {
        return liczbaDuzychLiter;
    }

    public void setLiczbaDuzychLiter(int liczbaDuzychLiter) {
        this.liczbaDuzychLiter = liczbaDuzychLiter;
    }

    public int getLiczbaLiczb() {
        return liczbaLiczb;
    }

    public void setLiczbaLiczb(int liczbaLiczb) {
        this.liczbaLiczb = liczbaLiczb;
    }

    public int getLiczbaZnakowSpecjalnych() {
        return liczbaZnakowSpecjalnych;
    }

    public void setLiczbaZnakowSpecjalnych(int liczbaZnakowSpecjalnych) {
        this.liczbaZnakowSpecjalnych = liczbaZnakowSpecjalnych;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

}
