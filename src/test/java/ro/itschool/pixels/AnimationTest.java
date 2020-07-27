package ro.itschool.pixels;

import java.io.File;
import java.io.IOException;

import junit.framework.TestCase;

public class AnimationTest extends TestCase{
	
	
	private static final String OUTPUT_FILE = "./test_animation.gif";

	@Override
	protected void tearDown() throws Exception {
		File outfile = new File(OUTPUT_FILE);
		if(outfile.exists()) {
			outfile.delete();
		}
	}
	
	public void testSimpleAnimation() throws IOException {
		Image frame1 = new Image(1, 1);
		frame1.putPixel(0, 0, Color.RED);
		
		Image frame2 = new Image(1, 1);
		frame2.putPixel(0, 0, Color.GREEN);

		Image frame3 = new Image(1, 1);
		frame3.putPixel(0, 0, Color.BLUE);
		
		Animation a = new Animation(1000, true);

		a.putFrame(frame1);
		a.putFrame(frame2);
		a.putFrame(frame3);
		
		a.write(OUTPUT_FILE);
		
		assertTrue(new File(OUTPUT_FILE).exists());
		
	}
}
