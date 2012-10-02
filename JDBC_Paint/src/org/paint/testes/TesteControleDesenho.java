package org.paint.testes;

import static org.junit.Assert.assertTrue;

import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.JOptionPane;

import junit.framework.Assert;

import org.junit.Before;
import org.junit.Test;
import org.paint.bd.BancoDados;
import org.paint.desenho.ControleDesenhos;
import org.paint.desenho.Desenho;

public class TesteControleDesenho {
	private BancoDados bd;
	private Desenho desenho;
	private ControleDesenhos controleDesenho;
	private int ultimaId;

	@Before
	public void inicializacao() {
		// Faz conexão com o Banco de Dados
		bd = new BancoDados("jdbc:mysql://localhost/paint", "root", "root");

		// Inicializa o controle de desenho
		controleDesenho = new ControleDesenhos();

		// Cria um novo desenho
		desenho = new Desenho("Desenho Teste");
	}

	@Test
	/**
	 * Testa se o desenho é salvo no Banco de Dados.
	 */
	public void testeSalvarDesenho() {
		// Salva o desenho e define o id do desenho
		desenho.setIdDesenho(controleDesenho.salvarDesenho(desenho));

		// Adquire o id do último desenho
		String sql = "SELECT MAX(id) as id " + "FROM desenhos";

		if (bd.executarSQL(sql)) {
			try {
				// Adquire o resultado
				ResultSet resultado = bd.getResultSet();

				// Vai para a segunda linha do resultado
				resultado.next();

				ultimaId = resultado.getInt(1);
			} catch (SQLException sqlEx) {
				JOptionPane.showMessageDialog(null, sqlEx.getMessage());
			}
		} else {
			ultimaId = 0;
		}

		Assert.assertEquals(ultimaId, desenho.getIdDesenho());
	}

	@Test
	/**
	 * Verifica se o desenho é removido do Banco de Dados.
	 */
	public void testeRemoverDesenho() {
		// Adquire o id do último desenho
		String sql = "SELECT MAX(id) as id " + "FROM desenhos";

		if (bd.executarSQL(sql)) {
			try {
				// Adquire o resultado
				ResultSet resultado = bd.getResultSet();

				// Vai para a segunda linha do resultado
				resultado.next();

				ultimaId = resultado.getInt(1);
			} catch (SQLException sqlEx) {
				JOptionPane.showMessageDialog(null, sqlEx.getMessage());
			}
		} else {
			ultimaId = 0;
		}

		// Define a id do desenho
		desenho.setIdDesenho(ultimaId);

		// Verifica se o desenho é removido do Banco de Dados
		assertTrue(controleDesenho.removerDesenho(desenho));
	}

	@Test
	/**
	 * Novo desenho a partir de um respectivo desenho do Banco de Dados.
	 */
	public void testeAdquireDesenho() {
		// Salva o desenho e adquire o número da id
		desenho.setIdDesenho(controleDesenho.salvarDesenho(desenho));

		Desenho novoDesenho = controleDesenho
				.getDesenho(desenho.getIdDesenho());

		Assert.assertEquals("Desenho Teste", novoDesenho.getNomeDesenho());
		Assert.assertEquals(desenho.getIdDesenho(), novoDesenho.getIdDesenho());
		
		// Remove o novo desenho
	    controleDesenho.removerDesenho(novoDesenho);
		
		// TODO Testar o retorno de primitivas do desenho
	}
}
