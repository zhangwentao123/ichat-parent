package test.thread;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * 线程间通信--------------等待与通知机制
 *  1.使用wait()、notify()和notifyAll()时需要先对调用对象加锁
 *  2.调用wait()方法后，线程状态由RUNNING变为WAITING，并将当前线程放置到对象的等待队列
 *  3.notify()或notifyAll()方法调用后，等待线程依旧不会从wait()返回，需要调用notify()或notifyAll()的线程释放锁之后，
 * 等待线程才有机会从wait()返回
 *  4.notify()方法将等待队列中的一个等待线程从等待队列中移到同步队列中，而notifyAll()方法则是将对待等待队列中的所有的线
 * 程全部移到同步队列，被移动的线程状态由WAITING变成BLOCKED
 *  5.从wait()方法返回的前提是获得了调用对象的锁
 * Created by hebo on 2016-1-21.
 */
public class WaitNotify {
    static boolean flag = true;
    static Object lock = new Object();

    public static void main(String[] args) throws Exception{
        Thread waitThread = new Thread(new Wait(),"WaitThread");
        waitThread.start();
        Thread.sleep(1000);
        Thread notifyThread = new Thread(new Notify(),"NotifyThread");
        notifyThread.start();
    }

    static class Wait implements Runnable {

        public void run() {
            synchronized (lock) {
                //条件不满足时，继续wait，同时释放lock的锁
                while (flag) {
                    try {
                        System.out.println(Thread.currentThread() + " flag is true. wait @ " + new SimpleDateFormat("HH:mm:ss").format(new Date()));
                        //wait()会释放锁
                        lock.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                //条件满足时完成工作
                System.out.println(Thread.currentThread() + " flag is false. running @ " + new SimpleDateFormat("HH:mm:ss").format(new Date()));
            }
        }
    }

    static class Notify implements Runnable {
        public void run() {
            synchronized (lock) {
                //获取lock对象锁，然后进行通知，通知不会释放lock的锁
                //知道当前线程释放了lock后，WaitThread才能从wait方法中返回
                System.out.println(Thread.currentThread() + " hold lock. notify @ " + new SimpleDateFormat("HH:mm:ss").format(new Date()));
                //notify()和notifyAll()并不释放锁，所以还会把当前加锁代码块走完
                lock.notifyAll();
                flag = false;
                try {
                    Thread.sleep(5000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }

            //再次加锁
            synchronized (lock) {
                System.out.println(Thread.currentThread() + " hold lock again. sleep @ " + new SimpleDateFormat("HH:mm:ss").format(new Date()));

                try {
                    Thread.sleep(5000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }


}
