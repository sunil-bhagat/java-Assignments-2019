package java_programming_array_list_and_sturctured_data.caesarcipher;

public class CaesarCipher {
    private String alphabets;
    private String encryptedAlphabets;
    private char  mostUsed;
    private int mostUsedPos;
    private int[] freq;

    public CaesarCipher(){
        mostUsed = 'e';
        mostUsedPos = 4;
    }

    public  CaesarCipher(char ch){
        resetDefaults();
        mostUsed = ch;
        mostUsedPos = alphabets.indexOf(mostUsed);

    }

    /**
     * this method creates encrypted alphabets by shifting English alphabets with key value;
     *
     * @param key
     */
    private void setEncryptionAlphabets(int key) {
        if (key > 26) key = key % 26;
        resetDefaults();
        encryptedAlphabets = alphabets.substring(key) + alphabets.substring(0, key);
        alphabets += alphabets.toLowerCase();
        encryptedAlphabets += encryptedAlphabets.toLowerCase();
    }

    /**
     * This method resets the alphabets to original value;
     */
    private void resetDefaults() {
        alphabets = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
    }

    /**
     * This method encrypts the input by shifting them with given key value,
     *
     * @param input string which is to be encrypted.
     * @param key   value which will be use to shift alphabets.
     * @return the encrypted alphabets.
     */
    public String encrypt(String input, int key) {
        int index;
        char newChar;
        StringBuilder output = new StringBuilder(input);
        setEncryptionAlphabets(key);
        for (int i = 0; i < output.length(); i++) {
            char currChar = output.charAt(i);
            index = alphabets.indexOf(currChar);
            if (index == -1) {
                continue;
            }
            newChar = encryptedAlphabets.charAt(index);
            output.setCharAt(i, newChar);
        }
        resetDefaults();
        return output.toString();
    }

    /**
     * this method encrypts the input with help of two keys, one for odd position character and other for even postion characters.
     *
     * @param input the string that is to be encrypted.
     * @param key1  key 1 for odd positons.
     * @param key2  key 2 for even positions.
     * @return the encrypted string.
     */
    public String encryptTwoKeys(String input, int key1, int key2) {
        int index;
        char newChar;
        StringBuilder output = new StringBuilder(input);
        setEncryptionAlphabets(key1);
        for (int i = 0; i < output.length(); i += 2) {
            char currChar = output.charAt(i);
            index = alphabets.indexOf(currChar);
            if (index == -1) continue;
            newChar = encryptedAlphabets.charAt(index);
            output.setCharAt(i, newChar);
        }
        setEncryptionAlphabets(key2);
        for (int i = 1; i < output.length(); i += 2) {
            char currChar = output.charAt(i);
            index = alphabets.indexOf(currChar);
            if (index == -1) continue;
            newChar = encryptedAlphabets.charAt(index);
            output.setCharAt(i, newChar);
        }
        resetDefaults();
        return output.toString();
    }

    /**
     * This methods counts the frequency of letters in the given string
     * and stroes them in the freq[] array.
     *
     * @param message the input string.
     */
    private void countLetters(String message) {
        freq = new int[26];
        for (int i = 0; i < message.length(); i++) {
            char ch = Character.toUpperCase(message.charAt(i));
            int index = alphabets.indexOf(ch);
            if (index == -1) continue;
            freq[index]++;
        }
    }

    /**
     * This method finds the index of highest frequency in the freq array.
     *
     * @return
     */
    private int getMaxCharIndex() {
        int maxIndex = 0;
        for (int i = 0; i < freq.length; i++) {
            if (freq[i] > freq[maxIndex]) {
                maxIndex = i;
            }
        }
        return maxIndex;
    }

    /**
     * This method decrypts the given encrypted message.
     * this method can only decrypts message whose original message had highest frequency of  variable mostUsed
     * which by default in english language is 'e'.
     * @param message the encrypted message.
     * @return the decrypted message.
     */
    public String decrypt(String message) {
        resetDefaults();
        countLetters(message);
        int maxCharIndex = getMaxCharIndex();
        int key = maxCharIndex - mostUsedPos;
        if (maxCharIndex < mostUsedPos) {
            key = 26 - (mostUsedPos - maxCharIndex);
        }
        return encrypt(message, 26 - key);
    }

    /**
     * this method returns string that contains char nly ar either odd or even position,
     *
     * @param message the original string
     * @param part    int value first or second part.
     * @return
     */
    private String halfString(String message, int part) {
        String halfMessage = "";
        int i = 0;
        if (part == 1) i = 1;
        for (; i < message.length(); i += 2) {
            halfMessage += message.charAt(i);
        }
        return halfMessage;
    }

    /**
     * this method decrypts messages that have been encrypted with two keys.
     *
     * @param message the encrypted message.
     * @return the decrypted message.
     */
    public String decrypttwo(String message) {
        int[] keys = new int[2];
        for (int i = 0; i < 2; i++) {
            String halfMessage = halfString(message, i);
            countLetters(halfMessage);
            int maxCharIndex = getMaxCharIndex();
            keys[i] = maxCharIndex - mostUsedPos;
            if (maxCharIndex < mostUsedPos) {
                keys[i] = 26 - (mostUsedPos - maxCharIndex);
            }
        }
        return encryptTwoKeys(message, 26 - keys[0], 26 - keys[1]);
    }
}
