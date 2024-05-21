package view;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.sql.Time;
import java.util.ArrayList;
import java.util.Random;

import javax.swing.ImageIcon;
import javax.swing.JPanel;
import javax.swing.Timer;

import controller.acction_FlappyBird;
import controller.actionPlaceTimer;
import controller.action_keyFlap;
import model.DB;
import model.Person;
import model.Pipe;
import model.bird;

public class FlappyBird extends JPanel {

//	private static final long serialVersionUID = 1L;
	private DB listPlayer;
//	private Person currPerson;
	private acction_FlappyBird ac;
	public int broadWidth = 360;
	public int broadHeight = 640;
	private Timer gameLoop;
	private Timer placePipesTimer;
	Image backgroundImg;
	Image birdImage;
	Image topPiPeImage;
	Image bottomPiPeImage;
	private bird b;
	private int velocityX = -4;
	private int velocityY = 0;
	private int gravity = 1;
	// game logic
	public ArrayList<Pipe> pipes;
	private Random random = new Random();
	private boolean gameOver = false;


	private boolean records = false;
	private double score = 0;
    private boolean isStart = true;
	
	
	public FlappyBird(DB listPlayer) {
//		this.currPerson = currPerson;
		this.listPlayer = listPlayer;
		this.drawFlappyBird();
	}

	public void drawFlappyBird() {
//		ac = new acction_FlappyBird(this);
		setPreferredSize(new Dimension(broadWidth, broadHeight));
		setBackground(Color.blue);
		setFocusable(true);
		addKeyListener(new action_keyFlap(this));
		addAncestorListener(null);

		backgroundImg = new ImageIcon(getClass().getResource("/Image/flappybirdbg.png")).getImage();
		birdImage = new ImageIcon(getClass().getResource("/Image/flappybird.png")).getImage();
		topPiPeImage = new ImageIcon(getClass().getResource("/Image/toppipe.png")).getImage();
		bottomPiPeImage = new ImageIcon(getClass().getResource("/Image/bottompipe.png")).getImage();
		// bird
		this.b = new bird(birdImage);
		this.pipes = new ArrayList<Pipe>();
		// place pipe timer
		placePipesTimer = new Timer(1500, new actionPlaceTimer(this));
		placePipesTimer.start();
		// game
		gameLoop = new Timer(1000 / 60, new acction_FlappyBird(this));
		gameLoop.start();
	}
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		draw(g);

	}

	public void draw(Graphics g) {
		g.drawImage(backgroundImg, 0, 0, broadWidth, broadHeight, null);
		g.drawImage(b.getImge(), b.getX(), b.getY(), b.getBirdWeight(), b.getBirdHeight(), null);

		for (int i = 0; i < pipes.size(); i++) {
			Pipe pipe = pipes.get(i);
			g.drawImage(pipe.getImg(), pipe.getX(), pipe.getY(), pipe.getWidth(), pipe.getHeight(), null);
		}
		g.setColor(Color.white);
		g.setFont(new Font("Arial", Font.PLAIN, 32));
		if (gameOver) {
			if (records) {
				g.setFont(new Font("Arial", Font.PLAIN, 30));
				g.drawString("Bạn đã đạt kỷ lục:" + String.valueOf((int) score), broadWidth / 8, broadHeight / 2);
			} else {
				g.setFont(new Font("Arial", Font.PLAIN, 40));
				g.drawString("Bạn đã thua:" + String.valueOf((int) score), broadWidth / 6, broadHeight / 2);
			}
		} else {
			g.drawString("Điểm:" + String.valueOf((int) score), 10, 35);
		}
	}

	public void move() {
		velocityY += gravity;
		b.setY(Math.max(b.getY() + velocityY, 0));
		// pipes
		for (int i = 0; i < pipes.size(); i++) {
			Pipe pipe = pipes.get(i);
			pipe.setX(pipe.getX() + velocityX);
			if (!pipe.isPassed() && b.getX() > pipe.getX() + pipe.getWidth()) {
				pipe.setPassed(true);
				score += 0.5;
			}
			if (collision(b, pipe)) {
				gameOver = true;
			}
		}
		if (b.getY() > broadHeight) {
			gameOver = true;
		}
	}

	public void placePipes() {
		Pipe topPipe = new Pipe(topPiPeImage);
		int randomPipeY = (int) (topPipe.getY() - topPipe.getHeight() / 4 - Math.random() * (topPipe.getHeight() / 2));
		int openingSpace = topPipe.getHeight() / 4;
		topPipe.setY(randomPipeY);
		pipes.add(topPipe);

		Pipe bottomPipe = new Pipe(bottomPiPeImage);
		bottomPipe.setY(topPipe.getY() + openingSpace + topPipe.getHeight());
		pipes.add(bottomPipe);
	}

	// check dk pass pipe
	public boolean collision(bird a, Pipe b) {
		return a.getX() < b.getX() + b.getWidth() && a.getX() + a.getBirdWeight() > b.getX()
				&& a.getY() < b.getY() + b.getHeight() && a.getY() + a.getBirdHeight() > b.getY();
	}

	public void checkInTop() {

	}
	public boolean isStart() {
		return isStart;
	}

	public void setStart(boolean isStart) {
		this.isStart = isStart;
	}

	public boolean isRecords() {
		return records;
	}

	public void setRecords(boolean records) {
		this.records = records;
	}
	public void getGameLoopStart() {
		gameLoop.start();
	}

	public void getGameLoopStop() {
		gameLoop.stop();
	}


	public void PlacePipesTimerStart() {
		 placePipesTimer.start();
	}

	public void PlacePipesTimerStop() {
		 placePipesTimer.stop();
	}

	public bird getB() {
		return b;
	}

	public void setBY(int y) {
		b.setY(y);
	}
	public void setSizeBird(int y) {
		b.setBirdWeight(b.getBirdWeight()+y);
    	b.setBirdHeight(b.getBirdHeight()+y);
	}
	public DB getListPlayer() {
		return listPlayer;
	}

	public void addListPlayer() {
		listPlayer.setCurrentscores((int) score);
	}
	public void addListPlayerDB() {
		listPlayer.addDB();
	}

	public boolean isGameOver() {
		return gameOver;
	}

	public void setGameOver(boolean gameOver) {
		this.gameOver = gameOver;
	}

	public ArrayList<Pipe> getPipes() {
		return pipes;
	}

	public void setPipes(ArrayList<Pipe> pipes) {
		this.pipes = pipes;
	}

	public int getVelocityX() {
		return velocityX;
	}

	public void setVelocityX(int velocityX) {
		this.velocityX = velocityX;
	}

	public int getVelocityY() {
		return velocityY;
	}

	public void setVelocityY(int velocityY) {
		this.velocityY = velocityY;
	}

	public double getScore() {
		return score;
	}

	public void setScore(double score) {
		this.score = score;
	}

	
/*
	@Override
	public void actionPerformed(ActionEvent e) {
		
		move();
		repaint();
		if (gameOver) {
			this.listPlayer.setCurrentscores((int) score);
			if (this.listPlayer.checkScore()) {
				this.listPlayer.addDB();
				this.records = true;
			}
			placePipesTimer.stop();
			gameLoop.stop();
		}
	}

	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void keyPressed(KeyEvent e) {
		if (e.getKeyCode() == KeyEvent.VK_SPACE) {
			velocityY = -9;
			if(!isStart) {
				placePipesTimer.start();
				gameLoop.start();
				isStart = true;
			}
		}
		if (e.getKeyCode() == KeyEvent.VK_ESCAPE) {
			placePipesTimer.stop();
			gameLoop.stop();
			isStart = false;
		}
		// restart game
		if (gameOver && e.getKeyCode() == KeyEvent.VK_ENTER) {
			b.setY(360 / 2);
			velocityY = 0;
			pipes.clear();
//			this.currPerson.setDiem((int)score);

			score = 0;
			gameOver = false;
			gameLoop.start();
			placePipesTimer.start();
		}
	}

	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub

	}
	*/
}
