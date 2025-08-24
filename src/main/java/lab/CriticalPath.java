package lab;
import java.util.*;
public final class CriticalPath {
    public record Timing(String id,double start,double finish) {}
    public static List<Timing> analyze(List<Job> jobs) {
        var byId=new HashMap<String,Job>();
        for(var job:jobs)byId.put(job.task().id(),job);
        var order=DependencyPlanner.schedule(jobs.stream().map(Job::task).toList());
        var end=new HashMap<String,Double>();var output=new ArrayList<Timing>();
        for(String id:order) {
            var job=byId.get(id);double start=0;
            for(String dep:job.task().dependencies())start=Math.max(start,end.get(dep));
            double finish=start+job.duration();end.put(id,finish);output.add(new Timing(id,start,finish));
        }
        return output;
    }
}
