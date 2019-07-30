package java_programming_array_list_and_sturctured_data.vigenereprogram;

import java_programming_array_list_and_sturctured_data.vigenereprogram.CaesarCracker;

import java.io.*;
import java.util.*;

public class VigenereBreaker {

    private  String readFile(String filePath)
    {
        StringBuilder message = new StringBuilder();
        try (BufferedReader br = new BufferedReader(new FileReader(filePath)))
        {

            String sCurrentLine;
            while ((sCurrentLine = br.readLine()) != null)
            {
                message.append(sCurrentLine).append("\n");
            }
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
        return message.toString();
    }

    public String sliceString(String message, int whichSlice, int totalSlices) {
        String result = new String();
        for (int i = whichSlice; i < message.length(); i += totalSlices){
            result+=message.charAt(i);
        }
        return result;
    }

    public int[] tryKeyLength(String encrypted, int klength, char mostCommon) {
        int[] key = new int[klength];
        CaesarCracker cracker = new CaesarCracker(mostCommon);
        for (int i = 0; i < klength; i++){
            String s = sliceString(encrypted, i, klength);
            int a = cracker.getKey(s);
            key[i] = a;
        }
        return key;
    }

    public void breakVigenere () throws IOException {
        Scanner scan = new Scanner(System.in);
        System.out.println("please enter file path");
        String path = scan.next();
        String message = readFile(path);
        HashSet<String> dict = readDictionary(new File("/home/user/IdeaProjects/Java-Assignments-2019/src/java_programming_array_list_and_sturctured_data/vigenereprogram/dictionaries/English"));
        String msg = breakForLanguage(message,dict);
        System.out.println(msg);
    }
    public HashSet<String> readDictionary(File file) throws IOException {
        HashSet<String> dict = new HashSet<>();
        BufferedReader reader = new BufferedReader(new FileReader(file));
        String line;
        try{
            while((line = reader.readLine())!=null){
                dict.add(line.toLowerCase());
            }
        }catch (IOException e){
            e.printStackTrace();
        }
        return dict;
    }

    public int countWords(String message, HashSet<String> dictionary){
        String[] words = message.split("\\W+");
        int count = 0;
        for (String word: words){
            String wordLower = word.toLowerCase();
            if (dictionary.contains(wordLower)){
                count++;
            }
        }
        return count;
    }

    public String breakForLanguage(String encrypted,HashSet<String> dictionary){
        int maxCount = 0;
        String decryptedMessage = null;
        char mostCommon = mostCommonCharIn(dictionary);
        for(int i=1;i<=100;i++){
            int[] key = tryKeyLength(encrypted, i, mostCommon);
            VigenereCipher breaker = new VigenereCipher(key);
            String msg = breaker.decrypt(encrypted);
            int currentCount = countWords(msg,dictionary);
            if(currentCount >  maxCount){
                maxCount = currentCount;
                decryptedMessage = msg;
            }
        }
        return decryptedMessage;
    }
    private char mostCommonCharIn(HashSet<String> dictionary){
        int mostCount = 0;
        char mostCommon = 'e';
        Map<Character,Integer> letterCount =  new HashMap<Character, Integer>();
        for(String word : dictionary){
            for(char ch : word.toCharArray()){
                if(letterCount.containsKey(ch)){
                    letterCount.put(ch,letterCount.get(ch)+1);
                }else{
                    letterCount.put(ch,1);
                }
            }
        }
        for (char c: letterCount.keySet()){
            if (letterCount.get(c) > mostCount){
                mostCount = letterCount.get(c);
                mostCommon = c;
            }
        }
        return mostCommon;
    }
    
}
