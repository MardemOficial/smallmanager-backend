package br.com.SmallManager.domain;

import jakarta.persistence.*;
import lombok.Data;


import java.math.BigDecimal;
import java.util.UUID;

@Data
@Entity
@Table(name = "people")
public class People {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    private String name;

    private String cpf;

    private BigDecimal valor;

}
