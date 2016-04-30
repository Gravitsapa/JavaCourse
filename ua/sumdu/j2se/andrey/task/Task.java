package ua.sumdu.j2se.andrey.task;

import java.util.Date;

public class Task {

    private String title;
    private Date time;
    private Date start;
    private Date end;
    private Date interval;

    private boolean active;
    private boolean repeated;

    public Task(String title, Date time) {
        this.title = title;
        this.time = time;
        active = false;
        repeated = false;
    }

    public Task(String title, Date start, Date end, Date interval) {
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

    public Date getTime() {
        return !repeated ? time : start;
    }

    public void setTime(Date time) {
        if(time.before(new Date(0)))
            throw new IllegalArgumentException("Time must be greater than 0");

        this.time = time;
        if(repeated) {
            start = null;
            end = null;
            interval = null;
            repeated = false;
        }
    }

    public Date getStartTime() {
        return repeated ? start : time;
    }

    public Date getEndTime() {
        return repeated ? end : time;
    }

    public Date getRepeatInterval() {
        return repeated ? interval : new Date(0);
    }

    public void setTime(Date start, Date end, Date interval) {
        if(start.before(new Date(0)) || end.before(new Date(0)) || interval.before(new Date(0)))
            throw new IllegalArgumentException("Value must be greater than 0");

        this.start = start;
        this.end = end;
        this.interval = interval;
        this.repeated = true;
    }

    public boolean isRepeated() {
        return repeated;
    }

    public Date nextTimeAfter(Date current) {
        if(current.before(new Date(0)))
            throw new IllegalArgumentException("Current time must be greater than 0");

        if (current.after(getEndTime()) || !isActive())
            return null;
        else if (current.before(getStartTime()) || current.equals(getEndTime()))
            return getStartTime();
        else {
            Date tmp = getStartTime();
            for (; tmp.before(getEndTime()); tmp.setTime(tmp.getTime() + interval.getTime())) {
                if (tmp.after(current))
                    break;
            }
            return tmp;
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Task task = (Task) o;

        if (active != task.active) return false;
        if (repeated != task.repeated) return false;
        if (title != null ? !title.equals(task.title) : task.title != null) return false;
        if (time != null ? !time.equals(task.time) : task.time != null) return false;
        if (start != null ? !start.equals(task.start) : task.start != null) return false;
        if (end != null ? !end.equals(task.end) : task.end != null) return false;
        return interval != null ? interval.equals(task.interval) : task.interval == null;

    }

    @Override
    public int hashCode() {
        int result = title != null ? title.hashCode() : 0;
        result = 31 * result + (time != null ? time.hashCode() : 0);
        result = 31 * result + (start != null ? start.hashCode() : 0);
        result = 31 * result + (end != null ? end.hashCode() : 0);
        result = 31 * result + (interval != null ? interval.hashCode() : 0);
        result = 31 * result + (active ? 1 : 0);
        result = 31 * result + (repeated ? 1 : 0);
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
