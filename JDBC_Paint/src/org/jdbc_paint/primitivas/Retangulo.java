package org.jdbc_paint.primitivas;

public class Retangulo extends Primitiva {
	private int largura, altura;

	/**
	 * Cria um novo ret�ngulo.
	 * 
	 * @param aX
	 *            Posi��o no eixo x.
	 * @param aY
	 *            Posi��o no eixo y.
	 * @param aLargura
	 *            Largura do ret�ngulo.
	 * @param aAltura
	 *            Altura do ret�ngulo.
	 */
	public Retangulo(int aX, int aY, int aLargura, int aAltura) {
		super(aX, aY);
		largura = aLargura;
		altura = aAltura;
	}

	/******************************************************************************************/
	/********************************** Getters e Setters *************************************/
	/******************************************************************************************/
	public int getLargura() {
		return largura;
	}

	public void setLargura(int largura) {
		this.largura = largura;
	}

	public int getAltura() {
		return altura;
	}

	public void setAltura(int altura) {
		this.altura = altura;
	}
}
