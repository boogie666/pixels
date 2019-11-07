package ro.itschool.pixels;

import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;

/**
 * Unit test for Color class.
 */
public class ColorTest extends TestCase {
	/**
	 * Create the test case
	 *
	 * @param testName name of the test case
	 */
	public ColorTest(String testName) {
		super(testName);
	}

	/**
	 * @return the suite of tests being tested
	 */
	public static Test suite() {
		return new TestSuite(ColorTest.class);
	}

	public void testColorConversion() {
		Color c = new Color(255,255,255, 255);
		int result = new java.awt.Color(255, 255, 255,255).getRGB();
		assertEquals(result, c.toRGBA());
	}
	
	public void testColorClamp() {
		Color c = new Color(1000,-100,100,0);
		assertEquals(255, c.getRed());
		assertEquals(0, c.getGreen());
		assertEquals(100, c.getBlue());
	}
	
	public void testColorClampEquals() {
		Color c = new Color(1000,-100,100,0);
		Color c2 = new Color(255,0,100,0);
		assertEquals(c, c2);
	}
}
