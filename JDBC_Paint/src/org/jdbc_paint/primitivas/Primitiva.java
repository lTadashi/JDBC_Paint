package org.jdbc_paint.primitivas;

public class Primitiva {
	private int x, y;

	/**
	 * Cria uma primitiva.
	 * 
	 * @param aX
	 *            Posição no eixo x.
	 * @param aY
	 *            Posição no eixo y.
	 */
	public Primitiva(int aX, int aY) {
		x = aX;
		y = aY;
	}

	/******************************************************************************************/
	/********************************** Getters e Setters *************************************/
	/******************************************************************************************/
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
}
