package edu.bsuir.onlineshop.web.model;

import lombok.Builder;

import java.util.List;

@Builder
public class ApiError {
    private List<String> details;
    private Integer status;
}
