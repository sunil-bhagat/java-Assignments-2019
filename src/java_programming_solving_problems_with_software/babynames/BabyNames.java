package javaprbabynames;

import java.io.*;

public class BabyNames {
    private String line;
    private BufferedReader file;
    int recordStartingYear = 1880;
    int rocordEndingYear = 2014;

    /**
     * this method verifies the given input and return the  bufferedReader file .
     * @param str name of the year or decade.
     */
    private BufferedReader verifyDate(String str) throws Exception{
        int year =0;
        boolean isDecade= false;
        try{
            if(str.charAt(str.length()-1)== 's'){
                year = Integer.parseInt(str.substring(0,str.length()-1));
                isDecade = true;
            }else{
                year = Integer.parseInt(str);
            }
            if(year<recordStartingYear || year>rocordEndingYear){
                throw new Exception("There aren't any records available for the given date.");
            }
            return getFile(year,isDecade);
        }catch(Exception e){
            throw e;
        }

    }

    /**
     * this method reads the file and store it in bufferedreader.
     * @param year the year or decade .
     * @param isDecade indicates weather the the file is year or deacde.
     */
    private BufferedReader getFile(int year, boolean isDecade) throws IOException{
        try{
            if(isDecade){
              file = new BufferedReader( new FileReader("src/babynames/us_babynames/us_babynames_by_decade/yob"+year+"s.csv"));
            }else{
                file = new BufferedReader( new FileReader("src/babynames/us_babynames/us_babynames_by_year/yob"+year+".csv"));
            }
        }catch(IOException e){
            throw new IOException("NO Such File Found.");
        }
        return file;

    }

    /**
     * this method finds the total Number of births in a given year.
     * @param str the year in which total births is to be found.
     * @throws Exception
     */
    public void totalBirths(String str) throws  Exception{
        file = verifyDate(str);
        int total=0,males=0, females=0;
        while((line = file.readLine())!=null){
           String[] data = line.split(",");
           total += Integer.parseInt(data[2]);
           if(data[1].equals("F")){
               females += Integer.parseInt(data[2]);
           }else{
               males += Integer.parseInt(data[2]);
           }

        }
        System.out.println("Total Births : "+total);
        System.out.println("Total Male Births : "+males);
        System.out.println("Total Female Births : "+females);
    }

    /**
     * This method finds the rank of the given name in given year.
     * @param name name of the person.
     * @param year the year of birth.
     * @param gender gender of the person.
     * @return the rank of the name.
     * @throws Exception
     */
    public int getRank(String name, String year, String gender) throws Exception{
        file = verifyDate(year);
        int rank = 1;
        while((line = file.readLine()) != null){
            String[] data = line.split(",");
            if(data[1].equals(gender)){
                if(data[0].equals(name)){
                    return rank;
                }
                rank++;
            }
        }
        return -1;
    }

    /**
     * this method returns a name given a year,rank and gender.
     * @param givenRank rank of the name.
     * @param year year in which name is to be found.
     * @param gender gender of the name.
     * @return the name of the rank in the given year.
     * @throws Exception
     */
    public String getName(int givenRank, String year, String gender) throws Exception{
        file = verifyDate(year);
        int rank = 1;
        while((line = file.readLine()) != null){
            String[] data = line.split(",");
            if(data[1].equals(gender)){
                if(rank == givenRank){
                    return data[0];
                }
                rank++;
            }
        }
        return "NO NAME";
    }

    /**
     * This method finds the name with same rank in another year.
     * @param name name of the person.
     * @param givenYear the year in  which we have to find the name.
     * @param birthYear the year in which name belongs to.
     * @param gender gender of the person.
     * @return the new name.
     * @throws Exception
     */
    public String whatIsNameInYear(String name, String givenYear,String birthYear,String gender) throws Exception{
        int rank = getRank(name,birthYear,gender);
        String newName = getName(rank,givenYear,gender);
        if(!newName.equals("NO NAME")){
            return name+" born in "+birthYear+" would be "+newName+" if she was born in "+givenYear;
        }
        return "Name "+name+"was not used in "+givenYear;
    }

    /**
     * This method finds the highest rank of name in all the csv files.
     * @param name name of the person.
     * @param gender gender of the person.
     * @return the highest rank of the name.
     * @throws Exception
     */
    public int yearOfHighestRank(String name,String gender) throws Exception{
      int highestRank =-1;
      int highestYear = -1;
      int year = recordStartingYear;
      while(year <=  rocordEndingYear && highestRank!=1){
          int rank =  getRank(name,String.valueOf(year),gender);
          if(highestRank == -1 && rank != -1){
              highestRank = rank;
              highestYear = year;
          }else if(rank != -1 && rank < highestRank){
              highestRank = rank;
              highestYear = year;
          }
          year++;
        }
        return highestYear;
    }

    /**
     * This method finds the average rank of person in all the  year csv files.
     * @param name Name of the person whose average ranked is to be found.
     * @param gender gender of the person
     * @return the average rank of the person
     * @throws Exception
     */
    public float getAverageRank(String name, String gender) throws Exception{
        float averageRank = 0;
        int totalRanks = 0;
        int year = recordStartingYear;
        while(year <=  rocordEndingYear){
            int rank =  getRank(name,String.valueOf(year),gender);
            if(rank != -1){
                totalRanks++;
                averageRank+=rank;
            }
            year++;
        }
        if(totalRanks == 0 ){
            return -1;
        }
        return averageRank/totalRanks;
    }

    /**
     * This method finds total number of births higher than a certain person ranks in a certain year of same gender.
     * @param name Name of the person
     * @param year year in which we have to find total Births higher than the persns rank.
     * @param gender gender of the person.
     * @return the total numbers of births ranked higher.
     */
    public int getTotalBirthsRankedHigher(String name ,String year,String gender) throws Exception{
        file = verifyDate(year);
        int totalRankedhigher=0;
        while((line = file.readLine())!=null){
            String[] data = line.split(",");
            if(data[1].equals(gender)){
               if(data[0].equals(name)){
                   break;
               }
               totalRankedhigher+=Integer.parseInt(data[2]);
            }
        }
        return totalRankedhigher;
    }

}
