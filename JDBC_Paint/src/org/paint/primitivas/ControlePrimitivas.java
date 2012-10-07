package org.paint.primitivas;

import java.util.List;

import org.paint.bd.BancoDados;

public class ControlePrimitivas {
	private BancoDados bd;

	/**
	 * Cria o controle de primitivas. O controle de primitivas permite
	 * salvar e remover primitivas do Banco de Dados.
	 * 
	 * @param aBancoDados
	 * Conexão com o Banco de Dados.
	 */
	public ControlePrimitivas(BancoDados aBancoDados) {
		this.bd = aBancoDados;
	}

	/**
	 * Salva os respectivos pontos no Banco de Dados.
	 * @param aIdDesenho
	 * Identificador do desenho no Banco de Dados.
	 * @param aPontos
	 * Pontos a serem salvos no Banco de Dados.
	 * @return
	 * Retorna <code>true</code> caso os pontos tenham sido salvos no Banco de Dados, 
	 * e retorna <code>false</code> caso tenha acontecido um erro durante o processo
	 * de salvar os pontos.
	 */
	public boolean salvarPontos(int aIdDesenho, List<Ponto> aPontos) {
		for(Ponto ponto : aPontos) {
			String sql = "INSERT INTO pontos VALUES (" + aIdDesenho
					+ "," + ponto.getX() + "," + ponto.getY() + ");";
			
			bd.adicionarBatch(sql);
		}
		
		if(bd.executarBatch().length == aPontos.size()) {
			bd.limparListaBatch();
			
			return true;
		}
		
		return false;
	}
	
	/**
	 * Remove os respectivos pontos do Banco de Dados.
	 * @param aIdDesenho
	 * Identificador do desenho no Banco de Dados.
	 * @param aPontos
	 * Pontos a serem removidos do Banco de Dados.
	 * @return
	 * Retorna <code>true</code> caso os pontos tenham sido removidos do Banco de Dados, 
	 * e retorna <code>false</code> caso tenha acontecido um erro durante o processo
	 * de remover os pontos.
	 */
	public boolean removerPontos(int aIdDesenho, List<Ponto> aPontos) {
		for(Ponto ponto : aPontos) {
			String sql = "DELETE FROM pontos" +
					 " WHERE id_desenho = " + aIdDesenho +
					 " AND pos_x = " + ponto.getX() +
					 " AND pos_y = " + ponto.getY();
			
			bd.adicionarBatch(sql);
		}
		
		if(bd.executarBatch().length == aPontos.size()) {
			bd.limparListaBatch();
			
			return true;
		}

		return false;
	}
	
	/**
	 * Salva as respectivas retas no Banco de Dados.
	 * @param aIdDesenho
	 * Identificador do desenho no Banco de Dados.
	 * @param aRetas
	 * Lista de retas a serem salvas no Banco de Dados.
	 * @return
	 * Retorna <code>true</code> caso as retas tenham sido salvas no Banco de Dados, 
	 * e retorna <code>false</code> caso tenha acontecido um erro durante o processo
	 * de salvar as retas.
	 */
	public boolean salvarRetas(int aIdDesenho, List<Reta> aRetas) {
		for(Reta reta : aRetas) {
			String sql = "INSERT INTO retas VALUES (" + aIdDesenho +
					"," + reta.getX1() + "," + reta.getY1() + "," + reta.getX2() + 
					"," + reta.getY2() + ");";
			
			bd.adicionarBatch(sql);
		}
		
		if(bd.executarBatch().length == aRetas.size()) {
			bd.limparListaBatch();
			
			return true;
		}
		
		return false;
	}
	
	/**
	 * Remove as respectivas retas do Banco de Dados.
	 * @param aIdDesenho
	 * Indice do desenho no Banco de Dados.
	 * @param aRetas
	 * Lista de retas a serem removidas do Banco de Dados.
	 * @return
	 * Retorna <code>true</code> caso as retas tenham sido removidas do Banco de Dados, 
	 * e retorna <code>false</code> caso tenha acontecido um erro durante o processo
	 * de remover as retas.
	 */
	public boolean removerRetas(int aIdDesenho, List<Reta> aRetas) {
		for(Reta reta : aRetas) {
			String sql = "DELETE FROM retas" +
						 " WHERE id_desenho = " + aIdDesenho +
						 " AND pos_x1 = " + reta.getX1() +
						 " AND pos_y1 = " + reta.getY1() +
						 " AND pos_x2 = " + reta.getX2() +
						 " AND pos_y2 = " + reta.getY2();
			
			bd.adicionarBatch(sql);
		}
		
		if(bd.executarBatch().length == aRetas.size()) {
			bd.limparListaBatch();
			
			return true;
		}
		
		return false;
	}
}
