package ro.itschool.pixels.ui;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.image.BufferedImage;

import javax.swing.JComponent;

import ro.itschool.pixels.Image;

public class ImageComponent extends JComponent {

	private static final long serialVersionUID = -7077588561482330243L;
	private Image image;

	public ImageComponent() {
	}

	public ImageComponent(Image image) {
		this.image = image;
	}

	public Image getImage() {
		return image;
	}

	@Override
	public int getWidth() {
		if (this.image == null) {
			return 0;
		}
		return this.image.getWidth();
	}

	@Override
	public int getHeight() {
		if (this.image == null) {
			return 0;
		}
		return this.image.getHeight();
	}

	@Override
	public Dimension getSize() {
		return this.getSize(null);
	}

	@Override
	public Dimension getSize(Dimension rv) {
		if (rv == null) {
			rv = new Dimension();
		}
		rv.setSize(this.getWidth(), this.getHeight());
		return rv;
	}

	@Override
	public Dimension getPreferredSize() {
		return this.getSize();
	}

	@Override
	public void paintComponent(Graphics g) {
		if (this.image == null) {
			return;
		}
		BufferedImage img = this.image.getRawImage();
		g.drawImage(img, 0, 0, null);
	}

	public void setImage(Image image) {
		this.image = image;
		this.revalidate();
		this.repaint();
	}
}
