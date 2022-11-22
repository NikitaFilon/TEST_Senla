package view;

import entity.Atm;
import entity.Card;
import parser.CustomFileReader;
import valid.ValidAtm;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.math.BigDecimal;
import java.time.YearMonth;
import java.util.List;
import java.util.Scanner;

import static java.lang.System.exit;

public class Menu {

    Card card;
    List<Card> cards;
    Scanner scanner;

    private void printMenu(String[] options) {
        for (String option : options) {
            System.out.println(option);
        }
        System.out.print("Choose your option : ");
    }

    private String[] options = {"1- Check the card balance  ",
            "2- deposit money",
            "3- withdraw money",
            "4- Exit",
    };

    public Menu() throws FileNotFoundException {
        this.cards = loadCards();
        this.scanner = new Scanner(System.in);
    }

    public void start() throws FileNotFoundException {
        int option = 1;
        card = enterNumberCard();
        enterPinCodeCard(card);

        while (option != 5) {
            printMenu(options);
            try {
                option = scanner.nextInt();
                switch (option) {
                    case 1:
                        System.out.println(card.getBalance());
                        break;
                    case 3:
                        System.out.println("Please enter the amount you want to withdraw: ");
                        BigDecimal summa = scanner.nextBigDecimal();
                        Atm.getMoney(card, summa, cards);
                        break;
                    case 2:
                        System.out.println("Please enter сthe amount you want to deposit: ");
                        summa = scanner.nextBigDecimal();
                        Atm.addMoney(card, summa, cards);
                        break;
                    case 4:
                        exit(0);
                }
            } catch (Exception ex) {
                System.out.println("Please enter an integer value between 1 and " + options.length);
                scanner.next();
            }
        }
    }

    private List<Card> loadCards() throws FileNotFoundException {
        return CustomFileReader.paymentsCards();
    }

    private Card enterNumberCard() throws FileNotFoundException {
        System.out.print("Enter the your payment card: ");
        String idCard = scanner.next();
        Card payCard = Atm.checkIdCard(idCard, cards);

        return ValidAtm.validNumberCard(payCard, scanner, cards, idCard);
    }

    private void enterPinCodeCard(Card card) {
        System.out.print("Enter the your pin code: ");
        try {
            int pinCode = scanner.nextInt();
            checkAttemptPinCode(pinCode, card);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    private void checkAttemptPinCode(int pinCode, Card card) throws FileNotFoundException {
        int countAttempt = 0;
        while (!Atm.checkPinCodeCard(pinCode, card)) {
            System.out.println("Please enter pin code for card ");
            if (countAttempt == 3) {
                try {
                    Atm.isActive(card, cards);
                    break;
                } catch (IOException e) {
                    e.printStackTrace();
                }
            } else {
                countAttempt++;
                pinCode = scanner.nextInt();
            }
        }
        ValidAtm.validActiveCard(card);
    }

}
