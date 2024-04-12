package org.example;
import org.example.*;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        System.out.println("Добро пожаловать в приложение классного журнала, выберите одну опцию: ");
        System.out.println("Узнать расписание (для студентов) - 1");
        System.out.println("Узнать расписание (для преподавателей) - 2");
        Scanner scan = new Scanner(System.in);
        if (scan.equals("1")) {

        } else if (scan.equals("2")) {

        } else {
            System.out.println("Такого варианта нет, попробуйте ещё раз");
        }
        Parse.Parsing();
    }
}