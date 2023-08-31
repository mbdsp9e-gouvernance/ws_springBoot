package com.project.gouvernance.model;

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
@Document(collection = "society")
public class Society {

    @Id
    private String id;

    @NotNull
    private String name;

    @NotNull
    private String nif;

    @NotNull
    private String stat;

    @NotNull
    private String password;

}
