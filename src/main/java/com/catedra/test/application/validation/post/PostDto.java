package com.catedra.test.application.validation.post;



import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class PostDto {

    @NotBlank(message = "title is mandatory")
    @Pattern(regexp = "^.{1,255}$", message = "Content must be between 1 and 255 characters")
    private String title;

    @NotBlank(message = "title is mandatory")
    @Pattern(regexp = "^.{1,255}$", message = "Content must be between 1 and 255 characters")
    private String content;

    @NotBlank(message = "The user id is mandatory")
    @Pattern(regexp = "^[0-9]*$", message = "The user id must be a number")
    @Min(value = 1, message = "The user id must be greater than 0")
    private int user_id;
}