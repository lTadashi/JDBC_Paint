package org.paint.desenho;

import java.util.ArrayList;
import java.util.List;

import org.paint.primitivas.Ponto;

public class Desenho {
	private int idDesenho;
	private String nomeDesenho;
	
	// Conexão com o Banco de Dados
	
	// Controle
	private ControleDesenhos controleDesenho;
	
	// Primitivas
	private List<Ponto> pontos;
	
	/**
	 * Cria um novo desenho.
	 * 
	 * @param aNomeDesenho
	 * Nome do desenho.
	 */
	public Desenho(String aNomeDesenho) {
		this.nomeDesenho = aNomeDesenho;
		this.idDesenho = 0;
		
		// Inicializa o controle de desenho
		controleDesenho = new ControleDesenhos();
		
		// Inicializa as primitivas
		pontos = new ArrayList<Ponto>();
	}
	
	/**
	 * Salva o desenho no Banco de Dados.
	 * 
	 * @return
	 * Retorna <code>true</code> caso o desenho seja salvo no Banco de Dados, 
	 * e retorna <code>false</code> caso ocorra algum erro.
	 */
	public boolean salvarDesenho() {
		// Salva o desenho
		idDesenho = controleDesenho.salvarDesenho(this);
		
		// TODO adicionar todas as primitivas
		// Salva as primitivas
		if(salvarTodosPontos()) {
			return true;
		}
		
		return false;
	}
	
	/**
	 * Remove o desenho do Banco de Dados.
	 * 
	 * @return
	 * Retorna <code>true</code> caso o desenho tenha sido removido do Banco de Dados, 
	 * e retorna <code>false</code> caso ocorra algum erro.
	 */
	public boolean removerDesenho() {
		// TODO adicionar todas as primitivas
		// Remove as primitivas
		if(removerTodosPontos()) {
			return true;
		}
		
		return false;
	}
	
	/**
	 * Adiciona o respectivo ponto ao desenho.
	 * 
	 * @param aPonto
	 * Ponto a ser adicionado a o desenho.
	 */
	public void adicionarPonto(Ponto aPonto) {
		// Define este desenho como dono do ponto
		aPonto.setDesenho(this.idDesenho);
		
		pontos.add(aPonto);
	}
	
	/**
	 * Salva todos os pontos no desenho
	 * 
	 * @return
	 * Retorna <code>true</code> caso os pontos sejam salvos. Retorna<code>false</code> caso 
	 * ocorra algum erro ao salvar os pontos.
	 */
	private boolean salvarTodosPontos() {
		return controleDesenho.salvarPontos(pontos);
	}
	
	/**
	 * Remove todos os pontos no desenho.
	 * 
	 * @return
	 * Retorna <code>true</code> caso os pontos tenham sido removidos. Retorna<code>false</code> caso 
	 * ocorra algum erro ao remover os pontos.
	 */
	private boolean removerTodosPontos() {
		if(controleDesenho.removerPontos(pontos)) {
			pontos.clear();
			return true;
		}
		
		return false;
	}

	/******************************************************************************************/
	/********************************** Getters e Setters *************************************/
	/******************************************************************************************/
	public String getNomeDesenho() {
		return nomeDesenho;
	}

	public void setNomeDesenho(String nomeDesenho) {
		this.nomeDesenho = nomeDesenho;
	}

	public int getIdDesenho() {
		return idDesenho;
	}

	public void setIdDesenho(int idDesenho) {
		this.idDesenho = idDesenho;
	}

	public List<Ponto> getPontos() {
		return pontos;
	}

	public void setPontos(List<Ponto> pontos) {
		this.pontos = pontos;
	}
}
