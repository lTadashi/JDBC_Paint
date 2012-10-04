package org.paint.primitivas;

public class Ponto {
	private double x, y;
	
	/**
	 * Cria um novo ponto.
	 * 
	 * @param aX
	 * Posição do ponto no eixo X.
	 * @param aY
	 * Posição do ponto no eixo Y.
	 */
	public Ponto(double aX, double aY) {
		this.x = aX;
		this.y = aY;
	}

	/******************************************************************************************/
	/********************************** Getters e Setters *************************************/
	/******************************************************************************************/
	public double getX() {
		return x;
	}

	public void setX(double x) {
		this.x = x;
	}

	public double getY() {
		return y;
	}

	public void setY(double y) {
		this.y = y;
	}
}
