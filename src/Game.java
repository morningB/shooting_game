import javax.swing.*;

import java.awt.*;
import javax.swing.Timer;
import java.util.*;

public class Game extends ImageIcon{
	int shipX = 10, shipY = 10;
	int shipWidth = 90, shipHeight = 90;
	int shipLife;
	private int shipSpeed = 10;
	public Game(String img, int x, int y, int width, int height) {
		super(img);
		this.shipX=x;
		this.shipY=y;
		this.shipWidth = width;
		this.shipHeight = height;
	}
	void gameLifeInit() {
		shipLife=3;
	}
	void gameDraw(Graphics g) {
		shipDraw(g);
	}

	public void shipDraw(Graphics g) {
		g.drawImage(this.getImage(), shipX, shipY, shipWidth, shipHeight, null);
	}

}
