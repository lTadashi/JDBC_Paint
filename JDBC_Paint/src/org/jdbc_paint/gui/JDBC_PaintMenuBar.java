package org.jdbc_paint.gui;

import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JRadioButtonMenuItem;

public class JDBC_PaintMenuBar extends JMenuBar {
	private JMenu menuArquivo, menuFerramentas;

	// Itens da aba Arquivo
	private JMenuItem menuItemNovo, menuItemAbrir, menuItemSalvar;
	// Itens da aba Ferramentas
	private ButtonGroup grupoRadioFerramentas;
	private JRadioButtonMenuItem radioItemLapis, radioItemReta, radioItemRetangulo, radioItemElipse;

	public JDBC_PaintMenuBar() {
		super();

		criarAbaArquivo();
		add(menuArquivo);
		
		criarAbaFerramentas();
		add(menuFerramentas);
		
		validate();
	}
	
	private void criarAbaArquivo() {
		menuArquivo = new JMenu("Arquivo");
		
		menuItemNovo = new JMenuItem("Novo Desenho", new ImageIcon("icones/novo.png"));
		menuItemNovo.setActionCommand("novo");
		menuItemAbrir = new JMenuItem("Abrir...", new ImageIcon("icones/abrir.png"));
		menuItemAbrir.setActionCommand("abrir");
		menuItemSalvar = new JMenuItem("Salvar", new ImageIcon("icones/salvar.png"));
		menuItemSalvar.setActionCommand("salvar");
		
		menuArquivo.add(menuItemNovo);
		menuArquivo.add(menuItemAbrir);
		menuArquivo.add(menuItemSalvar);
	}
	
	private void criarAbaFerramentas() {
		menuFerramentas = new JMenu("Ferramentas");
		
		radioItemLapis = new JRadioButtonMenuItem("Lápis", new ImageIcon("icones/lapis.png"));
		radioItemLapis.setActionCommand("lapis");
		radioItemReta = new JRadioButtonMenuItem("Reta", new ImageIcon("icones/reta.png"));
		radioItemReta.setActionCommand("reta");
		radioItemRetangulo = new JRadioButtonMenuItem("Retângular", new ImageIcon("icones/retangulo.png"));
		radioItemRetangulo.setActionCommand("retangular");
		radioItemElipse = new JRadioButtonMenuItem("Elipse", new ImageIcon("icones/elipse.png"));
		radioItemElipse.setActionCommand("elipse");
		
		grupoRadioFerramentas = new ButtonGroup();
		grupoRadioFerramentas.add(radioItemLapis);
		grupoRadioFerramentas.add(radioItemReta);
		grupoRadioFerramentas.add(radioItemRetangulo);
		grupoRadioFerramentas.add(radioItemElipse);
		
		radioItemLapis.setSelected(true);
		
		menuFerramentas.add(radioItemLapis);
		menuFerramentas.add(radioItemReta);
		menuFerramentas.add(radioItemRetangulo);
		menuFerramentas.add(radioItemElipse);
	}

	/******************************************************************************************/
	/********************************** Getters e Setters *************************************/
	/******************************************************************************************/
	public JMenuItem getMenuItemNovo() {
		return menuItemNovo;
	}

	public JMenuItem getMenuItemAbrir() {
		return menuItemAbrir;
	}

	public JMenuItem getMenuItemSalvar() {
		return menuItemSalvar;
	}

	public JRadioButtonMenuItem getRadioItemLapis() {
		return radioItemLapis;
	}

	public JRadioButtonMenuItem getRadioItemReta() {
		return radioItemReta;
	}

	public JRadioButtonMenuItem getRadioItemRetangulo() {
		return radioItemRetangulo;
	}

	public JRadioButtonMenuItem getRadioItemElipse() {
		return radioItemElipse;
	}
}
