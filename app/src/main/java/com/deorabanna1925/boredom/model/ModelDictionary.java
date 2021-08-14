package com.deorabanna1925.boredom.model;

import java.util.ArrayList;

public class ModelDictionary {

    private String word;
    private String phonetic;
    private ArrayList<Phonetics> phonetics;
    private String origin;
    private ArrayList<Meanings> meanings;

    public ModelDictionary() {
    }

    public ModelDictionary(String word, String phonetic, ArrayList<Phonetics> phonetics, String origin, ArrayList<Meanings> meanings) {
        this.word = word;
        this.phonetic = phonetic;
        this.phonetics = phonetics;
        this.origin = origin;
        this.meanings = meanings;
    }

    public String getWord() {
        return word;
    }

    public void setWord(String word) {
        this.word = word;
    }

    public String getPhonetic() {
        return phonetic;
    }

    public void setPhonetic(String phonetic) {
        this.phonetic = phonetic;
    }

    public ArrayList<Phonetics> getPhonetics() {
        return phonetics;
    }

    public void setPhonetics(ArrayList<Phonetics> phonetics) {
        this.phonetics = phonetics;
    }

    public String getOrigin() {
        return origin;
    }

    public void setOrigin(String origin) {
        this.origin = origin;
    }

    public ArrayList<Meanings> getMeanings() {
        return meanings;
    }

    public void setMeanings(ArrayList<Meanings> meanings) {
        this.meanings = meanings;
    }

    public class Phonetics {

        private String text;
        private String audio;

        public Phonetics() {
        }

        public Phonetics(String text, String audio) {
            this.text = text;
            this.audio = audio;
        }

        public String getText() {
            return text;
        }

        public void setText(String text) {
            this.text = text;
        }

        public String getAudio() {
            return audio;
        }

        public void setAudio(String audio) {
            this.audio = audio;
        }
    }

    public class Meanings {

        private String partOfSpeech;
        private ArrayList<Definitions> definitions;

        public Meanings() {
        }

        public Meanings(String partOfSpeech, ArrayList<Definitions> definitions) {
            this.partOfSpeech = partOfSpeech;
            this.definitions = definitions;
        }

        public String getPartOfSpeech() {
            return partOfSpeech;
        }

        public void setPartOfSpeech(String partOfSpeech) {
            this.partOfSpeech = partOfSpeech;
        }

        public ArrayList<Definitions> getDefinitions() {
            return definitions;
        }

        public void setDefinitions(ArrayList<Definitions> definitions) {
            this.definitions = definitions;
        }
    }

    public class Definitions {

        private String definition;
        private String example;
        private ArrayList<String> synonyms;
        private ArrayList<String> antonyms;

        public Definitions() {
        }

        public Definitions(String definition, String example, ArrayList<String> synonyms, ArrayList<String> antonyms) {
            this.definition = definition;
            this.example = example;
            this.synonyms = synonyms;
            this.antonyms = antonyms;
        }

        public String getDefinition() {
            return definition;
        }

        public void setDefinition(String definition) {
            this.definition = definition;
        }

        public String getExample() {
            return example;
        }

        public void setExample(String example) {
            this.example = example;
        }

        public ArrayList<String> getSynonyms() {
            return synonyms;
        }

        public void setSynonyms(ArrayList<String> synonyms) {
            this.synonyms = synonyms;
        }

        public ArrayList<String> getAntonyms() {
            return antonyms;
        }

        public void setAntonyms(ArrayList<String> antonyms) {
            this.antonyms = antonyms;
        }
    }

}

