package com.swapstyle.swapstyle.repository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.swapstyle.swapstyle.entity.Reserve;

public interface ReserveRepository extends JpaRepository <Reserve, Integer>{

    Optional<Reserve> findByArticleIdArticle(Integer idArticle);

    List<Reserve> findAllByUserWantsIdUser(Integer userId);
    
    boolean existsByArticleIdArticleAndUserWantsIdUser(Integer idArticle, Integer idUser);

    List<Reserve> findAllByExpiryDateBefore(LocalDateTime now);

}