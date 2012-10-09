package org.jdbc_paint.gui;

import java.awt.Dimension;

import javax.swing.JDesktopPane;

import org.jdbc_paint.bd.BancoDados;
import org.jdbc_paint.desenho.Desenho;

public class JDBC_PaintDesktopPane extends JDesktopPane {
	private BancoDados bd;

	public JDBC_PaintDesktopPane(Dimension aDimensao, BancoDados aBancoDados) {
		super();
		setSize(aDimensao);
		setVisible(true);
		
		bd = aBancoDados;
	}

	public void novoDesenho(String aNomeDesenho, int[] aFerramentas) {
		JDBC_PaintInternalFrame novoDesenho = new JDBC_PaintInternalFrame(
				aNomeDesenho, getSize(), bd, aFerramentas);

		add(novoDesenho);
		validate();
	}

	public void novoDesenho(Desenho desenho, int[] aFerramentas) {
		JDBC_PaintInternalFrame novoDesenho = new JDBC_PaintInternalFrame(
				desenho.getNomeDesenho(), getSize(), bd, aFerramentas);
		novoDesenho.setDesenho(desenho);
		
		add(novoDesenho);
		validate();
	}

	public Desenho getDesenho() {
		JDBC_PaintInternalFrame frame = (JDBC_PaintInternalFrame) getSelectedFrame();
	
		return frame.getDesenho();
	}
}
