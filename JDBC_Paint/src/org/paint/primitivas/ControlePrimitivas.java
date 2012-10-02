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
	 * 
	 * @param aPontos
	 * Pontos a serem salvos no Banco de Dados.
	 * @return
	 * Retorna <code>true</code> caso os pontos tenham sido salvos no Banco de Dados, 
	 * e retorna <code>false</code> caso tenha acontecido um erro durante o processo
	 * de salvar os pontos.
	 */
	public boolean salvarPontos(List<Ponto> aPontos) {
		for(Ponto ponto : aPontos) {
			String sql = "INSERT INTO pontos" + " VALUES (" + ponto.getDesenho()
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
	 * 
	 * @param aPontos
	 * Pontos a serem removidos do Banco de Dados.
	 * @return
	 * Retorna <code>true</code> caso os pontos tenham sido removidos do Banco de Dados, 
	 * e retorna <code>false</code> caso tenha acontecido um erro durante o processo
	 * de remover os pontos.
	 */
	public boolean removerPontos(List<Ponto> aPontos) {
		for(Ponto ponto : aPontos) {
			String sql = "DELETE FROM pontos" +
					 " WHERE id_desenho = " + ponto.getDesenho() +
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
}
