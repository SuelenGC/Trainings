package br.temporeal;


/**
 * Representa um contato da agenda
 * 
 * Encapsula nome,fone e retorna um AmigoOverlay
 * 
 * @author ricardo
 * 
 */
public class Amigo {

	public long id;
	public String fone;
	public String nome;

	public Amigo(long id, String nome, String fone) {
		this.id = id;
		this.nome = nome;
		this.fone = fone;
	}

	public String getDesc() {
		return nome + " - " + fone;
	}
}
