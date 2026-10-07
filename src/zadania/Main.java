package zadania;

public class Main {
    static void main() {


        /**
         * Zadanie 1: tworzenie wątków (po kroku 2).
         *
         * 1. Utwórz trzy wątki, każdy innym sposobem:
         *    - klasa, która dziedziczy po Thread ,
         *    - klasa, która implementuje Runnable
         *    - lambda.
         * 2. Każdy wątek ma wypisać, którym jest sposobem, np. "Wątek z klasy Thread".
         * 3. Uruchom wszystkie trzy

         */
      Thread lambda=new Thread(new Runnable() {
          @Override
          public void run() {
              System.out.println("lambda");
          }
      });
      Thread thread=new ThreadZad1(){};
      Thread runnable=new Thread(new RunnableZad1());
    lambda.start();
    thread.start();
    runnable.start();
    }

}
