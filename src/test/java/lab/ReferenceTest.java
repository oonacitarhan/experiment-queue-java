package lab;
import java.util.*;
import static lab.DependencyPlanner.schedule;
public class ReferenceTest {
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
