package com.example.bank.bank.domain.data;

public class TransactionDto {
    private String id;
    private Long fromId;
    private Long toId;
    private Long value;

    public TransactionDto() {

    }

    public TransactionDto(String id, Long fromId, Long toId, long value) {
        this.id = id;
        this.fromId = fromId;
        this.toId = toId;
        this.value = value;
    }

    public TransactionDto(Long fromId, Long toId, long value) {
        this.fromId = fromId;
        this.toId = toId;
        this.value = value;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Long getFromId() {
        return fromId;
    }

    public void setFromId(Long fromId) {
        this.fromId = fromId;
    }

    public Long getToId() {
        return toId;
    }

    public void setToId(Long toId) {
        this.toId = toId;
    }

    public long getValue() {
        return value;
    }

    public void setValue(long value) {
        this.value = value;
    }

}
