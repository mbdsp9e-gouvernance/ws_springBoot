package com.project.gouvernance.model;

import java.util.Date;
import java.util.List;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Document(collection = "tender")
public class Tender {
    @Id
    @NotNull
    private String id;

    @NotNull
    private String reference;

    @NotNull
    private String title;

    @NotNull
    private String description;

    @NotNull
    private List<Critere> critere;

    @NotNull
    private Date dateEmission;

    @NotNull
    private Date dateLimit;

    @NotNull
    private Integer tenderStatus; // 0: ouvert , 1: évaluation en cours, 2: clôturé
}
