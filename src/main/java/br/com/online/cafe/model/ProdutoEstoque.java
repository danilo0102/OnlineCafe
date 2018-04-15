package br.com.online.cafe.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

import org.springframework.format.annotation.DateTimeFormat;

@Entity
public class ProdutoEstoque implements Serializable {

    private static final long serialVersionUID = -4878244417904035115L;

    @Id
    @SequenceGenerator(name = "produto_estoque_seq", sequenceName = "produto_estoque_seq")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "produto_estoque_seq")
    private Long id;

    @ManyToOne
    private Produto produto;

    @Min(value=1, message = "A quantidade deve ser maior que 1")
    @Max(value=999999, message = "A quantidade deve ser menor que 999999")
    @NotNull(message = "A quantidade deve ser maior que 1")
    private Integer qtdDisponivel;

    @Column(nullable = false)
    @Temporal(TemporalType.DATE)
    @DateTimeFormat(pattern = "dd/MM/yyyy")
    @NotNull(message = "A data é obrigatória.")
    private Date dataDeEntrada;
    
    @ManyToOne
    private Comprador comprador;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Produto getProduto() {
        return produto;
    }

    public void setProduto(Produto produto) {
        this.produto = produto;
    }

    public Integer getQtdDisponivel() {
        return qtdDisponivel;
    }

    public void setQtdDisponivel(Integer qtdDisponivel) {
        this.qtdDisponivel = qtdDisponivel;
    }

    public Date getDataDeEntrada() {
        return dataDeEntrada;
    }

    public void setDataDeEntrada(Date dataDeEntrada) {
        this.dataDeEntrada = dataDeEntrada;
    }

    public Comprador getComprador() {
        return comprador;
    }

    public void setComprador(Comprador comprador) {
        this.comprador = comprador;
    }
    
}
