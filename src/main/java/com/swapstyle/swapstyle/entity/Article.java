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

  @Column(length = 50)
  private String title;

  @Column(length = 250)
  private String description;

  @Column(length = 20)
  private String size;

  @PositiveOrZero
  private Double price;

  @Enumerated(EnumType.STRING)
  private Category category;

  @Enumerated(EnumType.STRING)
  private State state;

  @Column(nullable = false)
  private String image;

  @CreationTimestamp
  private LocalDateTime published;

  @Column(nullable = false)
  private Boolean isReserved = false;

  @ManyToOne
  @JoinColumn(name = "user_offers_id", nullable = false, referencedColumnName = "idUser")
  private User userOffers;

  @OneToOne(mappedBy = "article")
  private Reserve reserve;
}