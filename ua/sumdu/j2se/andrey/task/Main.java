package ua.sumdu.j2se.andrey.task;

import javax.swing.*;
import java.util.Date;
import java.util.Iterator;
import java.util.Set;
import java.util.SortedMap;

public class Main {

    public static void main(String[] args) {


//        JFrame frame = new JFrame("First form");
//        frame.setSize(900, 600);
//        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//        frame.setLocationRelativeTo(null);
//
//        frame.setVisible(true);

        Task task1 = new Task("task1", new Date(393439393));
        Task task2 = new Task("task2", new Date(393839394));
        Task task3 = new Task("task3", new Date(393939395), new Date(393999395), 60);
        Task task4 = new Task("task4", new Date(393939396));
        Task task5 = new Task("task5", new Date(393939397));
        Task task6 = new Task("task6", new Date(393939398));
        Task task7 = new Task("task7", new Date(393939399));
        Task task8 = new Task("task8", new Date(393939390));
        Task task9 = new Task("task9", new Date(393939390));
        Task task10 = new Task("task10", new Date(393939390));
        //task1.setTime(-1);


        LinkedTaskList list = new LinkedTaskList();
        LinkedTaskList list2 = new LinkedTaskList();
        list.add(task1);
        list.add(task2);
        list.add(task3);
        list.add(task4);
        list.add(task8);
        list.add(task9);

        list2.add(task1);
        list2.add(task2);
        list2.add(task2);
        list2.add(task2);


        for (Task tasksks:  Tasks.incoming(list, new Date(393439393), new Date(493939395))) {
            System.out.println(tasksks.toString());
        }
        SortedMap<Date, Set<Task>> dfdf = Tasks.calendar(list, new Date(393439393), new Date(393939395));
        System.out.println(task1.nextTimeAfter(new Date(393439393)));
        //System.out.println(list.toString());

        Date date = new Date(393439393);

        System.out.println(date.after(new Date(10000)));
    }

}
