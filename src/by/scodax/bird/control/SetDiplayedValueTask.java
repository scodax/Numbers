package by.scodax.bird.control;

import by.scodax.bird.model.Fishka;

/**
 * User: Administrator
 * Date: 02.05.14
 * Time: 16:16
 */
public class SetDiplayedValueTask implements Task {

    private final Integer value;
    private final Fishka target;

    public SetDiplayedValueTask(Integer value, Fishka target) {
        this.value = value;
        this.target = target;
    }

    @Override
    public boolean execute(float delta, Fishka fishka) {
        target.setDisplayedValue(value);
        return true;
    }

    @Override
    public float getTime() {
        return 0;
    }
}
