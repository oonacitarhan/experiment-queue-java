package lab;
import java.util.*;
import java.nio.file.*;
public class FeatureTest {
    static void check(boolean b){if(!b)throw new AssertionError("critical-path test failed");}
    public static void main(String[] args)throws Exception{
        var jobs=List.of(new Job(new Task("a",1,List.of()),2),new Job(new Task("b",2,List.of()),4),new Job(new Task("c",3,List.of("a","b")),3));
        var times=CriticalPath.analyze(jobs);
        var c=times.stream().filter(t->t.id().equals("c")).findFirst().orElseThrow();
        check(c.start()==4&&c.finish()==7);
        var file=Files.createTempFile("queue-test",".tsv");
        try{Files.writeString(file,"a\t1\t2\t\n");check(JobFile.read(file).size()==1);
            Files.writeString(file,"bad input\n");boolean rejected=false;
            try{JobFile.read(file);}catch(IllegalArgumentException e){rejected=true;}check(rejected);
        }finally{Files.delete(file);}
        boolean rejected=false;try{new Job(new Task("a",1,List.of()),-1);}catch(IllegalArgumentException e){rejected=true;}check(rejected);
        check(CriticalPath.analyze(List.of()).isEmpty());
        System.out.println("Critical path, parallel prerequisites, input parsing and duration validation pass");
    }
}
