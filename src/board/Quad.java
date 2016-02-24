package board;

public class Quad {

	public int col;
	public int x, y;
	public int width, height;

	public Quad(int col, int x, int y, int width, int height) {
		this.col = col;
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;
	}

	public boolean contains(int x) {
		return (x >= this.x && x < this.x + width);
	}

}