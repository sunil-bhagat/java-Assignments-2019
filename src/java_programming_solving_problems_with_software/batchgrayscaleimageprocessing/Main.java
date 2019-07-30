package batchgrayscaleimageprocessing;

import batchgrayscaleimageprocessing.images.Images;
import  java.util.Scanner;

public class Main {
    public static void main(String[] args) throws Exception{
        String source, destination;
        Scanner scan  = new Scanner(System.in);
        System.out.println("Enter the source directory path.");
        source = scan.next();
        System.out.println("Enter the destination directory path.");
        destination = scan.next();

        Images images = new Images(source);
        images.extractImages();
        images.setDestination(destination);
        images.grayScale();
    }
}
