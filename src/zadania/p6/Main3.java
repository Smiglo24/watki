package zadania.p6;

import java.util.ArrayList;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingDeque;
import java.util.concurrent.BlockingQueue;

public class Main3 {
    static void main() throws InterruptedException {
        BlockingQueue<Integer> queue=new ArrayBlockingQueue<>(3);
        Thread producent =new Thread(()->{
        try {
            for (int i = 1; i <=10 ; i++) {
                queue.put(i);

            }
            queue.put(99);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        });
        Thread client=new Thread(()->{
           try{
              while (true){

                  int value = queue.take();
                  System.out.println(value);
                  if(value==(99)){
                      break;
                  }
               }
           } catch (InterruptedException e) {
               throw new RuntimeException(e);
           }
        });

        /**
         * Producent wkłada do BlockingQueue liczby od 1 do 10, konsument je wyjmuje i wypisuje.
         * Po ostatniej liczbie konsument ma się sam zakończyć.
         */
    producent.start();
    client.start();
    }

}
