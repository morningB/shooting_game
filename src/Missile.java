import java.awt.Graphics;

import java.awt.Image;
import java.awt.Rectangle;

import javax.swing.ImageIcon;

public class Missile extends ImageIcon{
	int missileX=80,missileY=50;
	int missileWidth=30,missileHeight=30;
	public Missile(String img,int x,int y,int w,int h){
		super(img);
		this.missileX=x;
		this.missileY=y;
		this.missileWidth=w;
		this.missileHeight=h;
	}
	public void fire() {
		this.missileX+=4;
	}
	public void missileDraw(Graphics g) {
		g.drawImage(this.getImage(),missileX,missileY,missileWidth,missileHeight,null);
	}
	Rectangle getRect() {
			return new Rectangle(missileX,missileY,missileWidth,missileHeight);
		}
	
	
}
