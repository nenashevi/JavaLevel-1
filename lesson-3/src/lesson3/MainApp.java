package lesson3;

import java.util.Random;
import java.util.Scanner;

public class MainApp {
    public static void main(String[] args) {
        //Задание №1
        guessNumber();
        //Задание №2
        guessWord();
        //Задание №3
        System.out.println();
        String s = "Предложение     один  Теперь     предложение    два   Тут  предложение          три";
        String s1 = correctText(s);
        System.out.println(s);
        System.out.println(s1);
    }
    // Задание #1 ********************************************************************
    public static void guessNumber() {
        int userAttempt = 3;
        int minNumber = 0;
        int maxNumber = 9;
        int number;
        int userAnswer;
        int playAgain;

        do {
            number = genRandomNumber(maxNumber);
            for (int i = 0; i < userAttempt; i++) {
                userAnswer = getNumberFromScanner(minNumber, maxNumber);
                if (userAnswer == number) {
                    System.out.println("Вы выиграли. Загаданное чило равно " + number);
                    break;
                } else if (number != userAnswer && (i+1) == userAttempt ) {
                    System.out.println("У вас закончились попытки. Вы проиграли");
                    break;
                } else if (number < userAnswer) {
                    System.out.println("Загаданное число меньше. У вас осталось " + (userAttempt -i-1) + " попыток");
                } else if (number > userAnswer) {
                    System.out.println("Загаданное число больше. У вас осталось " + (userAttempt -i-1) + " попыток");
                }

            }
            System.out.println("Повторить игру еще раз? 1 – да / 0 – нет");
            playAgain = getNumberFromScanner();
        } while ( playAgain == 1);
    }

    public static int getNumberFromScanner() {
        Scanner scanner = new Scanner (System.in);
        int x;
        do {
            x = scanner.nextInt();
        } while (x < 0 || x > 1);
        return x;
    }

    public static int getNumberFromScanner(int minNumber, int maxNumber) {
        Scanner scanner = new Scanner (System.in);
        int x;
        do {
            System.out.println("Веедите целое число от " + minNumber + " до " +maxNumber);
            x = scanner.nextInt();
        } while (x < minNumber || x > maxNumber);
        return x;
    }

    public static int genRandomNumber(int maxNumber) {
        Random random = new Random();
        int x = random.nextInt(maxNumber+1);
        return x;
    }

    // Задание #2 ********************************************************************
    public static void guessWord() {
        String[] words = {"apple", "orange", "lemon", "banana", "apricot", "avocado", "broccoli", "carrot", "cherry",
                "garlic", "grape", "melon", "leak", "kiwi", "mango", "mushroom", "nut", "olive", "pea", "peanut",
                "pear", "pepper", "pineapple", "pumpkin", "potato"};
        String word = getRandomWord(words);
        String userWord;
        String prompt;

        do {
            System.out.println("Введите слово: ");
            userWord = getStringFromScanner();
            if (word.equals(userWord)) {
                System.out.println("Поздравляю Вы угадали. Загаданное слово " + word);
                break;
            } else {
                System.out.println("Вы не угадали слово, ниже подсказка, попробуйте еще раз.\n" + getPrompt(word, userWord));
            }
        } while (true);
    };

    public static String getRandomWord(String[] words) {
        Random random = new Random();
        String x = words[random.nextInt(words.length)];
        return x;
    }

    public static String getStringFromScanner() {
        Scanner scanner = new Scanner(System.in);
        String str = scanner.nextLine();
        return str;
    }

    public static String getPrompt(String word, String userWord) {
        String prompt="";
        for (int i = 0, j = 0; i < word.length() && j < userWord.length(); i++, j++) {
            if (word.charAt(i) == userWord.charAt(j)) {
                prompt = prompt + word.charAt(i);
            } else
                prompt = prompt + "#";

        }
        int promptLength = prompt.length();
        for (int i = 0; i < 15 -promptLength; i++) {
            prompt = prompt + "#";
        }
        return prompt;
    }

    // Задание #3 ********************************************************************
    public static String correctText (String s) {
        String s1 = s.replaceAll(" +", " ");
        StringBuilder s2 = new StringBuilder(s1);
        for (int i = 1; i < s2.length() ; i++) {
            if (s2.charAt(i) >= 'А' && s2.charAt(i) <='Я') {
                s2.insert(i-1, '.');
                i++;
            }
        }
        s2.append('.');
        return s2.toString();
    }
}
