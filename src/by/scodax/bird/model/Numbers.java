package by.scodax.bird.model;

import by.scodax.bird.control.*;
import by.scodax.bird.helpers.AssetLoader;

import java.util.LinkedList;
import java.util.List;
import java.util.Random;

/**
 * patrick 27.04.14.
 */
public class Numbers {

    private Random random = new Random();
    private Fishka[][] fishki = new Fishka[4][4];

    private int score = 0;

    public Numbers() {
        for (int i = 0; i < fishki.length; i++) {
            Fishka[] column = fishki[i];
            for (int j = 0; j < column.length; j++) {
                column[j] = new Fishka();
            }
        }
    }

//    public void fill() {
//        for (int i = 0; i < fishki.length; i++) {
//            Integer[] ints = fishki[i];
//            for (int j = 0; j < ints.length; j++) {
//                ints[j] = (int) Math.pow(2, random.nextInt(11) + 1);
//            }
//        }
//    }

    public void init() {
        generateFishka(0);
        generateFishka(0);
    }

    private void generateFishka(float swipeTime) {
        List<Integer> freeCells = new LinkedList<Integer>();
        for (int i = 0; i < fishki.length; i++) {
            Fishka[] column = fishki[i];
            for (int j = 0; j < column.length; j++) {
                Fishka fishka = column[j];
                if (fishka.isEmpty())
                    freeCells.add(i + (j * 4));
            }
        }
        if (!freeCells.isEmpty()) {
            int i = random.nextInt(freeCells.size());
            Integer freeCellNumber = freeCells.get(i);
            int value = random.nextInt(3) == 2 ? 4 : 2;
            Fishka fishka = fishki[freeCellNumber % 4][freeCellNumber / 4];
            fishka.setValue(value);
            fishka.addTask(new WaitTask(swipeTime));
            fishka.addTask(new SetDiplayedValueTask(value, fishka));
            fishka.addTask(new NewbieTask());
        }
    }

    public void swipe(Direction direction) {
        float swipeTime = 0;
        for (int i = 0; i < fishki.length; i++) {
            switch (direction) {
                case Left:
                    swipeTime = Math.max(swipeTime, shiftRowLeft(i));
                    swipeTime = Math.max(swipeTime, sumRowLeft(i));
                    swipeTime = Math.max(swipeTime, shiftRowLeft(i));
                    break;
                case Right:
                    swipeTime = Math.max(swipeTime, shiftRowRight(i));
                    swipeTime = Math.max(swipeTime, sumRowRight(i));
                    swipeTime = Math.max(swipeTime, shiftRowRight(i));
                    break;
                case Up:
                    swipeTime = Math.max(swipeTime, shiftColumnUp(i));
                    swipeTime = Math.max(swipeTime, sumColumnUp(i));
                    swipeTime = Math.max(swipeTime, shiftColumnUp(i));
                    break;
                case Down:
                    swipeTime = Math.max(swipeTime, shiftColumnDown(i));
                    swipeTime = Math.max(swipeTime, sumColumnDown(i));
                    swipeTime = Math.max(swipeTime, shiftColumnDown(i));
                    break;
            }
        }
        if (swipeTime > 0)
            generateFishka(swipeTime);
    }

    private float shiftRowLeft(int i) {
        float time = 0;
        for (int j = 0; j < 3; j++) {
            Fishka cell = fishki[j][i];
            if (cell.isEmpty()) {
                for (int k = j + 1; k < 4; k++) {
                    Fishka nextCell = fishki[k][i];
                    if (!nextCell.isEmpty()) {
                        shiftCells(cell, nextCell, k - j, Direction.Left);
                        time = Math.max(time, cell.getSubmittedShiftTime());
                        time = Math.max(time, nextCell.getSubmittedShiftTime());
                        break;
                    }
                }
            }
        }
        return time;
    }

    private void shiftCells(Fishka cell, Fishka nextCell, int shift, Direction direction) {
        cell.setValue(nextCell.getValue());
        cell.addTask(new SetDiplayedValueTask(cell.getValue(), cell));
        nextCell.setValue(null);
        nextCell.addTask(new SetDiplayedValueTask(null, nextCell));
        cell.addTask(new ShiftTask(shift, direction));
    }

    private float sumRowLeft(int i) {
        float time = 0;
        for (int j = 0; j < 3; j++) {
            Fishka cell = fishki[j][i];
            Fishka nextCell = fishki[j + 1][i];
            if (!cell.isEmpty() && !nextCell.isEmpty() && cell.equals(nextCell)) {
                sumCells(cell, nextCell, Direction.Left);
                time = Math.max(time, cell.getSubmittedShiftTime());
                time = Math.max(time, nextCell.getSubmittedShiftTime());
            }
        }
        return time;
    }

    private void sumCells(Fishka cell, Fishka nextCell, Direction direction) {
        cell.setValue(cell.getValue() * 2);
        nextCell.setValue(null);
//        nextCell.addTask(new ShiftTask(0, -1, direction));
        nextCell.addTask(new CompositeTask(new ShiftTask(0, -1, direction), new SetDiplayedValueTask(null, nextCell), new SetDiplayedValueTask(cell.getValue(), cell)));
        cell.addTask(new WaitTask(nextCell.getSubmittedShiftTime()));
//        cell.addTask(new SetDiplayedValueTask(cell.getValue(), cell));
        cell.addTask(new CompositeTask(new AddScoreTask(cell.getValue(), this), new ZoomTask()));
    }

    private float shiftColumnDown(int i) {
        float time = 0;
        for (int j = 3; j > 0; j--) {
            Fishka cell = fishki[i][j];
            if (cell.isEmpty()) {
                for (int k = j - 1; k >= 0; k--) {
                    Fishka nextCell = fishki[i][k];
                    if (!nextCell.isEmpty()) {
                        shiftCells(cell, nextCell, j - k, Direction.Down);
                        time = Math.max(time, cell.getSubmittedShiftTime());
                        time = Math.max(time, nextCell.getSubmittedShiftTime());
                        break;
                    }
                }
            }
        }
        return time;
    }

    private float sumColumnDown(int i) {
        float time = 0;
        for (int j = 3; j > 0; j--) {
            Fishka cell = fishki[i][j];
            Fishka nextCell = fishki[i][j - 1];
            if (!cell.isEmpty() && !nextCell.isEmpty() && cell.equals(nextCell)) {
                sumCells(cell, nextCell, Direction.Down);
                time = Math.max(time, cell.getSubmittedShiftTime());
                time = Math.max(time, nextCell.getSubmittedShiftTime());
            }
        }
        return time;
    }

    private float shiftRowRight(int i) {
        float time = 0;
        for (int j = 3; j > 0; j--) {
            Fishka cell = fishki[j][i];
            if (cell.isEmpty()) {
                for (int k = j - 1; k >= 0; k--) {
                    Fishka nextCell = fishki[k][i];
                    if (!nextCell.isEmpty()) {
                        shiftCells(cell, nextCell, j - k, Direction.Right);
                        time = Math.max(time, cell.getSubmittedShiftTime());
                        time = Math.max(time, nextCell.getSubmittedShiftTime());
                        break;
                    }
                }
            }
        }
        return time;
    }

    private float sumRowRight(int i) {
        float time = 0;
        for (int j = 3; j > 0; j--) {
            Fishka cell = fishki[j][i];
            Fishka nextCell = fishki[j - 1][i];
            if (!cell.isEmpty() && !nextCell.isEmpty() && cell.equals(nextCell)) {
                sumCells(cell, nextCell, Direction.Right);
                time = Math.max(time, cell.getSubmittedShiftTime());
                time = Math.max(time, nextCell.getSubmittedShiftTime());
            }
        }
        return time;
    }

    private float shiftColumnUp(int i) {
        float time = 0;
        for (int j = 0; j < 3; j++) {
            Fishka cell = fishki[i][j];
            if (cell.isEmpty()) {
                for (int k = j + 1; k < 4; k++) {
                    Fishka nextCell = fishki[i][k];
                    if (!nextCell.isEmpty()) {
                        shiftCells(cell, nextCell, k - j, Direction.Up);
                        time = Math.max(time, cell.getSubmittedShiftTime());
                        time = Math.max(time, nextCell.getSubmittedShiftTime());
                        break;
                    }
                }
            }
        }
        return time;
    }

    private float sumColumnUp(int i) {
        float time = 0;
        for (int j = 0; j < 3; j++) {
            Fishka cell = fishki[i][j];
            Fishka nextCell = fishki[i][j + 1];
            if (!cell.isEmpty() && !nextCell.isEmpty() && cell.equals(nextCell)) {
                sumCells(cell, nextCell, Direction.Up);
                time = Math.max(time, cell.getSubmittedShiftTime());
                time = Math.max(time, nextCell.getSubmittedShiftTime());
            }
        }
        return time;
    }

    public Fishka[][] getFishki() {
        return fishki;
    }

    public void update(float delta) {
        for (int i = 0; i < fishki.length; i++) {
            Fishka[] fishkas = fishki[i];
            for (int j = 0; j < fishkas.length; j++) {
                Fishka fishka = fishkas[j];
                fishka.update(delta);
            }
        }
    }

    public void addScore(int value) {
        score += value;
        if (score > AssetLoader.getHighScore()) {
            AssetLoader.setHighScore(score);
        }
    }

    public int getScore() {
        return score;
    }

    public void reset() {
        score = 0;
        for (int i = 0; i < fishki.length; i++) {
            Fishka[] column = fishki[i];
            for (int j = 0; j < column.length; j++) {
                column[j] = new Fishka();
            }
        }
        init();
    }

    public void setScore(int score) {
        this.score = score;
    }
}
