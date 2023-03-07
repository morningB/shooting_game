//20195128 윤주원
import java.awt.BorderLayout;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.Timer;

// 미사일 적 충돌하다 끝남
public class ShootGame {
	JFrame f;
	DrawPanel mainPanel;
	JPanel bottom;
	JLabel l1, l2, l3, scoreLabel;
	int enemyTime = 0, missileTime = 0;
	static int life, score;
	final static int FrameWidth = 800, FrameHeight = 620;

	private Game game;
	private Missile m;
	private Enemy enemy;
	private Boss boss;
	private Image mainScreen = new ImageIcon("메인사진.jpg").getImage();
	private Image readyScreen = new ImageIcon("준비사진.jpg").getImage();
	private Image gameScreen = new ImageIcon("게임사진.jpg").getImage();
	private Image gameOverScreen = new ImageIcon("게임종료.jpg").getImage();
	private ImageIcon happyLife = new ImageIcon("happyFace.gif");

	private boolean isMainScreen, isReadyScreen, isGameScreen, isGameOver;
	private boolean space;
	ArrayList<Game> shipList = new ArrayList<>();
	ArrayList<Enemy> enemyList = new ArrayList<>();
	ArrayList<Missile> missileList = new ArrayList<>();
	ArrayList<Boss> bossList = new ArrayList<>();

	ShootGame() {
		mainPanel = new DrawPanel();

		bottom = new JPanel(new GridLayout(0, 4));
		Font font = new Font("양재블럭체", Font.BOLD, 30);
		l1 = new JLabel();
		l2 = new JLabel();
		l3 = new JLabel();
		game.gameLifeInit();
		scoreLabel = new JLabel("score: " + score);
		scoreLabel.setFont(font);
		l1.setIcon(happyLife);
		l2.setIcon(happyLife);
		l3.setIcon(happyLife);
		bottom.add(l1);
		bottom.add(l2);
		bottom.add(l3);
		bottom.add(scoreLabel);
		bottom.setVisible(false);
		init();
		f = new JFrame();
		f.setTitle("Shooting Game");
		f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		f.add(BorderLayout.SOUTH, bottom);
		f.add(BorderLayout.CENTER, mainPanel);
		f.setSize(FrameWidth, FrameHeight);
		f.setVisible(true);

		Timer timer = new Timer(10, new TimerLsistener());
		timer.start();

	}

	public static void main(String[] agrs) {
		ShootGame s = new ShootGame();
	}

	class TimerLsistener implements ActionListener {
		public void actionPerformed(ActionEvent e) {

			int s = enemyList.size();

			// 시간마다 적 나옴
			int x = mainPanel.getWidth();
			int y = mainPanel.getHeight();
			if (enemyTime++ == 100) {
				if (s <= 20) {
					enemyTime = 0;
					Enemy e2 = new Enemy("적.png", (int) (Math.random() * x + 20), (int) (Math.random() * y - 20), 40,40);
					enemyList.add(e2);
				}
			}			for (Boss pp : bossList) {// boss 움직임
				pp.bossMove(pp.bossMoveX, pp.bossMoveY);

				if (pp.bossX <= 0 || pp.bossX >= x - 70)
					pp.bossMoveX = pp.bossMoveX * -1;

				if (pp.bossY <= 0 || pp.bossY >= y - 70)
					pp.bossMoveY = pp.bossMoveY * -1;

			}
			for (Enemy pi : enemyList) {
				pi.enemyMove(pi.enemymoveX, pi.enemymoveY);

				if (enemyDistance(pi) < 30) {// 배랑 적 충돌
					game.shipLife--;

					if (game.shipLife == 2) {
						boss = new Boss("보스.png", (int) (Math.random() * x - 20), (int) (Math.random() * y), 80,80);
						bossList.add(boss);
						l1.setIcon(null);
						enemyList.remove(pi);
						break;

					} else if (game.shipLife == 1) {

						boss = new Boss("보스.png", (int) (Math.random() * x - 20), (int) (Math.random() * y), 80,80);
						bossList.add(boss);

						l2.setIcon(null);
						enemyList.remove(pi);
						break;

					} else if (game.shipLife == 0) {
						l3.setIcon(null);
						gameEnd();
					}

				}
				// 그림이 화면에서 벗어나지 않도록 방향을 + <=> - 변환해 줌
				if (pi.enemyX <= 0 || pi.enemyX >= x - 70)
					pi.enemymoveX = pi.enemymoveX * -1;

				if (pi.enemyY <= 0 || pi.enemyY >= y - 70)
					pi.enemymoveY = pi.enemymoveY * -1;
				
				
			}
			if (space) {
				m = new Missile("미사일.png", game.shipX + 70, game.shipY + 20, 30, 30);
				missileList.add(m);
				space = false;
			}
			coll();
			missileAttack();
			bossAttack();
			f.repaint();
			
			
		}
	}
	// public double collision

	class DrawPanel extends JPanel {

		public void paintComponent(Graphics g) {
			int x = this.getWidth();
			int y = this.getHeight();

			if (isMainScreen)// 시작 화면
				g.drawImage(mainScreen, 0, 0, x, y, null);
			else if (isReadyScreen) {// 준비 화면
				g.drawImage(readyScreen, 0, 0, x, y, null);
			} else if (isGameScreen) {// 게임화면
				g.drawImage(gameScreen, 0, 0, x, y, null);
				game.shipDraw(g);

				for (Missile m : missileList) { // 미사일 그리기
					m.missileDraw(g);
					m.fire();
				}
				for (Enemy pi : enemyList) {// 적 그리기
					pi.enemyDraw(g);
				}
				for (Boss bo : bossList) {// 보스 그리기
					bo.bossDraw(g);
				}

			} 
			else if (isGameOver) { // 게임 종료
				g.drawImage(gameOverScreen, 0, 0, x, y, null);
			}

			repaint();
		}

		public DrawPanel() {
			game = new Game("배.png", 40, FrameHeight / 2, 70, 70);
			shipList.add(game);

			addKeyListener(new KeyListener() { // 무명클래스로 키 리스너
				public void keyPressed(KeyEvent e) {
					if (e.getKeyCode() == KeyEvent.VK_W) {
						game.shipY -= 10;
					}
					if (e.getKeyCode() == KeyEvent.VK_A) {
						game.shipX -= 10;
					}
					if (e.getKeyCode() == KeyEvent.VK_S) {
						game.shipY += 10;
					}
					if (e.getKeyCode() == KeyEvent.VK_D) {
						game.shipX += 10;
					}
					if (e.getKeyCode() == KeyEvent.VK_ESCAPE)
						System.exit(0);
					if (e.getKeyCode() == KeyEvent.VK_ENTER) {
						gameReady();
						bottom.setVisible(false);
					}
					if (e.getKeyCode() == KeyEvent.VK_SPACE) {
						gameStart();
						bottom.setVisible(true);

					}
					if (e.getKeyCode() == KeyEvent.VK_SPACE) {
						space = true;
					}
					if (e.getKeyCode() == KeyEvent.VK_UP) {
						for (Enemy pi : enemyList) {
							if (Math.abs(pi.enemymoveX) <= 5 && Math.abs(pi.enemymoveY) <= 5) { // 2배 빠르게 함. 그러나 10은 넘지
									// 않게
								pi.enemymoveX *= 2;
								pi.enemymoveY *= 2;
							}
						}
						for (Boss bo : bossList) {
							if (Math.abs(bo.bossMoveX) <= 7 && Math.abs(bo.bossMoveY) <= 7) { // 빠르게함
								bo.bossMoveY *= 2;
								bo.bossMoveX *= 2;
							}
						}

					}
					if (e.getKeyCode() == KeyEvent.VK_DOWN) {
						for (Enemy pi : enemyList) {
							if (Math.abs(pi.enemymoveX) >= 2 && Math.abs(pi.enemymoveY) >= 2) { // 2배 느리게함
								pi.enemymoveX /= 2;
								pi.enemymoveY /= 2;
							}
						}
						for (Boss bo : bossList) {
							if (Math.abs(bo.bossMoveX) >= 2 && Math.abs(bo.bossMoveY) >= 2) { // 2배 느리게 함
								bo.bossMoveY /= 2;
								bo.bossMoveX /= 2;
							}
						}

					}
					repaint();

				}

				public void keyReleased(KeyEvent e) {

				}

				@Override
				public void keyTyped(KeyEvent e) {
					// TODO Auto-generated method stub

				}
			});
			this.requestFocus();
			setFocusable(true);

		}

	}

	private void init() {// 종료와 같이
		isMainScreen = true;
		isReadyScreen = false;
		isGameScreen = false;
		isGameOver=false;
	}

	private void gameReady() {
		isMainScreen = false;
		isReadyScreen = true;
		isGameScreen = false;
		isGameOver=false;
	}

	private void gameStart() {
		isMainScreen = false;
		isReadyScreen = false;
		isGameScreen = true;
		isGameOver=false;
	}

	private void gameEnd() {
		isMainScreen = false;
		isReadyScreen = false;
		isGameScreen = false;
		isGameOver = true;
	}

	public double enemyDistance(Enemy e) { //
		return Math.sqrt(Math.pow(((game.shipX + (game.shipWidth / 2))) - e.enemyX, 2)
				+ Math.pow((game.shipY + (game.shipHeight / 2)) - e.enemyY, 2));
	}



	private void missileAttack() {
		for (int i = 0; i < missileList.size(); i++) {
			m = missileList.get(i);
			m.fire();

			for (int j = 0; j < enemyList.size(); j++) {
				enemy = enemyList.get(j);
				if (m.missileX > enemy.enemyX && m.missileX < enemy.enemyX + enemy.enemyWidth
						&& m.missileY > enemy.enemyY-10 && m.missileY < enemy.enemyY + enemy.enemyHeight) {
					enemyList.remove(enemy);
					missileList.remove(m);
					scoreLabel.setText("score: " + ++score);
					
				}

			}
		}
	}

	private void bossAttack() {
		for (int i = 0; i < missileList.size(); i++) {
			m = missileList.get(i);
			m.fire();

			for (int j = 0; j < bossList.size(); j++) {
				boss = bossList.get(j);
				if (m.missileX > boss.bossX && m.missileX < boss.bossX + boss.bossWidth && m.missileY > boss.bossY
						&& m.missileY < boss.bossY + boss.bossHeight) {
					boss.bL--;
					if (boss.bL == 0) {
						bossList.remove(boss);
						score = score + 10;
						scoreLabel.setText("score: " + score);
					}
					missileList.remove(m);
				}

			}
		}
	}

	private void coll() {
		for (int i = 0; i < shipList.size(); i++) {
			game = shipList.get(i);
			for (int j = 0; j < bossList.size(); j++) {
				boss = bossList.get(j);
				if (game.shipX > boss.bossX-45 && game.shipX < boss.bossX + boss.bossWidth-30 && game.shipY > boss.bossY-45
						&& game.shipY < boss.bossY + boss.bossHeight-20) {
					gameEnd();

				}

			}
		}
	}
}
