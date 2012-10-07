package org.jdbc_paint.testes;

import static org.junit.Assert.assertTrue;

import java.sql.ResultSet;
import java.sql.SQLException;

import junit.framework.Assert;

import org.jdbc_paint.bd.BancoDados;
import org.jdbc_paint.desenho.ControleDesenhos;
import org.jdbc_paint.desenho.Desenho;
import org.junit.Before;
import org.junit.Test;

public class TesteControleDesenho {
	private BancoDados bd;
	private Desenho desenho;
	private ControleDesenhos controleDesenho;

	@Before
	public void inicializacao() {
		// Faz conexão com o Banco de Dados
		bd = new BancoDados("jdbc:mysql://localhost/paint", "root", "root");

		// Inicializa o controle de desenho
		controleDesenho = new ControleDesenhos(bd);

		// Cria um novo desenho
		desenho = new Desenho("Desenho Teste", bd);
	}

	@Test
	/**
	 * Testa se o desenho é salvo e removido do Banco de Dados.
	 */
	public void testeSalvarRemoverDesenho() {
		// Salva o desenho e define o id do desenho
		desenho.setIdDesenho(controleDesenho.salvarDesenhoEmBranco(desenho));

		// Adquire o id do último desenho
		String sql = "SELECT MAX(id) as id " + "FROM desenhos";

		int ultimaId = 0;		
		if (bd.executarSQL(sql)) {
			try {
				// Adquire o resultado
				ResultSet resultado = bd.getResultSet();

				// Vai para a segunda linha do resultado
				resultado.next();

				ultimaId = resultado.getInt(1);
			} catch (SQLException sqlEx) {
				sqlEx.printStackTrace();
			}
		}

		Assert.assertEquals(ultimaId, desenho.getIdDesenho());
		
		// Verifica se o desenho é removido do Banco de Dados
		assertTrue(controleDesenho.removerDesenhoEmBranco(desenho));
	}

	@Test
	/**
	 * Novo desenho a partir de um respectivo desenho do Banco de Dados.
	 */
	public void testeAdquireDesenho() {
		// Salva o desenho e adquire o número da id
		desenho.setIdDesenho(controleDesenho.salvarDesenhoEmBranco(desenho));

		Desenho novoDesenho = controleDesenho
				.getDesenho(desenho.getIdDesenho());

		Assert.assertEquals("Desenho Teste", novoDesenho.getNomeDesenho());
		Assert.assertEquals(desenho.getIdDesenho(), novoDesenho.getIdDesenho());
		
		// Remove o novo desenho
	    controleDesenho.removerDesenhoEmBranco(novoDesenho);
	}
}
