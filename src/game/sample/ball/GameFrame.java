/*** In The Name of Allah ***/
package game.sample.ball;
import game.troubleTankSample.Bullet;
import game.troubleTankSample.MapHandler;
import game.troubleTankSample.Tank;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;

/**
 * The window on which the rendering is performed.
 * This example uses the modern BufferStrategy approach for double-buffering,
 * actually it performs triple-buffering!
 * For more information on BufferStrategy check out:
 * http://docs.oracle.com/javase/tutorial/extra/fullscreen/bufferstrategy.html
 * http://docs.oracle.com/javase/8/docs/api/java/awt/image/BufferStrategy.html
 *
 * @author Seyed Mohammad Ghaffarian
 */
public class GameFrame extends JFrame {


	public static final int GAME_HEIGHT = 720;                  // 720p game resolution
	public static final int GAME_WIDTH = 16 * GAME_HEIGHT / 9;  // wide aspect ratio
	private static Tank myTank;
	public static final int TANK_SIZE = 70;
	private static ArrayList<Bullet> bullets;
	//MapHandler mapHandler =new MapHandler();
    private ArrayList<Tank> tanks;

	private BufferedImage heartIcon;
	private BufferedImage laserIcon;
	private BufferedImage shieldIcon;
	private BufferedImage powerIcon;


    //uncomment all /*...*/ in the class for using Tank icon instead of a simple circle
    /*private BufferedImage image;*/

    private long lastRender;
    private ArrayList<Float> fpsHistory;
    private MapHandler mapHandler ;
    private BufferStrategy bufferStrategy;

    public GameFrame(String title) {
        super(title);
        bullets = new ArrayList<>();
        mapHandler = new MapHandler();
        myTank = new Tank(100, 20, 20, "resources/tank_blue.png");

        setResizable(false);
        setSize(GAME_WIDTH, GAME_HEIGHT);
        lastRender = -1;
        fpsHistory = new ArrayList<>(100);

		try {
			heartIcon = ImageIO.read(new File("./resources/awards resources/heartIcon.png"));
			laserIcon = ImageIO.read(new File("./resources/awards resources/laser.png"));
			shieldIcon = ImageIO.read(new File("./resources/awards resources/shield.png"));
			powerIcon = ImageIO.read(new File("./resources/awards resources/Xpower.png"));
		} catch (IOException e) {
		}

	/*	try{
			image = ImageIO.read(new File("Icon.png"));
		}
		catch(IOException e){
			System.out.println(e);
		}*/

        //////////             JUST FOR TEST            //////////////////
        tanks = new ArrayList<Tank>();
        Tank one = new Tank(50, 500, 90, "./resources/tank_dark.png");
		one.setPowerBoosted(true);
		one.setLaser(true);
        one.setShield(true);
        tanks.add(one);
        tanks.add(new Tank(60, 700, 90, "./resources/tank_sand.png"));
        tanks.add(new Tank(100, 300, 503, "./resources/tank_green.png"));
        tanks.add(new Tank(40, 300, 503, "./resources/tank_blue.png"));
        /////


    }

    /**
     * This must be called once after the JFrame is shown:
     * frame.setVisible(true);
     * and before any rendering is started.
     */
    public void initBufferStrategy() {
        // Triple-buffering
        createBufferStrategy(3);
        bufferStrategy = getBufferStrategy();
    }


    /**
     * Game rendering with triple-buffering using BufferStrategy.
     */
    public void render(GameState state) {
        // Render single frame
        do {
            // The following loop ensures that the contents of the drawing buffer
            // are consistent in case the underlying surface was recreated
            do {
                // Get a new graphics context every time through the loop
                // to make sure the strategy is validated
                Graphics2D graphics = (Graphics2D) bufferStrategy.getDrawGraphics();
                try {
                    doRendering(graphics, state);
                } finally {
                    // Dispose the graphics
                    graphics.dispose();
                }
                // Repeat the rendering if the drawing buffer contents were restored
            } while (bufferStrategy.contentsRestored());

            // Display the buffer
            bufferStrategy.show();
            // Tell the system to do the drawing NOW;
            // otherwise it can take a few extra ms and will feel jerky!
            Toolkit.getDefaultToolkit().sync();

            // Repeat the rendering if the drawing buffer was lost
        } while (bufferStrategy.contentsLost());
    }

    /**
     * Rendering all game elements based on the game state.
     */
    private void doRendering(Graphics2D g2d, GameState state) {
        // Draw background
//        g2d.setColor(Color.GRAY);
//        g2d.fillRect(0, 0, GAME_WIDTH, GAME_HEIGHT);
        // Draw ball
//		g2d.setColor(Color.BLACK);
//		g2d.fillOval(state.locX, state.locY, state.diam, state.diam);
        //////////////////////////// RENDER MAP////////////////////////////////////
        mapHandler.setBlocksLocations(GAME_WIDTH, GAME_HEIGHT);
        mapHandler.renderer(g2d);

        ////////////// RENDER DETAILS/////////////
		renderTank(myTank, g2d, state);

        renderDetails(g2d);

		Iterator<Bullet> it = bullets.iterator();
		while (it.hasNext()){
			Bullet bullet = it.next();
			bullet.update();
			renderBullet(g2d, bullet);
			if (bullet.getX3() <= 0 || bullet.getX3() >= GameFrame.GAME_WIDTH) {
				bullet.setBarrelAngel(Math.toRadians(180) - bullet.getBarrelAngel());
			}
			if (bullet.getY3() <= 0 || bullet.getY3() >= GameFrame.GAME_HEIGHT) {
				bullet.setBarrelAngel(Math.toRadians(360) - bullet.getBarrelAngel());
			}
			if (bullet.getPresentTimeOfTheBullet() >= 4000){
				it.remove();
			}
		}

		// Print FPS info
		long currentRender = System.currentTimeMillis();
		if (lastRender > 0) {
			fpsHistory.add(1000.0f / (currentRender - lastRender));
			if (fpsHistory.size() > 100) {
				fpsHistory.remove(0); // remove oldest
			}
			float avg = 0.0f;
			for (float fps : fpsHistory) {
				avg += fps;
			}
			avg /= fpsHistory.size();
			String str = String.format("Average FPS = %.1f , Last Interval = %d ms",
					avg, (currentRender - lastRender));
			g2d.setColor(Color.CYAN);
			g2d.setFont(g2d.getFont().deriveFont(18.0f));
			int strWidth = g2d.getFontMetrics().stringWidth(str);
			int strHeight = g2d.getFontMetrics().getHeight();
			g2d.drawString(str, (GAME_WIDTH - strWidth) / 2, strHeight+50);
		}
		lastRender = currentRender;
		// Print user guide
		String userGuide
				= "Use the MOUSE or ARROW KEYS to move the BALL. "
				+ "Press ESCAPE to end the game.";
		g2d.setFont(g2d.getFont().deriveFont(18.0f));
		g2d.drawString(userGuide, 10, GAME_HEIGHT - 10);
		// Draw GAME OVER
		if (state.gameOver) {
			String str = "GAME OVER";
			g2d.setColor(Color.WHITE);
			g2d.setFont(g2d.getFont().deriveFont(Font.BOLD).deriveFont(64.0f));
			int strWidth = g2d.getFontMetrics().stringWidth(str);
			g2d.drawString(str, (GAME_WIDTH - strWidth) / 2, GAME_HEIGHT / 2);
		}
	}

	private void renderTank(Tank tank, Graphics2D g2d, GameState state) {
		Integer tankBodyX = tank.getLocX();
		Integer tankBodyY = tank.getLocY();
		BufferedImage tankBodyImage = tank.getTankBodyImage();
		renderByChangeOfCoordinates(g2d, tankBodyX + TANK_SIZE / 2, tankBodyY + TANK_SIZE / 2, tankBodyImage, Math.toRadians(tank.getBodyAngel()));

	}

	public void renderByChangeOfCoordinates(Graphics2D g2d, Integer tankCenterX, Integer tankCenterY, BufferedImage shapeImage, double g2dAngel) {
		g2d.translate(tankCenterX, tankCenterY);
		g2d.rotate(g2dAngel);
		g2d.drawImage(shapeImage, -TANK_SIZE / 2, -TANK_SIZE / 2, TANK_SIZE, TANK_SIZE, null);
		g2d.rotate(-g2dAngel);
		g2d.translate(-tankCenterX, -tankCenterY);
	}

	private void renderBullet(Graphics2D g2d, Bullet bullet){
		g2d.translate((int) bullet.getX1(), (int) bullet.getY1());
		g2d.rotate(bullet.getBarrelAngel());
		g2d.drawImage(bullet.getBulletImage(), 0, 0, bullet.getBulletHeight(), bullet.getBulletWidth(), null);
		g2d.rotate(-bullet.getBarrelAngel());
		g2d.translate((int) -bullet.getX1(), (int) -bullet.getY1());
	}

	public Tank getMyTank() {
		return myTank;
	}
	public static ArrayList<Bullet> getBullets() {
		return bullets;
	}



    public void renderDetails(Graphics2D g2d) {
        g2d.setColor(new Color(200, 200, 200, 100));
        g2d.fillRoundRect(10, 30, 165, tanks.size() * 35, 10, 10);
        g2d.setColor(Color.black);
        g2d.drawRoundRect(10, 30, 165, tanks.size() * 35, 10, 10);
        int i = 0;
        for (Tank tank : tanks) {
            g2d.drawImage(tank.getTankBodyImage(), 15, 35 * i + 35, 30, 30, null, null);
            i++;
        }
        i = 0;
        for (Tank tank : tanks) {
            int health =( tank.getHealth() > 9999999) ? 10 : (tank.getHealth()) / 10;

            for (int j = 0; j <= health; j++) {
				g2d.drawImage(heartIcon, 50 + j * 11, 35 * i + 35, 10, 10, null, null);

			}
			if (tank.isLaser())
				g2d.drawImage(laserIcon, 50 , 40 * i + 45, 20, 20, null, null);
			if (tank.isShield())
				g2d.drawImage(shieldIcon, 65 , 40 * i + 45, 20, 20, null, null);
			if (tank.isPowerBoosted())
				g2d.drawImage(powerIcon, 80 , 40 * i + 45, 20, 20, null, null);
			i++;
        }


    }
}
