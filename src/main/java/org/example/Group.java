package org.example;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import java.util.LinkedList;

//"group" - "11-305"

//"monday" - listForDay
//"tuesday" - listForDay
//"wednesday" - listForDay
//"thirsday" - listForDay
//"friday" - listForDay
//"saturday" - listForDay

@JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.ANY)
public class Group {
    public String number;
    public LinkedList<String> monday;
    public LinkedList<String> tuesday;
    public LinkedList<String> wednesday;
    public LinkedList<String> thirsday;
    public LinkedList<String> friday;
    public LinkedList<String> saturday;


    public Group (String number) {
        this.number = number;
        this.monday = new LinkedList<>();
        this.tuesday = new LinkedList<>();
        this.wednesday = new LinkedList<>();
        this.thirsday = new LinkedList<>();
        this.friday = new LinkedList<>();
        this.saturday = new LinkedList<>();
    }
}
