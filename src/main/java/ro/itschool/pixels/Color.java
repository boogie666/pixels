package ro.itschool.pixels;

/**
 * A simple Color class that represents a given RGBA color.
 * Allows for getting a individual R,G,B and A components of a Color.
 * @author boogie666
 *
 */
public class Color {
	private final int r, g, b, a;
	private final int rgba;

	public static final Color TRANSPARENT = new Color(0, 0, 0, 0);
	public static final Color BLACK = new Color(0, 0, 0);
	public static final Color WHITE = new Color(255, 255, 255);
	public static final Color RED = new Color(255, 0, 0);
	public static final Color GREEN = new Color(0, 255, 0);
	public static final Color BLUE = new Color(0, 0, 255);

	/**
	 * Constructs a new color from given R,G,B and A values.
	 * Values are clamped between 0 and 255.
	 * @param r
	 * @param g
	 * @param b
	 * @param a
	 */
	public Color(int r, int g, int b, int a) {
		this.r = clamp(r, 0, 255);
		this.g = clamp(g, 0, 255);
		this.b = clamp(b, 0, 255);
		this.a = clamp(a, 0, 255);

		int rgba = this.a;
		rgba = (rgba << 8) + this.r;
		rgba = (rgba << 8) + this.g;
		rgba = (rgba << 8) + this.b;
		this.rgba = rgba;
	}

	/**
	 * Constructs a new Color with given R, G and B values
	 * and default Alpha of 255
	 * Values a re clamped between 0 and 255
	 * @param r
	 * @param g
	 * @param b
	 */
	public Color(int r, int g, int b) {
		this(r, g, b, 255);
	}

	/**
	 * Return the Red channel of this {@link Color}
	 * @return
	 */
	public int getRed() {
		return r;
	}
	
	/**
	 * Returns the Green channel of this {@link Color}
	 * @return
	 */
	public int getGreen() {
		return g;
	}
	
	/**
	 * Returns the Blue channel of this {@link Color}
	 * @return
	 */
	public int getBlue() {
		return b;
	}
	
	/**
	 * Returns the Alpha channel of this {@link Color}
	 * @return
	 */
	public int getAlpha() {
		return a;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + a;
		result = prime * result + b;
		result = prime * result + g;
		result = prime * result + r;
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Color other = (Color) obj;
		return this.rgba == other.rgba;
	}

	@Override
	public String toString() {
		return "Color [r=" + r + ", g=" + g + ", b=" + b + ", a=" + a + "]";
	}

	private static int clamp(int value, int min, int max) {
		if (value < min) {
			return min;
		}
		if (value > max) {
			return max;
		}

		return value;
	}

	/**
	 * Returns a integer representations of this {@link Color}
	 * @return
	 */
	public int toRGBA() {
		return rgba;
	}

}
