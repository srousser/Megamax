package player;

public abstract class Player {

	public String name;
	public int symbol;
	public boolean active = false;
	public boolean moveMade;
	public int move;
	public int depthLimiter;

	public abstract void reset();
}