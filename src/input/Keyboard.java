package input;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

/**
 * Created by Andriy on 2/24/16.
 */
public class Keyboard implements KeyListener {

	public boolean r = false;


	public void keyTyped(KeyEvent e) {
	}

	public void keyPressed(KeyEvent e) {
	}

	public void keyReleased(KeyEvent e) {
		if (e.getKeyChar() == 'r') {
			r = true;
		}
	}
}