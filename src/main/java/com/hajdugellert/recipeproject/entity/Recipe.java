package com.hajdugellert.recipeproject.entity;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import jakarta.validation.constraints.*;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;
import java.util.List;


@Getter
@Setter
@Entity
@Table(name = "recipes")
public class Recipe {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotBlank
    private String name;
    @NotBlank
    private String description;
    @NotBlank
    private String category;
    private List<String> ingredients;
    @NotBlank
    private String instructions;
    @NotNull
    private Integer prepTime;
    @NotNull
    private Double cost;
    private Boolean favorite =false;
    @ManyToOne
    @JoinColumn(name = "written_by_id")
    private User writtenBy;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate createdAt = LocalDate.now();


}
