package com.pros.exception;

public class StudentNotFoundException extends RuntimeException {

    public StudentNotFoundException(String msg){
        super(msg);
    }
    StudentNotFoundException(){
        super("Student Not Found!!!");
    }

}
