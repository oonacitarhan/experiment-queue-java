import java.util.*;

/** In-memory topological scheduling. Higher priority wins among ready tasks. */
public class ExperimentQueue {
    record Task(String id, int priority, List<String> dependencies) {
        Task { dependencies = List.copyOf(dependencies); }
    }
    static List<String> schedule(List<Task> tasks) {
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
    static void require(boolean condition){if(!condition)throw new AssertionError("self-test failed");}
    public static void main(String[] args) {
        var tasks=List.of(new Task("evaluate",9,List.of("train")),
            new Task("train",3,List.of("dataset")),new Task("dataset",1,List.of()),
            new Task("baseline",2,List.of()));
        var result=schedule(tasks);
        require(result.equals(List.of("baseline","dataset","train","evaluate")));
        require(schedule(List.of()).isEmpty());
        require(schedule(List.of(new Task("b",1,List.of()),new Task("a",1,List.of()))).equals(List.of("a","b")));
        for(var bad:List.of(
            List.of(new Task("a",1,List.of("b")),new Task("b",1,List.of("a"))),
            List.of(new Task("a",1,List.of("missing"))),
            List.of(new Task("a",1,List.of()),new Task("a",2,List.of())))) {
            boolean rejected=false;
            try{schedule(bad);}catch(IllegalArgumentException expected){rejected=true;}
            require(rejected);
        }
        System.out.println(result);System.out.println("Self-tests passed");
    }
}
