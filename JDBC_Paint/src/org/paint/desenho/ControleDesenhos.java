package org.paint.desenho;

import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.JOptionPane;

import org.paint.bd.BancoDados;

public class ControleDesenhos {
	private BancoDados bd;

	/**
	 * Cria o Controle de Desenhos. O Controle de Desenhos permite salvar e
	 * remover desenhos inteiros, ou primitivas de um respectivo desenho do
	 * Banco de Dados.
	 */
	public ControleDesenhos() {
		// Faz conexão com o Banco de Dados
		this.bd = new BancoDados("jdbc:mysql://localhost/paint", "root", "root");
	}

	/**
	 * Salva o respectivo desenho em branco no Banco de Dados. Isto é, salva as informações do desenho,
	 * mas não o que está desenhado nele.
	 * 
	 * @param aDesenho
	 *            Desenho a ser salvo no Banco de Dados.
	 * @return Retorna o <code>id</code> do desenho, ou -1 caso tenha acontecido
	 *         algum erro.
	 */
	public int salvarDesenhoEmBranco(Desenho aDesenho) {
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
			}
		}

		return -1;
	}

	/**
	 * Remove o desenho em branco do Banco de Dados. Isto é, remove as informações do desenho,
	 * mas não o que está desenhado nele.
	 * 
	 * @param aDesenho
	 * Desenho a ser removido.
	 * @return
	 * Retorna <code>true</code> caso o desenho tenha sido removido do Banco de Dados, e
	 * retorna <code>false</code> caso tenha acontecido um erro.
	 */
	public boolean removerDesenhoEmBranco(Desenho aDesenho) {
		String sql = "DELETE FROM desenhos " + "WHERE id = "
				+ aDesenho.getIdDesenho() + ";";
        
		if (!bd.executarSQL(sql)) {
			if (bd.getNumLinhasAfetadas() == 1) {
				return true;
			}
		}

		return false;
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

				return novoDesenho;
			} catch(SQLException sqlEx) {
				JOptionPane.showMessageDialog(null, sqlEx.getMessage());
			}
		}
		
		return null;
	}
}
