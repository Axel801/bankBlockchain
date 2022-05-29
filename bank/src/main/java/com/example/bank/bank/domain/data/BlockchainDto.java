package com.example.bank.bank.domain.data;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class BlockchainDto {

    private String address;
    private String privateKey;
    private Long amountWei;
}
