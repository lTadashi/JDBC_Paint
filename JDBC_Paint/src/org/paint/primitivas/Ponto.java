package org.paint.primitivas;

public class Ponto {
	private int x, y;
	
	/**
	 * Cria um novo ponto.
	 * 
	 * @param aX
	 * Posição do ponto no eixo X.
	 * @param aY
	 * Posição do ponto no eixo Y.
	 */
	public Ponto(int aX, int aY) {
		this.x = aX;
		this.y = aY;
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
