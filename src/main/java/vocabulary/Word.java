package vocabulary;

public class Word {
    private String russia;
    private String english;

    public Word(String russia, String english) {
        this.russia = russia;
        this.english = english;
    }
    public Word() {
    }

    public String getRussia() {
        return russia;
    }

    public String getEnglish() {
        return english;
    }

}
