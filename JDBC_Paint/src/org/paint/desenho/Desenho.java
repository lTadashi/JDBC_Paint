package org.paint.desenho;

import java.util.ArrayList;
import java.util.List;

import org.paint.primitivas.ControlePrimitivas;
import org.paint.primitivas.Ponto;

public class Desenho {
	private int idDesenho;
	private String nomeDesenho;
	
	// Conexão com o Banco de Dados
	
	// Controles
	private ControleDesenhos controleDesenho;
	private ControlePrimitivas controlePrimitivas;
	
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
		this.idDesenho = -1;
		
		// Inicializa o controle de desenho
		controleDesenho = new ControleDesenhos();
		
		// Inicializa as primitivas
		pontos = new ArrayList<Ponto>();
	}
	
	/**
	 * Salva o desenho em branco no Banco de Dados. Isto é, salva as informações do desenho,
	 * mas não o que está desenhado nele.
	 * 
	 * @return
	 * Retorna <code>true</code> caso o desenho seja salvo no Banco de Dados, 
	 * e retorna <code>false</code> caso ocorra algum erro.
	 */
	public boolean salvarDesenhoEmBranco() {
		// Salva o desenho
		idDesenho = controleDesenho.salvarDesenhoEmBranco(this);
		
		return false;
	}
	
	/**
	 * Remove o desenho em branco do Banco de Dados. Isto é, remove as informações do desenho,
	 * mas não o que está desenhado nele.
	 * 
	 * @return
	 * Retorna <code>true</code> caso o desenho tenha sido removido do Banco de Dados, 
	 * e retorna <code>false</code> caso ocorra algum erro.
	 */
	public boolean removerDesenhoEmBranco() {
		controleDesenho.removerDesenhoEmBranco(this);
		
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
		pontos.add(aPonto);
	}
	
	/**
	 * Remove um respectivo ponto do desenho.
	 * 
	 * @param aPonto
	 * Indice do ponto na lista de pontos do desenho.
	 */
	public void removerPonto(int aPonto) {
		pontos.remove(aPonto);
	}
	
	/**
	 * Salva todos os pontos no desenho
	 * 
	 * @return
	 * Retorna <code>true</code> caso os pontos sejam salvos. Retorna<code>false</code> caso 
	 * ocorra algum erro ao salvar os pontos.
	 */
	private boolean salvarTodosPontos() {
		return controlePrimitivas.salvarPontos(idDesenho, pontos);
	}
	
	/**
	 * Remove todos os pontos no desenho.
	 * 
	 * @return
	 * Retorna <code>true</code> caso os pontos tenham sido removidos. Retorna<code>false</code> caso 
	 * ocorra algum erro ao remover os pontos.
	 */
	private boolean removerTodosPontos() {
		if(controlePrimitivas.removerPontos(idDesenho, pontos)) {
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
