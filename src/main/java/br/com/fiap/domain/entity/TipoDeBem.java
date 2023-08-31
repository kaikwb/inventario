package br.com.fiap.domain.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "TB_TIPO_DE_BEM", uniqueConstraints = {
    @UniqueConstraint(name = "UK_TIPO_DE_BEM_NOME", columnNames = {"NOME"})
})
public class TipoDeBem {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SQ_TIPO_DE_BEM")
    @SequenceGenerator(name = "SQ_TIPO_DE_BEM", sequenceName = "SQ_TIPO_DE_BEM")
    @Column(name = "ID")
    private Long id;

    @Column(name = "NOME", nullable = false)
    private String nome;

    public TipoDeBem() {
    }

    public TipoDeBem(Long id, String nome) {
        this.id = id;
        this.nome = nome;
    }

    public Long getId() {
        return id;
    }

    public TipoDeBem setId(Long id) {
        this.id = id;
        return this;
    }

    public String getNome() {
        return nome;
    }

    public TipoDeBem setNome(String nome) {
        this.nome = nome;
        return this;
    }

    @Override
    public String toString() {
        return "TipoDeBem{" +
            "id=" + id +
            ", nome='" + nome + '\'' +
            '}';
    }
}
