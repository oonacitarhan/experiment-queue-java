package lab;
import java.nio.file.*;
import java.io.IOException;
import java.util.*;
public final class JobFile {
    public static List<Job> read(Path path)throws IOException {
        var jobs=new ArrayList<Job>();int number=0;
        for(String line:Files.readAllLines(path)){
            number++;if(line.isBlank()||line.startsWith("#"))continue;
            String[] fields=line.split("\t",-1);
            if(fields.length!=4)throw new IllegalArgumentException("line "+number+": expected 4 tab-separated fields");
            var deps=fields[3].isBlank()?List.<String>of():List.of(fields[3].split(","));
            jobs.add(new Job(new Task(fields[0],Integer.parseInt(fields[1]),deps),Double.parseDouble(fields[2])));
        }
        return jobs;
    }
}
