package by.scodax.bird.control;

import by.scodax.bird.model.Fishka;
import by.scodax.bird.model.Numbers;

/**
 * patrick 04.05.14.
 */
public class AddScoreTask implements Task {

    private final int value;
    private final Numbers numbers;

    public AddScoreTask(int value, Numbers numbers) {

        this.value = value;
        this.numbers = numbers;
    }

    @Override
    public boolean execute(float delta, Fishka fishka) {
        numbers.addScore(value);
        return true;
    }

    @Override
    public float getTime() {
        return 0;
    }
}
