package concurrency;

import java.util.concurrent.TimeUnit;

public class StopThread {
    private static volatile boolean stopBGThread;

    public static void main(String[] args) throws InterruptedException {

        Thread backgroundThread = new Thread(() -> {
            int i=0;
            while (!stopBGThread) {
                i++;
                System.out.println(i);
            }
            System.out.println("stopped by main thread");
        });
        backgroundThread.start();

        TimeUnit.SECONDS.sleep(1);
        stopBGThread = true;
    }
}
