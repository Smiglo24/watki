package zadania;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Main {
    static void main() {
        List<Integer> numbers = new ArrayList<>(Arrays.asList(null,1230, 1120, 1113, 1567, -1230, null));
        System.out.println(zad1(numbers));
        //Zwroc sume liczb o parzystej liczbie cyfr, w ktorych suma cyfr lewej polowy jest rowna sumie prawej (1230 -> 1+2 == 3+0)

    }

    public static boolean even(Integer number) {
        int counter = 0;
        while (number > 0) {
            number /= 10;
            counter++;
        }
        if (counter % 2 == 0) {
            return true;
        }
        return false;
    }

    public static Integer zad1(List<Integer> numbers) {
        Integer result = 0;
        List<Integer> even = new ArrayList<>();
        for (Integer x : numbers) {
            if (x == null) {
               continue;
            }
            if (even(x)) {
                even.add(x);
            }
        }
        for (Integer numb : even) {
            int suml = 0;
            int sumr = 0;
            String number = numb.toString();
            int mid = number.length() / 2;
            for (int i = 0; i < mid; i++) {
                suml += number.charAt(i);
                sumr += number.charAt(i + mid);
            }
            if (suml == sumr) {
                result += numb;
            }
        }

        return result;
    }

}
