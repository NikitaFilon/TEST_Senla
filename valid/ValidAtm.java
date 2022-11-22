package valid;

import entity.Atm;
import entity.Card;
import view.Menu;

import java.io.FileNotFoundException;
import java.util.List;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ValidAtm {
    public static void validActiveCard(Card card) throws FileNotFoundException {
        if (!card.getActivity()) {
            System.out.println("Card blocked!");
            Menu menu = new Menu();
            menu.start();
        } else {
            System.out.println("Success!");
        }
    }

    public static boolean validSuccessCard(Card card) {
        if (!card.getActivity()) {
            System.out.println("Not Success!");
            return false;
        } else {
            System.out.println("Success!");
            return true;
        }
    }



    public static Card validNumberCard(Card payCard, Scanner scanner, List<Card> cards, String idCard) {

            if (ValidAtm.validSuccessCard(payCard)) {
                System.out.println("Please enter payment card ");
                idCard = scanner.next();
                payCard = Atm.checkIdCard(idCard, cards);
            }

        return payCard;
    }


}
