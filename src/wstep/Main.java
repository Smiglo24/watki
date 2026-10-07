package wstep;

public class Main {
    static void main() {

        Thread thread1 = new MyThread();

        Thread thread2 = new Thread(new MyRunnable());

        Thread thread3 = new Thread(new Runnable() {
            @Override
            public void run() {
                System.out.println("3. klasa anonimowa      -> wątek: " + Thread.currentThread().getName());
            }
        });

        Thread thread4 = new Thread(
                () -> System.out.println("4. lambda               -> wątek: " + Thread.currentThread().getName()));


        thread1.start();
        thread2.start();
        thread3.start();
        thread4.start();

    }
}
