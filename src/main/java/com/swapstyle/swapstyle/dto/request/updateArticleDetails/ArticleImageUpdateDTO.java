package com.swapstyle.swapstyle.dto.request.updateArticleDetails;

import org.springframework.web.multipart.MultipartFile;

public record ArticleImageUpdateDTO(
    MultipartFile image
) {

}
