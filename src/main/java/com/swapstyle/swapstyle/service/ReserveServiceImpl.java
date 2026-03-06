package com.swapstyle.swapstyle.service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.swapstyle.swapstyle.dto.request.ReserveRequestDTO;
import com.swapstyle.swapstyle.dto.response.ReserveResponseDTO;
import com.swapstyle.swapstyle.entity.Article;
import com.swapstyle.swapstyle.entity.Reserve;
import com.swapstyle.swapstyle.entity.User;
import com.swapstyle.swapstyle.repository.ArticleRepository;
import com.swapstyle.swapstyle.repository.ReserveRepository;
import com.swapstyle.swapstyle.repository.UserRepository;

@Service
public class ReserveServiceImpl implements ReserveService {

    private final ReserveRepository reserveRepository;
    private final ArticleRepository articleRepository;
    private final UserRepository userRepository;

    public ReserveServiceImpl(ReserveRepository reserveRepository,
            ArticleRepository articleRepository,
            UserRepository userRepository) {
        this.reserveRepository = reserveRepository;
        this.articleRepository = articleRepository;
        this.userRepository = userRepository;
    }

    @Override
    @Transactional
    public Optional<ReserveResponseDTO> toggleReservation(ReserveRequestDTO dto) {
        Article article = articleRepository.findById(dto.articleId())
                .orElseThrow(() -> new RuntimeException("Article not found"));
        User user = userRepository.findById(dto.userId())
                .orElseThrow(() -> new RuntimeException("User not found"));
        if (article.getUserOffers().getIdUser().equals(user.getIdUser())) {
            throw new RuntimeException("You cannot reserve your own article");
        }
        Optional<Reserve> existingReserve = reserveRepository.findByArticleIdArticle(article.getIdArticle());
        if (existingReserve.isPresent()) {
            handleCancellation(existingReserve.get(), user.getIdUser());
            return Optional.empty();
        } else {
            ReserveResponseDTO newReserve = handleNewReservation(article, user);
            return Optional.of(newReserve);
        }
    }

    @Override
    public List<ReserveResponseDTO> getUserReservations(Integer userId) {
        return reserveRepository.findAllByUserWantsIdUser(userId)
                .stream()
                .map(this::mapToResponseDTO)
                .collect(Collectors.toList());
    }

    @Override
    @Transactional
    public void clearExpiredReservations() {
        List<Reserve> expired = reserveRepository.findAllByExpiryDateBefore(LocalDateTime.now());
        for (Reserve res : expired) {
            Article art = res.getArticle();
            art.setIsReserved(false);
            articleRepository.save(art);
            reserveRepository.delete(res);
        }
    }


    private ReserveResponseDTO mapToResponseDTO(Reserve reserve) {
        return new ReserveResponseDTO(
            reserve.getIdReserve(),
            reserve.getReservationDate(),
            reserve.getExpiryDate(),
            reserve.getUserWants().getUserName(),
            reserve.getArticle().getTitle(),
            reserve.getArticle().getPrice()
        );
    }

    private void handleCancellation(Reserve reserve, Integer requestingUserId) {
        if (!reserve.getUserWants().getIdUser().equals(requestingUserId)) {
            throw new RuntimeException("This article is already reserved by another user");
        }
        Article article = reserve.getArticle();
        article.setIsReserved(false);
        articleRepository.save(article);
        reserveRepository.delete(reserve);
    }

    private ReserveResponseDTO handleNewReservation(Article article, User user) {
        article.setIsReserved(true);
        articleRepository.save(article);
        Reserve newReserve = new Reserve();
        newReserve.setArticle(article);
        newReserve.setUserWants(user);
        Reserve saved = reserveRepository.save(newReserve);
        return mapToResponseDTO(saved);
    }

}
