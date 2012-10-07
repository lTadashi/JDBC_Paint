package org.paint.testes;

import static org.junit.Assert.assertTrue;

import java.sql.ResultSet;
import java.sql.SQLException;

import junit.framework.Assert;

import org.junit.Before;
import org.junit.Test;
import org.paint.bd.BancoDados;
import org.paint.desenho.Desenho;
import org.paint.primitivas.Elipse;
import org.paint.primitivas.Ponto;
import org.paint.primitivas.Reta;
import org.paint.primitivas.Retangulo;

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
		Assert.assertEquals(0, desenho.getPonto(0).getX());
		Assert.assertEquals(0, desenho.getPonto(0).getY());

		desenho.adicionarPonto(p1);
		Assert.assertEquals(2, desenho.getPontos().size());
		Assert.assertEquals(2147483647, desenho.getPonto(1).getX());
		Assert.assertEquals(0, desenho.getPonto(1).getY());

		desenho.adicionarPonto(p2);
		Assert.assertEquals(3, desenho.getPontos().size());
		Assert.assertEquals(0, desenho.getPonto(2).getX());
		Assert.assertEquals(2147483647, desenho.getPonto(2).getY());

		desenho.adicionarPonto(p3);
		Assert.assertEquals(4, desenho.getPontos().size());
		Assert.assertEquals(2147483647, desenho.getPonto(3).getX());
		Assert.assertEquals(2147483647, desenho.getPonto(3).getY());
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
		Assert.assertEquals(0, desenho.getReta(0).getX());
		Assert.assertEquals(0, desenho.getReta(0).getY());
		Assert.assertEquals(2147483647, desenho.getReta(0).getX2());
		Assert.assertEquals(0, desenho.getReta(0).getY2());

		desenho.adicionarReta(reta1);
		Assert.assertEquals(2, desenho.getRetas().size());
		Assert.assertEquals(2147483647, desenho.getReta(1).getX());
		Assert.assertEquals(0, desenho.getReta(1).getY());
		Assert.assertEquals(2147483647, desenho.getReta(1).getX2());
		Assert.assertEquals(2147483647, desenho.getReta(1).getY2());

		desenho.adicionarReta(reta2);
		Assert.assertEquals(3, desenho.getRetas().size());
		Assert.assertEquals(2147483647, desenho.getReta(2).getX());
		Assert.assertEquals(2147483647, desenho.getReta(2).getY());
		Assert.assertEquals(0, desenho.getReta(2).getX2());
		Assert.assertEquals(2147483647, desenho.getReta(2).getY2());

		desenho.adicionarReta(reta3);
		Assert.assertEquals(4, desenho.getRetas().size());
		Assert.assertEquals(0, desenho.getReta(3).getX());
		Assert.assertEquals(2147483647, desenho.getReta(3).getY());
		Assert.assertEquals(0, desenho.getReta(3).getX2());
		Assert.assertEquals(0, desenho.getReta(3).getY2());

		desenho.adicionarReta(reta4);
		Assert.assertEquals(5, desenho.getRetas().size());
		Assert.assertEquals(0, desenho.getReta(4).getX());
		Assert.assertEquals(0, desenho.getReta(4).getY());
		Assert.assertEquals(2147483647, desenho.getReta(4).getX2());
		Assert.assertEquals(2147483647, desenho.getReta(4).getY2());
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
		Assert.assertEquals(2147483647, desenho.getReta(0).getX());
		Assert.assertEquals(0, desenho.getReta(0).getY());
		Assert.assertEquals(2147483647, desenho.getReta(0).getX2());
		Assert.assertEquals(2147483647, desenho.getReta(0).getY2());

		Assert.assertEquals(0, desenho.getReta(1).getX());
		Assert.assertEquals(2147483647, desenho.getReta(1).getY());
		Assert.assertEquals(0, desenho.getReta(1).getX2());
		Assert.assertEquals(0, desenho.getReta(1).getY2());
	}

	@Test
	/**
	 * Testa se é possível adicionar retângulos a lista de retângulos do desenho.
	 */
	public void testeAdicionarRetangulos() {
		Desenho desenho = new Desenho("Desenho Teste", bd);

		Retangulo retangulo = new Retangulo(0, 0, 0, 0);
		Retangulo retangulo1 = new Retangulo(0, 0, 2147483647, 2147483647);
		Retangulo retangulo2 = new Retangulo(0, 0, 200, 200);
		Retangulo retangulo3 = new Retangulo(100, 100, 200, 200);

		desenho.adicionarRetangulo(retangulo);
		Assert.assertEquals(1, desenho.getRetangulos().size());
		Assert.assertEquals(0, desenho.getRetangulo(0).getX());
		Assert.assertEquals(0, desenho.getRetangulo(0).getY());
		Assert.assertEquals(0, desenho.getRetangulo(0).getLargura());
		Assert.assertEquals(0, desenho.getRetangulo(0).getAltura());

		desenho.adicionarRetangulo(retangulo1);
		Assert.assertEquals(2, desenho.getRetangulos().size());
		Assert.assertEquals(0, desenho.getRetangulo(1).getX());
		Assert.assertEquals(0, desenho.getRetangulo(1).getY());
		Assert.assertEquals(2147483647, desenho.getRetangulo(1).getLargura());
		Assert.assertEquals(2147483647, desenho.getRetangulo(1).getAltura());

		desenho.adicionarRetangulo(retangulo2);
		Assert.assertEquals(3, desenho.getRetangulos().size());
		Assert.assertEquals(0, desenho.getRetangulo(2).getX());
		Assert.assertEquals(0, desenho.getRetangulo(2).getY());
		Assert.assertEquals(200, desenho.getRetangulo(2).getLargura());
		Assert.assertEquals(200, desenho.getRetangulo(2).getAltura());

		desenho.adicionarRetangulo(retangulo3);
		Assert.assertEquals(4, desenho.getRetangulos().size());
		Assert.assertEquals(100, desenho.getRetangulo(3).getX());
		Assert.assertEquals(100, desenho.getRetangulo(3).getY());
		Assert.assertEquals(200, desenho.getRetangulo(3).getLargura());
		Assert.assertEquals(200, desenho.getRetangulo(3).getAltura());
	}

	@Test
	/**
	 * Testa se é possível remover retângulos da lista de retângulos do desenho.
	 */
	public void testeRemoverRetangulos() {
		Desenho desenho = new Desenho("Desenho Teste", bd);

		Retangulo retangulo = new Retangulo(0, 0, 0, 0);
		Retangulo retangulo1 = new Retangulo(0, 0, 2147483647, 2147483647);
		Retangulo retangulo2 = new Retangulo(0, 0, 200, 200);
		Retangulo retangulo3 = new Retangulo(100, 100, 200, 200);
		Retangulo retangulo4 = new Retangulo(200, 200, 5, 5);

		desenho.adicionarRetangulo(retangulo);
		desenho.adicionarRetangulo(retangulo1);
		desenho.adicionarRetangulo(retangulo2);
		desenho.adicionarRetangulo(retangulo3);
		desenho.adicionarRetangulo(retangulo4);

		desenho.removerRetangulo(0);
		Assert.assertEquals(4, desenho.getRetangulos().size());

		desenho.removerRetangulo(3);
		Assert.assertEquals(3, desenho.getRetangulos().size());

		desenho.removerRetangulo(1);
		Assert.assertEquals(2, desenho.getRetangulos().size());

		// Verifica se os retângulos restantes na lista de retângulos são os esperados
		Assert.assertEquals(0, desenho.getRetangulo(0).getX());
		Assert.assertEquals(0, desenho.getRetangulo(0).getY());
		Assert.assertEquals(2147483647, desenho.getRetangulo(0).getLargura());
		Assert.assertEquals(2147483647, desenho.getRetangulo(0).getAltura());

		Assert.assertEquals(100, desenho.getRetangulo(1).getX());
		Assert.assertEquals(100, desenho.getRetangulo(1).getY());
		Assert.assertEquals(200, desenho.getRetangulo(1).getLargura());
		Assert.assertEquals(200, desenho.getRetangulo(1).getAltura());
	}
	
	@Test
	/**
	 * Testa se é possível adicionar elipses a lista de elipses do desenho.
	 */
	public void testeAdicionarElipses() {
		Desenho desenho = new Desenho("Desenho Teste", bd);

		Elipse elipse = new Elipse(0, 0, 0, 0);
		Elipse elipse1 = new Elipse(0, 0, 2147483647, 2147483647);
		Elipse elipse2 = new Elipse(0, 0, 200, 200);
		Elipse elipse3 = new Elipse(100, 100, 200, 200);

		desenho.adicionarElipse(elipse);
		Assert.assertEquals(1, desenho.getElipses().size());
		Assert.assertEquals(0, desenho.getElipse(0).getX());
		Assert.assertEquals(0, desenho.getElipse(0).getY());
		Assert.assertEquals(0, desenho.getElipse(0).getLargura());
		Assert.assertEquals(0, desenho.getElipse(0).getAltura());

		desenho.adicionarElipse(elipse1);
		Assert.assertEquals(2, desenho.getElipses().size());
		Assert.assertEquals(0, desenho.getElipse(1).getX());
		Assert.assertEquals(0, desenho.getElipse(1).getY());
		Assert.assertEquals(2147483647, desenho.getElipse(1).getLargura());
		Assert.assertEquals(2147483647, desenho.getElipse(1).getAltura());

		desenho.adicionarElipse(elipse2);
		Assert.assertEquals(3, desenho.getElipses().size());
		Assert.assertEquals(0, desenho.getElipse(2).getX());
		Assert.assertEquals(0, desenho.getElipse(2).getY());
		Assert.assertEquals(200, desenho.getElipse(2).getLargura());
		Assert.assertEquals(200, desenho.getElipse(2).getAltura());

		desenho.adicionarElipse(elipse3);
		Assert.assertEquals(4, desenho.getElipses().size());
		Assert.assertEquals(100, desenho.getElipse(3).getX());
		Assert.assertEquals(100, desenho.getElipse(3).getY());
		Assert.assertEquals(200, desenho.getElipse(3).getLargura());
		Assert.assertEquals(200, desenho.getElipse(3).getAltura());
	}
	
	@Test
	/**
	 * Testa se é possível remover elipses da lista de elipses do desenho.
	 */
	public void testeRemoverElipses() {
		Desenho desenho = new Desenho("Desenho Teste", bd);

		Elipse elipse = new Elipse(0, 0, 0, 0);
		Elipse elipse1 = new Elipse(0, 0, 2147483647, 2147483647);
		Elipse elipse2 = new Elipse(0, 0, 200, 200);
		Elipse elipse3 = new Elipse(100, 100, 200, 200);
		Elipse elipse4 = new Elipse(1, 1, 500, 500);

		desenho.adicionarElipse(elipse);
		desenho.adicionarElipse(elipse1);
		desenho.adicionarElipse(elipse2);
		desenho.adicionarElipse(elipse3);
		desenho.adicionarElipse(elipse4);
		
		desenho.removerElipse(0);
		Assert.assertEquals(4, desenho.getElipses().size());

		desenho.removerElipse(3);
		Assert.assertEquals(3, desenho.getElipses().size());

		desenho.removerElipse(1);
		Assert.assertEquals(2, desenho.getElipses().size());

		// Verifica se as elipses restantes na lista de elipses são as esperadas
		Assert.assertEquals(0, desenho.getElipse(0).getX());
		Assert.assertEquals(0, desenho.getElipse(0).getY());
		Assert.assertEquals(2147483647, desenho.getElipse(0).getLargura());
		Assert.assertEquals(2147483647, desenho.getElipse(0).getAltura());

		Assert.assertEquals(100, desenho.getElipse(1).getX());
		Assert.assertEquals(100, desenho.getElipse(1).getY());
		Assert.assertEquals(200, desenho.getElipse(1).getLargura());
		Assert.assertEquals(200, desenho.getElipse(1).getAltura());
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
