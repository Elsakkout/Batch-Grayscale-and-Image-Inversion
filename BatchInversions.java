import edu.duke.*;
import java.io.*;

/**
 * Write a description of BatchInversions here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class BatchInversions {
    public ImageResource makeInversion (ImageResource inImage) {
		//I made a blank image of the same size
		ImageResource outImage = new ImageResource(inImage.getWidth(), inImage.getHeight());
		//for each pixel in outImage
		for (Pixel pixel: outImage.pixels()) {
			//look at the corresponding pixel in inImage
			Pixel inPixel = inImage.getPixel(pixel.getX(), pixel.getY());
			//compute inPixel's red + inPixel's blue + inPixel's green
			//divide that sum by 3 (call it average)
			int newR = 255 - inPixel.getRed(); 
			int newG = 255 - inPixel.getGreen();
			int newB = 255 - inPixel.getBlue();
			//set pixel's red to average
			pixel.setRed(newR);
			//set pixel's green to average
			pixel.setGreen(newG);
			//set pixel's blue to average
			pixel.setBlue(newB);
		}
		//outImage is your answer
		return outImage;
   }
   
   public void selectAndConvert () {
		DirectoryResource dr = new DirectoryResource();
		for (File f : dr.selectedFiles()) {
			ImageResource inImage = new ImageResource(f);
			ImageResource inverse = makeInversion(inImage);
			String fname = inImage.getFileName();
			String newName = "inverted-" + fname;
			inverse.setFileName("./images/" + newName);
			inverse.draw();
			inverse.save();
		}
	}
}
