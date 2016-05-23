package ua.sumdu.j2se.andrey.task;

import javax.swing.*;
import java.util.Date;
import java.util.Iterator;

public class Main {

    public static void main(String[] args) {


//        JFrame frame = new JFrame("First form");
//        frame.setSize(900, 600);
//        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//        frame.setLocationRelativeTo(null);
//
//        frame.setVisible(true);

        Task task1 = new Task("task1", new Date(393939393));
        Task task2 = new Task("task2", new Date(393939393));
        Task task3 = new Task("task3", new Date(393939393));
        Task task4 = new Task("task4", new Date(393939393));
        Task task5 = new Task("task5", new Date(393939393));
        Task task6 = new Task("task6", new Date(393939393));
        Task task7 = new Task("task7", new Date(393939393));
        Task task8 = new Task("task8", new Date(393939393));
        Task task9 = new Task("task9", new Date(393939393));
        Task task10 = new Task("task10", new Date(393939393));
        //task1.setTime(-1);


        LinkedTaskList list = new LinkedTaskList();
        LinkedTaskList list2 = new LinkedTaskList();
        list.add(task1);
        list.add(task2);
        list.add(task3);
        list.add(task4);

        list2.add(task1);
        list2.add(task2);
        list2.add(task2);
        list2.add(task2);




        //System.out.println(list.toString());

    }

}
