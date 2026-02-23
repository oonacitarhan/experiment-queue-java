package lab;
import java.util.*;
public class Benchmark{public static void main(String[]args){
long start=System.nanoTime();
var jobs=new ArrayList<Job>();for(int i=0;i<1000;i++)jobs.add(new Job(new Task("job_"+i,1,i==0?List.of():List.of("job_"+(i-1))),1));
var result=CriticalPath.analyze(jobs);if(result.get(999).finish()!=1000)throw new AssertionError();
System.out.println("benchmark_seconds,"+(System.nanoTime()-start)/1e9);
}}
