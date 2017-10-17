package com.map;

import java.lang.ref.ReferenceQueue;
import java.lang.ref.SoftReference;


class StudentReference extends SoftReference<Student>{  
    private String id;  
      
    public StudentReference(Student referent) {  
        super(referent);  
        this.id=referent.getId();  
    }  
      
    public StudentReference(Student referent, ReferenceQueue<Student> q) {  
        super(referent, q);  
        this.id=referent.getId();  
    }  
      
    public void setId(String id){  
        this.id=id;  
    }  
      
    public String getId(){  
        return this.id;  
    }  
}  
