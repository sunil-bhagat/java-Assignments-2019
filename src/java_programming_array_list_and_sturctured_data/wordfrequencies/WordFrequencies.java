package java_programming_array_list_and_sturctured_data.wordfrequencies;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class WordFrequencies {
    private ArrayList<String> myWords;
    private ArrayList<Integer> frequency;
    private BufferedReader file;

    public WordFrequencies() {
        myWords = new ArrayList<>();
        frequency = new ArrayList<>();
    }

    private void readFile(String path) {
        try {
            file = new BufferedReader(new FileReader(path));
        } catch (IOException e) {
            System.out.println("Error while reading File...." + e.getStackTrace());
        }

    }

    private void resetDefault() {
        myWords.clear();
        frequency.clear();
    }

    public void printUniqueWords() {
        int index;
        for (int i = 0; i < myWords.size(); i++) {
            System.out.println("word : " + myWords.get(i) + " - " + frequency.get(i));
        }
    }

    public void findUnique(String path) throws IOException {
        resetDefault();
        readFile(path);
        String line;
        while ((line = file.readLine()) != null) {
            String[] words = line.toLowerCase().split(" ");
            for (String word : words) {
                int index = myWords.indexOf(word);
                if (index == -1) {
                    myWords.add(word);
                    frequency.add(1);
                    index = myWords.indexOf(word);
                } else {
                    frequency.set(index, 1 + frequency.get(index));
                }

            }

        }
        printUniqueWords();
    }

    public int findIndexOfMax() {
        int max = 0;
        for (int i = 0; i < frequency.size(); i++) {
            if (frequency.get(i) > frequency.get(max)) {
                max = i;
            }
        }
        return max;
    }

}
