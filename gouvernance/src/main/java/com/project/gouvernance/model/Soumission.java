package com.project.gouvernance.model;

import java.util.Date;

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
@Document(collection = "soumission")
public class Soumission {
    @Id
    private String id;

    @NotNull
    private Society society;

    @NotNull
    private Date dateSoumission;

    @NotNull
    private Tender tender;

    @NotNull
    private Integer status; // Reject : 0 , Valid : 1
}
