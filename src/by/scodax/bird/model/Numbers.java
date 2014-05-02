package by.scodax.bird.model;

import by.scodax.bird.control.Direction;
import by.scodax.bird.control.DisappearInTask;

import java.util.LinkedList;
import java.util.List;
import java.util.Random;

/**
 * patrick 27.04.14.
 */
public class Numbers {

    private Random random = new Random();
    private Fishka[][] fishki = new Fishka[4][4];

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
        generateFishka();
        generateFishka();
    }

    private void generateFishka() {
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
            fishki[freeCellNumber % 4][freeCellNumber / 4] = new Fishka(random.nextInt(3) == 2 ? 4 : 2, true);
        }
    }

    public void swipeLeft() {
        boolean swipeDone = false;
        for (int i = 0; i < fishki.length; i++) {
            swipeDone |= shiftRowLeft(i);
            swipeDone |= sumRowLeft(i);
            swipeDone |= shiftRowLeft(i);
        }
        if (swipeDone)
            generateFishka();
    }

    private boolean shiftRowLeft(int i) {
        boolean swipeDone = false;
        for (int j = 0; j < 3; j++) {
            Fishka cell = fishki[j][i];
            if (cell.isEmpty()) {
                for (int k = j + 1; k < 4; k++) {
                    Fishka nextCell = fishki[k][i];
                    if (!nextCell.isEmpty()) {
                        cell.setValue(nextCell.getValue());
                        nextCell.setValue(null);
                        swipeDone = true;
                        cell.addShiftLeft(k - j);
                        break;
                    }
                }
            }
        }
        return swipeDone;
    }

    private boolean sumRowLeft(int i) {
        boolean swipeDone = false;
        for (int j = 0; j < 3; j++) {
            Fishka cell = fishki[j][i];
            Fishka nextCell = fishki[j + 1][i];
            if (!cell.isEmpty() && !nextCell.isEmpty() && cell.equals(nextCell)) {
                cell.setValue(cell.getValue() * 2);
                nextCell.setValue(null);
                nextCell.addTask(new DisappearInTask(nextCell, 1, cell.getSubmittedShiftTime(), Direction.Left));
                swipeDone = true;
            }
        }
        return swipeDone;
    }
    public void swipeDown() {
        boolean swipeDone = false;
        for (int i = 0; i < fishki.length; i++) {
            swipeDone |= shiftColumnDown(i);
            swipeDone |= sumColumnDown(i);
            swipeDone |= shiftColumnDown(i);
        }
        if (swipeDone)
            generateFishka();
    }

    private boolean shiftColumnDown(int i) {
        boolean swipeDone = false;
        for (int j = 3; j > 0; j--) {
            Fishka cell = fishki[i][j];
            if (cell.isEmpty()) {
                for (int k = j - 1; k >= 0; k--) {
                    Fishka nextCell = fishki[i][k];
                    if (!nextCell.isEmpty()) {
                        cell.setValue(nextCell.getValue());
                        cell.addShiftDown(j - k);
                        nextCell.setValue(null);
                        swipeDone = true;
                        break;
                    }
                }
            }
        }
        return swipeDone;
    }

    private boolean sumColumnDown(int i) {
        boolean swipeDone = false;
        for (int j = 3; j > 0; j--) {
            Fishka cell = fishki[i][j];
            Fishka nextCell = fishki[i][j - 1];
            if (!cell.isEmpty() && !nextCell.isEmpty() && cell.equals(nextCell)) {
                cell.setValue(cell.getValue() * 2);
                nextCell.setValue(null);
                nextCell.addTask(new DisappearInTask(nextCell, 1, cell.getSubmittedShiftTime(), Direction.Down));
                swipeDone = true;
            }
        }
        return swipeDone;
    }

    public void swipeRight() {
        boolean swipeDone = false;
        for (int i = 0; i < fishki.length; i++) {
            swipeDone |= shiftRowRight(i);
            swipeDone |= sumRowRight(i);
            swipeDone |= shiftRowRight(i);
        }
        if (swipeDone)
            generateFishka();
    }

    private boolean shiftRowRight(int i) {
        boolean swipeDone = false;
        for (int j = 3; j > 0; j--) {
            Fishka cell = fishki[j][i];
            if (cell.isEmpty()) {
                for (int k = j - 1; k >= 0; k--) {
                    Fishka nextCell = fishki[k][i];
                    if (!nextCell.isEmpty()) {
                        cell.setValue(nextCell.getValue());
                        cell.addShiftRight(j - k);
                        nextCell.setValue(null);
                        swipeDone = true;
                        break;
                    }
                }
            }
        }
        return swipeDone;
    }

    private boolean sumRowRight(int i) {
        boolean swipeDone = false;
        for (int j = 3; j > 0; j--) {
            Fishka cell = fishki[j][i];
            Fishka nextCell = fishki[j - 1][i];
            if (!cell.isEmpty() && !nextCell.isEmpty() && cell.equals(nextCell)) {
                cell.setValue(cell.getValue() * 2);
                nextCell.setValue(null);
                nextCell.addTask(new DisappearInTask(nextCell, 1, cell.getSubmittedShiftTime(), Direction.Right));
                swipeDone = true;
            }
        }
        return swipeDone;
    }

    public void swipeUp() {
        boolean swipeDone = false;
        for (int i = 0; i < fishki.length; i++) {
            swipeDone |= shiftColumnUp(i);
            swipeDone |= sumColumnUp(i);
            swipeDone |= shiftColumnUp(i);
        }
        if (swipeDone)
            generateFishka();
    }

    private boolean shiftColumnUp(int i) {
        boolean swipeDone = false;
        for (int j = 0; j < 3; j++) {
            Fishka cell = fishki[i][j];
            if (cell.isEmpty()) {
                for (int k = j + 1; k < 4; k++) {
                    Fishka nextCell = fishki[i][k];
                    if (!nextCell.isEmpty()) {
                        cell.setValue(nextCell.getValue());
                        cell.addShiftUp(k - j);
                        nextCell.setValue(null);
                        swipeDone = true;
                        break;
                    }
                }
            }
        }
        return swipeDone;
    }

    private boolean sumColumnUp(int i) {
        boolean swipeDone = false;
        for (int j = 0; j < 3; j++) {
            Fishka cell = fishki[i][j];
            Fishka nextCell = fishki[i][j + 1];
            if (!cell.isEmpty() && !nextCell.isEmpty() && cell.equals(nextCell)) {
                cell.setValue(cell.getValue() * 2);
                nextCell.setValue(null);
                nextCell.addTask(new DisappearInTask(nextCell, 1, cell.getSubmittedShiftTime(), Direction.Up));
                swipeDone = true;
            }
        }
        return swipeDone;
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
}
