package com.swapstyle.swapstyle.entity;

import java.time.LocalDateTime;
import org.hibernate.annotations.CreationTimestamp;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
import jakarta.persistence.PrePersist;
import jakarta.persistence.Table;

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
    @Column(updatable = false, nullable = false)
    private LocalDateTime reservationDate;

    @Column(nullable = false)
    private LocalDateTime expiryDate;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_want_id", nullable = false, referencedColumnName = "idUser")
    private User userWants;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "article_id", unique = true, nullable = false, referencedColumnName = "idArticle")
    private Article article;

    @PrePersist
    protected void onCreate(){
        this.expiryDate = LocalDateTime.now().plusHours(24);
    }

}