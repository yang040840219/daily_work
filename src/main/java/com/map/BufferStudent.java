package com.map;

import java.lang.ref.ReferenceQueue;
import java.lang.ref.SoftReference;
import java.util.HashMap;
import java.util.Map;

class BufferStudent{  
    private int mode;  
    private Map<String,Object> buffer;  
    private ReferenceQueue<Student> queue = new ReferenceQueue<Student>();  
    
    public BufferStudent(int mode){  
       this.mode=mode;  
       buffer=new HashMap<String,Object>();  
    }  
      
    public void put(Student student){  
        switch(mode){  
            case 0:  
               buffer.put(student.getId(),new SoftReference<Student>(student));  
               break;  
            case 1:  
               buffer.put(student.getId(),student);  
               break;  
        }  
    }  
      
    public Student get(String name){  
        switch(mode){  
            case 0:  
                return ((SoftReference<Student>)buffer.get(name)).get();  
            case 1:  
                return (Student)buffer.get(name);  
            default:  
                return null;  
        }  
    }  
      
    public int size(){  
        return buffer.size();  
    }  
}  