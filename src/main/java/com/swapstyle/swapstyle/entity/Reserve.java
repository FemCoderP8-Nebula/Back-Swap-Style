package com.swapstyle.swapstyle.entity;

import java.time.LocalDateTime;

import org.hibernate.annotations.CreationTimestamp;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
import jakarta.persistence.PrePersist;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "reserves")
@Data
@NoArgsConstructor
public class Reserve {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idReserve;

    @CreationTimestamp
    private LocalDateTime reservationDate;

    private LocalDateTime expiryDate;

    @PrePersist
    protected void onCreate(){
        this.expiryDate = LocalDateTime.now().plusHours(24);
    }

    @ManyToOne
    @JoinColumn(name = "user_want_id", nullable = false, referencedColumnName = "idUser")
    private User userWants;

    @OneToOne
    @JoinColumn(name = "article_id", unique = true, nullable = false, referencedColumnName = "idArticle")
    private Article article;

}