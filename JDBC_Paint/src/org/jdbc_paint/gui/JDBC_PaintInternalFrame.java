package org.jdbc_paint.gui;

import java.awt.Dimension;

import javax.swing.JInternalFrame;

import org.jdbc_paint.bd.BancoDados;

public class JDBC_PaintInternalFrame extends JInternalFrame {
	private JDBC_PaintCanvas canvas;

	public JDBC_PaintInternalFrame(String aNomeDesenho, Dimension aDimensao,
			BancoDados aBancoDados, int[] aFerramentas) {
		super(aNomeDesenho, true, true, true, true);

		canvas = new JDBC_PaintCanvas(aNomeDesenho, aDimensao, aBancoDados,
				aFerramentas);
		add(canvas);

		validate();
		pack();
	}
}
