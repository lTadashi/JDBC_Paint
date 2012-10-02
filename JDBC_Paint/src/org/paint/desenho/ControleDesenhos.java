package org.paint.desenho;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import javax.swing.JOptionPane;

import org.paint.bd.BancoDados;
import org.paint.primitivas.ControlePrimitivas;
import org.paint.primitivas.Ponto;

public class ControleDesenhos {
	private BancoDados bd;
	private ControlePrimitivas controlePrimitivas;

	/**
	 * Cria o Controle de Desenhos. O Controle de Desenhos permite salvar e
	 * remover desenhos inteiros, ou primitivas de um respectivo desenho do
	 * Banco de Dados.
	 */
	public ControleDesenhos() {
		// Faz conexão com o Banco de Dados
		this.bd = new BancoDados("jdbc:mysql://localhost/paint", "root", "root");

		// Inicializa o controle de primitivas
		controlePrimitivas = new ControlePrimitivas(bd);
	}

	/**
	 * Salva o respectivo desenho no Banco de Dados.
	 * 
	 * @param aDesenho
	 *            Desenho a ser salvo no Banco de Dados.
	 * @return Retorna o <code>id</code> do desenho, ou -1 caso tenha acontecido
	 *         algum erro.
	 */
	public int salvarDesenho(Desenho aDesenho) {
		String sql = "INSERT INTO desenhos (nome_desenho) VALUES ('"
				+ aDesenho.getNomeDesenho() + "');";

		if (!bd.executarSQL(sql)) {
			if (bd.getNumLinhasAfetadas() == 1) {
				// Adquire o id do desenho
				sql = "SELECT MAX(id) as id" + " FROM desenhos";

				if (bd.executarSQL(sql)) {
					try {
						// Adquire o resultado
						ResultSet resultado = bd.getResultSet();

						// Vai para a segunda linha do resultado
						resultado.next();
						
						// Retorna o id do desenho
						return resultado.getInt(1);
					} catch (SQLException sqlEx) {
						JOptionPane.showMessageDialog(null, sqlEx.getMessage());
					}
				}
				
				// TODO Salvar as primitivas do desenho
			}
		}

		return -1;
	}

	/**
	 * Remove o desenho do Banco de Dados.
	 * 
	 * @param aDesenho
	 * Desenho a ser removido.
	 * @return
	 * Retorna <code>true</code> caso o desenho tenha sido removido do Banco de Dados, e
	 * retorna <code>false</code> caso tenha acontecido um erro.
	 */
	public boolean removerDesenho(Desenho aDesenho) {
		String sql = "DELETE FROM desenhos " + "WHERE id = "
				+ aDesenho.getIdDesenho() + ";";
        
		if (!bd.executarSQL(sql)) {
			if (bd.getNumLinhasAfetadas() == 1) {
				// TODO Remover as primitivas do desenho
				
				return true;
			}
		}

		return false;
	}

	/**
	 * Salva os respectivos pontos no Banco de Dados.
	 * 
	 * @param aPontos
	 *            Pontos a serem salvos no Banco de Dados.
	 * @return Retorna <code>true</code> caso os pontos tenham sido salvos no
	 *         Banco de Dados, e retorna <code>false</code> caso tenha
	 *         acontecido um erro durante o processo de salvar os pontos.
	 */
	public boolean salvarPontos(List<Ponto> aPontos) {
		return controlePrimitivas.salvarPontos(aPontos);
	}

	/**
	 * Remove os respectivos pontos do Banco de Dados.
	 * 
	 * @param aPontos
	 *            Pontos a serem removidos do Banco de Dados.
	 * @return Retorna <code>true</code> caso os pontos tenham sido removidos do
	 *         Banco de Dados, e retorna <code>false</code> caso tenha
	 *         acontecido um erro durante o processo de remover os pontos.
	 */
	public boolean removerPontos(List<Ponto> aPontos) {
		return controlePrimitivas.removerPontos(aPontos);
	}
	
	/******************************************************************************************/
	/********************************** Getters e Setters *************************************/
	/******************************************************************************************/
	public Desenho getDesenho(int aIdDesenho) {
		String sql = "SELECT * FROM desenhos WHERE id = " + aIdDesenho + ";";
		
		if(bd.executarSQL(sql)) {
			ResultSet resultado = bd.getResultSet();
			
			try {
				// Vai para a primeira linha do resultado
				resultado.next();
				
				int id = resultado.getInt(1);
				String nome = resultado.getString(2);
				
				Desenho novoDesenho = new Desenho(nome);
				novoDesenho.setIdDesenho(id);
				
				// TODO Adquirir as primitivas do desenho
				
				return novoDesenho;
			} catch(SQLException sqlEx) {
				JOptionPane.showMessageDialog(null, sqlEx.getMessage());
			}
		}
		
		return null;
	}
}
