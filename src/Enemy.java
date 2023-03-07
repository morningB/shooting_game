import java.awt.Graphics;
import java.awt.Image;
import java.awt.Rectangle;

import javax.swing.ImageIcon;

public class Enemy extends ImageIcon {
		int enemyX, enemyY;
		int enemyWidth = 70, enemyHeight = 70, enemymoveX = 1, enemymoveY = 1;

		public Enemy(String img, int x, int y, int width, int height) {
			super(img);
			this.enemyX = x;
			this.enemyY = y;
			this.enemyWidth = width;
			this.enemyHeight = height;
		}
		public void enemyDraw(Graphics g) {
			g.drawImage(this.getImage(), enemyX, enemyY, enemyWidth, enemyHeight, null);

		}
		public void enemyMove(int x, int y) {
			this.enemyX += x;
			this.enemyY += y;
		}
		Rectangle getRect() {
			return new Rectangle(enemyX,enemyY,enemyWidth,enemyHeight);
		}
	}