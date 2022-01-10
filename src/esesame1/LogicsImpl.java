package esesame1;

import java.util.LinkedList;
import java.util.List;
import java.util.Random;

public class LogicsImpl implements Logics {

    private final int gridSize;
    private final int shipSize;
    private final List<Pair<Integer, Integer>> shipLocation = new LinkedList<>();
    //private final List<Pair<Integer, Integer>> testedLocations = new LinkedList<>();
    private int tries;
   
    public LogicsImpl(int gridSize, int shipSize) {
        this.gridSize = gridSize;
        this.shipSize = shipSize;
        createShip();
    }

    @Override
    public boolean hit(int x, int y) {
        tries++;
        if (shipLocation.contains(new Pair<>(x, y))) {
            shipLocation.remove(new Pair<>(x, y));
            return true;
        }
        return false;
    }

    @Override
    public boolean isOver() {
        if (shipLocation.size() == 0) {
            System.out.println("You Won!");
            return true;
        }
        if (tries == 5) {
            System.out.println("You Lost!");
            return true;
        }
        return false;
    }

    private void createShip() {
        if (shipSize > gridSize) {
            throw new IllegalArgumentException("Wrong ship size, can't be bigger than grid!");
        }
        var random = new Random();
        int y = random.nextInt(gridSize);
        int x = random.nextInt(gridSize - shipSize);
        for (int i = 0; i < shipSize; i++) {
            shipLocation.add(new Pair<>(x + i, y));
            System.out.println(x+i + ", " + y);
        }
    }
}
