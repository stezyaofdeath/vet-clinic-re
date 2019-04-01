package javaCore;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DateFromString {
    public static void main(String[] args) {
        try {
            Date date = new SimpleDateFormat("yyyy-MM-dd").parse("2019-04-25");
            System.out.println(date);
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }
}
