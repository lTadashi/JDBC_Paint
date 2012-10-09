package org.jdbc_paint.gui;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.image.BufferedImage;

import javax.swing.JOptionPane;
import javax.swing.JPanel;

import org.jdbc_paint.bd.BancoDados;
import org.jdbc_paint.desenho.Desenho;
import org.jdbc_paint.primitivas.Elipse;
import org.jdbc_paint.primitivas.Ponto;
import org.jdbc_paint.primitivas.Reta;
import org.jdbc_paint.primitivas.Retangulo;

public class JDBC_PaintCanvas extends JPanel implements MouseListener,
		MouseMotionListener {
	private Desenho desenho;
	private Desenho novoDesenho;
	private BancoDados bancoDados;
	private BufferedImage frameDesenho;
	private Graphics grafico;
	private int[] ferramentas;

	// Primitivas temporárias
	private Reta tempReta;
	private Retangulo tempRetangulo;
	private Elipse tempElipse;

	// Coordenadas temporárias para a criação das primitivas temporárias
	private int xTemp, yTemp;
	
	// Variável de estado de edição de desenho já existente
	private boolean edicaoDesenhoExistente;

	public JDBC_PaintCanvas(String aNomeDesenho, Dimension aDimensao,
			BancoDados aBancoDados, int[] aFerramentas) {
		super();
		setSize(new Dimension(aDimensao.width / 2, aDimensao.height / 2));
		setPreferredSize(new Dimension(aDimensao.width / 2,
				aDimensao.height / 2));
		setBackground(Color.WHITE);
		addMouseMotionListener(this);
		addMouseListener(this);
		setVisible(true);

		bancoDados = aBancoDados;
		ferramentas = aFerramentas;

		desenho = new Desenho(aNomeDesenho, aBancoDados);
		
		desenharFrame();
	}

	@Override
	public void mouseDragged(MouseEvent e) {
		if (ferramentas[0] == 1) {
			// Lápis
			if(isEdicaoDesenhoExistente()) {
				novoDesenho.adicionarPonto(new Ponto(e.getX(), e.getY()));
			} else {
				desenho.adicionarPonto(new Ponto(e.getX(), e.getY()));
			}
		} else if (ferramentas[1] == 1) {
			// Reta
			tempReta.setX2(e.getX());
			tempReta.setY2(e.getY());
		} else if (ferramentas[2] == 1) {
			// Retângular
			if (e.getY() < yTemp) {
				tempRetangulo.setY(e.getY());
				tempRetangulo.setAltura(Math.abs(yTemp - tempRetangulo.getY()));
			} else {
				tempRetangulo.setAltura(Math.abs(e.getY()
						- tempRetangulo.getY()));
			}

			if (e.getX() < xTemp) {
				tempRetangulo.setX(e.getX());
				tempRetangulo
						.setLargura(Math.abs(xTemp - tempRetangulo.getX()));
			} else {
				tempRetangulo.setLargura(Math.abs(e.getX()
						- tempRetangulo.getX()));
			}
		} else if (ferramentas[3] == 1) {
			// Elipse
			if (e.getY() < yTemp) {
				tempElipse.setY(e.getY());
				tempElipse.setAltura(Math.abs(yTemp - tempElipse.getY()));
			} else {
				tempElipse.setAltura(Math.abs(e.getY() - tempElipse.getY()));
			}

			if (e.getX() < xTemp) {
				tempElipse.setX(e.getX());
				tempElipse.setLargura(Math.abs(xTemp - tempElipse.getX()));
			} else {
				tempElipse.setLargura(Math.abs(e.getX() - tempElipse.getX()));
			}
		}

		desenharFrame();
	}

	@Override
	public void mouseMoved(MouseEvent e) {

	}

	private void desenharFrame() {
		if (frameDesenho == null) {
			// cria o frame
			frameDesenho = new BufferedImage(getWidth(), getHeight(), BufferedImage.TYPE_INT_ARGB);

			if (frameDesenho == null) {
				JOptionPane.showMessageDialog(this, "Erro ao criar frames",
						"Erro de desenho", JOptionPane.WARNING_MESSAGE);
			} else {
				grafico = frameDesenho.getGraphics();
			}
		}

		grafico.setColor(Color.WHITE);
		grafico.fillRect(0, 0, getWidth(), getHeight());

		grafico.setColor(Color.BLACK);

		for (Ponto ponto : desenho.getPontos()) {
			grafico.drawLine(ponto.getX(), ponto.getY(), ponto.getX(),
					ponto.getY());
		}

		for (Reta reta : desenho.getRetas()) {
			grafico.drawLine(reta.getX(), reta.getY(), reta.getX2(),
					reta.getY2());
		}

		for (Retangulo retangulo : desenho.getRetangulos()) {
			grafico.drawRect(retangulo.getX(), retangulo.getY(),
					retangulo.getLargura(), retangulo.getAltura());
		}

		for (Elipse elipse : desenho.getElipses()) {
			grafico.drawOval(elipse.getX(), elipse.getY(), elipse.getLargura(),
					elipse.getAltura());
		}
		
		if(isEdicaoDesenhoExistente()) {
			for (Ponto ponto : novoDesenho.getPontos()) {
				grafico.drawLine(ponto.getX(), ponto.getY(), ponto.getX(),
						ponto.getY());
			}

			for (Reta reta : novoDesenho.getRetas()) {
				grafico.drawLine(reta.getX(), reta.getY(), reta.getX2(),
						reta.getY2());
			}

			for (Retangulo retangulo : novoDesenho.getRetangulos()) {
				grafico.drawRect(retangulo.getX(), retangulo.getY(),
						retangulo.getLargura(), retangulo.getAltura());
			}

			for (Elipse elipse : novoDesenho.getElipses()) {
				grafico.drawOval(elipse.getX(), elipse.getY(), elipse.getLargura(),
						elipse.getAltura());
			}
		}

		if (ferramentas[1] == 1 && tempReta != null) {
			grafico.drawLine(tempReta.getX(), tempReta.getY(),
					tempReta.getX2(), tempReta.getY2());
		} else if (ferramentas[2] == 1 && tempRetangulo != null) {
			grafico.drawRect(tempRetangulo.getX(), tempRetangulo.getY(),
					tempRetangulo.getLargura(), tempRetangulo.getAltura());
		} else if (ferramentas[3] == 1 && tempElipse != null) {
			grafico.drawOval(tempElipse.getX(), tempElipse.getY(),
					tempElipse.getLargura(), tempElipse.getAltura());
		}

		// redesenha a tela
		repaint();
	}

	public void paintComponent(Graphics g) {
		super.paintComponent(g);

		if (frameDesenho != null) {
			g.drawImage(frameDesenho, 0, 0, null);
		}
	}

	@Override
	public void mouseClicked(MouseEvent e) {

	}

	@Override
	public void mousePressed(MouseEvent e) {
		if (ferramentas[0] == 1) {
			// Lápis
			if(isEdicaoDesenhoExistente()) {
				novoDesenho.adicionarPonto(new Ponto(e.getX(), e.getY()));
			} else {
				desenho.adicionarPonto(new Ponto(e.getX(), e.getY()));
			}
		} else if (ferramentas[1] == 1) {
			// Reta
			tempReta = new Reta(e.getX(), e.getY(), e.getX(), e.getY());
		} else if (ferramentas[2] == 1) {
			// Retângular
			tempRetangulo = new Retangulo(e.getX(), e.getY(), 0, 0);
			xTemp = e.getX();
			yTemp = e.getY();
		} else if (ferramentas[3] == 1) {
			// Elipse
			tempElipse = new Elipse(e.getX(), e.getY(), 0, 0);
			xTemp = e.getX();
			yTemp = e.getY();
		}
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		if (ferramentas[1] == 1) {
			// Reta
			if(isEdicaoDesenhoExistente()) {
				novoDesenho.adicionarReta(tempReta);
				tempReta = null;
			} else {
				desenho.adicionarReta(tempReta);
				tempReta = null;
			}
		} else if (ferramentas[2] == 1) {
			// Retângular
			if(isEdicaoDesenhoExistente()) {
				novoDesenho.adicionarRetangulo(tempRetangulo);
				tempRetangulo = null;
			} else {
				desenho.adicionarRetangulo(tempRetangulo);
				tempRetangulo = null;
			}
		} else if (ferramentas[3] == 1) {
			// Elipse
			if(isEdicaoDesenhoExistente()) {
				novoDesenho.adicionarElipse(tempElipse);
				tempElipse = null;
			} else {
				desenho.adicionarElipse(tempElipse);
				tempElipse = null;
			}
		}
	}

	@Override
	public void mouseEntered(MouseEvent e) {

	}

	@Override
	public void mouseExited(MouseEvent e) {

	}

	/******************************************************************************************/
	/********************************** Getters e Setters *************************************/
	/******************************************************************************************/
	public Desenho getDesenho() {
		return desenho;
	}
	
	public void setDesenho(Desenho aDesenho) {
		desenho = aDesenho;
		novoDesenho = new Desenho(desenho.getNomeDesenho(), bancoDados);
		novoDesenho.setIdDesenho(desenho.getIdDesenho());
		edicaoDesenhoExistente = true;
		
		desenharFrame();
	}

	public boolean isEdicaoDesenhoExistente() {
		return edicaoDesenhoExistente;
	}

	public Desenho getNovoDesenho() {
		return novoDesenho;
	}
}
