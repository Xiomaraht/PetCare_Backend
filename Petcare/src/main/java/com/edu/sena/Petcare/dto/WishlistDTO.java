package com.edu.sena.Petcare.dto;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class WishlistDTO {

    private Long id;

    private String createDate;

    private Long customerId;

    private List<Long> productIds;
}
