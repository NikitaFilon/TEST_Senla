package parser;

import constants.PathConstants;
import entity.Card;

import java.io.File;
import java.io.FileNotFoundException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class CustomFileReader {

    static List<String> resultStringUser = new ArrayList<>();
    public static List<Card> cardUsers = new ArrayList<>();


    private static void readerAll() throws FileNotFoundException {
        File file = new File(PathConstants.PATH_DATABASE_USER);
        Scanner scanner = new Scanner(file);
        while (scanner.hasNext()) {
            resultStringUser.add(scanner.nextLine());
        }
    }

    public static List<Card> paymentsCards() throws FileNotFoundException {
        readerAll();
        for (String card : resultStringUser) {
            List<String> dataPaymentCard = Arrays.asList(card.split(" "));
            System.out.println(dataPaymentCard);
            cardUsers.add(new Card(String.valueOf(dataPaymentCard.get(0)),
                    Integer.parseInt(dataPaymentCard.get(1)),
                    Boolean.parseBoolean(dataPaymentCard.get(2)),
                    BigDecimal.valueOf(Long.parseLong(dataPaymentCard.get(3)))));

        }
        return cardUsers;
    }


}