package lab;
import java.nio.file.Path;
public class Main {
    public static void main(String[] args){
        try{
            if(args.length!=1)throw new IllegalArgumentException("usage: lab.Main JOBS.tsv");
            var timings=CriticalPath.analyze(JobFile.read(Path.of(args[0])));
            System.out.println("id,earliest_start,earliest_finish");
            for(var t:timings)System.out.println(t.id()+","+t.start()+","+t.finish());
        }catch(Exception error){System.err.println(error.getMessage());System.exit(2);}
    }
}
