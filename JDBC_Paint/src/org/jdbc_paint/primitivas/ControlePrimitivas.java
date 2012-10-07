package org.jdbc_paint.primitivas;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.jdbc_paint.bd.BancoDados;

public class ControlePrimitivas {
	private BancoDados bd;

	/**
	 * Cria o controle de primitivas. O controle de primitivas permite salvar e
	 * remover primitivas do Banco de Dados.
	 * 
	 * @param aBancoDados
	 *            Conexão com o Banco de Dados.
	 */
	public ControlePrimitivas(BancoDados aBancoDados) {
		this.bd = aBancoDados;
	}

	/**
	 * Salva os respectivos pontos no Banco de Dados.
	 * 
	 * @param aIdDesenho
	 *            Identificador do desenho no Banco de Dados.
	 * @param aPontos
	 *            Pontos a serem salvos no Banco de Dados.
	 * @return Retorna <code>true</code> caso os pontos tenham sido salvos no
	 *         Banco de Dados, e retorna <code>false</code> caso tenha
	 *         acontecido um erro durante o processo de salvar os pontos.
	 */
	public boolean salvarPontos(int aIdDesenho, List<Ponto> aPontos) {
		for (Ponto ponto : aPontos) {
			String sql = "INSERT INTO pontos VALUES (" + aIdDesenho + ","
					+ ponto.getX() + "," + ponto.getY() + ");";

			bd.adicionarBatch(sql);
		}

		if (bd.executarBatch().length == aPontos.size()) {
			bd.limparListaBatch();

			return true;
		}

		return false;
	}

	/**
	 * Remove os respectivos pontos do Banco de Dados.
	 * 
	 * @param aIdDesenho
	 *            Identificador do desenho no Banco de Dados.
	 * @param aPontos
	 *            Pontos a serem removidos do Banco de Dados.
	 * @return Retorna <code>true</code> caso os pontos tenham sido removidos do
	 *         Banco de Dados, e retorna <code>false</code> caso tenha
	 *         acontecido um erro durante o processo de remover os pontos.
	 */
	public boolean removerPontos(int aIdDesenho, List<Ponto> aPontos) {
		for (Ponto ponto : aPontos) {
			String sql = "DELETE FROM pontos" + " WHERE id_desenho = "
					+ aIdDesenho + " AND pos_x = " + ponto.getX()
					+ " AND pos_y = " + ponto.getY() + ";";

			bd.adicionarBatch(sql);
		}

		if (bd.executarBatch().length == aPontos.size()) {
			bd.limparListaBatch();

			return true;
		}

		return false;
	}

	/**
	 * Salva as respectivas retas no Banco de Dados.
	 * 
	 * @param aIdDesenho
	 *            Identificador do desenho no Banco de Dados.
	 * @param aRetas
	 *            Lista de retas a serem salvas no Banco de Dados.
	 * @return Retorna <code>true</code> caso as retas tenham sido salvas no
	 *         Banco de Dados, e retorna <code>false</code> caso tenha
	 *         acontecido um erro durante o processo de salvar as retas.
	 */
	public boolean salvarRetas(int aIdDesenho, List<Reta> aRetas) {
		for (Reta reta : aRetas) {
			String sql = "INSERT INTO retas VALUES (" + aIdDesenho + ","
					+ reta.getX() + "," + reta.getY() + "," + reta.getX2()
					+ "," + reta.getY2() + ");";

			bd.adicionarBatch(sql);
		}

		if (bd.executarBatch().length == aRetas.size()) {
			bd.limparListaBatch();

			return true;
		}

		return false;
	}

	/**
	 * Remove as respectivas retas do Banco de Dados.
	 * 
	 * @param aIdDesenho
	 *            Indice do desenho no Banco de Dados.
	 * @param aRetas
	 *            Lista de retas a serem removidas do Banco de Dados.
	 * @return Retorna <code>true</code> caso as retas tenham sido removidas do
	 *         Banco de Dados, e retorna <code>false</code> caso tenha
	 *         acontecido um erro durante o processo de remover as retas.
	 */
	public boolean removerRetas(int aIdDesenho, List<Reta> aRetas) {
		for (Reta reta : aRetas) {
			String sql = "DELETE FROM retas" + " WHERE id_desenho = "
					+ aIdDesenho + " AND pos_x1 = " + reta.getX()
					+ " AND pos_y1 = " + reta.getY() + " AND pos_x2 = "
					+ reta.getX2() + " AND pos_y2 = " + reta.getY2() + ";";

			bd.adicionarBatch(sql);
		}

		if (bd.executarBatch().length == aRetas.size()) {
			bd.limparListaBatch();

			return true;
		}

		return false;
	}

	/**
	 * Salva os respectivos retângulos no Banco de Dados.
	 * 
	 * @param aIdDesenho
	 *            Identificar do desenho no Banco de Dados.
	 * @param aRetangulos
	 *            Lista de retângulos a serem salvos no Banco de Dados.
	 * @return Retorna <code>true</code> caso os retângulos tenham sido salvos
	 *         no Banco de Dados, e retorna <code>false</code> caso tenha
	 *         acontecido um erro durante o processo de salvar os retângulos.
	 */
	public boolean salvarRetangulos(int aIdDesenho, List<Retangulo> aRetangulos) {
		for (Retangulo retangulo : aRetangulos) {
			String sql = "INSERT INTO retangulos VALUES (" + aIdDesenho + ","
					+ retangulo.getX() + "," + retangulo.getY() + ","
					+ retangulo.getLargura() + "," + retangulo.getAltura()
					+ ");";

			bd.adicionarBatch(sql);
		}

		if (bd.executarBatch().length == aRetangulos.size()) {
			bd.limparListaBatch();

			return true;
		}

		return false;
	}

	/**
	 * Remove os respectivos retângulos do Banco de Dados.
	 * 
	 * @param aIdDesenho
	 *            Identificador do desenho no Banco de Dados.
	 * @param aRetangulos
	 *            Lista de retângulos a serem removidos do Banco de Dados.
	 * @return Retorna <code>true</code> caso os retângulos tenham sido
	 *         removidos do Banco de Dados, e retorna <code>false</code> caso
	 *         tenha acontecido um erro durante o processo de remover os
	 *         retângulos.
	 */
	public boolean removerRetangulos(int aIdDesenho, List<Retangulo> aRetangulos) {
		for (Retangulo retangulo : aRetangulos) {
			String sql = "DELETE FROM retangulos" + " WHERE id_desenho = "
					+ aIdDesenho + " AND pos_x = " + retangulo.getX()
					+ " AND pos_y = " + retangulo.getY() + " AND largura = "
					+ retangulo.getLargura() + " AND altura = "
					+ retangulo.getAltura() + ";";

			bd.adicionarBatch(sql);
		}

		if (bd.executarBatch().length == aRetangulos.size()) {
			bd.limparListaBatch();

			return true;
		}

		return false;
	}

	/**
	 * Salva as respectivas elipses no Banco de Dados.
	 * 
	 * @param aIdDesenho
	 *            Indentificador do desenho no Banco de Dados.
	 * @param aElipses
	 *            Lista de elipses a serem salvas no Banco de Dados.
	 * @return Retorna <code>true</code> caso as elipses tenham sido salvas no
	 *         Banco de Dados, e retorna <code>false</code> caso tenha
	 *         acontecido um erro durante o processo de salvar as elipses.
	 * 
	 */
	public boolean salvarElipses(int aIdDesenho, List<Elipse> aElipses) {
		for (Elipse elipse : aElipses) {
			String sql = "INSERT INTO elipses VALUES (" + aIdDesenho + ","
					+ elipse.getX() + "," + elipse.getY() + ","
					+ elipse.getLargura() + "," + elipse.getAltura() + ");";

			bd.adicionarBatch(sql);
		}

		if (bd.executarBatch().length == aElipses.size()) {
			bd.limparListaBatch();

			return true;
		}

		return false;
	}

	/**
	 * Remove as respectivas elipses do Banco de Dados.
	 * 
	 * @param aIdDesenho
	 *            Indentificar do desenho no Banco de Dados.
	 * @param aElipses
	 *            Lista de elipses a serem removidas do Banco de Dados.
	 * @return Retorna <code>true</code> caso as elipses tenham sido removidas
	 *         do Banco de Dados, e retorna <code>false</code> caso tenha
	 *         acontecido um erro durante o processo de remover as elipses.
	 */
	public boolean removerElipses(int aIdDesenho, List<Elipse> aElipses) {
		for (Elipse elipse : aElipses) {
			String sql = "DELETE FROM elipses" + " WHERE id_desenho = "
					+ aIdDesenho + " AND pos_x = " + elipse.getX()
					+ " AND pos_y = " + elipse.getY() + " AND largura = "
					+ elipse.getLargura() + " AND altura = "
					+ elipse.getAltura() + ";";

			bd.adicionarBatch(sql);
		}

		if (bd.executarBatch().length == aElipses.size()) {
			bd.limparListaBatch();

			return true;
		}

		return false;
	}

	/******************************************************************************************/
	/********************************** Getters e Setters *************************************/
	/******************************************************************************************/
	/**
	 * Retorna uma lista de pontos de um determinado desenho.
	 * 
	 * @param aIdDesenho
	 *            Identificador do desenho no Banco de Dados que deseja-se
	 *            adquirir os pontos
	 * @return Retorna a lista de pontos do desenho ou null caso não seja
	 *         encontrado nenhum ponto.
	 * 
	 */
	public List<Ponto> getPontosBD(int aIdDesenho) {
		String sql = "SELECT * FROM pontos WHERE id_desenho = " + aIdDesenho
				+ ";";

		if (bd.executarSQL(sql)) {
			try {
				ResultSet resultado = bd.getResultSet();
				List<Ponto> pontosBD = new ArrayList<Ponto>();

				while (resultado.next()) {
					Ponto novoPonto = new Ponto(resultado.getInt(2),
							resultado.getInt(3));
					pontosBD.add(novoPonto);
				}

				return pontosBD;
			} catch (SQLException sqlEx) {
				sqlEx.printStackTrace();
			}
		}

		return null;
	}

	/**
	 * Adquire as retas de um respectivo desenho do Banco de Dados.
	 * 
	 * @param aIdDesenho
	 *            Identificar do desenho no Banco de Dados que deseja-se
	 *            adquirir as retas.
	 * @return Retorna a lista de retas do respectivo desenho, ou null caso não
	 *         seja encontrada nenhuma reta.
	 */
	public List<Reta> getRetasBD(int aIdDesenho) {
		String sql = "SELECT * FROM retas WHERE id_desenho = " + aIdDesenho
				+ ";";

		if (bd.executarSQL(sql)) {
			try {
				ResultSet resultado = bd.getResultSet();
				List<Reta> retasBD = new ArrayList<Reta>();

				while (resultado.next()) {
					Reta novaReta = new Reta(resultado.getInt(2),
							resultado.getInt(3), resultado.getInt(4),
							resultado.getInt(5));

					retasBD.add(novaReta);
				}

				return retasBD;
			} catch (SQLException sqlEx) {
				sqlEx.printStackTrace();
			}
		}

		return null;
	}

	/**
	 * Adquire os retângulos de um determinado desenho do Banco de Dados.
	 * 
	 * @param aIdDesenho
	 *            Identificador do desenho no Banco de Dados que deseja-se
	 *            adquirir os retângulos.
	 * @return Retorna a lista de retângulos do desenho, ou retorna null caso
	 *         nenhum retângulo tenha sido encontrado.
	 */
	public List<Retangulo> getRetangulosBD(int aIdDesenho) {
		String sql = "SELECT * FROM retangulos WHERE id_desenho = "
				+ aIdDesenho + ";";

		if (bd.executarSQL(sql)) {
			try {
				ResultSet resultado = bd.getResultSet();
				List<Retangulo> retangulosBD = new ArrayList<Retangulo>();

				while (resultado.next()) {
					Retangulo novoRetangulo = new Retangulo(
							resultado.getInt(2), resultado.getInt(3),
							resultado.getInt(4), resultado.getInt(5));

					retangulosBD.add(novoRetangulo);
				}

				return retangulosBD;
			} catch (SQLException sqlEx) {
				sqlEx.printStackTrace();
			}
		}

		return null;
	}

	/**
	 * Adquire as elipses de um determinado desenho do Banco de Dados.
	 * 
	 * @param aIdDesenho
	 *            Identificador do desenho no Banco de Dados que deseja-se
	 *            adquirir as elipses.
	 * @return Retorna a lista de elipses do desenho, ou null caso não tenha
	 *         sido encontrada nenhuma elipse.
	 */
	public List<Elipse> getElipsesBD(int aIdDesenho) {
		String sql = "SELECT * FROM elipses WHERE id_desenho = " + aIdDesenho
				+ ";";

		if (bd.executarSQL(sql)) {
			try {
				ResultSet resultado = bd.getResultSet();
				List<Elipse> elipsesBD = new ArrayList<Elipse>();

				while (resultado.next()) {
					Elipse novaElipse = new Elipse(resultado.getInt(2),
							resultado.getInt(3), resultado.getInt(4),
							resultado.getInt(5));

					elipsesBD.add(novaElipse);
				}

				return elipsesBD;
			} catch (SQLException sqlEx) {
				sqlEx.printStackTrace();
			}
		}

		return null;
	}
}
