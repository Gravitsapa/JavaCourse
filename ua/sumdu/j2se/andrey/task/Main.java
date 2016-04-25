package ua.sumdu.j2se.andrey.task;

import javax.swing.*;

public class Main {

    public static void main(String[] args) {


        JFrame frame = new JFrame("First form");
        frame.setSize(900, 600);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLocationRelativeTo(null);

        frame.setVisible(true);




        Task task1 = new Task("task1", 2);
        Task task2 = new Task("task2", 2);
        Task task3 = new Task("task3", 2);
        Task task4 = new Task("task4", 2);
        Task task5 = new Task("task5", 2);
        Task task6 = new Task("task6", 2);
        Task task7 = new Task("task7", 2);
        Task task8 = new Task("task8", 2);
        Task task9 = new Task("task9", 2);
        Task task10 = new Task("task10", 2);
        //task1.setTime(-1);



        LinkedTaskList list = new LinkedTaskList();
        LinkedTaskList list2 = new LinkedTaskList();
        list.add(task1);
        list.add(task2);
        list.add(task2);
        list.add(task2);

        list2.add(task1);
        list2.add(task2);
        list2.add(task2);
        list2.add(task2);



//       for (Task lalka:list) {
//            System.out.println(lalka.getTitle());
//        }
//
//
//
//        for (int i = 0; i<list.size(); ++i)
//           System.out.println(list.getTask(i).getTitle());

        System.out.println(list.toString());

    }

}
