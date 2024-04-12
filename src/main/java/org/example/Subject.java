package org.example;
import java.util.LinkedList;

public class Subject { // у каждой дисциплины есть название и список преподавателей, которые он ведет
    public String title;
    public LinkedList<Teacher> teachersList;

    public Subject(String title, LinkedList teachersList) {
        this.title = title;
        this.teachersList = teachersList;
    }

    public void makeListofTeachers() { //парсим данные с журнала по каждому предмету и добавляем в Json каждый новый предмет

    }
}
