package by.scodax.bird.control;

import by.scodax.bird.model.Fishka;

import java.util.Arrays;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

/**
 * patrick 03.05.14.
 */
public class CompositeTask implements Task {

    private final List<Task> tasks = new LinkedList<Task>();

    public CompositeTask(List<Task> tasks) {
        this.tasks.addAll(tasks);
    }

    public CompositeTask(Task... tasks){
        this.tasks.addAll(Arrays.asList(tasks));
    }

    @Override
    public boolean execute(float delta, Fishka fishka) {
        Iterator<Task> iterator = tasks.iterator();
        while (iterator.hasNext()) {
            Task next = iterator.next();
            boolean execute = next.execute(delta, fishka);
            if (execute)
                iterator.remove();
            else
                break;
        }
        return tasks.isEmpty();
    }

    @Override
    public float getTime() {
        float time = 0;
        for (Task task : tasks) {
            time += task.getTime();
        }
        return time;
    }
}
