package ua.sumdu.j2se.andrey.task;

import java.util.Date;
import java.util.Iterator;

public class LinkedTaskList extends TaskList {

    private Task task;
    private LinkedTaskList next;
    private LinkedTaskList head;
    private LinkedTaskList tail;
    private int size = 0;

    @Override
    public void add(Task task) {
        if(head == null){
            head = tail = new LinkedTaskList();
            head.task = task;
            head.next = tail;
            tail = head;
        } else {
            tail.next = new LinkedTaskList();
            tail = tail.next;
            tail.task = task;
        }
        size++;
    }

    @Override
    public boolean remove(Task task) {

        LinkedTaskList temp = head;
        LinkedTaskList two = null;

        if(head.task.equals(task)) {
            head = head.next;
            size--;
            return true;
        } else if(tail.task.equals(task)){
            tail.next = null;
            size--;
            return true;
        }

        while(temp != null && !temp.task.equals(task)){
            two = temp;
            temp = temp.next;
        }

        if(temp == null) return false;

        two.next = temp.next;
        size--;

        return true;
    }

    @Override
    public Task getTask(int index){
        if(index < 0)
            throw new IllegalArgumentException("Index of task must be greater than 0");

        LinkedTaskList tmp = head;

        for(int i = 0; i < index; i++)
            tmp = tmp.next;
        return tmp.task;
    }

    @Override
    public int size() {
        return size;
    }

//    public LinkedTaskList incoming(Date from, Date to) {
//        LinkedTaskList incomingTasks = new LinkedTaskList();
//        for (int i = 0; i < size(); ++i) {
//            if (getTask(i).nextTimeAfter(from) != null &&
//                    getTask(i).nextTimeAfter(from).compareTo(to) < 0 || getTask(i).nextTimeAfter(from).compareTo(to) == 0)
//                incomingTasks.add(getTask(i));
//        }
//        return incomingTasks;
//    }

    @Override
    public LinkedTaskList clone(){
        LinkedTaskList out = null;
        if(size() > 0)
            for (Task task : this)
                out.add(task);
        return out;
    }

}