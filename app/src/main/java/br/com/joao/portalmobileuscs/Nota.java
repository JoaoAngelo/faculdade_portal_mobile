package br.com.joao.portalmobileuscs;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

@DatabaseTable(tableName = "nota")
public class Nota {

	@DatabaseField(id = true)
	private int id_nota;

	@DatabaseField(columnName = "id_curso_disciplina", canBeNull = false, foreign = true)
	private CursoDisciplina curso_disciplina;

	@DatabaseField(columnName = "id_estudante", canBeNull = false, foreign = true)
	private Estudante estudante;

	@DatabaseField
	private double aproveitamento;

	@DatabaseField
	private double prova;

	@DatabaseField
	private double substitutiva;

	@DatabaseField
	private double total_pontos;

	@DatabaseField
	private double reavaliacao;

	@DatabaseField
	private double media_final;

	@DatabaseField
	private String resultado_final;

	public Nota() {

	}

	public Nota(int id_nota, CursoDisciplina curso_disciplina, Estudante estudante, double aproveitamento, double prova, double substitutiva, double totalPontos, double reavaliacao, double mediaFinal, String resultadoFinal) {
		this.id_nota = id_nota;
		this.curso_disciplina = curso_disciplina;
		this.estudante = estudante;
		this.aproveitamento = aproveitamento;
		this.prova = prova;
		this.substitutiva = substitutiva;
		this.total_pontos = totalPontos;
		this.reavaliacao = reavaliacao;
		this.media_final = mediaFinal;
		this.resultado_final = resultadoFinal;
	}

	public int getId_nota() {
		return id_nota;
	}

	public void setId_nota(int id_nota) {
		this.id_nota = id_nota;
	}

	public CursoDisciplina getCursodisciplina() {
		return curso_disciplina;
	}

	public void setCursodisciplina(CursoDisciplina curso_disciplina) {
		this.curso_disciplina = curso_disciplina;
	}

	public Estudante getEstudante() {
		return estudante;
	}

	public void setEstudante(Estudante estudante) {
		this.estudante = estudante;
	}

	public double getAproveitamento() {
		return aproveitamento;
	}

	public void setAproveitamento(double aproveitamento) {
		this.aproveitamento = aproveitamento;
	}

	public double getProva() {
		return prova;
	}

	public void setProva(double prova) {
		this.prova = prova;
	}

	public double getSubstitutiva() {
		return substitutiva;
	}

	public void setSubstitutiva(double substitutiva) {
		this.substitutiva = substitutiva;
	}

	public double getTotalPontos() {
		return total_pontos;
	}

	public void setTotalPontos(double totalPontos) {
		this.total_pontos = totalPontos;
	}

	public double getReavaliacao() {
		return reavaliacao;
	}

	public void setReavaliacao(double reavaliacao) {
		this.reavaliacao = reavaliacao;
	}

	public double getMediaFinal() {
		return media_final;
	}

	public void setMediaFinal(double mediaFinal) {
		this.media_final = mediaFinal;
	}

	public String getResultadoFinal() {
		return resultado_final;
	}

	public void setResultadoFinal(String resultadoFinal) {
		this.resultado_final = resultadoFinal;
	}

}