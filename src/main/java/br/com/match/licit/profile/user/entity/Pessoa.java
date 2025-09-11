package br.com.match.licit.profile.user.entity;

import io.quarkus.hibernate.orm.panache.PanacheEntityBase;
import io.quarkus.runtime.annotations.RegisterForReflection;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Table(name = "pessoa", schema = "licitmatch")
@Data
@EqualsAndHashCode(callSuper = false)
@NoArgsConstructor
@AllArgsConstructor
@RegisterForReflection
public class Pessoa extends PanacheEntityBase {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "pessoa_id_seq")
    @SequenceGenerator(name = "pessoa_id_seq", sequenceName = "licitmatch.pessoa_id_seq", allocationSize = 1)
    private Long id;

    @Column(name = "nome")
    private String nome;

    @Column(name = "cpf", length = 11)
    private String cpf;

    @Column(name = "data_nascimento")
    private LocalDate dataNascimento;

}
