package display;

import board.Board;
import board.Grid;
import input.Mouse;
import player.Player;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferStrategy;

public class Viewport extends Canvas implements Runnable {

	private int rows = 6, cols = 7, winLength = 4;
	private int height = 600;
	private int width = height / rows * cols;
	private String title = "Game";
	public JFrame frame;

	private Thread thread;
	private boolean running;
	private int cupdates = 0, cframes = 0;

	private Render render;

	private Board board;
	private Grid grid;
	private Player player1;
	private Player player2;

	private Mouse mouse;

	public Viewport() {
		setPreferredSize(new Dimension(width, height));

		frame = new JFrame(title);
		thread = new Thread(this, title);
		render = new Render(width, height, rows, cols);

		player1 = new Player("Player 1 (sub for computer)", -1); //computer always plays as -1, in this case player 1 is acting as computer
		player2 = new Player("Player 2", 1); //player 2 is acting as standard opponent, plays with 1
		board = new Board(rows, cols, winLength, player1, player2);
		grid = new Grid(width, height, rows, cols, render.horizontalSlotSpacer);

		mouse = new Mouse();

		addMouseListener(mouse);
		addMouseMotionListener(mouse);
	}

	public void start() {
		thread.start();
		running = true;
	}

	public void stop() {
		try {
			thread.join();
			running = false;
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	public void run() {
		long then = System.nanoTime();
		long timer = System.currentTimeMillis();
		final double nanos = 1000000000.0 / 100.0;
		double delta = 0;
		int frames = 0;
		int updates = 0;
		while (running) {
			long now = System.nanoTime();
			delta += (now - then) / nanos;
			then = now;
			while (delta >= 1) {
				update();
				updates++;
				delta--;
			}
			render();
			frames++;

			if (System.currentTimeMillis() - timer > 1000) {
				timer += 1000;
				cupdates = updates;
				cframes = frames;
				updates = 0;
				frames = 0;
			}
		}
	}

	public void update() {
		grid.update(mouse);
		board.update(grid);
	}

	public void render() {
		BufferStrategy bs = getBufferStrategy();
		if (bs == null) {
			createBufferStrategy(3);
			return;
		}
		Graphics g = bs.getDrawGraphics();
		Graphics2D g2 = (Graphics2D) bs.getDrawGraphics();

		g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

		render.g2 = g2;

		render.clear();
		render.renderBoard(board);
		render.renderQuad(grid.active);

		g2.setColor(Color.white);
		g2.setFont(new Font("Helvetica", Font.BOLD, 18));
		g2.drawString(cupdates + " updates, " + cframes + " frames.", 10, 25);

		g2.dispose();
		g.dispose();
		bs.show();
	}

	public static void main(String[] args) {
		Viewport v = new Viewport();
		v.frame.add(v);
		v.frame.pack();
		v.frame.setResizable(false);
		v.frame.setLocationRelativeTo(null);
		v.frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		v.frame.setVisible(true);
		v.start();
	}

}