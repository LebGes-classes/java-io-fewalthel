package org.example;
import java.util.LinkedList;



public class Teacher { //у каждого преподавателя есть имя и список его преподаваемях дисциплин
    public String name;
    public LinkedList<Subject> subjectsTeacher;

    public Teacher(String name, LinkedList subjectsTeacher) {
        this.name = name;
        this.subjectsTeacher = subjectsTeacher;
    }

}
