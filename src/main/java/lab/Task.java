package lab;
import java.util.*;
public record Task(String id, int priority, List<String> dependencies) {
        public Task { dependencies = List.copyOf(dependencies); }
    }
