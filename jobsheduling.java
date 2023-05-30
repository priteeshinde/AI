import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.*;

class Job {
    private String name;
    private int deadline;
    private int profit;

    public Job(String name, int deadline, int profit) {
        this.name = name;
        this.deadline = deadline;
        this.profit = profit;
    }

    public String getName() {
        return name;
    }

    public int getDeadline() {
        return deadline;
    }

    public int getProfit() {
        return profit;
    }
}

class JobScheduling {
    private List<Job> jobs;

    public JobScheduling() {
        jobs = new ArrayList<>();
    }

    public void addJob(Job job) {
        jobs.add(job);
    }

    public List<Job> scheduleJobs() {
        List<Job> scheduledJobs = new ArrayList<>();
        Collections.sort(jobs, Comparator.comparingInt(Job::getProfit).reversed());

        boolean[] slots = new boolean[jobs.size()];

        for (Job job : jobs) {
            for (int i = Math.min(job.getDeadline() - 1, jobs.size() - 1); i >= 0; i--) {
                if (!slots[i]) {
                    slots[i] = true;
                    scheduledJobs.add(job);
                    break;
                }
            }
        }

        return scheduledJobs;
    }
}

public class jobsheduling {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        JobScheduling jobScheduling = new JobScheduling();
        System.out.println("Enter no of jobs:");
        int j = sc.nextInt();
        System.out.println("enter job id deadline profit");
        for (int i = 0; i < j; i++) {
            String j1 = sc.next();
            int j2 = sc.nextInt();
            int j3 = sc.nextInt();
            jobScheduling.addJob(new Job(j1, j2, j3));
        }
        
        List<Job> scheduledJobs = jobScheduling.scheduleJobs();

        System.out.println("Scheduled Jobs:");

        for (Job job : scheduledJobs) {
            System.out.println(job.getName());
        }
        sc.close();
    }
}