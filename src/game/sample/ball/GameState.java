/*** In The Name of Allah ***/
package game.sample.ball;

import game.troubleTankSample.Tank;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

/**
 * This class holds the state of game and all of its elements.
 * This class also handles user inputs, which affect the game state.
 * 
 * @author Seyed Mohammad Ghaffarian
 */
public class GameState {
	private GameFrame gameFrame;
	public int locX, locY, diam;
	public boolean gameOver;
	
	private boolean keyUP, keyDOWN, keyRIGHT, keyLEFT;
	private boolean mousePress;
	private int mouseX, mouseY;
	public static boolean isMoving = false;
	private KeyHandler keyHandler;
	private MouseHandler mouseHandler;
	
	public GameState(GameFrame gameFrame) {
		this.gameFrame = gameFrame;
		locX = 100;
		locY = 100;
		diam = 32;
		gameOver = false;
		//
		keyUP = false;
		keyDOWN = false;
		keyRIGHT = false;
		keyLEFT = false;
		//
		mousePress = false;
		mouseX = 0;
		mouseY = 0;
		//
		keyHandler = new KeyHandler();
		mouseHandler = new MouseHandler();
	}
	
	/**
	 * The method which updates the game state.
	 */
	public void update() {
		isMoving = keyDOWN || keyUP || keyLEFT || keyRIGHT;
		Tank tank = gameFrame.getMyTank();
		int centerX = tank.getLocX() + GameFrame.TANK_SIZE/2;
		int centerY = tank.getLocY() + GameFrame.TANK_SIZE/2;
		int angle = tank.getBodyAngel();
		double diam = GameFrame.TANK_SIZE;


		if (keyUP && !keyDOWN) {
			if (keyRIGHT && !keyLEFT) {
				if (angle > 40){
					angle = angle - 360;
				}
				if (angle > -140) {
					tank.setBodyAngel(angle - 10);
				} else if (angle < -140) {
					tank.setBodyAngel(angle + 10);
				}
			} else if (keyLEFT && !keyRIGHT) {
				if (angle < -40){
					angle = 360 + angle;
				}
				if (angle > 140) {
					tank.setBodyAngel(angle - 10);
				} else if (angle < 140) {
					tank.setBodyAngel(angle + 10);
				}
			}
//			else {
//				if (angle > 0) {
//					tank.setBodyAngel(angle - 10);
//				} else if (angle < 0) {
//					tank.setBodyAngel(angle + 10);
//				}
//			}
			tank.changeY(-10);
			tank.setLocY(Math.max(tank.getLocY(), 0));
			tank.setLocY((int) Math.min(tank.getLocY(), GameFrame.GAME_HEIGHT - diam));
		}

		if (keyDOWN && !keyUP) {
			if (keyRIGHT && !keyLEFT) {
				if (angle > -40) {
					tank.setBodyAngel(angle - 10);
				} else if (angle < -40) {
					tank.setBodyAngel(angle + 10);
				}
			} else if (keyLEFT && !keyRIGHT) {
				if (angle > 40) {
					tank.setBodyAngel(angle - 10);
				} else if (angle < 40) {
					tank.setBodyAngel(angle + 10);
				}
			}
//			else {
//				if (angle > 180) {
//					tank.setBodyAngel(angle - 10);
//				} else if (angle < 180) {
//					tank.setBodyAngel(angle + 10);
//				}
//			}

			tank.changeY(10);
			tank.setLocY(Math.max(tank.getLocY(), 0));
			tank.setLocY((int) Math.min(tank.getLocY(), GameFrame.GAME_HEIGHT - diam));
		}

		if (keyLEFT && !keyRIGHT) {
//			if (!keyUP && !keyDOWN) {
//				if (angle > -90) {
//					tank.setBodyAngel(angle - 10);
//				} else if (angle < -90) {
//					tank.setBodyAngel(angle + 10);
//				}
//			}
			tank.changeX(-10);
			tank.setLocX(Math.max(tank.getLocX(), 0));
			tank.setLocX((int) Math.min(tank.getLocX(), GameFrame.GAME_WIDTH - diam));
		}

		if (keyRIGHT && !keyLEFT) {
//			if (!keyUP && !keyDOWN) {
//				if (angle > 90) {
//					tank.setBodyAngel(angle - 10);
//				} else if (angle < 90) {
//					tank.setBodyAngel(angle + 10);
//				}
//			}
			tank.changeX(10);
			tank.setLocX(Math.max(tank.getLocX(), 0));
			tank.setLocX((int) Math.min(tank.getLocX(), GameFrame.GAME_WIDTH - diam));
		}




//		if (mousePress) {
//			locY = mouseY - diam / 2;
//			locX = mouseX - diam / 2;
//		}
//		if (keyUP)
//			locY -= 8;
//		if (keyDOWN)
//			locY += 8;
//		if (keyLEFT)
//			locX -= 8;
//		if (keyRIGHT)
//			locX += 8;
//
//		locX = Math.max(locX, 0);
//		locX = Math.min(locX, GameFrame.GAME_WIDTH - diam);
//		locY = Math.max(locY, 0);
//		locY = Math.min(locY, GameFrame.GAME_HEIGHT - diam);
	}
	
	
	public KeyListener getKeyListener() {
		return keyHandler;
	}
	public MouseListener getMouseListener() {
		return mouseHandler;
	}
	public MouseMotionListener getMouseMotionListener() {
		return mouseHandler;
	}



	/**
	 * The keyboard handler.
	 */
	class KeyHandler extends KeyAdapter {

		@Override
		public void keyPressed(KeyEvent e) {
			switch (e.getKeyCode())
			{
				case KeyEvent.VK_UP:
					keyUP = true;
					break;
				case KeyEvent.VK_DOWN:
					keyDOWN = true;
					break;
				case KeyEvent.VK_LEFT:
					keyLEFT = true;
					break;
				case KeyEvent.VK_RIGHT:
					keyRIGHT = true;
					break;
				case KeyEvent.VK_ESCAPE:
					gameOver = true;
					break;
			}
		}

		@Override
		public void keyReleased(KeyEvent e) {
			switch (e.getKeyCode())
			{
				case KeyEvent.VK_UP:
					keyUP = false;
					break;
				case KeyEvent.VK_DOWN:
					keyDOWN = false;
					break;
				case KeyEvent.VK_LEFT:
					keyLEFT = false;
					break;
				case KeyEvent.VK_RIGHT:
					keyRIGHT = false;
					break;
			}
		}

	}

	/**
	 * The mouse handler.
	 */
	class MouseHandler extends MouseAdapter {

		@Override
		public void mousePressed(MouseEvent e) {
			mouseX = e.getX();
			mouseY = e.getY();
			mousePress = true;
			Tank tank = gameFrame.getMyTank();
			if (e.getButton() == MouseEvent.BUTTON1)
				tank.fire(70);
		}

		@Override
		public void mouseReleased(MouseEvent e) {
			mousePress = false;
		}

		@Override
		public void mouseDragged(MouseEvent e) {
			mouseX = e.getX();
			mouseY = e.getY();
		}
	}
}

