package lab;
import java.util.*;
public final class DependencyPlanner {
    public static List<String> schedule(List<Task> tasks) {
        Map<String,Task> byId=new HashMap<>();
        for(Task t:tasks) {
            if(t.id()==null || t.id().isBlank() || byId.putIfAbsent(t.id(),t)!=null)
                throw new IllegalArgumentException("blank or duplicate id");
        }
        Map<String,Integer> degrees=new HashMap<>();
        Map<String,List<String>> children=new HashMap<>();
        for(Task t:tasks) {
            Set<String> dependencies=new HashSet<>(t.dependencies());
            degrees.put(t.id(),dependencies.size());
            for(String dep:dependencies) {
                if(!byId.containsKey(dep))throw new IllegalArgumentException("unknown dependency: "+dep);
                children.computeIfAbsent(dep,k->new ArrayList<>()).add(t.id());
            }
        }
        PriorityQueue<Task> ready=new PriorityQueue<>(Comparator.comparingInt(Task::priority).reversed().thenComparing(Task::id));
        for(Task t:tasks)if(degrees.get(t.id())==0)ready.add(t);
        List<String> order=new ArrayList<>();
        while(!ready.isEmpty()) {
            Task task=ready.remove();order.add(task.id());
            for(String child:children.getOrDefault(task.id(),List.of())) {
                int degree=degrees.merge(child,-1,Integer::sum);
                if(degree==0)ready.add(byId.get(child));
            }
        }
        if(order.size()!=tasks.size())throw new IllegalArgumentException("dependency cycle");
        return order;
    }
}
