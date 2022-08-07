package snake.game;

import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

public class Snake {
    
    private Canvas head;
    
    public Snake() {
        createSnake();
    }
    
    private void createSnake() {
        this.head = CanvasUtils.buildSquare(Color.BLUE);
        resetPosition();
    }
    
    private void resetPosition() {
        this.head.setTranslateX(Config.WIDTH / 2 - Config.SQUARE_SIZE);
        this.head.setTranslateY(Config.HEIGHT / 2 - Config.SQUARE_SIZE);
    }
    
    public Canvas getHead() {
        return head;
    }
    
    public Integer getPositionX() {
        return (int)this.head.getTranslateX();
    }
    
    public Integer getPositionY() {
        return (int)this.head.getTranslateY();
    }
    
    public void setPosition(Integer x, Integer y) {
        this.head.setTranslateX(x);
        this.head.setTranslateY(y);
    }
    
    public Canvas reset() {
        resetPosition();
        return this.head;
    }
    
    public void die() {
        GraphicsContext gc = this.getHead().getGraphicsContext2D();
        gc.clearRect(0, 0, Config.SQUARE_SIZE, Config.SQUARE_SIZE);
        gc.setFill(Color.CRIMSON);
        gc.fillRect(0, 0, Config.SQUARE_SIZE, Config.SQUARE_SIZE);
    }
     
}
