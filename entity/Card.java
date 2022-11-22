package entity;

import java.math.BigDecimal;


public class Card {

    private final String idCard;
    private final Integer pinCode;
    private Boolean activity;
    private BigDecimal balance;

    public Card(String idCard, int pinCode, Boolean activity, BigDecimal balance) {
        this.idCard = idCard;
        this.pinCode = pinCode;
        this.activity = activity;
        this.balance = balance;
    }

    public Boolean getActivity() {
        return activity;
    }

    public void setActivity(Boolean activity) {
        this.activity = activity;
    }

    public BigDecimal getBalance() {
        return balance;
    }

    public String getIdCard() {
        return idCard;
    }

    public int getPinCode() {
        return pinCode;
    }

    public void setBalance(BigDecimal balance) {
        this.balance = balance;
    }

    @Override
    public String toString() {
        return idCard + " " + pinCode + " " + activity + " " + balance;
    }


}
