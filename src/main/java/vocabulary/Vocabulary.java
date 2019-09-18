package vocabulary;

import java.util.HashMap;
import java.util.HashSet;

public class Vocabulary {
    private HashSet<Word> vocabulary;
    private static int size;

    public Vocabulary() {
        vocabulary = new HashSet<Word>();
    }

    public int getSize() {
        return Vocabulary.size;
    }

    public void addWord(Word word) {
        if (word != null) vocabulary.add(word);
        Vocabulary.size++;
    }

    public String printWord(Word word) {
        String wor="";
        wor=wor+word.getRussia() + " " + word.getEnglish()+"\n";
return wor;
    }

    public String printVocabulary() {
        String vocab="";
        for (Word word : vocabulary) {
            vocab+=printWord(word);
        }
        return vocab;
    }

    public Word searchFromRussia(String russia) {
        Word searchWord = new Word("", "");
        for (Word word : vocabulary) if (word.getRussia().equals(russia)) searchWord = word;
        return searchWord;
    }

    public Word searchFromEnglish(String english) {
        Word searchWord = new Word("", "");
        for (Word word : vocabulary) if (word.getEnglish().equals(english)) searchWord = word;
        return searchWord;
    }

    public void deleteWord(Word word) {
        vocabulary.remove(searchFromRussia(word.getRussia()));
        Vocabulary.size--;
    }
}
