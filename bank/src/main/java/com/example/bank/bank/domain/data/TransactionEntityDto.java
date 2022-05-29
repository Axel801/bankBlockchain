package com.example.bank.bank.domain.data;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class TransactionEntityDto {
    private Long id;
    private UserDto fromId;
    private UserDto toId;
    private long value;


    public TransactionEntityDto(UserDto fromId, UserDto toId, long value) {
        this.fromId = fromId;
        this.toId = toId;
        this.value = value;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public UserDto getFromId() {
        return fromId;
    }

    public void setFromId(UserDto fromId) {
        this.fromId = fromId;
    }

    public UserDto getToId() {
        return toId;
    }

    public void setToId(UserDto toId) {
        this.toId = toId;
    }

    public long getValue() {
        return value;
    }

    public void setValue(long value) {
        this.value = value;
    }

}
