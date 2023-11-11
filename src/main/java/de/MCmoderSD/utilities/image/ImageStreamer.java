package de.MCmoderSD.utilities.image;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.HashMap;

@SuppressWarnings("unused")
public class ImageStreamer {

    // Attributes
    private final HashMap<String, BufferedImage> bufferedImageCache;
    private final HashMap<String, ImageIcon> imageIconCache;

    // Constructor without isAbsolute
    public ImageStreamer() {
        bufferedImageCache = new HashMap<>();
        imageIconCache = new HashMap<>();
    }

    // Read image file and return BufferedImage
    public BufferedImage read(String url) {
        if (!url.endsWith(".png") && !url.endsWith(".jpg") && !url.endsWith(".jpeg") && !url.endsWith(".gif"))
            throw new IllegalArgumentException("Unsupported image format: " + url); // Image format is not supported
        if (bufferedImageCache.containsKey(url))
            return bufferedImageCache.get(url); // Checks the cache for the image

        BufferedImage image = null;
        try {
            image = ImageIO.read(new URL(url));
        } catch (IOException e) {
            System.err.println(e.getMessage());
        }

        // Null check
        if (image == null)
            throw new IllegalArgumentException("The image could not be loaded: " + url); // Image could not be loaded (Image is null

        // Add to cache
        bufferedImageCache.put(url, image);
        return image;
    }

    public BufferedImage read(String url, int width, int height) {
        return scaleImage(read(url), width, height);
    }

    public BufferedImage read(String url, int scale) {
        return scaleImage(read(url), scale);
    }

    public BufferedImage read(URL url) {
        if (!url.toString().endsWith(".png") && !url.toString().endsWith(".jpg") && !url.toString().endsWith(".jpeg") && !url.toString().endsWith(".gif"))
            throw new IllegalArgumentException("Unsupported image format: " + url); // Image format is not supported
        if (bufferedImageCache.containsKey(url.toString()))
            return bufferedImageCache.get(url.toString()); // Checks the cache for the image

        BufferedImage image = null;

        try {
            image = ImageIO.read(url);
        } catch (IOException e) {
            System.err.println(e.getMessage());
        }

        // Null check
        if (image == null) throw new IllegalArgumentException("The image could not be loaded: " + url);

        bufferedImageCache.put(url.toString(), image);
        return image;
    }

    public BufferedImage read(URL url, int width, int height) {
        return scaleImage(read(url), width, height);
    }

    public BufferedImage read(URL url, int scale) {
        return scaleImage(read(url), scale);
    }

    public BufferedImage read(ImageIcon image) {
        return (BufferedImage) image.getImage();
    }

    public BufferedImage read(ImageIcon image, int width, int height) {
        return scaleImage(read(image), width, height);
    }

    public BufferedImage read(ImageIcon image, int scale) {
        return scaleImage(read(image), scale);
    }

    public BufferedImage read(Image image) {
        return (BufferedImage) image;
    }

    public BufferedImage read(Image image, int width, int height) {
        return scaleImage(read(image), width, height);
    }

    public BufferedImage read(Image image, int scale) {
        return scaleImage(read(image), scale);
    }

    // Create ImageIcon
    public ImageIcon createImageIcon(String url) {
        if (imageIconCache.containsKey(url)) return imageIconCache.get(url);

        ImageIcon imageIcon = new ImageIcon(read(url));
        imageIconCache.put(url, imageIcon);

        return imageIcon;
    }

    public ImageIcon createImageIcon(String url, int width, int height) {
        return new ImageIcon(scaleImage(url, width, height));
    }

    public ImageIcon createImageIcon(String url, int scale) {
        return new ImageIcon(scaleImage(url, scale, scale));
    }

    public ImageIcon createImageIcon(BufferedImage image) {
        return new ImageIcon(image);
    }

    public ImageIcon createImageIcon(BufferedImage image, int width, int height) {
        return new ImageIcon(scaleImage(image, width, height));
    }

    public ImageIcon createImageIcon(BufferedImage image, int scale) {
        return new ImageIcon(scaleImage(image, scale));
    }

    public ImageIcon createImageIcon(URL url) {
        return new ImageIcon(read(url));
    }

    public ImageIcon createImageIcon(URL url, int width, int height) {
        return new ImageIcon(scaleImage(url, width, height));
    }

    public ImageIcon createImageIcon(URL url, int scale) {
        return new ImageIcon(scaleImage(url, scale));
    }

    public ImageIcon createImageIcon(Image image) {
        return new ImageIcon(image);
    }

    public ImageIcon createImageIcon(Image image, int width, int height) {
        return new ImageIcon(scaleImage(image, width, height));
    }

    public ImageIcon createImageIcon(Image image, int scale) {
        return new ImageIcon(scaleImage(image, scale));
    }

    // Animation Loader
    public ImageIcon readGif(String url) {
        if (!url.endsWith(".gif"))
            throw new IllegalArgumentException("Unsupported image format: " + url); // Animation format is not supported
        if (imageIconCache.containsKey(url))
            return imageIconCache.get(url); // Checks the cache for the Animation

        ImageIcon imageIcon; // Load the Animation

        try {
            imageIcon = new ImageIcon(new URL(url));
        } catch (MalformedURLException e) {
            throw new RuntimeException(e);
        }

        imageIconCache.put(url, imageIcon);
        return imageIcon;
    }

    public ImageIcon readGif(String url, int width, int height) {
        return new ImageIcon(readGif(url).getImage().getScaledInstance(width, height, Image.SCALE_DEFAULT));
    }

    public ImageIcon readGif(String url, int scale) {
        return readGif(url, scale, scale);
    }

    public ImageIcon readGif(URL url) {
        if (!url.toString().endsWith(".gif"))
            throw new IllegalArgumentException("Unsupported image format: " + url); // Animation format is not supported
        if (imageIconCache.containsKey(url.toString()))
            return imageIconCache.get(url.toString()); // Checks the cache for the Animation

        ImageIcon imageIcon = new ImageIcon(url);

        imageIconCache.put(url.toString(), imageIcon);
        return imageIcon;
    }

    public ImageIcon readGif(URL url, int width, int height) {
        return new ImageIcon(readGif(url).getImage().getScaledInstance(width, height, Image.SCALE_DEFAULT));
    }

    public ImageIcon readGif(URL url, int scale) {
        return readGif(url, scale, scale);
    }


    // Image scaling
    public BufferedImage scaleImage(BufferedImage image, int width, int height) {
        BufferedImage scaledImage = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);
        scaledImage.getGraphics().drawImage(image, 0, 0, width, height, null);
        return scaledImage;
    }

    public BufferedImage scaleImage(BufferedImage image, int scale) {
        return scaleImage(image, scale, scale);
    }

    public BufferedImage scaleImage(String url, int width, int height) {
        return scaleImage(read(url), width, height);
    }

    public BufferedImage scaleImage(String url, int scale) {
        return scaleImage(url, scale, scale);
    }

    public BufferedImage scaleImage(URL url, int width, int height) {
        return scaleImage(read(url), width, height);
    }

    public BufferedImage scaleImage(URL url, int scale) {
        return scaleImage(url, scale, scale);
    }

    public BufferedImage scaleImage(ImageIcon image, int width, int height) {
        return scaleImage((BufferedImage) image.getImage(), width, height);
    }

    public BufferedImage scaleImage(ImageIcon image, int scale) {
        return scaleImage(image, scale, scale);
    }

    public BufferedImage scaleImage(Image image, int width, int height) {
        return scaleImage((BufferedImage) image, width, height);
    }

    public BufferedImage scaleImage(Image image, int scale) {
        return scaleImage(image, scale, scale);
    }


    // ImageIcon scaling
    public ImageIcon scaleImageIcon(ImageIcon image, int width, int height) {
        return createImageIcon((BufferedImage) image.getImage(), width, height);
    }

    public ImageIcon scaleImageIcon(ImageIcon image, int scale) {
        return createImageIcon((BufferedImage) image.getImage(), scale);
    }

    public ImageIcon scaleImageIcon(String url, int width, int height) {
        return createImageIcon(scaleImage(url, width, height));
    }

    public ImageIcon scaleImageIcon(String url, int scale) {
        return createImageIcon(scaleImage(url, scale));
    }

    public ImageIcon scaleImageIcon(URL url, int width, int height) {
        return createImageIcon(scaleImage(url, width, height));
    }

    public ImageIcon scaleImageIcon(URL url, int scale) {
        return createImageIcon(scaleImage(url, scale));
    }

    public ImageIcon scaleImageIcon(BufferedImage image, int width, int height) {
        return createImageIcon(scaleImage(image, width, height));
    }

    public ImageIcon scaleImageIcon(BufferedImage image, int scale) {
        return createImageIcon(scaleImage(image, scale));
    }

    public ImageIcon scaleImageIcon(Image image, int width, int height) {
        return createImageIcon(scaleImage(image, width, height));
    }

    public ImageIcon scaleImageIcon(Image image, int scale) {
        return createImageIcon(scaleImage(image, scale));
    }

    // Animation scaling
    public ImageIcon scaleAnimation(String url, int width, int height) {
        return new ImageIcon(readGif(url).getImage().getScaledInstance(width, height, Image.SCALE_DEFAULT));
    }

    public ImageIcon scaleAnimation(String url, int scale) {
        return scaleAnimation(url, scale, scale);
    }

    public ImageIcon scaleAnimation(ImageIcon image, int width, int height) {
        return new ImageIcon(image.getImage().getScaledInstance(width, height, Image.SCALE_DEFAULT));
    }

    public ImageIcon scaleAnimation(ImageIcon image, int scale) {
        return scaleAnimation(image, scale, scale);
    }

    public ImageIcon scaleAnimation(URL url, int width, int height) {
        return new ImageIcon(readGif(url).getImage().getScaledInstance(width, height, Image.SCALE_DEFAULT));
    }

    public ImageIcon scaleAnimation(URL url, int scale) {
        return scaleAnimation(url, scale, scale);
    }

    // Setter
    public void addImageToCache(String url, BufferedImage image) {
        bufferedImageCache.put(url, image);
    }

    public void addImageToCache(String url, ImageIcon image) {
        imageIconCache.put(url, image);
    }

    public void clearImageIconCache() {
        imageIconCache.clear();
    }

    public void removeImageIcon(String url) {
        imageIconCache.remove(url);
    }

    public void clearImageCache() {
        bufferedImageCache.clear();
    }

    public void removeImage(String url) {
        bufferedImageCache.remove(url);
    }

    public void clearCache() {
        bufferedImageCache.clear();
        imageIconCache.clear();
    }

    // Getter
    public HashMap<String, BufferedImage> getBufferedImageCache() {
        return bufferedImageCache;
    }

    public HashMap<String, ImageIcon> getImageIconCache() {
        return imageIconCache;
    }

    public boolean isCached(String url, boolean isImageIcon) {
        return isImageIcon ? imageIconCache.containsKey(url) : bufferedImageCache.containsKey(url);
    }

    public boolean isCached(String url) {
        return isCached(url, false) || isCached(url, true);
    }

    public boolean isCached(BufferedImage image) {
        return bufferedImageCache.containsValue(image);
    }

    public boolean isCached(ImageIcon image) {
        return imageIconCache.containsValue(image);
    }
}