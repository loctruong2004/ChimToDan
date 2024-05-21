package model;

import java.awt.Image;

public class bird {

	private int x = 640 / 8;
	private int y = 360/2;
	

	private int birdWeight = 32;
	private int birdHeight = 24;
	private Image imge; 
	
	public int getBirdWeight() {
		return birdWeight;
	}

	public void setBirdWeight(int birdWeight) {
		this.birdWeight = birdWeight;
	}

	public int getBirdHeight() {
		return birdHeight;
	}

	public void setBirdHeight(int birdHeight) {
		this.birdHeight = birdHeight;
	}

	public Image getImge() {
		return imge;
	}

	public void setImge(Image imge) {
		this.imge = imge;
	}


    
	public int getX() {
		return x;
	}

	public void setX(int x) {
		this.x = x;
	}

	public int getY() {
		return y;
	}

	public void setY(int y) {
		this.y = y;
	}

	public bird(Image img) {
        super();
		this.imge = img;
	}

}
