package org.paint.primitivas;

public class Ponto {
	private int desenho;
	private double x, y;
	
	/**
	 * Cria um novo ponto.
	 * 
	 * @param aDesenho
	 * Identificador do desenho.
	 * @param aX
	 * Posição do ponto no eixo X.
	 * @param aY
	 * Posição do ponto no eixo Y.
	 */
	public Ponto(int aDesenho, double aX, double aY) {
		this.desenho = aDesenho;
		this.x = aX;
		this.y = aY;
	}

	/******************************************************************************************/
	/********************************** Getters e Setters *************************************/
	/******************************************************************************************/
	public int getDesenho() {
		return desenho;
	}

	public void setDesenho(int desenho) {
		this.desenho = desenho;
	}

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
