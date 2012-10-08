package org.jdbc_paint.gui;

import java.awt.Dimension;

import javax.swing.JDesktopPane;

import org.jdbc_paint.bd.BancoDados;

public class JDBC_PaintDesktopPane extends JDesktopPane {
	private BancoDados bd;
	
	public JDBC_PaintDesktopPane(Dimension aDimensao, BancoDados aBancoDados) {
		super();
		setSize(aDimensao);
		
		bd = aBancoDados;
	}
	
	public void novoDesenho(String aNomeDesenho, int[] aFerramentas) {
		JDBC_PaintInternalFrame novoDesenho = new JDBC_PaintInternalFrame(aNomeDesenho, getSize(), bd, aFerramentas);
		novoDesenho.setVisible(true);

		add(novoDesenho);
		validate();
	}
}
