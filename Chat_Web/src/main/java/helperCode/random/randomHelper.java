package helperCode.random;

import java.util.Random;

public class randomHelper {
    public String getRandomCode() {
        Random random = new Random();
        String[] alphabet_lowerCase = {"q", "w", "e", "r", "t", "y", "u", "i", "o", "p", "a", "s", "d", "f", "g", "h", "j", "k", "l", "z", "x", "c", "v", "b", "n", "m"};
        String[] alphabet_upperCase = {"Q", "W", "E", "R", "T", "Y", "U", "I", "O", "P", "A", "S", "D", "F", "G", "H", "J", "K", "L", "Z", "X", "C", "V", "B", "N", "M"};
        String[] number = {"0", "1", "2", "3", "4", "5", "6", "7", "8", "9"};

        StringBuilder result = new StringBuilder();
        for (int i = 0; i < 6; i++) {
            int option = random.nextInt(3);
            switch (option) {
                case 0:
                    result.append(alphabet_lowerCase[random.nextInt(alphabet_lowerCase.length)]);
                    break;
                case 1:
                    result.append(alphabet_upperCase[random.nextInt(alphabet_upperCase.length)]);
                    break;
                case 2:
                    result.append(number[random.nextInt(number.length)]);
                    break;
            }

        }

        System.out.println(result.toString());
        return result.toString();
    }
}
