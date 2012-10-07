package org.paint.primitivas;

public class Retangulo extends Primitiva {
	private int largura, altura;
	
	public Retangulo(int aX, int aY, int aLargura, int aAltura) {
		super(aX, aY);
		largura = aLargura;
		altura = aAltura;
	}

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
