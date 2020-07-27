package ro.itschool.pixels;

import java.awt.image.BufferedImage;
import java.awt.image.DataBufferInt;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class Image {
	private final Color[] pixels;
	private final int width;
	private final int height;

	public Image(int width, int height) {
		this.pixels = new Color[width * height];
		this.width = width;
		this.height = height;
	}

	public int getWidth() {
		return width;
	}

	public int getHeight() {
		return height;
	}

	public void putPixel(int x, int y, Color c) {
		final int index = y * width + x;
		if (index >= this.pixels.length) {
			throw new IndexOutOfBoundsException("Index (" + x + ", " + y + ") is out of bounds, position " + index + " max position " + this.pixels.length);
		}
		this.pixels[index] = c;
	}

	public Color getPixel(int x, int y) {
		final int index = y * width + x;
		if (index >= this.pixels.length) {
			throw new IndexOutOfBoundsException("Index (" + x + ", " + y + ") is out of bounds, position " + index);
		}
		final Color c = this.pixels[index];
		if(c == null) {
			return Color.TRANSPARENT;
		}
		return c;
	}
	
	BufferedImage toBufferedImage() {
		final BufferedImage image = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);
		final int[] result = ( (DataBufferInt) image.getRaster().getDataBuffer() ).getData();
		for(int i = 0; i < this.pixels.length; i++) {
			if(this.pixels[i] != null)
				result[i] = this.pixels[i].toRGBA();
		}
		return image;
	}
	
	
	public void write(String path) throws IOException{
		ImageIO.write(this.toBufferedImage(), "png", new File(path));
	}
	
	public static Image load(String path) throws IOException{
		final BufferedImage image = ImageIO.read(new File(path));
		final Image result = new Image(image.getWidth(), image.getHeight());
		for(int i = 0; i < result.width; i++) {
			for(int j = 0; j < result.height; j++) {
				int rgba = image.getRGB(i, j);
				int b = (rgba)&0xFF;
		        int g = (rgba>>8)&0xFF;
		        int r = (rgba>>16)&0xFF;
		        int a = (rgba>>24)&0xFF;
		        result.putPixel(i, j, new Color(r,g,b,a));
			}
		}
		return result;
	}

}
