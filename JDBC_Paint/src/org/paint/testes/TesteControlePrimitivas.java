package org.paint.testes;

import java.sql.ResultSet;
import java.sql.SQLException;

import junit.framework.Assert;

import org.junit.Before;
import org.junit.Test;
import org.paint.bd.BancoDados;
import org.paint.desenho.Desenho;
import org.paint.primitivas.ControlePrimitivas;
import org.paint.primitivas.Elipse;
import org.paint.primitivas.Ponto;
import org.paint.primitivas.Reta;
import org.paint.primitivas.Retangulo;

public class TesteControlePrimitivas {
	private BancoDados bd;
	private ControlePrimitivas controlePrimitivas;

	@Before
	public void inicilizacao() {
		bd = new BancoDados("jdbc:mysql://localhost/paint", "root", "root");

		controlePrimitivas = new ControlePrimitivas(bd);
	}

	@Test
	/**
	 * Testa se é possível salvar e remover os pontos do Banco de Dados.
	 */
	public void testeSalvarRemoverPontos() {
		Desenho desenho = new Desenho("Desenho Teste", bd);

		Ponto p = new Ponto(0, 0);
		Ponto p1 = new Ponto(2147483647, 0);
		Ponto p2 = new Ponto(0, 2147483647);
		Ponto p3 = new Ponto(2147483647, 2147483647);

		desenho.adicionarPonto(p);
		desenho.adicionarPonto(p1);
		desenho.adicionarPonto(p2);
		desenho.adicionarPonto(p3);

		// Salvar o desenho no Banco de Dados
		desenho.salvarDesenhoEmBranco();

		controlePrimitivas.salvarPontos(desenho.getIdDesenho(),
				desenho.getPontos());

		// Adquire a quantidade de pontos do Banco de Dados
		String sql = "SELECT COUNT(id_desenho) FROM pontos WHERE id_desenho = "
				+ desenho.getIdDesenho();
		int quantidadePontos = 0;

		if (bd.executarSQL(sql)) {
			try {
				// Adquire o resultado
				ResultSet resultado = bd.getResultSet();

				resultado.next();

				quantidadePontos = resultado.getInt(1);
			} catch (SQLException sqlEx) {
				sqlEx.printStackTrace();
			}
		}

		Assert.assertEquals(4, quantidadePontos);

		controlePrimitivas.removerPontos(desenho.getIdDesenho(),
				desenho.getPontos());

		// Adquire a quantidade de pontos do Banco de Dados
		sql = "SELECT COUNT(id_desenho) FROM pontos WHERE id_desenho = "
				+ desenho.getIdDesenho();
		quantidadePontos = 0;

		if (bd.executarSQL(sql)) {
			try {
				// Adquire o resultado
				ResultSet resultado = bd.getResultSet();

				resultado.next();

				quantidadePontos = resultado.getInt(1);
			} catch (SQLException sqlEx) {
				sqlEx.printStackTrace();
			}
		}
		
		Assert.assertEquals(0, quantidadePontos);
		
		desenho.removerDesenhoEmBranco();
	}

	@Test
	/**
	 * Testa se é possível salvar e remover retas do Banco de Dados.
	 */
	public void testeSalvarRemoverRetas() {
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

		// Salvar o desenho no Banco de Dados
		desenho.salvarDesenhoEmBranco();

		controlePrimitivas.salvarRetas(desenho.getIdDesenho(),
				desenho.getRetas());

		// Adquire a quantidade de retas do Banco de Dados
		String sql = "SELECT COUNT(id_desenho) FROM retas WHERE id_desenho = "
				+ desenho.getIdDesenho();
		int quantidadeRetas = 0;

		if (bd.executarSQL(sql)) {
			try {
				// Adquire o resultado
				ResultSet resultado = bd.getResultSet();

				resultado.next();

				quantidadeRetas = resultado.getInt(1);
			} catch (SQLException sqlEx) {
				sqlEx.printStackTrace();
			}
		}

		Assert.assertEquals(5, quantidadeRetas);

		controlePrimitivas.removerRetas(desenho.getIdDesenho(),
				desenho.getRetas());

		// Adquire a quantidade de retas do Banco de Dados
		sql = "SELECT COUNT(id_desenho) FROM retas WHERE id_desenho = "
				+ desenho.getIdDesenho();
		quantidadeRetas = 0;

		if (bd.executarSQL(sql)) {
			try {
				// Adquire o resultado
				ResultSet resultado = bd.getResultSet();

				resultado.next();

				quantidadeRetas = resultado.getInt(1);
			} catch (SQLException sqlEx) {
				sqlEx.printStackTrace();
			}
		}
		
		Assert.assertEquals(0, quantidadeRetas);
		
		desenho.removerDesenhoEmBranco();
	}
	
	@Test
	/**
	 * Teste se é possível salvar e remover retângulos do Banco de Dados.
	 */
	public void testeSalvarRemoverRetangulos() {
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

		// Salvar o desenho no Banco de Dados
		desenho.salvarDesenhoEmBranco();

		controlePrimitivas.salvarRetangulos(desenho.getIdDesenho(),
				desenho.getRetangulos());

		// Adquire a quantidade de retas do Banco de Dados
		String sql = "SELECT COUNT(id_desenho) FROM retangulos WHERE id_desenho = "
				+ desenho.getIdDesenho();
		int quantidadeRetangulos = 0;

		if (bd.executarSQL(sql)) {
			try {
				// Adquire o resultado
				ResultSet resultado = bd.getResultSet();

				resultado.next();

				quantidadeRetangulos = resultado.getInt(1);
			} catch (SQLException sqlEx) {
				sqlEx.printStackTrace();
			}
		}

		Assert.assertEquals(5, quantidadeRetangulos);

		controlePrimitivas.removerRetangulos(desenho.getIdDesenho(),
				desenho.getRetangulos());

		// Adquire a quantidade de retas do Banco de Dados
		sql = "SELECT COUNT(id_desenho) FROM retangulos WHERE id_desenho = "
				+ desenho.getIdDesenho();
		quantidadeRetangulos = 0;

		if (bd.executarSQL(sql)) {
			try {
				// Adquire o resultado
				ResultSet resultado = bd.getResultSet();

				resultado.next();

				quantidadeRetangulos = resultado.getInt(1);
			} catch (SQLException sqlEx) {
				sqlEx.printStackTrace();
			}
		}
		
		Assert.assertEquals(0, quantidadeRetangulos);
		
		desenho.removerDesenhoEmBranco();
	}
	
	@Test
	/**
	 * Testa se é possível salvar e remover elipses do Banco de Dados.
	 */
	public void testeSalvarRemoverElipses() {
		Desenho desenho = new Desenho("Desenho Teste", bd);

		Elipse retangulo = new Elipse(0, 0, 0, 0);
		Elipse retangulo1 = new Elipse(0, 0, 2147483647, 2147483647);
		Elipse retangulo2 = new Elipse(0, 0, 200, 200);
		Elipse retangulo3 = new Elipse(100, 100, 200, 200);
		Elipse retangulo4 = new Elipse(200, 200, 5, 5);

		desenho.adicionarElipse(retangulo);
		desenho.adicionarElipse(retangulo1);
		desenho.adicionarElipse(retangulo2);
		desenho.adicionarElipse(retangulo3);
		desenho.adicionarElipse(retangulo4);

		// Salvar o desenho no Banco de Dados
		desenho.salvarDesenhoEmBranco();

		controlePrimitivas.salvarElipses(desenho.getIdDesenho(),
				desenho.getElipses());

		// Adquire a quantidade de retas do Banco de Dados
		String sql = "SELECT COUNT(id_desenho) FROM elipses WHERE id_desenho = "
				+ desenho.getIdDesenho();
		int quantidadeElipses = 0;

		if (bd.executarSQL(sql)) {
			try {
				// Adquire o resultado
				ResultSet resultado = bd.getResultSet();

				resultado.next();

				quantidadeElipses = resultado.getInt(1);
			} catch (SQLException sqlEx) {
				sqlEx.printStackTrace();
			}
		}

		Assert.assertEquals(5, quantidadeElipses);

		controlePrimitivas.removerElipses(desenho.getIdDesenho(),
				desenho.getElipses());

		// Adquire a quantidade de retas do Banco de Dados
		sql = "SELECT COUNT(id_desenho) FROM elipses WHERE id_desenho = "
				+ desenho.getIdDesenho();
		quantidadeElipses = 0;

		if (bd.executarSQL(sql)) {
			try {
				// Adquire o resultado
				ResultSet resultado = bd.getResultSet();

				resultado.next();

				quantidadeElipses = resultado.getInt(1);
			} catch (SQLException sqlEx) {
				sqlEx.printStackTrace();
			}
		}
		
		Assert.assertEquals(0, quantidadeElipses);
		
		desenho.removerDesenhoEmBranco();
	}
}
