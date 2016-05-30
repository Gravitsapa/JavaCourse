package ua.sumdu.j2se.andrey.task;

import java.io.Serializable;
import java.util.Iterator;

public abstract class TaskList implements Iterable<Task>, Serializable {

    public abstract void add(Task task);

    public abstract boolean remove(Task task);

    public abstract int size();

    public abstract Task getTask(int index);

/*
    public Task[] incoming(int from, int to) {
        if(from < 0 || to < 0 || from > to)
            throw new IllegalArgumentException("Invalid value(s)");

        Task[] incomingTasks = new Task[size()];
        int incomingIndex = 0;
        for (int i = 0; i < size(); ++i) {
            if (getTask(i).getStartTime() >= from && getTask(i).getEndTime() <= to) {
                incomingTasks[incomingIndex++] = getTask(i);
                //incomingIndex++;
            }
        }
        return incomingTasks;
    }
*/
    @Override
    public Iterator<Task> iterator(){
        return new Iterator<Task>() {
            int tmp = 0;

            @Override
            public boolean hasNext() {
                return size() > tmp && getTask(tmp) != null;
            }

            @Override
            public Task next() {
                return getTask(tmp++);
            }

            @Override
            public void remove(){
                TaskList.this.remove(getTask(tmp));
            }
        };
    }

    @Override
    public boolean equals(Object obj){

        boolean eql = false;

        LinkedTaskList list = (LinkedTaskList) obj;
        if(size() != list.size())
            return eql;

        for (int i = 0; i < size(); i++){
            if(getTask(i).equals(list.getTask(i)))
                eql = true;
        }
        return eql;
    }

    @Override
    public int hashCode(){
        final int prime = 31;
        int result = 1;
        for (int i = 0; i < size(); i++)
            result = prime * result + getTask(i).hashCode();
        return result;
    }

    @Override
    public String toString(){
        String out = "";
        for (int i = 0; i < size(); i++)
            out += getTask(i).toString()+"\n";
        return out;
    }

}
