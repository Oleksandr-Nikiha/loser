package com.project.nikiha.loser.data;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Currency {
    private String currencyCode;
    private Double nbuRate;
    private Double saleRate;
    private Double purchaseRate;
}
