package batchgrayscaleimageprocessing.images;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.ArrayList;
import javax.imageio.ImageIO;
import java.io.File;


public class Images {
    private ArrayList<BufferedImage> images = new ArrayList<>();
    private File folder = null;
    private File fileList[];
    private String destination;

    /**
     * Constructor which reads the path of the given folder.
     * @param path path of the  folder
     * @throws Exception
     */
    public Images(String path) throws Exception {
        try{
            folder = new File(path);
            fileList = folder.listFiles();
            if(fileList==null) {
                throw new Exception("Invalid Path");
            }
        }catch(Exception e){
            throw e;
        }
    }
    /**
     * this method extracts images from the given folder.
     */
    public void extractImages(){
        for(int i=0;i<fileList.length;i++){
           if(fileList[i].isFile()){
               try{
                   BufferedImage image = ImageIO.read(fileList[i]);
                   if(image!=null){
                       images.add(image);
                   }
               }catch(IOException e){
                   System.out.println("Not a valid image");
               }
           }
        }
    }

    /**\
     * set destination where the new images are the be saved.
     * @param destination
     */
    public void setDestination(String destination){
        this.destination =  destination;
    }

    /**
     * converts all the images into grayscale images.s
     * @throws Exception
     */
    public void grayScale() throws Exception{
        try {
            int index =0;
            for (BufferedImage image : images) {
                for (int i = 0; i < image.getHeight(); i++) {
                    for (int j = 0; j < image.getWidth(); j++) {
                        Color c = new Color(image.getRGB(j, i));
                        int red = (int) (c.getRed() * 0.2126);
                        int green = (int) (c.getGreen() * 0.7125);
                        int blue = (int) (c.getBlue() * 0.0722);
                        Color newColor = new Color(red + green + blue, red + green + blue, red + green + blue);
                        image.setRGB(j, i, newColor.getRGB());
                    }
                }
                File output = new File(destination+"/image"+ (index++) +".jpg");
                System.out.println("Converting Image "+index);
                ImageIO.write(image, "jpg", output);
            }
        }catch (IOException e){
            throw new Exception("invalid destination path.");
        }
    }
}
