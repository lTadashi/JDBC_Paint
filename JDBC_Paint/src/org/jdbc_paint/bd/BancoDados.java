package org.jdbc_paint.bd;

import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.JOptionPane;

import com.mysql.jdbc.Connection;
import com.mysql.jdbc.Statement;

public class BancoDados {
	private Connection conexao;
	private Statement statement;

	/**
	 * Cria a conex�o com o Banco de Dados.
	 * 
	 * @param aUrl
	 *            URL para conex�o com o banco (ex:
	 *            jdbc:mysql://localhost/banco_de_dados)
	 * @param aUsuario
	 *            Usu�rio para a conex�o com o Banco de Dados
	 * @param aSenha
	 *            Senha para a conex�o com o Banco de Dados
	 */
	public BancoDados(String aUrl, String aUsuario, String aSenha) {
		try {
			Class.forName("com.mysql.jdbc.Driver");
			conexao = (Connection) DriverManager.getConnection(aUrl, aUsuario,
					aSenha);
			statement = (Statement) conexao.createStatement();
		} catch (ClassNotFoundException classNotFound) {
			JOptionPane.showMessageDialog(null, classNotFound.getMessage());
		} catch (SQLException sqlException) {
			JOptionPane.showMessageDialog(null, sqlException.getMessage());
		} catch (Exception ex) {
			JOptionPane.showMessageDialog(null, ex.getMessage());
		}
	}

	/**
	 * Executa um comando SQL.
	 * 
	 * @param aSql
	 *            Comando SQL a ser executado.
	 * @return Retorna <code>true</code> caso o comando SQL tenha retornado um
	 *         ResultSet, e retorna <code>false</code> caso o comando SQL tenha
	 *         retornado um n�mero de atualiza��es ou n�o h� resultados.
	 */
	public boolean executarSQL(String aSql) {
		try {
			return statement.execute(aSql);
		} catch (SQLException sqlEx) {
			JOptionPane.showMessageDialog(null, sqlEx.getMessage());
		}

		return false;
	}

	/**
	 * Adiciona um comando SQL a uma lista de comandos, a lista Batch.
	 * 
	 * @param aSql
	 *            Comando SQL a ser adicionado a lista.
	 */
	public void adicionarBatch(String aSql) {
		try {
			statement.addBatch(aSql);
		} catch (SQLException sqlEx) {
			JOptionPane.showMessageDialog(null, sqlEx.getMessage());
		}
	}

	/**
	 * Executa os comandos SQL na lista Batch.
	 * 
	 * @return Retorna um array com a contagem de atualiza��es feitas no Banco
	 *         de Dados para cada comando SQL na lista Batch. A ordem de cada
	 *         contagem retornada � a ordem em que os comandos SQL foram
	 *         adicionados a lista Batch.
	 */
	public int[] executarBatch() {
		try {
			return statement.executeBatch();
		} catch (SQLException sqlEx) {
			JOptionPane.showMessageDialog(null, sqlEx.getMessage());
		}

		return null;
	}

	/**
	 * Limpa a lista Batch.
	 */
	public void limparListaBatch() {
		try {
			statement.clearBatch();
		} catch (SQLException sqlEx) {
			JOptionPane.showMessageDialog(null, sqlEx.getMessage());
		}
	}

	/**
	 * Adquire o ResultSet.
	 * 
	 * @return Retorna o <code>ResultSet</code>, ou <code>null</code> caso n�o
	 *         tenha sido retornado nenhum <code>ResultSet</code> em uma
	 *         execu��o de comando SQL passada.
	 */
	public ResultSet getResultSet() {
		try {
			return statement.getResultSet();
		} catch (SQLException sqlEx) {
			JOptionPane.showMessageDialog(null, sqlEx.getMessage());
		}

		return null;
	}

	/**
	 * Adquire o n�mero de linhas atualizadas por um comando SQL executado.
	 * 
	 * @return Retorna o n�mero de linhas atualizadas por um comand SQL
	 *         executado. Ou retorna -1 caso tenha ocorrido algum erro.
	 */
	public int getNumLinhasAfetadas() {
		try {
			return statement.getUpdateCount();
		} catch (SQLException sqlEx) {
			JOptionPane.showMessageDialog(null, sqlEx.getMessage());
		}

		return -1;
	}
}
