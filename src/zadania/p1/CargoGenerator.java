package zadania.p1;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Locale;
import java.util.Random;

public class CargoGenerator {


         static void main() {
             int numberOfGoods = 10000;
            Random random = new Random();

            try (PrintWriter writer = new PrintWriter(new FileWriter("towary.txt"))) {
                for (int i = 1; i <= numberOfGoods; i++) {

                    double weight = random.nextDouble(100);

                    writer.println(i + " " + weight);
                }
            } catch (IOException e) {
                System.out.println("exception");
            }
        }
    }

