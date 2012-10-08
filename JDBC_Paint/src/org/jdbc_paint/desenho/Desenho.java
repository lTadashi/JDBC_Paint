package org.jdbc_paint.desenho;

import java.util.ArrayList;
import java.util.List;

import org.jdbc_paint.bd.BancoDados;
import org.jdbc_paint.primitivas.ControlePrimitivas;
import org.jdbc_paint.primitivas.Elipse;
import org.jdbc_paint.primitivas.Ponto;
import org.jdbc_paint.primitivas.Reta;
import org.jdbc_paint.primitivas.Retangulo;

public class Desenho {
	private int idDesenho;
	private String nomeDesenho;

	// Conexão com o Banco de Dados

	// Controles
	private ControleDesenhos controleDesenho;
	private ControlePrimitivas controlePrimitivas;

	// Primitivas
	private List<Ponto> pontos;
	private List<Reta> retas;
	private List<Retangulo> retangulos;
	private List<Elipse> elipses;

	/**
	 * Cria um novo desenho.
	 * 
	 * @param aNomeDesenho
	 *            Nome do desenho.
	 */
	public Desenho(String aNomeDesenho, BancoDados aBancoDados) {
		this.nomeDesenho = aNomeDesenho;
		this.idDesenho = -1;

		// Inicializa os controles
		controleDesenho = new ControleDesenhos(aBancoDados);
		controlePrimitivas = new ControlePrimitivas(aBancoDados);

		// Inicializa as primitivas
		pontos = new ArrayList<Ponto>();
		retas = new ArrayList<Reta>();
		retangulos = new ArrayList<Retangulo>();
		elipses = new ArrayList<Elipse>();
	}

	/**
	 * Salva o desenho em branco no Banco de Dados. Isto é, salva as informações
	 * do desenho, mas não o que está desenhado nele.
	 * 
	 * @return Retorna <code>true</code> caso o desenho seja salvo no Banco de
	 *         Dados, e retorna <code>false</code> caso ocorra algum erro.
	 */
	public boolean salvarDesenhoEmBranco() {
		// Salva o desenho
		idDesenho = controleDesenho.salvarDesenhoEmBranco(this);

		if (idDesenho != -1) {
			return true;
		}

		return false;
	}

	/**
	 * Remove o desenho em branco do Banco de Dados. Isto é, remove as
	 * informações do desenho, mas não o que está desenhado nele.
	 * 
	 * @return Retorna <code>true</code> caso o desenho tenha sido removido do
	 *         Banco de Dados, e retorna <code>false</code> caso ocorra algum
	 *         erro.
	 */
	public boolean removerDesenhoEmBranco() {
		return controleDesenho.removerDesenhoEmBranco(this);
	}

	/**
	 * Adiciona o respectivo ponto a lista de pontos do desenho.
	 * 
	 * @param aPonto
	 *            Ponto a ser adicionado a o desenho.
	 */
	public void adicionarPonto(Ponto aPonto) {
		// Define este desenho como dono do ponto
		pontos.add(aPonto);
	}

	/**
	 * Remove um respectivo ponto da lista de pontos do desenho.
	 * 
	 * @param aIndicePonto
	 *            Indice do ponto na lista de pontos do desenho.
	 */
	public void removerPonto(int aIndicePonto) {
		pontos.remove(aIndicePonto);
	}

	/**
	 * Adiciona a respectiva reta a lista de retas do desenho.
	 * 
	 * @param aReta
	 *            Reta a ser adicionada a lista de retas do desenho.
	 */
	public void adicionarReta(Reta aReta) {
		retas.add(aReta);
	}

	/**
	 * Remove uma respectiva reta da lista de retas do desenho.
	 * 
	 * @param aIndiceReta
	 *            Indice da reta na lista de retas do desenho.
	 */
	public void removerReta(int aIndiceReta) {
		retas.remove(aIndiceReta);
	}

	/**
	 * Adiciona o respectivo retângulo a lista de retângulos do desenho.
	 * 
	 * @param aRetangulo
	 *            Retângulo a ser adicionado a lista de retângulos do desenho.
	 */
	public void adicionarRetangulo(Retangulo aRetangulo) {
		retangulos.add(aRetangulo);
	}

	/**
	 * Remove o respectivo retângulo da lista de retângulos do desenho.
	 * 
	 * @param aIndiceRetangulo
	 *            Indice do retângulo a ser removido da lista de retângulos do
	 *            desenho.
	 */
	public void removerRetangulo(int aIndiceRetangulo) {
		retangulos.remove(aIndiceRetangulo);
	}

	/**
	 * Adiciona a respectiva elipse a lista de elipses do desenho.
	 * 
	 * @param aElipse
	 *            Elipse a ser adicionada a lista de elipses do desenho.
	 */
	public void adicionarElipse(Elipse aElipse) {
		elipses.add(aElipse);
	}

	/**
	 * Remove a respectiva elipse da lista de elipses do desenho.
	 * 
	 * @param aIndiceElipse
	 *            Indice da elipse a ser removida da lista de elipses do
	 *            desenho.
	 */
	public void removerElipse(int aIndiceElipse) {
		elipses.remove(aIndiceElipse);
	}

	/**
	 * Salva todos os pontos no desenho
	 * 
	 * @return Retorna <code>true</code> caso os pontos sejam salvos. Retorna
	 *         <code>false</code> caso ocorra algum erro ao salvar os pontos.
	 */
	private boolean salvarTodosPontos() {
		return controlePrimitivas.salvarPontos(idDesenho, pontos);
	}

	/**
	 * Remove todos os pontos no desenho.
	 * 
	 * @return Retorna <code>true</code> caso os pontos tenham sido removidos.
	 *         Retorna<code>false</code> caso ocorra algum erro ao remover os
	 *         pontos.
	 */
	private boolean removerTodosPontos() {
		if (controlePrimitivas.removerPontos(idDesenho, pontos)) {
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

	/**
	 * Retorna o ponto em uma determinada posição na lista de pontos do desenho.
	 * 
	 * @param aIndicePonto
	 *            O indice do ponto na lista de pontos do desenho.
	 * @return O respectivo ponto na posição informada.
	 */
	public Ponto getPonto(int aIndicePonto) {
		return pontos.get(aIndicePonto);
	}

	public List<Ponto> getPontos() {
		return pontos;
	}

	public void setPontos(List<Ponto> pontos) {
		this.pontos = pontos;
	}

	/**
	 * Retorna a reta em uma determinada posição na lista de retas do desenho.
	 * 
	 * @param aIndiceReta
	 *            Indice da reta na lista de retas do desenho.
	 * @return A respectiva reta na posição informada.
	 */
	public Reta getReta(int aIndiceReta) {
		return retas.get(aIndiceReta);
	}

	public List<Reta> getRetas() {
		return retas;
	}

	public void setRetas(List<Reta> retas) {
		this.retas = retas;
	}

	/**
	 * Retorna o retângulo em uma determinada posição na lista de retângulos do
	 * desenho.
	 * 
	 * @param aIndiceRetangulo
	 *            Indice do retângulo na lista de retângulos do desenho.
	 * @return O respectivo retângulo na posição informada.
	 */
	public Retangulo getRetangulo(int aIndiceRetangulo) {
		return retangulos.get(aIndiceRetangulo);
	}

	public List<Retangulo> getRetangulos() {
		return retangulos;
	}

	public void setRetangulos(List<Retangulo> retangulos) {
		this.retangulos = retangulos;
	}

	/**
	 * Retorna a elipse em uma determinada posição na lista de elipses do
	 * desenho.
	 * 
	 * @param aIndiceElipse
	 *            Indice da elipse na lista de elipses do desenho.
	 * @return A respectiva elipse na posição informada.
	 */
	public Elipse getElipse(int aIndiceElipse) {
		return elipses.get(aIndiceElipse);
	}

	public List<Elipse> getElipses() {
		return elipses;
	}

	public void setElipses(List<Elipse> elipses) {
		this.elipses = elipses;
	}
}
