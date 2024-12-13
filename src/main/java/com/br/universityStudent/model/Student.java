package com.br.universityStudent.model;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDate;

@Data
@Entity
public class Student {

    @Id
    @GeneratedValue
    private long id;

    private String nome;
    private LocalDate dataNascimento;
    private String cep;
    private String cidade;
    private String curso;

}

