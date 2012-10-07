package org.paint.testes;

import static org.junit.Assert.assertTrue;

import java.sql.ResultSet;
import java.sql.SQLException;

import junit.framework.Assert;

import org.junit.Before;
import org.junit.Test;
import org.paint.bd.BancoDados;
import org.paint.desenho.Desenho;
import org.paint.primitivas.Ponto;
import org.paint.primitivas.Reta;

public class TesteDesenho {
	private BancoDados bd;

	@Before
	public void inicializacao() {
		bd = new BancoDados("jdbc:mysql://localhost/paint", "root", "root");
	}

	@Test
	/**
	 * Testa se é possível adicionar pontos a lista de pontos no desenho.
	 */
	public void testeAdicionarPontos() {
		Desenho desenho = new Desenho("Desenho Teste", bd);

		Ponto p = new Ponto(0, 0);
		Ponto p1 = new Ponto(2147483647, 0);
		Ponto p2 = new Ponto(0, 2147483647);
		Ponto p3 = new Ponto(2147483647, 2147483647);

		desenho.adicionarPonto(p);
		Assert.assertEquals(1, desenho.getPontos().size());

		desenho.adicionarPonto(p1);
		Assert.assertEquals(2, desenho.getPontos().size());

		desenho.adicionarPonto(p2);
		Assert.assertEquals(3, desenho.getPontos().size());

		desenho.adicionarPonto(p3);
		Assert.assertEquals(4, desenho.getPontos().size());
	}

	@Test
	/**
	 * Testa se é possível remover pontos da lista de pontos do desenho.
	 */
	public void testeRemoverPontos() {
		Desenho desenho = new Desenho("Desenho Teste", bd);

		Ponto p = new Ponto(0, 0);
		Ponto p1 = new Ponto(2147483647, 0);
		Ponto p2 = new Ponto(0, 2147483647);
		Ponto p3 = new Ponto(2147483647, 2147483647);
		Ponto p4 = new Ponto(1, 1);

		desenho.adicionarPonto(p);
		desenho.adicionarPonto(p1);
		desenho.adicionarPonto(p2);
		desenho.adicionarPonto(p3);
		desenho.adicionarPonto(p4);

		desenho.removerPonto(0);
		Assert.assertEquals(4, desenho.getPontos().size());

		desenho.removerPonto(3);
		Assert.assertEquals(3, desenho.getPontos().size());

		desenho.removerPonto(1);
		Assert.assertEquals(2, desenho.getPontos().size());

		// Verifica se os pontos restantes na lista de pontos são os esperados
		Assert.assertEquals(2147483647, desenho.getPonto(0).getX());
		Assert.assertEquals(0, desenho.getPonto(0).getY());
		Assert.assertEquals(2147483647, desenho.getPonto(1).getX());
		Assert.assertEquals(2147483647, desenho.getPonto(1).getY());
	}
	
	@Test
	/**
	 * Testa se é possível adicionar retas a lista de retas do desenho.
	 */
	public void testeAdicionarRetas() {
		Desenho desenho = new Desenho("Desenho Teste", bd);

		Reta reta = new Reta(0, 0, 2147483647, 0);
		Reta reta1 = new Reta(2147483647, 0, 2147483647, 2147483647);
		Reta reta2 = new Reta(2147483647, 2147483647, 0, 2147483647);
		Reta reta3 = new Reta(0, 2147483647, 0, 0);
		Reta reta4 = new Reta(0, 0, 2147483647, 2147483647);

		desenho.adicionarReta(reta);
		Assert.assertEquals(1, desenho.getRetas().size());

		desenho.adicionarReta(reta1);
		Assert.assertEquals(2, desenho.getRetas().size());

		desenho.adicionarReta(reta2);
		Assert.assertEquals(3, desenho.getRetas().size());

		desenho.adicionarReta(reta3);
		Assert.assertEquals(4, desenho.getRetas().size());
		
		desenho.adicionarReta(reta4);
		Assert.assertEquals(5, desenho.getRetas().size());
	}
	
	@Test
	/**
	 * Testa se é possível remover retas da lista de retas do desenho.
	 */
	public void testeRemoverRetas() {
		Desenho desenho = new Desenho("Desenho Teste", bd);

		Reta reta = new Reta(0, 0, 2147483647, 0);
		Reta reta1 = new Reta(2147483647, 0, 2147483647, 2147483647);
		Reta reta2 = new Reta(2147483647, 2147483647, 0, 2147483647);
		Reta reta3 = new Reta(0, 2147483647, 0, 0);
		Reta reta4 = new Reta(0, 0, 2147483647, 2147483647);

		desenho.adicionarReta(reta);
		desenho.adicionarReta(reta1);
		desenho.adicionarReta(reta2);
		desenho.adicionarReta(reta3);
		desenho.adicionarReta(reta4);

		desenho.removerReta(0);
		Assert.assertEquals(4, desenho.getRetas().size());

		desenho.removerReta(3);
		Assert.assertEquals(3, desenho.getRetas().size());

		desenho.removerReta(1);
		Assert.assertEquals(2, desenho.getRetas().size());

		// Verifica se as retas restantes na lista de retas são as esperadas
		Assert.assertEquals(2147483647, desenho.getReta(0).getX1());
		Assert.assertEquals(0, desenho.getReta(0).getY1());
		Assert.assertEquals(2147483647, desenho.getReta(0).getX2());
		Assert.assertEquals(2147483647, desenho.getReta(0).getY2());
		
		Assert.assertEquals(0, desenho.getReta(1).getX1());
		Assert.assertEquals(2147483647, desenho.getReta(1).getY1());
		Assert.assertEquals(0, desenho.getReta(1).getX2());
		Assert.assertEquals(0, desenho.getReta(1).getY2());
	}

	@Test
	/**
	 * Testa se é possível salvar e remover o desenho em branco no Banco de Dados.
	 */
	public void testeSalvarRemoverDesenhoEmBranco() {
		Desenho desenho = new Desenho("Desenho Teste", bd);

		desenho.salvarDesenhoEmBranco();

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
		assertTrue(desenho.removerDesenhoEmBranco());
	}
}
