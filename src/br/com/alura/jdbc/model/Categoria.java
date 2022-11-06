package br.com.alura.jdbc.model;

public class Categoria {
	
	private Integer id;
	private String nome;
	
	public Categoria(Integer id, String nome) {
		super();
		this.id = id;
		this.nome = nome;
	}
	
	@Override
	public String toString() {
		return String.format("A Categoria é: %d, %s", this.id, this.nome);
	}
}
