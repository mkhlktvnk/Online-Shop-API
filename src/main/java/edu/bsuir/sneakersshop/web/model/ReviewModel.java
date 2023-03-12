package edu.bsuir.sneakersshop.web.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ReviewModel {
    @JsonProperty("id")
    @NotNull
    @Min(0)
    private Long id;

    @JsonProperty("topic")
    @NotNull
    @NotBlank
    @Length(max = 255)
    private String topic;

    @JsonProperty("content")
    @NotNull
    @NotBlank
    @Length(max = 10000)
    private String content;

    @JsonProperty("mark")
    @NotNull
    @Min(1)
    @Max(5)
    private Integer mark;
}
