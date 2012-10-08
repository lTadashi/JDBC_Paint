package org.jdbc_paint.gui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

import org.jdbc_paint.bd.BancoDados;

public class JDBC_PaintFrame extends JFrame implements ActionListener {
	private JDBC_PaintDesktopPane desktopPane;
	private JDBC_PaintMenuBar menuBar;
	private int[] ferramentas;

	public JDBC_PaintFrame(String aNomeJanela, int aLargura, int aAltura,
			BancoDados aBancoDados) {
		super(aNomeJanela);
		setSize(aLargura, aAltura);
		setDefaultCloseOperation(EXIT_ON_CLOSE);

		criarMenu();

		desktopPane = new JDBC_PaintDesktopPane(getSize(), aBancoDados);
		add(desktopPane);

		ferramentas = new int[] { 1, 0, 0, 0 };

		validate();
	}

	private void criarMenu() {
		menuBar = new JDBC_PaintMenuBar();
		menuBar.getMenuItemNovo().addActionListener(this);
		menuBar.getMenuItemAbrir().addActionListener(this);
		menuBar.getMenuItemSalvar().addActionListener(this);

		menuBar.getRadioItemLapis().addActionListener(this);
		menuBar.getRadioItemReta().addActionListener(this);
		menuBar.getRadioItemRetangulo().addActionListener(this);
		menuBar.getRadioItemElipse().addActionListener(this);

		setJMenuBar(menuBar);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getActionCommand() == "novo") {
			String nomeDesenho = JOptionPane
					.showInputDialog("Entre com o nome do novo desenho");

			desktopPane.novoDesenho(nomeDesenho, ferramentas);
		} else if (e.getActionCommand() == "abrir") {
			// TODO Abrir desenho
		} else if (e.getActionCommand() == "salvar") {
			// TODO salvar desenho
		} else if (e.getActionCommand() == "lapis") {
			setFerramentaLapis();
		} else if (e.getActionCommand() == "reta") {
			setFerramentaReta();
		} else if (e.getActionCommand() == "retangular") {
			setFerramentaRetangular();
		} else if (e.getActionCommand() == "elipse") {
			setFerramentaElipse();
		}
	}

	private void setFerramentaLapis() {
		// Ferramenta lápis
		ferramentas[0] = 1;
		// Ferramenta reta
		ferramentas[1] = 0;
		// Ferramenta retângular
		ferramentas[2] = 0;
		// Ferramenta elipse
		ferramentas[3] = 0;
	}

	private void setFerramentaReta() {
		// Ferramenta lápis
		ferramentas[0] = 0;
		// Ferramenta reta
		ferramentas[1] = 1;
		// Ferramenta retângular
		ferramentas[2] = 0;
		// Ferramenta elipse
		ferramentas[3] = 0;
	}

	private void setFerramentaRetangular() {
		// Ferramenta lápis
		ferramentas[0] = 0;
		// Ferramenta reta
		ferramentas[1] = 0;
		// Ferramenta retângular
		ferramentas[2] = 1;
		// Ferramenta elipse
		ferramentas[3] = 0;
	}

	private void setFerramentaElipse() {
		// Ferramenta lápis
		ferramentas[0] = 0;
		// Ferramenta reta
		ferramentas[1] = 0;
		// Ferramenta retângular
		ferramentas[2] = 0;
		// Ferramenta elipse
		ferramentas[3] = 1;
	}
}
