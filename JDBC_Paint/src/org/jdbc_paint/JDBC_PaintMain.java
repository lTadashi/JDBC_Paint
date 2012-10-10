package org.jdbc_paint;

import org.jdbc_paint.bd.BancoDados;
import org.jdbc_paint.gui.JDBC_PaintFrame;

public class JDBC_PaintMain {
	public static void main(String[] args) {
		BancoDados bd = new BancoDados("jdbc:mysql://localhost/paint", "root",
				"root");

		JDBC_PaintFrame jdbc_paintFrame = new JDBC_PaintFrame("JDBC Paint",
				800, 600, bd);

		jdbc_paintFrame.setVisible(true);
	}

}
