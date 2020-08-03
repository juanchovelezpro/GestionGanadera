package animation;

import java.awt.Graphics;
import java.awt.image.BufferedImage;

/**
 * This class represents the animation of puts sprite sheets in a sequence.
 * @author Juan Camilo V�lez Olaya
 *
 */
public class Animation {

	/**
	 * The speed of the animation
	 */
	private int speed;
	
	/**
	 * The quantity of frames (Sprite Sheets)
	 */
	private int frames;
	
	/**
	 * An auxiliar index
	 */
	private int index = 0;
	
	/**
	 * An auxiliar index to next frames method.
	 */
	private int count = 0;
	
	/**
	 * The number of columns of the image to run the animation.
	 */
	private int col;
	
	/**
	 * The number of rows of the image to run the animation
	 */
	private int row;
	
	/**
	 * 
	 */
	private int startRow;
	
	/**
	 * 
	 */
	private int startCol;

	/**
	 * It is the image to get the sprite sheets.
	 */
	private BufferedImage image;
	
	/**
	 * It is an array of the sub images (sprites)
	 */
	private BufferedImage[] images;
	
	/**
	 * It is the current sprite for the animation.
	 */
	private BufferedImage currentImage;
	
	/**
	 * Variable to know if the animation has finished.
	 */
	private boolean alive;

	/**
	 * Constructor to create an animation
	 * @param image The image with sprites.
	 * @param frames The quantity of sprites.
	 * @param speed The speed of the animation
	 * @param col The number of columns that the image has of sprites.
	 * @param row The number of rows that the image has of sprites.
	 */
	public Animation(BufferedImage image, int frames, int speed, int col, int row) {

		this.image = image;
		startCol = 1;
		startRow = 1;
		this.frames = frames;
		this.speed = speed;
		this.col = col;
		this.row = row;
		images = new BufferedImage[frames];
		alive = true;

		fillSprites();

	}
	
	/**
	 * 
	 * @param image
	 * @param frames
	 * @param speed
	 * @param col
	 * @param row
	 */
	public Animation(BufferedImage image, int frames, int speed,int startCol, int startRow, int col, int row) {

		this.image = image;
		this.startCol = startCol;
		this.startRow = startRow;
		this.frames = frames;
		this.speed = speed;
		this.col = col;
		this.row = row;
		images = new BufferedImage[frames];
		alive = true;

		fillSprites();

	}


	/**
	 * Puts all the sprites sheets in an array.
	 * 
	 */
	public void fillSprites() {

		SpriteSheet ss = new SpriteSheet(image);

		int k = 0;

		for (int i = startRow; i <= row; i++) {

			for (int j = startCol; j <= col; j++, k++) {

				images[k] = ss.grabImage(i, j, (int) image.getWidth() / col, (int) image.getHeight() / row);

			}

		}

	}

	/**
	 * Change the current image with the next sprite. 
	 */
	public void nextFrame() {

		currentImage = images[count];

		count++;

		if (count >= frames - 1) {  // You can modify frames - 1 to another number if your image does not have sprite sheets in all the image.
									// If your image has sprite sheets in all the image you can put only frames.
			alive = false;
			count = 0;

		}

	}

	public boolean isAlive() {
		return alive;
	}

	public void setAlive(boolean alive) {
		this.alive = alive;
	}

	/**
	 * Uses the method next frames to simulate an animation changing frames in an execution
	 */
	public void runAnimation() {

		index++;
		if (index > speed) {

			index = 0;
			nextFrame();

		}

	}

	/**
	 * Draws the animation
	 * @param g The graphics to draw.
	 * @param x Pos in X
	 * @param y Pos in Y
	 * @param offset Move the position in X 
	 */
	public void drawAnimation(Graphics g, double x, double y, int offset) {

		g.drawImage(currentImage, (int) x - offset, (int) y, null);

	}

	public void setImage(BufferedImage image) {
		
	this.image = image;	
		
	}
	
	public void setFrames(int frames) {
	
	this.frames = frames;
	}
	
	public void setCol(int col) {
	
		this.col = col;
		
	}
	
	public void setRow(int row) {
		
	this.row = row;	
		
	}
		
}