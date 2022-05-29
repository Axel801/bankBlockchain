package com.example.bank.bank.domain.data;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigInteger;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class BankTransferDto {

    private String privateKey;
    private String addressTo;
    private BigInteger amountWei;
    private String addressFrom;
}
