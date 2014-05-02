package by.scodax.bird.model;

import by.scodax.bird.control.*;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

/**
 * patrick 27.04.14.
 */
public class Fishka {
    private Integer value = null;
    private Integer displayedValue = null;
    private float alpha = 1;

    private float shiftLeft = 0;
    private float shiftRight = 0;
    private float shiftUp = 0;
    private float shiftDown = 0;

    private List<Task> tasks = new LinkedList<Task>();
    private float zoom = 1;

    public Fishka(Integer value, boolean newbie) {
        this.value = value;
        this.displayedValue = value;
        if (newbie) {
            tasks.add(new NewbieTask());
        }
    }

    public Fishka() {
    }

    public Integer getValue() {
        return value;
    }

    public void setValue(Integer value) {
        this.value = value;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Fishka fishka = (Fishka) o;

        if (value != null ? !value.equals(fishka.value) : fishka.value != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        return value != null ? value.hashCode() : 0;
    }

    public boolean isEmpty() {
        return value == null;
    }

    public void update(float delta) {
        Iterator<Task> it = tasks.iterator();
        while (it.hasNext()) {
            Task task = it.next();
            if (task.execute(delta, this)) {
                it.remove();
            } else {
                break;
            }
        }
    }

    public float getAlpha() {
        return alpha;
    }

    public float getShiftLeft() {
        return shiftLeft;
    }

    public float getShiftRight() {
        return shiftRight;
    }

    public float getShiftUp() {
        return shiftUp;
    }

    public float getShiftDown() {
        return shiftDown;
    }

    public Integer getDisplayedValue() {
        return displayedValue;
    }

    public void setShiftLeft(float shiftLeft) {
        this.shiftLeft = shiftLeft;
    }

    public void setShiftRight(float shiftRight) {
        this.shiftRight = shiftRight;
    }

    public void setShiftUp(float shiftUp) {
        this.shiftUp = shiftUp;
    }

    public void setShiftDown(float shiftDown) {
        this.shiftDown = shiftDown;
    }

    public void setAlpha(float alpha) {
        this.alpha = alpha;
    }

    public float getSubmittedShiftTime() {
        float ret = 0;
        for (Task task : tasks) {
            ret += task.getTime();
        }
        return ret;
    }

    public void addTask(Task task) {
        tasks.add(task);
    }

    public void setZoom(float zoom) {
        this.zoom = zoom;
    }

    public float getZoom() {
        return zoom;
    }

    public void setDisplayedValue(Integer displayedValue) {
        this.displayedValue = displayedValue;
    }
}
