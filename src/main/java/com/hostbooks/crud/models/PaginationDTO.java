package com.hostbooks.crud.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PaginationDTO {
    private Integer fromValue;
    private Integer psize;
    private String search;
    private String sort;
    private String filter;
    private String order;
    private String filterField;
}