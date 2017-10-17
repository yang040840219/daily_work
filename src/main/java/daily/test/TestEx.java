package daily.test;

import java.util.Arrays;
import java.util.ArrayList;
import java.util.List;

public class TestEx {
    public static List<String> words() {
        return new ArrayList<String>(Arrays.asList("hi", "there"));
    }

    /**
     TestEx.java:17: warning: [rawtypes] found raw type: List
      missing type arguments for generic class List<E>
      where E is a type-variable:
        E extends Object declared in interface List
    **/                 
    public static final List wordsRaw = words();

    // there is no warning for this
    public static final List<?> wordsET = words();
    
    public static void addThing(final List xs) {
        xs.add(42);
    }

//    public static void swapAround(final List<?> xs) {
//        xs.add(84);
//    }
//    
    public static void main(String[] args){
    	
    }
    
}