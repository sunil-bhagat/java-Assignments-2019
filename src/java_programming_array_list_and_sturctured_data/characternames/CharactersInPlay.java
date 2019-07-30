package java_programming_array_list_and_sturctured_data.characternames;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class CharactersInPlay {
    private ArrayList<String> myWords;
    private ArrayList<Integer> frequency;
    private BufferedReader file;

    public CharactersInPlay() {
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
            if (frequency.get(i) > 10)
                System.out.println("word : " + myWords.get(i) + " - " + frequency.get(i));
        }
    }

    private void update(String person) {
        int index = myWords.indexOf(person);
        if (index == -1) {
            myWords.add(person);
            frequency.add(1);
            index = myWords.indexOf(person);
        } else {
            frequency.set(index, 1 + frequency.get(index));
        }
    }

    public void findAllCharacters(String path) throws IOException {
        resetDefault();
        readFile(path);
        String line;
        while ((line = file.readLine()) != null) {
            String[] words = line.split(" ");
            String person = "";
            if (words.length > 2) {
                for (String word : words) {
                    word.trim();
                    if (word.length() > 0) {
                        //  System.out.println(word);
                        if (word.charAt(word.length() - 1) == ',') break;
                        if (word.charAt(0) == word.toUpperCase().charAt(0)) {
                            person += word + " ";
                        } else {
                            break;
                        }
                        if (person.length() > 0 && person.charAt(person.length() - 2) == '.') {
                            update(person);
                            update(person);
                            break;
                        }
                    }
                }
            }

        }
        printUniqueWords();
    }
    public void characterWithNumParts(int num1 , int num2){
        for(int i=0;i<frequency.size();i++){
            if(frequency.get(i)>=num1 && frequency.get(i)<=num2){
                System.out.println("word : " + myWords.get(i) + " - " + frequency.get(i));
            }
        }
    }

}
