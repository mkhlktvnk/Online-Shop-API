package edu.bsuir.sneakersshop.web.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ImageModel {
    private Long id;
    private String imageUrl;
}
