package org.jdbc_paint.gui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

import org.jdbc_paint.bd.BancoDados;
import org.jdbc_paint.desenho.Desenho;

public class JDBC_PaintFrame extends JFrame implements ActionListener {
	private JDBC_PaintDesktopPane desktopPane;
	private JDBC_PaintMenuBar menuBar;
	private int[] ferramentas;
	private BancoDados bancoDados;

	public JDBC_PaintFrame(String aNomeJanela, int aLargura, int aAltura,
			BancoDados aBancoDados) {
		super(aNomeJanela);
		setSize(aLargura, aAltura);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		bancoDados = aBancoDados;

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
		menuBar.getMenuItemRemover().addActionListener(this);

		menuBar.getRadioItemLapis().addActionListener(this);
		menuBar.getRadioItemReta().addActionListener(this);
		menuBar.getRadioItemRetangulo().addActionListener(this);
		menuBar.getRadioItemElipse().addActionListener(this);

		setJMenuBar(menuBar);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getActionCommand() == "novo") {
			novoDesenho();
		} else if (e.getActionCommand() == "abrir") {
			abrirDesenho();
		} else if (e.getActionCommand() == "salvar") {
			salvarDesenho();
		} else if (e.getActionCommand() == "remover") {
			removerDesenho();
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

	private void novoDesenho() {
		String nomeDesenho = JOptionPane
				.showInputDialog("Entre com o nome do novo desenho");

		desktopPane.novoDesenho(nomeDesenho, ferramentas);
	}

	private void abrirDesenho() {
		String[] opcoes = { "Abrir", "Fechar" };
		JDBC_PaintAbrirPanel abrirPanel = new JDBC_PaintAbrirPanel(bancoDados);

		if (JDBC_PaintAbrirPanel.quantidadeDesenhos(bancoDados) > 0) {
			if (JOptionPane.showOptionDialog(this, abrirPanel, "Abrir...",
					JOptionPane.NO_OPTION, JOptionPane.PLAIN_MESSAGE, null,
					opcoes, 1) == 0) {
				Desenho novoDesenho = new Desenho(abrirPanel
						.getDesenhoSelecionado().getIdDesenho(), bancoDados);
				desktopPane.novoDesenho(novoDesenho, ferramentas);
			}
		}
	}

	private void salvarDesenho() {
		if (desktopPane.isEdicaoDesenhoExistente()) {
			if (!desktopPane.getNovoDesenho().salvarTodasPrimitivas()) {
				JOptionPane.showMessageDialog(this,
						"Erro ao salvar desenho no Banco de Dados", "Erro",
						JOptionPane.WARNING_MESSAGE);
			} else {
				JOptionPane.showMessageDialog(this,
						"Desenho salvo com sucesso!", "Desenho salvo",
						JOptionPane.INFORMATION_MESSAGE);

				// Define o desenho como edição de desenho já salvo
				desktopPane.setEdicaoDesenhoExistente(true);
			}
		} else {
			if (!desktopPane.getDesenho().salvarDesenho()) {
				JOptionPane.showMessageDialog(this,
						"Erro ao salvar desenho no Banco de Dados", "Erro",
						JOptionPane.WARNING_MESSAGE);
			} else {
				JOptionPane.showMessageDialog(this,
						"Desenho salvo com sucesso!", "Desenho salvo",
						JOptionPane.INFORMATION_MESSAGE);

				// Define o desenho como edição de desenho já salvo
				desktopPane.setEdicaoDesenhoExistente(true);
			}
		}
	}

	private void removerDesenho() {
		String[] opcoes = { "Remover", "Fechar" };
		JDBC_PaintAbrirPanel abrirPanel = new JDBC_PaintAbrirPanel(bancoDados);

		if (JDBC_PaintAbrirPanel.quantidadeDesenhos(bancoDados) > 0) {
			if (JOptionPane.showOptionDialog(this, abrirPanel, "Remover",
					JOptionPane.NO_OPTION, JOptionPane.PLAIN_MESSAGE, null,
					opcoes, 1) == 0) {
				Desenho novoDesenho = new Desenho(abrirPanel
						.getDesenhoSelecionado().getIdDesenho(), bancoDados);

				if (novoDesenho.removerDesenho()) {
					JOptionPane
							.showMessageDialog(this,
									"Desenho removido com sucesso!",
									"Desenho removido",
									JOptionPane.INFORMATION_MESSAGE);
				} else {
					JOptionPane.showMessageDialog(this,
							"Erro ao remover o desenho!", "Erro",
							JOptionPane.WARNING_MESSAGE);
				}
			}
		}
	}
}
