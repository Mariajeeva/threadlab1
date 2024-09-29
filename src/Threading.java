import java.util.Random;

public class Threading {

    private static Integer randomNumber; // Shared variable for random number
    private static boolean flag = false; // Flag to indicate if the number has been processed

    public static void main(String[] args) {
        Thread generatorThread = new Thread(new NumberGenerator());
        Thread evenThread = new Thread(new EvenNumberProcessor());
        Thread oddThread = new Thread(new OddNumberProcessor());

        generatorThread.start();
        evenThread.start();
        oddThread.start();
    }

    static class NumberGenerator implements Runnable {
        @Override
        public void run() {
            Random random = new Random();
            while (true) {
                randomNumber = random.nextInt(100); // Generate random number between 0 and 99
                flag = false; // Reset the processed flag
                System.out.println("Generated: " + randomNumber);
                try {
                    Thread.sleep(1000); // Sleep for 1 second
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                }
            }
        }
    }

    static class EvenNumberProcessor implements Runnable {
        @Override
        public void run() {
            while (true) {
                if (randomNumber != null && !flag&& randomNumber % 2 == 0) {
                    System.out.println("Square of " + randomNumber + " is " + (randomNumber * randomNumber));
                    flag= true; // Mark the number as processed
                }
                try {
                    Thread.sleep(500); // Check every 500 milliseconds
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                }
            }
        }
    }

    static class OddNumberProcessor implements Runnable {
        @Override
        public void run() {
            while (true) {
                if (randomNumber != null && !flag && randomNumber % 2 != 0) {
                    System.out.println("Cube of " + randomNumber + " is " + (randomNumber * randomNumber * randomNumber));
                    flag= true; // Mark the number as processed
                }
                try {
                    Thread.sleep(500); // Check every 500 milliseconds
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                }
            }
        }
    }
}
