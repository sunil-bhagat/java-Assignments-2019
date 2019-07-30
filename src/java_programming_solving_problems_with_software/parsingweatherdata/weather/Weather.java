package parsingweatherdata.weather;

import java.io.*;


public class Weather
{
    private StringBuffer lowestLine;
    private File file = null;
    private String line;
    private float bogustemp = -9999;
    private File folder = null;
    private File fileList[];
    private float coldestHour = bogustemp;
    private int lowestHumidity = (int)bogustemp;
    private boolean isHeader = true;


    /**
     * reads all the files in the given directory.
     * @param path path of the directory.
     * @throws IOException throws IOException when invalid path is given.
     */
    private void readFolder(String path) throws IOException {
        try{
            folder = new File(path);
            fileList = folder.listFiles();
            if(fileList==null) {
                throw new IOException("Invalid Path");
            }
        }catch(IOException e){
            throw e;
        }
    }

    /**
     * read the given file and store it in file variable.
     * @param path path of the csv file from which data is to read.
     */
    private void readFile(String path) throws IOException{
        file = new File(path);
    }

    /**
     * find the coldest hour in the given file.
     * @param inputPath path of the csv file from which data is to read.
     * @return returns a string containing coldest temp and its associated details in csv record format.
     * @throws IOException throws exception when incorrect path file is inputted.
     */
    public String coldestHourInFile(String inputPath) throws IOException{
        coldestHour = bogustemp;
        StringBuffer coldestHourLine =new StringBuffer();
        BufferedReader bufferedFile = new BufferedReader(new FileReader(file));
        readFile(inputPath);
        while ((line = bufferedFile.readLine())!=null){
            if(isHeader){
                isHeader = false;
                continue;
            }
            String[] data = line.split(",");
            float temp = Float.parseFloat(data[1]);
            if(temp==bogustemp){
                continue;
            }
            if(coldestHour == bogustemp || temp < coldestHour){
                coldestHour = temp;
                coldestHourLine = new StringBuffer(line);
            }
        }
        isHeader = true;
        return coldestHourLine.toString();
    }

    /**
     * This method parses a csv record and finds the minimum temperature.
     * @param tempFile the input file.
     * @param printContents boolean value indicating if we want to print data while parsing.
     * @throws IOException throws IOException if there is error while reading file.
     */
    private void  findMinTemp(File tempFile,boolean printContents) throws  IOException{
        BufferedReader currentFile = new BufferedReader( new FileReader(tempFile));
        while ((line = currentFile.readLine())!=null){
            if(isHeader){
                isHeader = false;
                continue;
            }
            String[] data = line.split(",");
            if(printContents){
                System.out.println(data[data.length-1]+" "+data[0]+" "+data[1]);
            }
            float temp = Float.parseFloat(data[1]);
            if(temp==bogustemp){
                continue;
            }
            if(coldestHour == bogustemp || temp < coldestHour){
                coldestHour = temp;
                file =  tempFile;
            }
        }
        isHeader = true;
    }

    /**
     * This method finds the minimum temperature in all the files in  a directory.
     * @param path path of the directory containing the csv files.
     * @return returns the string containing minimum temperature.
     * @throws IOException throws IOException if there is error while reading file.
     */
    public String fileWithColdestTemperature(String path)throws IOException{
        coldestHour =  bogustemp;
        readFolder(path);
        for(File tempFile :fileList){
            if(tempFile.isFile()){
                try{
                    findMinTemp(tempFile,false);
                }catch(IOException e){
                    System.out.println("Not a valid File...");
                }
            }
        }
        System.out.println("Coldest day was in the file "+file.getName());
        System.out.println("Coldest temperature on that day was "+coldestHour);
        System.out.println("All the Temperature on the Coldest Day were :");
        findMinTemp(file,true);

        return file.getName();
    }

    /**
     * This methods finds the lowest humidity in the given csv file.
     * @param currentFile csv file whose minimum humidity we want to find.
     * @throws IOException throws IOException if there is error while reading file.
     */
    private void  findMinHumidity(File currentFile) throws IOException {
       BufferedReader bufferedFile = new BufferedReader(new FileReader(currentFile));
        while ((line = bufferedFile.readLine()) != null) {
            if (isHeader) {
                isHeader = false;
                continue;
            }
            String[] data = line.split(",");
            if (data[3].equals("N/A")) continue;
            int tempHumidity = Integer.parseInt(data[3]);
            if (lowestHumidity == bogustemp || tempHumidity < lowestHumidity) {
                lowestHumidity = tempHumidity;
                lowestLine = new StringBuffer(line);
            }

        }
        isHeader = true;
    }

    /**
     * THIS method takes calls the findtheMinHumidity() and passes its result in string.
     * @param path path of the csv file.
     * @return return the lowest humidity in string format.
     * @throws IOException throws IOException if there is error while reading file.
     */
    public String lowestHumidityInFile(String path) throws IOException{
        lowestHumidity =  (int)bogustemp;
        readFile(path);
        findMinHumidity(file);
        return lowestLine.toString();

    }

    /**
     * finds the lowest humidity in multiple files.
     * @param path path of the directory containing csv files.
     * @return returns the lowest humidity ammong all the files.
     * @throws IOException
     */
    public String lowestHumidityInManyFiles(String path) throws IOException{
        lowestHumidity =  (int)bogustemp;
        readFolder(path);
        for(File tempFile :fileList){
            if(tempFile.isFile()){
                try{
                    findMinHumidity(tempFile);
                }catch(IOException e){
                    System.out.println("Not a valid File...");
                }
            }
        }
        return lowestLine.toString();

    }

    /**
     *  finds the average temperature in a csv file.
     * @param path path of the csv file.
     * @return the average temperature
     * @throws IOException throws IOException if there is error while reading file.
     * @throws NullPointerException
     */
    public double averageTemperatueInFile(String path) throws IOException , NullPointerException{
        readFile(path);
        float total = 0,count = 0;
        BufferedReader bufferedFile = new BufferedReader(new FileReader(file));
        while ((line = bufferedFile.readLine())!=null){
            if(isHeader){
                isHeader = false;
                continue;
            }
            String[] data = line.split(",");
            float temp = Float.parseFloat(data[1]);
            if(temp==bogustemp){
                continue;
            }
            total+=temp;
            count++;
        }
        return total/count;
    }

    /**
     *  This method finds the average temperature at a given minimum humidity.
     * @param path path of the given csv file.
     * @param humidity the minimum humidity.
     * @return returns the average temperature.
     * @throws IOException throws IOException if there is error while reading file.
     */
    public String averageTemperatureWithHumidityInFile(String path,float humidity)throws IOException{
        readFile(path);
        float total = 0,count = 0;
        BufferedReader bufferedFile = new BufferedReader(new FileReader(file));
        while ((line = bufferedFile.readLine())!=null){
            if(isHeader){
                isHeader = false;
                continue;
            }
            String[] data = line.split(",");
            float temp = Float.parseFloat(data[1]);
            if(temp==bogustemp){
                continue;
            }
            if(humidity <= Float.parseFloat(data[3])){
                total+=temp;
                count++;
            }

        }
        if(total>0){
            total = total/count;
            return "Average Temp when high Humidity is "+total;
        }
        return "No temperatures with that humidity";

    }


}
