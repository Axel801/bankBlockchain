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
public class TrxBlockchainDto {

    private String transactionHash;

    private BigInteger amountSC;

    private String wallet;
}
