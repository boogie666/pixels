package ro.itschool.pixels;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

/**
 * A simple Image manipulation class.
 * Allows for getting and setting pixels within an image and saving the image on disk
 * @author boogie666
 *
 */
public class Image {
	private final BufferedImage image;
	
	/**
	 * Constructs a new {@link Image} of given width and height
	 * @param width
	 * @param height
	 */
	public Image(int width, int height) {
		this(new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB));
	}

	Image(BufferedImage image) {
		this.image = image;
	}
	
	/**
	 * Returns the width if this {@link Image}
	 * @return the width in pixels
	 */
	public int getWidth() {
		return this.image.getWidth();
	}

	/**
	 * Return the height of this {@link Image}
	 * @return the height in pixels
	 */
	public int getHeight() {
		return this.image.getHeight();
	}

	/**
	 * Puts a pixel of given {@link Color} c at position x, y
	 * 
	 * @param x - x position
	 * @param y - y position
	 * @param c - the {@link Color}
	 */
	public void putPixel(int x, int y, Color c) {
		if (c != null) {
			this.image.setRGB(x, y, c.toRGBA());
		} else {
			this.image.setRGB(x, y, Color.TRANSPARENT.toRGBA());
		}
	}

	/**
	 * Return the color of pixel at position x,y
	 * 
	 * @param x - the x position
	 * @param y - the y position
	 * @return - the Color at given position
	 */
	public Color getPixel(int x, int y) {
		final int c = this.image.getRGB(x, y);
		if (c == 0) {
			return Color.TRANSPARENT;
		}
		int a = (c >> 24) & 0xff;
		int r = (c >> 16) & 0xff;
		int g = (c >> 8) & 0xff;
		int b = (c >> 0) & 0xff;

		return new Color(r, g, b, a);
	}

	BufferedImage toBufferedImage() {
		return this.image;
	}

	/**
	 * Write this {@link Image} to given path
	 * @param path - the path at which this {@link Image} is saved
	 * @throws IOException - if the path is invalid.
	 */
	public void write(String path) throws IOException {
		ImageIO.write(this.toBufferedImage(), "png", new File(path));
	}

	/**
	 * Loads a {@link Image} from give path.
	 * @param path - the path from where this image will be loaded
	 * @return a {@link Image}
	 * @throws IOException - if the file is not found, or it's not an image.
	 */
	public static Image load(String path) throws IOException {
		final BufferedImage image = ImageIO.read(new File(path));
		return new Image(image);
	}

}
