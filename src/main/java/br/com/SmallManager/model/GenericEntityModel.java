package br.com.SmallManager.model;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Getter;

import java.util.UUID;

@Getter
public abstract class GenericEntityModel {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

}
