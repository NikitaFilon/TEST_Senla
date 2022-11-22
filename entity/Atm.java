package entity;

import constants.PathConstants;

import java.io.FileWriter;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.List;

public class Atm {

    public static Card checkIdCard(String idCard, List<Card> cardUsers) {
        for (Card card : cardUsers) {
            if (card.getIdCard().equals(idCard)) {
                return card;
            } else {

            }
        }
        return null;
    }

    public static Boolean checkPinCodeCard(int pinCode, Card carder) {
        if (carder.getPinCode() == pinCode && carder.getActivity()) {
            return true;
        }
        return false;
    }

    private static void safePaymentCard(List<Card> cardUsers) throws IOException {
        FileWriter writer = new FileWriter(PathConstants.PATH_DATABASE_USER);
        for (int i = 0; i < cardUsers.size(); i++) {
            String str = cardUsers.get(i).toString();
            System.out.println(str);
            writer.write(str);
            if (i < cardUsers.size() - 1)
                writer.write("\n");
        }
        writer.close();
    }

    public static void getMoney(Card card, BigDecimal sum, List<Card> cardUsers) throws IOException {
        for (int i = 0; i < cardUsers.size(); i++) {
            if (cardUsers.get(i).equals(card)) {
                cardUsers.get(i).setBalance(card.getBalance().subtract(sum));
            }
        }
        safePaymentCard(cardUsers);
    }

    public static void addMoney(Card card, BigDecimal sum, List<Card> cardUsers) throws IOException {
        for (int i = 0; i < cardUsers.size(); i++) {
            if (cardUsers.get(i).equals(card)) {
                cardUsers.get(i).setBalance(card.getBalance().add(sum));
            }
        }
        safePaymentCard(cardUsers);
    }

    public static void isActive(Card card, List<Card> cardUsers) throws IOException {
        for (int i = 0; i < cardUsers.size(); i++) {
            card.setActivity(false);
        }
        safePaymentCard(cardUsers);
    }

}
