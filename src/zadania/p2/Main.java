package zadania.p2;
/*Create a class Letters which will be used to run several threads parallely. The
constructor of class Letters takes a string, subsequent letters of which will be printed
by separate threads (as many threads as are letters in the string; each thread prints
‘its’ letters every second).
The main function (of a separate class) creates one object of the class Letters, then
it starts all threads, sleeps for 5 seconds, and then terminates all the threads, as
shown below (do not modify it):
download SLettersThread.java
public static void main(String[] args) {
Letters letters = new Letters("ABCD");
for (Thread t : letters)
System.out.println(t.getName() + " starting");
letters.start();
try { Thread.sleep(5000); }
catch(InterruptedException ignore) { }
letters.stop();
System.out.println("\nProgram completed.");
}
The program should write something like:
Thread A starting
Thread B starting
Thread C starting
Thread D starting
ACDBDBACACDBCBDA
Program completed.
Important:
The methods stop, resume, suspend and destroy from the Thread class are inhe-
rently unsafe and must not be used!
Deadline: Jan */
public class Main {
    public static void main(String[] args) {
        Letters letters = new Letters("ABCD");
        for (Thread t : letters)
            System.out.println(t.getName() + " starting");
        letters.start();
        try { Thread.sleep(5000); }
        catch(InterruptedException ignore) { }
        letters.stop();
        System.out.println();
        System.out.println("Program completed.");
    }


}
