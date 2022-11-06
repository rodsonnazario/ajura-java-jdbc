package br.com.alura.jdbc.model;

public class Categoria {
	
	private Integer id;
	private String nome;
	
	public Categoria(Integer id, String nome) {
		super();
		this.id = id;
		this.nome = nome;
	}
	
	public Integer getId() {
		return id;
	}
	
	public String getNome() {
		return nome;
	}
	
	@Override
	public String toString() {
		return String.format("Categoria: %d, %s", this.id, this.nome);
	}
}
