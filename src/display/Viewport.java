package display;

import board.Board;
import board.Grid;
import input.Keyboard;
import input.Mouse;
import player.LimMinimaxAIPlayer;
import player.RandomSampleAIPlayer;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferStrategy;

public class Viewport extends Canvas implements Runnable {

	public JFrame frame;
	private int rows = 6, cols = 7, winLength = 4;
	private int height = 360;
	private int width = height / rows * cols;
	private String title = "Minimax";
	private Thread thread;
	private boolean running;
	private int cupdates = 0, cframes = 0;

	private Render render;

	private Board board;
	private Grid grid;

	/* Player 1 Constructors, UNCOMMENT THE ONE YOU'RE CURRENTLY TESTING */
//	private HumanPlayer player1;
//	private UnlimMinimaxAIPlayer player1;
//	private LimMinimaxAIPlayer player1;
	private RandomSampleAIPlayer player1;
	/* End */

	/* Player 2 Constructors, KEEP THIS THE SAME FOR ALL TRIALS */
//	private HumanPlayer player2;
//	private UnlimMinimaxAIPlayer player2;
	private LimMinimaxAIPlayer player2;
//	private RandomSampleAIPlayer player2;
	/* End */

	private Mouse mouse;
	private Keyboard keyboard;

	private Viewport() {
		setPreferredSize(new Dimension(width, height));

		frame = new JFrame(title);
		thread = new Thread(this, title);
		render = new Render(width, height, rows, cols);

		//when choosing a player option, remember to go up a few lines ^^ and uncomment the corresponding constructors
		/* Player 1 Options, ALL COMBINATIONS FOR TESTING PRESENT */
//		player1 = new LimMinimaxAIPlayer("Player 1 (Limited Minimax AI)", -1, 3);
//		player1 = new LimMinimaxAIPlayer("Player 1 (Limited Minimax AI)", -1, 4);
//		player1 = new LimMinimaxAIPlayer("Player 1 (Limited Minimax AI)", -1, 5);
//		player1 = new LimMinimaxAIPlayer("Player 1 (Limited Minimax AI)", -1, 6);
//		player1 = new LimMinimaxAIPlayer("Player 1 (Limited Minimax AI)", -1, 7);
		player1 = new RandomSampleAIPlayer("Player 1 (Random Sampling AI", -1, rows * cols, 100);
//		player1 = new RandomSampleAIPlayer("Player 1 (Random Sampling AI", -1, rows * cols, 500);
//		player1 = new RandomSampleAIPlayer("Player 1 (Random Sampling AI", -1, rows * cols, 1000);
//		player1 = new RandomSampleAIPlayer("Player 1 (Random Sampling AI", -1, rows * cols, 2000);
//		player1 = new RandomSampleAIPlayer("Player 1 (Random Sampling AI", -1, rows * cols, 5000);
//		player1 = new RandomSampleAIPlayer("Player 1 (Random Sampling AI", -1, 10, 100);
//		player1 = new RandomSampleAIPlayer("Player 1 (Random Sampling AI", -1, 18, 100);
//		player1 = new RandomSampleAIPlayer("Player 1 (Random Sampling AI", -1, 26, 100);
//		player1 = new RandomSampleAIPlayer("Player 1 (Random Sampling AI", -1, 34, 100);
		/* End */

		/* Player 2 Options, KEEP THIS CONSTANT FOR ALL TRIALS */
		player2 = new LimMinimaxAIPlayer("Player 2 (Limited Minimax AI", 1, 2);
		/* End */

		board = new Board(rows, cols, winLength, player1, player2, true); //the boolean indicates whether you're doing automatic testing;
		//you want it in true because false makes it spit out text
		//which slows it down significantly
		grid = new Grid(width, height, rows, cols, render.horizontalSlotSpacer);

		mouse = new Mouse();
		keyboard = new Keyboard();

		addMouseListener(mouse);
		addMouseMotionListener(mouse);
		addKeyListener(keyboard);
	}

	public static void main(String[] args) {
		Viewport v = new Viewport();
		v.frame.add(v);
		v.frame.pack();
		v.frame.setResizable(false);
		v.frame.setLocationRelativeTo(null);
		v.frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		v.frame.setVisible(true);
		v.start();
	}

	private void start() {
		thread.start();
		running = true;
	}

	public void run() {
//		long then = System.nanoTime();
//		long timer = System.currentTimeMillis();
//		final double nanos = 1000000000.0 / 60.0;
//		double delta = 0;
//		int frames = 0;
//		int updates = 0;
//		while (running) {
//			long now = System.nanoTime();
//			delta += (now - then) / nanos;
//			then = now;
//			while (delta >= 1) {
//				update();
//				updates++;
//				delta--;
//			}
//			render();
//			frames++;
//
//			if (System.currentTimeMillis() - timer > 1000) {
//				timer += 1000;
//				cupdates = updates;
//				cframes = frames;
//				updates = 0;
//				frames = 0;
//			}
//		}
		while (running) {
			update();
			render();
		}
	}

	private void update() {
		grid.update(mouse);
		board.update();
//		player1.update(grid);
		player1.update(board);
//		player2.update(grid);
		player2.update(board);
		if (keyboard.r) {
			board.reset();
			keyboard.r = !keyboard.r;
		}
	}

	private void render() {
		BufferStrategy bs = getBufferStrategy();
		if (bs == null) {
			createBufferStrategy(2);
			return;
		}
		Graphics g = bs.getDrawGraphics();
		Graphics2D g2 = (Graphics2D) bs.getDrawGraphics();

		g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

		render.g2 = g2;

		render.clear();
		render.renderBoard(board);
		render.renderQuad(grid.active);

//		g2.setColor(Color.white);
//		g2.setFont(new Font("Helvetica", Font.BOLD, 18));
//		g2.drawString(cupdates + " updates, " + cframes + " frames.", 10, 25);

		g2.dispose();
		g.dispose();
		bs.show();
	}

}