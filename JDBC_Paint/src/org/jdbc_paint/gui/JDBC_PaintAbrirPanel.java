package org.jdbc_paint.gui;

import java.awt.Dimension;
import java.util.List;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import org.jdbc_paint.bd.BancoDados;
import org.jdbc_paint.desenho.ControleDesenhos;
import org.jdbc_paint.desenho.Desenho;

public class JDBC_PaintAbrirPanel extends JPanel {
	private JTable tabelaDesenhos;
	private DefaultTableModel modeloTabela;
	private JScrollPane tabelaScroll;
	private ControleDesenhos controleDesenhos;
	private List<Desenho> desenhos;

	public JDBC_PaintAbrirPanel(BancoDados aBancoDados) {
		modeloTabela = new DefaultTableModel(new Object[] { "Nome Desenho" }, 0);
		tabelaDesenhos = new JTable(modeloTabela) {
			// Faz com que a tabela não seja editável
			public boolean isCellEditable(int rowIndex, int colIndex) {
				return false;
			}
		};

		tabelaScroll = new JScrollPane();
		tabelaScroll.setViewportView(tabelaDesenhos);
		tabelaScroll.validate();
		tabelaScroll.setPreferredSize(new Dimension(400, 400));

		add(tabelaScroll);

		validate();

		controleDesenhos = new ControleDesenhos(aBancoDados);
		adquireTodosDesenhos();
	}

	private void adquireTodosDesenhos() {
		desenhos = controleDesenhos.getDesenhosBD();

		for (Desenho desenho : desenhos) {
			modeloTabela.addRow(new Object[] { desenho.getNomeDesenho() });
		}
	}

	public Desenho getDesenhoSelecionado() {
		if (desenhos != null && desenhos.size() > 0) {
			return desenhos.get(tabelaDesenhos.getSelectedRow());
		}

		return null;
	}

	public static int quantidadeDesenhos(BancoDados aBancoDados) {
		ControleDesenhos controleDesenhoBD = new ControleDesenhos(aBancoDados);
		List<Desenho> desenhosBD = controleDesenhoBD.getDesenhosBD();
		int numDesenhos = desenhosBD.size();

		desenhosBD.clear();

		return numDesenhos;
	}
}
