package test.thread;

import com.mysql.jdbc.TimeUtil;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by hebo on 2016-1-21.
 */
public class Priority {

    private static volatile boolean notStart = true;
    private static volatile boolean notEnd = true;

    public static void main(String[] args) throws Exception{
        List<Job> jobs = new ArrayList<Job>();
        for (int i = 0; i < 10; i++) {
            int priority = i < 5? Thread.MIN_PRIORITY:Thread.MAX_PRIORITY;
            Job job = new Job(priority);
            jobs.add(job);
            Thread thread = new Thread(job);
            thread.start();
        }

        notStart = false;
        Thread.sleep(1000);
        notEnd = false;

        for (Job job : jobs) {
            System.out.println("Priority:" + job.priority + "\tjobCount:" + job.jobCount);
        }
    }

    static class Job implements Runnable {
        private int priority;
        private long jobCount;

        public Job(int priority) {
            this.priority = priority;
        }

        public void run() {
            while (notStart){
                Thread.yield();
            }

            while (notEnd) {
                Thread.yield();
                jobCount++;
            }

        }
    }
}
