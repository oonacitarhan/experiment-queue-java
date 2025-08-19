import javax.tools.ToolProvider;
public class Compile {
    public static void main(String[] args) {
        var compiler=ToolProvider.getSystemJavaCompiler();
        if(compiler==null)throw new IllegalStateException("A full JDK 17+ is required");
        System.exit(compiler.run(null,System.out,System.err,args));
    }
}
