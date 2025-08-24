package lab;
public record Job(Task task,double duration) {
    public Job {
        if(task==null||!Double.isFinite(duration)||duration<0)
            throw new IllegalArgumentException("nonnegative finite duration required");
        if(!task.id().matches("[A-Za-z0-9_-]+"))
            throw new IllegalArgumentException("IDs use letters, digits, underscores and hyphens");
    }
}
