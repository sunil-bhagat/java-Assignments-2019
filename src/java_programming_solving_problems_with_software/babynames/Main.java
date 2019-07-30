package babynames;

public class Main {
    public static void main(String[] args) throws  Exception{
        BabyNames names = new BabyNames();
        System.out.println(names.getTotalBirthsRankedHigher("Emily","2008","F"));
    }
}
