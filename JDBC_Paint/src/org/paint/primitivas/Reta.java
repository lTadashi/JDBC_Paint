package org.paint.primitivas;

public class Reta extends Primitiva {
	private int x2, y2;
	
	public Reta(int aX1, int aY1, int aX2, int aY2) {
		super(aX1, aY1);
		x2 = aX2;
		y2 = aY2;
	}

	public int getX2() {
		return x2;
	}

	public void setX2(int x2) {
		this.x2 = x2;
	}

	public int getY2() {
		return y2;
	}

	public void setY2(int y2) {
		this.y2 = y2;
	}
}
