package classloader;

public class TestClassLoader {

    public static void main(String[] args) throws Exception {
        ClassLoader loader = TestClassLoader.class.getClassLoader();
        System.out.println(loader.toString());
        System.out.println(loader.getParent().toString());
        System.out.println(loader.getParent().getParent());
        
        Class.forName("java.lang.String") ;
    }
}