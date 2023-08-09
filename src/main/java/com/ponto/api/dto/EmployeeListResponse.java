package com.ponto.api.dto;

import lombok.Data;

import java.util.List;

@Data
public class EmployeeListResponse {
    private List<EmployeeDTO> content;
    private int page;
    private int pageSize;
    private long totalElements;
    private int totalPages;
    private boolean last;
}
