package ro.itschool.pixels;

import java.io.File;
import java.io.IOException;

import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;

/**
 * Unit test for Image class.
 */
public class ImageTest extends TestCase {

	private static final String OUTPUT_FILE = "test_image.png";

	@Override
	protected void tearDown() throws Exception {
		File f = new File(OUTPUT_FILE);
		if (f.exists()) {
			f.delete();
		}
	}

	public void testImagePixels() {
		Image i = new Image(3, 1);
		i.putPixel(0, 0, new Color(255, 0, 0));
		i.putPixel(1, 0, new Color(0, 255, 0));
		i.putPixel(2, 0, new Color(0, 0, 255));

		assertEquals(i.getPixel(0, 0), new Color(255, 0, 0));
		assertEquals(i.getPixel(1, 0), new Color(0, 255, 0));
		assertEquals(i.getPixel(2, 0), new Color(0, 0, 255));
	}

	public void testImageReadWriteCycle() throws IOException {
		Image i = new Image(4, 1);
		i.putPixel(0, 0, new Color(255, 0, 0));
		i.putPixel(1, 0, new Color(0, 255, 0));
		i.putPixel(2, 0, new Color(0, 0, 255));

		i.write(OUTPUT_FILE);

		Image i2 = Image.load(OUTPUT_FILE);

		assertEquals(i2.getPixel(0, 0), new Color(255, 0, 0));
		assertEquals(i2.getPixel(1, 0), new Color(0, 255, 0));
		assertEquals(i2.getPixel(2, 0), new Color(0, 0, 255));
		assertEquals(i2.getPixel(3, 0), Color.TRANSPARENT);
	}

	public void testNullIsTransparent() throws IOException {
		Image i = new Image(1, 1);
		assertEquals(i.getPixel(0, 0), Color.TRANSPARENT);

		i.putPixel(0, 0, null);
		assertEquals(i.getPixel(0, 0), Color.TRANSPARENT);

	}
}
