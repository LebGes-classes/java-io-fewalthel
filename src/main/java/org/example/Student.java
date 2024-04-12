package org.example;
import java.util.LinkedList;


public class Student { //у каждого студента есть имя, группа, и список его дисциплин, которым он обучается
    public String name;
    public String group;
    public LinkedList<Subject> subjectsStudent;

    public Student (String name, String group, LinkedList subjectsStudent) {
        this.name = name;
        this.group = group;
        this.subjectsStudent = subjectsStudent;
    }
}
