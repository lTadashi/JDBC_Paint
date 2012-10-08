package org.jdbc_paint.gui;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

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
	private Image frameDesenho;
	private Graphics grafico;
	private int[] ferramentas;

	// Primitivas temporárias
	private Reta tempReta;
	private Retangulo tempRetangulo;
	private Elipse tempElipse;

	public JDBC_PaintCanvas(String aNomeDesenho, Dimension aDimensao,
			BancoDados aBancoDados, int[] aFerramentas) {
		super();
		setPreferredSize(new Dimension(aDimensao.width / 2,
				aDimensao.height / 2));
		addMouseMotionListener(this);
		addMouseListener(this);

		ferramentas = aFerramentas;

		desenho = new Desenho(aNomeDesenho, aBancoDados);
	}

	@Override
	public void mouseDragged(MouseEvent e) {
		if (ferramentas[0] == 1) {
			// Lápis
			desenho.adicionarPonto(new Ponto(e.getX(), e.getY()));
		} else if (ferramentas[1] == 1) {
			// Reta
			tempReta.setX2(e.getX());
			tempReta.setY2(e.getY());
		} else if (ferramentas[2] == 1) {
			// Retângular
			tempRetangulo.setLargura(Math.abs(e.getX() - tempRetangulo.getX()));
			tempRetangulo.setAltura(Math.abs(e.getY() - tempRetangulo.getY()));
		}
		// TODO Criar evento para elipse
		
		desenharFrame();
	}

	@Override
	public void mouseMoved(MouseEvent e) {

	}

	private void desenharFrame() {
		if (frameDesenho == null) {
			// cria o frame
			frameDesenho = createImage(getWidth(), getHeight());

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

		if (ferramentas[1] == 1) {
			grafico.drawLine(tempReta.getX(), tempReta.getY(),
					tempReta.getX2(), tempReta.getY2());
		} else if (ferramentas[2] == 1) {
			grafico.drawRect(tempRetangulo.getX(), tempRetangulo.getY(),
					tempRetangulo.getLargura(), tempRetangulo.getAltura());
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
		if (ferramentas[1] == 1) {
			// Reta
			tempReta = new Reta(e.getX(), e.getY(), e.getX(), e.getY());
		} else if (ferramentas[2] == 1) {
			// Retângular
			tempRetangulo = new Retangulo(e.getX(), e.getY(), 0, 0);
		}
		// TODO Criar evento para elipse
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		if (ferramentas[1] == 1) {
			// Reta
			desenho.adicionarReta(tempReta);
			tempReta = null;
		} else if(ferramentas[2] == 1) {
			// Retângular
			desenho.adicionarRetangulo(tempRetangulo);
			tempRetangulo = null;
		}
		// TODO Criar evento para elipse
	}

	@Override
	public void mouseEntered(MouseEvent e) {

	}

	@Override
	public void mouseExited(MouseEvent e) {

	}
}
