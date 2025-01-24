package GUI;

import Logic.Score;
import Event.*;
import Logic.Tick;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.Random;

public class Board extends JPanel implements KeyListener {
    private static final int boardSize = 3;
    private int[] board;
    private Random random;
    private Score scoreCounter;
    private  boolean move;
    private int tickCounter;
    private  boolean running = false;
    private int collision = 0;
    private int increment = 0;
    private TickEvent.TickEventListener tickEvent = this::tickEvent;


    public Board(Score scoreCounter) {
        this.board = new int[7];
        board[0] = 1;
        this.scoreCounter = scoreCounter;
        this.random = new Random();
        this.tickCounter = -1;

        setOpaque(false);
        setPreferredSize(new Dimension(800, 600));
        setLayout(new FlowLayout());
        addKeyListener(this);
        setFocusable(true);

        TickEvent.addTickEventListener(tickEvent);
    }


    @Override
    public void keyPressed(KeyEvent e) {
        int key = e.getKeyCode();
        if (key == KeyEvent.VK_A) {
            moveCarLeft();
        } else if (key == KeyEvent.VK_D) {
            moveCarRight();
        } else if (key == KeyEvent.VK_S && !running) {
            new StartEvent().notifyStartEventListeners();
            start();
            running = true;
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {}

    @Override
    public void keyTyped(KeyEvent e) {}

    private void moveCarLeft() {
        if (board[0] > 0 && move) {
            board[0]--;
            move = false;
        }
    }

    private void moveCarRight() {
        if (board[0] < boardSize - 1 && move) {
            board[0]++;
            move = false;
        }
    }

    private void makeObstacles() {
        for (int i = 1; i < 6; i++) {
            board[i] = board[i + 1];
        }
        int previous = 0;
        for (int i = 6; i > 0; i--) {
            if(board[i] != 0) {
                previous = board[i];
                break;
            }
        }
        if(previous <= 3 && previous > 0) {
            for (int j = 1; j < 4; j++) {
                if(j != previous && random.nextBoolean()) {
                    board[6] = j;
                    break;
                }else{
                    board[6] = previous + 3;
                }
            }

        }else if (previous > 3) {
            board[6] = previous - 3;
        }else{
            board[6] = random.nextInt(6) + 1;
        }
    }

    public void tickEvent() {
        increment = board[1];
        collision = board[1];
        move = true;
        if(scoreCounter.isMaxScore()){
            ResetEvent.notifyResetEvent();
            running = false;
        }
        tickCounter++;
        if (tickCounter == 9) tickCounter++;
        int zeroCount = scoreCounter.countObstacles();
        int frequency = zeroCount == 0 ? 1 : zeroCount + 1;
        if (tickCounter % frequency == 0) {
            makeObstacles();
        }else{
            generateEmpty();
        }
        checkCollision();
        increment();
    }

    private void increment(){
        if(increment != 0){
            PlusOneEvent.notifyPlusOneListeners();
            increment = 0;
        }
    }

    private void checkCollision() {
        if (((1 << board[0]) & getObstacleBits(collision)) != 0) {
            ResetEvent.notifyResetEvent();
            Tick.getInstance().resetGame();
            running = false;
            collision = 0;
        }
    }

    private int getObstacleBits(int obstacle) {
        return switch (obstacle) {
            case 1 -> 0b001;
            case 2 -> 0b010;
            case 3 -> 0b100;
            case 4 -> 0b110;
            case 5 -> 0b101;
            case 6 -> 0b011;
            default -> 0b000;
        };
    }

    public void generateEmpty(){
        for (int i = 1; i < 6; i++) {
            board[i] = board[i + 1];
        }
        board[6] = 0;
    }

    public void start(){
        board = new int[7];
        board[0] = 1;
        tickCounter = -1;
    }

    public int[] getBoardArr(){
        return board;
    }
}