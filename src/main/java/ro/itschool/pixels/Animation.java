package ro.itschool.pixels;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.imageio.stream.FileImageOutputStream;
import javax.imageio.stream.ImageOutputStream;

/**
 * A simple animation class Allows for write GIF files with looping animation
 * 
 * @author boogie666
 *
 */
public class Animation {

	private final long msBetweenFrames;
	private final boolean loop;
	private final List<Image> frames;

	/**
	 * Creates a new animation with given msBetweenFrame time and control over
	 * whether or not the animation loops
	 * 
	 * @param msBetweenFrames
	 * @param loop
	 */
	public Animation(long msBetweenFrames, boolean loop) {
		this.msBetweenFrames = msBetweenFrames;
		this.loop = loop;
		this.frames = new ArrayList<Image>();
	}

	/**
	 * Creates a new animation with given msBetweenFrame time and looping enabled
	 * 
	 * @param msBetweenFrames
	 */
	public Animation(long msBetweenFrames) {
		this(msBetweenFrames, true);
	}

	/**
	 * Creates a new 60 frames per second animation that loops
	 */
	public Animation() {
		this(1000 / 60, true);
	}

	/**
	 * Puts a frame of animation into this animation
	 * 
	 * @param img
	 */
	public void putFrame(Image img) {
		this.frames.add(img);
	}

	/**
	 * Writes this {@link Animation} to disk at given path.
	 * 
	 * @param pathName - the path where the animation will be written
	 * @throws IOException - if path is invalid
	 */
	public void write(String pathName) throws IOException {
		ImageOutputStream output = new FileImageOutputStream(new File(pathName));
		GifSequenceWriter writer = new GifSequenceWriter(output, BufferedImage.TYPE_INT_ARGB, msBetweenFrames, loop);
		for (Image i : this.frames) {
			writer.writeToSequence(i.toBufferedImage());
		}
		writer.close();
		output.close();

	}
}
