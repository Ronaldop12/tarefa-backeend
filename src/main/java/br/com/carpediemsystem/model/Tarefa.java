package br.com.carpediemsystem.model;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.sun.istack.NotNull;

import br.com.carpediemsystem.deserializer.CustomBigDecimalDeserializer;


@Entity
@Table(name="tarefas")
public class Tarefa implements Serializable{
	private static final long serialVersionUID = 1L;
	
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

	@Column(nullable = false)
    private String nome;


    @NotNull
    @Column(nullable = false)
    @JsonDeserialize(using = CustomBigDecimalDeserializer.class)
    private BigDecimal custo;

    @Temporal(TemporalType.DATE)
	@Column(nullable = false)
    private Date dataLimite;

	@Column(nullable = false)
    private int ordemApresentacao;
    
    
    public Tarefa() {
    	
    }


	public Long getId() {
		return id;
	}


	public void setId(Long id) {
		this.id = id;
	}


	public String getNome() {
		return nome;
	}


	public void setNome(String nome) {
		this.nome = nome;
	}


	public BigDecimal getCusto() {
		return custo;
	}


	public void setCusto(BigDecimal custo) {
		this.custo = custo;
	}


	public Date getDataLimite() {
		return dataLimite;
	}


	public void setDataLimite(Date dataLimite) {
		this.dataLimite = dataLimite;
	}


	public int getOrdemApresentacao() {
		return ordemApresentacao;
	}


	public void setOrdemApresentacao(int ordemApresentacao) {
		this.ordemApresentacao = ordemApresentacao;
	}


	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}


	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Tarefa other = (Tarefa) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}
       
    
}