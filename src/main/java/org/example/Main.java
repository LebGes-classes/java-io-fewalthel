package org.example;
import org.example.*;

import java.io.IOException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws IOException {
        Parse.Parsing();
        System.out.println("Добро пожаловать в приложение классного журнала, введите свою группу ");
        Scanner scan = new Scanner(System.in);
        String group = scan.nextLine();

        System.out.println("На какой день недели вы хотите узнать расписание");
        String day = scan.nextLine();

        Output.readFromJson(group, day);
    }
}