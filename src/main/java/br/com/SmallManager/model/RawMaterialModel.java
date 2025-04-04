package br.com.SmallManager.model;

import jakarta.persistence.*;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.math.BigDecimal;

@Getter
@Setter
@ToString
@EqualsAndHashCode
@Entity
@Table(name = "rawmaterial")
public class RawMaterialModel extends GenericEntityModel{

    private String code;
    private String description;
    private BigDecimal price;

}
