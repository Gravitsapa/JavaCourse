package ua.sumdu.j2se.andrey.task;

import java.util.Objects;

public class Task {

    private String title;
    private int time;
    private int start;
    private int end;
    private int interval;

    private boolean active;
    private boolean repeated;

    public Task(String title, int time) {
        this.title = title;
        this.time = time;
        active = false;
        repeated = false;
    }

    public Task(String title, int start, int end, int interval) {
        this.title = title;
        this.start = start;
        this.end = end;
        this.interval = interval;
        this.active = false;
        this.repeated = true;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    public int getTime() {
        return !repeated ? time : start;
    }

    public void setTime(int time) {
        if(time < 0)
            throw new IllegalArgumentException("Time must be greater than 0");

        this.time = time;
        if(repeated) {
            start = 0;
            end = 0;
            interval = 0;
            repeated = false;
        }
    }

    public int getStartTime() {
        return repeated ? start : time;
    }

    public int getEndTime() {
        return repeated ? end : time;
    }

    public int getRepeatInterval() {
        return repeated ? interval : 0;
    }

    public void setTime(int start, int end, int interval) {
        if(time < 0 || end < 0 || interval < 0)
            throw new IllegalArgumentException("Value must be greater than 0");

        this.start = start;
        this.end = end;
        this.interval = interval;
        this.repeated = true;
    }

    public boolean isRepeated() {
        return repeated;
    }

    public int nextTimeAfter(int current) {
        if(current < 0)
            throw new IllegalArgumentException("Current time must be greater than 0");

        if (current > getEndTime() || !isActive())
            return -1;
        else if (current <= getStartTime())
            return getStartTime();
        else {
            int tmp = getStartTime();
            for (; tmp < getEndTime(); tmp += interval) {
                if (tmp > current)
                    break;
            }
            return tmp;
        }

    }

    @Override
    public boolean equals(Object obj) {
        if(this == obj)
            return true;
        if(obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        Task task = (Task) obj;
        if (time != task.time)
            return false;
        if (!Objects.equals(title, task.title) || start != task.start || end != task.end)
            return false;
        if (active != task.active || repeated != task.repeated || interval != task.interval)
            return false;
        return true;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result+ ((title == null) ? 0 : title.hashCode());
        result = prime * result + (active ? 1231 : 1237);
        result = prime * result + (repeated ? 1231 : 1237);
        result = prime * result + time;
        result = prime * result + start;
        result = prime * result + end;
        result = prime * result + interval;
        return result;
    }

    @Override
    public String toString(){
        String repeated;
        if(isRepeated())
            repeated = ", Start: " + this.start + ", End: " + this.end + ", Interval: " + this.interval;
        else
            repeated = ", Time: " + this.time;

        return "Name: " + this.title + repeated;
    }

    @Override
    public Task clone(){
        Task tmp;

        if(isRepeated()){
            tmp = new Task(this.title, this.start, this.end, this.interval);
        } else {
            tmp = new Task(this.title, this.time);
        }
        return tmp;
    }
}
