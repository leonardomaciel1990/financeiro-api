package com.leonardo.modelo;

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
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.springframework.format.annotation.DateTimeFormat;

@Entity
@Table(name="despesa")
public class Pagamento {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	@NotNull
	@Size(max = 20)
	private String cliente;
	
	@Temporal(TemporalType.DATE)
	private Date data;
	
	@NotNull
	@Size(max = 20)
	@Column(name = "forma_pagamento")
	private String formaPagamento;
	
	@Size(max = 500)
	private String observacao;
	
	@NotNull
	@Size(max = 20)
	private String projeto;
	
	@NotNull
	@Size(max = 20)
	@Column(name = "tipo_despesa")
	private String tipoDespesa;
	
	@NotNull
	@Size(max = 20)
	@Column(name = "tipo_reembolso")
	private String tipoReembolso;
	
	@NotNull
	private BigDecimal valor;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getCliente() {
		return cliente;
	}

	public void setCliente(String cliente) {
		this.cliente = cliente;
	}

	public Date getData() {
		return data;
	}

	public void setData(Date data) {
		this.data = data;
	}

	public String getFormaPagamento() {
		return formaPagamento;
	}

	public void setFormaPagamento(String formaPagamento) {
		this.formaPagamento = formaPagamento;
	}

	public String getObservacao() {
		return observacao;
	}

	public void setObservacao(String observacao) {
		this.observacao = observacao;
	}

	public String getProjeto() {
		return projeto;
	}

	public void setProjeto(String projeto) {
		this.projeto = projeto;
	}

	public String getTipoDespesa() {
		return tipoDespesa;
	}

	public void setTipoDespesa(String tipoDespesa) {
		this.tipoDespesa = tipoDespesa;
	}

	public String getTipoReembolso() {
		return tipoReembolso;
	}

	public void setTipoReembolso(String tipoReembolso) {
		this.tipoReembolso = tipoReembolso;
	}

	public BigDecimal getValor() {
		return valor;
	}

	public void setValor(BigDecimal valor) {
		this.valor = valor;
	}
	
}
