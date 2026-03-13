package com.swapstyle.swapstyle.dto.request;

import org.springframework.web.multipart.MultipartFile;

import com.swapstyle.swapstyle.entity.enums.Category;
import com.swapstyle.swapstyle.entity.enums.State;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record ArticleRequestDTO(

        @NotBlank(message = "Title is required, maximun 50 characters.") String title,

        @NotBlank(message = "Description required, maximun 250 characters.") String description,

        @NotBlank(message = "Size is required") String size,

        @NotNull(message = "Price is required") Double price,

        @NotNull(message = "Select a Category") Category category,

        @NotNull(message = "Select a state of article") State state,

@NotNull(message = "Add your photo here.")
MultipartFile image
) {

}
