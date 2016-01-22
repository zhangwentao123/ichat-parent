package test.thread;

/**
 * Created by hebo on 2016-1-21.
 */
public class Join {
    public static void main(String[] args) throws Exception{
        Thread pre = Thread.currentThread();
        for (int i = 0; i < 10; i++) {
            Thread thread = new Thread(new Domino(pre), String.valueOf(i));
            thread.start();
            pre = thread;
        }
//        Thread.sleep(5000);
        System.out.println(Thread.currentThread().getName() + " terminate");
    }

    static class Domino implements Runnable {
        private Thread thread;

        public Domino(Thread thread) {
            this.thread = thread;
        }
        public void run() {
            try {
                thread.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(Thread.currentThread().getName() + " terminate");
        }
    }
}
