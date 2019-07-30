package java_programming_array_list_and_sturctured_data;

import java_programming_array_list_and_sturctured_data.caesarcipher.CaesarCipher;
import java_programming_array_list_and_sturctured_data.characternames.CharactersInPlay;
import java_programming_array_list_and_sturctured_data.vigenereprogram.VigenereBreaker;
import java_programming_array_list_and_sturctured_data.wordfrequencies.WordFrequencies;

public class Main {
    public static void main(String[] args) throws Exception {
//        CaesarCipher lock = new CaesarCipher();
//        String message = lock.encrypt("Heeello how are you ?", 3);
//        System.out.println("Encrypted message : " + message);
//        message = lock.decrypt(message);
//        System.out.println("\nDecrypted message : " + message);
//
//        message = lock.encryptTwoKeys("Heeeeeello how you doing?", 23, 17);
//        System.out.println("\nEncrypted twokeyMessage : " + message);
//        message = lock.decrypttwo(message);
//        System.out.println("\nDecrypted twokeyMessage : " + message);
//
//        WordFrequencies words = new WordFrequencies();
//        System.out.println("\nFrequencies of all words ");
//        words.findUnique("src/test.txt");
//        System.out.println(words.findIndexOfMax());
//
//        CharactersInPlay characters = new CharactersInPlay();
//        System.out.println("\n Find All Characters");
//        characters.findAllCharacters("src/macbeth.txt");
//        System.out.println("\n Character With Num Parts.\n");
//        characters.characterWithNumParts(12,12);

        VigenereBreaker breaker = new VigenereBreaker();
        //path : /home/user/IdeaProjects/Java-Assignments-2019/src/java_programming_array_list_and_sturctured_data/vigenereprogram/messages/athensKeyFlute.txt
        breaker.breakVigenere();
    }
}
