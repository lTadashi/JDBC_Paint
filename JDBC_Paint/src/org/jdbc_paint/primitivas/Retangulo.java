package org.jdbc_paint.primitivas;

public class Retangulo extends Primitiva {
	private int largura, altura;

	/**
	 * Cria um novo retângulo.
	 * 
	 * @param aX
	 *            Posição no eixo x.
	 * @param aY
	 *            Posição no eixo y.
	 * @param aLargura
	 *            Largura do retângulo.
	 * @param aAltura
	 *            Altura do retângulo.
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
