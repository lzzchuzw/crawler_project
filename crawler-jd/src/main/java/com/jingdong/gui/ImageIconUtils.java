package com.jingdong.gui;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.URL;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;



public class ImageIconUtils {
	public final static String jpeg = "jpeg";
    public final static String jpg = "jpg";
    public final static String gif = "gif";
    public final static String tiff = "tiff";
    public final static String tif = "tif";
    public final static String png = "png";
    
    public final static String ICON_PATH = "file:////F:/Lighthouse.jpg";
    public final static String ICON_PATH_1 = "F:/Lighthouse.jpg";

    /*
     * Get the extension of a file.
     */
    public static String getExtension(File f) {
        String ext = null;
        String s = f.getName();
        int i = s.lastIndexOf('.');

        if (i > 0 &&  i < s.length() - 1) {
            ext = s.substring(i+1).toLowerCase();
        }
        return ext;
    }

    /** Returns an ImageIcon, or null if the path was invalid. */
   /* public static ImageIcon createImageIcon(String path) {
        URL imgURL = Utils.class.getResource(path);
        if (imgURL != null) {
            return new ImageIcon(imgURL,path.substring(path.lastIndexOf("/")+1));
        } else {
            System.err.println("Couldn't find file: " + path);
            return null;
        }
    }*/
    
    public static ImageIcon createImageIconByFilePath(String imageFilePath) {
        if(null==imageFilePath) {
        	return null;
        }
        ImageIcon imageIcon = new ImageIcon(imageFilePath);
        return imageIcon;
    }
    
    public static ImageIcon createImageIconByImageIO(String path) {
    	BufferedImage bi = null;
    	try {
			 bi = ImageIO.read(new File(path));
		} catch (IOException e) {
			System.out.println("IOException");
			e.printStackTrace();
		}
    	ImageIcon imageIcon =  null;
    	if(null!=bi) {
    		imageIcon = new ImageIcon(bi);
    	}
    	return imageIcon;
    }

}
