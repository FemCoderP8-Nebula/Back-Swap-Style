package com.swapstyle.swapstyle.entity;

import java.time.LocalDateTime;
import org.hibernate.annotations.CreationTimestamp;
import com.swapstyle.swapstyle.entity.enums.Category;
import com.swapstyle.swapstyle.entity.enums.State;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PositiveOrZero;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "articles")
@Data
@NoArgsConstructor
public class Article {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idArticle;

    @NotBlank(message = "Title is required, maximun 50 characters.")
      @Column(length = 50)
    private String title;

    @NotBlank(message = "Description required, maximun 250 characters.")
    @Column(length = 250)
    private String description;

    @NotBlank(message = "Size is required")
    private String size;

    @NotNull(message = "Price is required")
    @PositiveOrZero
    private Double price;

    @Enumerated(EnumType.STRING)
    @NotNull(message = "Select a Category")
    private Category category;

    @Enumerated(EnumType.STRING)
    @NotNull(message = "Select a state of article")
    private State state;

    // @NotBlank(message = "Add your photo here.") irá en dto
    @Column(nullable = false)
    private String image;

    @CreationTimestamp
    private LocalDateTime published;

    @NotNull
    private Boolean isReserved = false;

    @ManyToOne
    @JoinColumn(name = "user_offers_id", nullable = false, referencedColumnName = "idUser")
    private User userOffers;

    @OneToOne(mappedBy = "article")
    private Reserve reserve;
}