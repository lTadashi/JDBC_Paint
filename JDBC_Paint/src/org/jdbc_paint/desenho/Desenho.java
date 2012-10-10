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
	 * @param aBancoDados
	 *            Conexão com o Banco de Dados.
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
	 * Cria um novo desenho.
	 * 
	 * @param aIdDesenho
	 *            Identificado do desenho no Banco de Dados.
	 * @param aBancoDados
	 *            Conexão com o Banco de Dados.
	 */
	public Desenho(int aIdDesenho, BancoDados aBancoDados) {
		// Inicializa os controles
		controleDesenho = new ControleDesenhos(aBancoDados);
		controlePrimitivas = new ControlePrimitivas(aBancoDados);

		Desenho tempDesenho = controleDesenho.getDesenho(aIdDesenho);
		nomeDesenho = tempDesenho.getNomeDesenho();
		idDesenho = aIdDesenho;

		pontos = controlePrimitivas.getPontosBD(aIdDesenho);
		retas = controlePrimitivas.getRetasBD(aIdDesenho);
		retangulos = controlePrimitivas.getRetangulosBD(aIdDesenho);
		elipses = controlePrimitivas.getElipsesBD(aIdDesenho);
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
	 * Salva todo o desenho no Banco de Dados.
	 * 
	 * @return Retorna <code>true</code> caso todo o desenho tenha sido salvo no
	 *         Banco de Dados. Ou retorna <code>false</code> caso tenha ocorrido
	 *         um erro durante o processo de salvar o desenho no Banco de Dados.
	 */
	public boolean salvarDesenho() {
		if (salvarDesenhoEmBranco()) {
			if (salvarTodosPontos()) {
				if (salvarTodasRetas()) {
					if (salvarTodosRetangulos()) {
						if (salvarTodasElipses()) {
							return true;
						}
					}
				}
			}
		}

		return false;
	}

	/**
	 * Remove todo o desenho do Banco de Dados, isto é, o desenho e suas
	 * primitivas.
	 * 
	 * @return Retorna <code>true</code> caso o desenho e suas primitivas tenham
	 *         sido removidas do Banco de Dados. Retorna <code>false</code> caso
	 *         tenha acontecido algum erro durante o processo de remover o
	 *         desenho e suas primitivas do Banco de Dados.
	 */
	public boolean removerDesenho() {
		if (removerTodasElipses()) {
			if (removerTodosRetangulos()) {
				if (removerTodasRetas()) {
					if (removerTodosPontos()) {
						if (removerDesenhoEmBranco()) {
							return true;
						}
					}
				}
			}
		}

		return false;
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

	/**
	 * Salva todas as retas deste desenho no Banco de Dados.
	 * 
	 * @return Retorna <code>true</code> caso todas as retas tenham sido salvas
	 *         no Banco de Dados. Retorna <code>false</code> caso tenha
	 *         acontecido algum erro durante o processo de salvar as retas no
	 *         Banco de Dados.
	 */
	private boolean salvarTodasRetas() {
		return controlePrimitivas.salvarRetas(idDesenho, retas);
	}

	/**
	 * Remove todas as retas deste desenho do Banco de Dados.
	 * 
	 * @return Retorna <code>true</code> caso todas as retas do desenho tenham
	 *         sido removidas do Banco de Dados. Retorna <code>false</code> caso
	 *         tenha acontecido algum erro durante o processo de remover as
	 *         retas do Banco de Dados.
	 */
	private boolean removerTodasRetas() {
		if (controlePrimitivas.removerRetas(idDesenho, retas)) {
			retas.clear();
			return true;
		}

		return false;
	}

	/**
	 * Salva todos os retângulos deste desenho no Banco de Dados.
	 * 
	 * @return Retorna <code>true</code> caso os retângulos deste desenho tenham
	 *         sido salvos no Banco de Dados. Retorna <code>false</code> caso
	 *         tenha acontecido algum erro durante o processo de salvar os
	 *         retângulos deste desenho no Banco de Dados.
	 */
	private boolean salvarTodosRetangulos() {
		return controlePrimitivas.salvarRetangulos(idDesenho, retangulos);
	}

	/**
	 * Remove todos os retângulos deste desenho do Banco de Dados.
	 * 
	 * @return Retorna <code>true</code> caso todas os retângulos do desenho
	 *         tenham sido removidos do Banco de Dados. Retorna
	 *         <code>false</code> caso tenha acontecido algum erro durante o
	 *         processo de remover os retângulos do Banco de Dados.
	 */
	private boolean removerTodosRetangulos() {
		if (controlePrimitivas.removerRetangulos(idDesenho, retangulos)) {
			retangulos.clear();
			return true;
		}

		return false;
	}

	/**
	 * Salva todas as elipses deste desenho no Banco de Dados.
	 * 
	 * @return Retorna <code>true</code> caso as elipses deste desenho tenham
	 *         sido salvas no Banco de Dados. Retorna <code>false</code> caso
	 *         tenha acontecido algum erro durante o processo de salvar as
	 *         elipses deste desenho no Banco de Dados.
	 */
	private boolean salvarTodasElipses() {
		return controlePrimitivas.salvarElipses(idDesenho, elipses);
	}

	/**
	 * Remove todas as elipses deste desenho do Banco de Dados.
	 * 
	 * @return Retorna <code>true</code> caso todas as elipses do desenho tenham
	 *         sido removidas do Banco de Dados. Retorna <code>false</code> caso
	 *         tenha acontecido algum erro durante o processo de remover as
	 *         elipses do Banco de Dados.
	 */
	private boolean removerTodasElipses() {
		if (controlePrimitivas.removerElipses(idDesenho, elipses)) {
			elipses.clear();
			return true;
		}

		return false;
	}

	/**
	 * Salva todas as primitivas deste desenho no Banco de Dados.
	 * 
	 * @return Retorna <code>true</code> caso todas as primitivas do desenho
	 *         tenham sido salvas no Banco de Dados. Retorna <code>false</code>
	 *         caso tenha acontecido algum erro durante o processo de salvar as
	 *         primitivas no Banco de Dados.
	 */
	public boolean salvarTodasPrimitivas() {
		if (salvarTodosPontos()) {
			if (salvarTodasRetas()) {
				if (salvarTodosRetangulos()) {
					if (salvarTodasElipses()) {
						return true;
					}
				}
			}
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
