package ro.itschool.pixels;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.imageio.stream.FileImageOutputStream;
import javax.imageio.stream.ImageOutputStream;

public class Animation {

	private final long msBetweenFrames;
	private final boolean loop;
	private final List<Image> frames;

	public Animation(long msBetweenFrames, boolean loop) {
		this.msBetweenFrames = msBetweenFrames;
		this.loop = loop;
		this.frames = new ArrayList<Image>();
	}

	public Animation(long msBetweenFrames) {
		this(msBetweenFrames, true);
	}

	public Animation() {
		this(1000 / 60, true);
	}

	public void putFrame(Image img) {
		this.frames.add(img);
	}

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
