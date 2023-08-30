package com.application.finances.dto;


import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class PaginationDto {
    private Integer pageNumber;
    private Integer pageSize;
}
