import java.awt.Graphics;
import java.awt.Image;
import java.awt.Rectangle;

import javax.swing.ImageIcon;

public class Boss extends ImageIcon {
		int bossX, bossY;
		int bossWidth = 70, bossHeight = 70, bossMoveX = 3, bossMoveY = 3;
		int bL=10;
		public Boss(String img, int x, int y, int width, int height) {
			super(img);
			this.bossX = x;
			this.bossY = y;
			this.bossWidth = width;
			this.bossHeight = height;
			
		}
		public void bossDraw(Graphics g) {
			g.drawImage(this.getImage(), bossX, bossY, bossWidth, bossHeight, null);

		}

		public void bossMove(int x, int y) {
			this.bossX += x;
			this.bossY += y;
		}
	}