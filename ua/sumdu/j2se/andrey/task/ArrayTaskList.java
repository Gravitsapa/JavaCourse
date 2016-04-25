package ua.sumdu.j2se.andrey.task;

import java.util.Iterator;

public class ArrayTaskList extends TaskList {

    private Task[] tasks;
    private int index = 0;

    @Override
    public void add(Task task) {
        if (tasks == null)
            tasks = new Task[10];

        if (index < tasks.length) {
            tasks[index] = task;
        } else {
            Task[] tmp = new Task[index+10];
            System.arraycopy(tasks, 0, tmp, 0, index);
            tmp[index] = task;
            tasks = tmp;
        }
        index++;
    }

    @Override
    public boolean remove(Task task) {
        boolean removed = false;

        for (int i = 0; i < tasks.length; ++i)
            if (tasks[i] == task) {
                tasks[i] = null;
                index--;
                removed = true;
                break;
            }
        if (removed) {
            Task[] tmp = new Task[index];
            int pos = 0;
            for (Task task1 : tasks) {
                if (task1 != null) {
                    tmp[pos] = task1;
                    pos++;
                }
            }
            tasks = tmp;
        }
        return removed;
    }

    @Override
    public int size() {
        int tmp = 0;
        for (Task task : tasks)
            if (task != null) ++tmp;
        return tmp;
    }

    @Override
    public Task getTask(int index) {
        if(index < 0)
            throw new IllegalArgumentException("Index of task must be greater than 0");

        return tasks[index];
    }

//TODO FIX incoming method

/*
    public Task[] incoming(int from, int to) {
        Task[] incomingTasks = new Task[index];
        int incomingIndex = 0;
        for (int i = 0; i < index; ++i) {
            if (tasks[i].getStartTime() >= from && tasks[i].getEndTime() <= to) {
                incomingTasks[incomingIndex] = tasks[i];
                incomingIndex++;
            }
        }
        return incomingTasks;
    }
*/
    public ArrayTaskList incoming(int from, int to) {
        ArrayTaskList incomingTasks = new ArrayTaskList();
        for (int i = 0; i < index; ++i) {
            if (tasks[i].nextTimeAfter(from) != -1 && tasks[i].nextTimeAfter(from) <= to)
                incomingTasks.add(tasks[i]);
        }
        return incomingTasks;
    }

    @Override
    public ArrayTaskList clone(){
        ArrayTaskList out = null;
        if(size() > 0)
            for (Task task : this)
                out.add(task);
        return out;
    }

}