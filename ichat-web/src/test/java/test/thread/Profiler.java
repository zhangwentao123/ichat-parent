package test.thread;

/**
 * Created by hebo on 2016-1-22.
 */
public class Profiler {

    //第一次get()方法调用时会进行初始化(如果set方法没有调用)每个线程会调用一次
    private static final ThreadLocal<Long> TIME_THREADLOCAL = new ThreadLocal<Long>(){
        @Override
        protected Long initialValue() {
            return System.currentTimeMillis();
        }
    };

    public static final void begin(){
        TIME_THREADLOCAL.set(System.currentTimeMillis());
    }

    public static final long end(){
        return System.currentTimeMillis() - TIME_THREADLOCAL.get();
    }

    public static void main(String[] args) throws InterruptedException {
        Profiler.begin();
        Thread.sleep(1000);
        System.out.println("cost : " + Profiler.end() + " mills");
    }
}
