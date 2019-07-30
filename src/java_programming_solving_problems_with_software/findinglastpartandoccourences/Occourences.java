package findinglastpartandoccourences;

public class Occourences {
    /**
     * this method verifies weather there are at least two occurrence of first string in second string.
     * @param a sfirst string.
     * @param b second string.
     * @return boolean value indicating true or false.
     */
    public static boolean twoOccourences(String a, String b){
        int firstOcc = b.indexOf(a);
        if(firstOcc == -1) return false;
        int secondOcc = b.indexOf(a,firstOcc+a.length());
        if(secondOcc == -1) return false;
        return true;
    }

    /**
     * this method finds the string after occurrence of first string in second string.
     * @param first first string.
     * @param second second string
     * @return the remaining part of second string.
     */
    public static String lastPart(String first, String second){
        int index = second.indexOf(first);
        if(index == -1) return second;
        return second.substring(index+first.length());
    }
    public static void main(String[] args){
        System.out.println(twoOccourences("asd","ddasddasdasdfdfasd"));
        System.out.println(lastPart("zoo","forest"));
    }
}
