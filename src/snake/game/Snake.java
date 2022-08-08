package snake.game;

import java.util.ArrayList;
import java.util.List;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

public class Snake {
    
    private Canvas head;
    private List<Canvas> body = new ArrayList<>();
    private List<Integer[]> positionHistory = new ArrayList<>();
    
    public Snake() {
        createSnake();
    }
    
    private void createSnake() {
        this.head = CanvasUtils.buildSquare(Color.GREEN);
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
        
        for(int i=0; i<body.size(); i++) {
            Canvas bodyPart = body.get(i);
            Integer[] position = this.positionHistory.get(this.positionHistory.size() - (i + 1));
            bodyPart.setTranslateX((position[0]));
            bodyPart.setTranslateY((position[1]));
        }
        
        this.positionHistory.add(new Integer[] {x, y});
        
        if(positionHistory.size() > body.size() + 1) {
            positionHistory.remove(0);
        }
    }
    
    public Canvas reset() {
        resetPosition();
        return this.head;
    }
    
    public void die() {
        GraphicsContext gc = this.getHead().getGraphicsContext2D();
        gc.clearRect(0, 0, Config.SQUARE_SIZE, Config.SQUARE_SIZE);
        gc.setFill(Color.RED);
        gc.fillRect(0, 0, Config.SQUARE_SIZE, Config.SQUARE_SIZE);
    }
    
    public void eat(Scenario scenario) {
        Canvas bodyPart = CanvasUtils.buildSquare(Color.GREEN);
        scenario.add(bodyPart);
        this.body.add(bodyPart);
    }
     
}

