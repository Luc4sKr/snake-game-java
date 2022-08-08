package snake.game;

import javafx.util.Duration;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.scene.input.KeyCode;

public class EventLoop {
        
    private Scenario scenario;
    private KeyCode currentDirection;
    private Snake snake;
    private Timeline timeline = new Timeline();
    private Food food;
    
    public EventLoop(Scenario scenario, Snake snake, Food food) {
        this.scenario = scenario;
        this.snake = snake;
        this.food = food;
        this.scenario.setKeyPressed(e -> {
            KeyCode keyPressed = e.getCode();
            
            if (keyPressed.equals(KeyCode.RIGHT)) {
                currentDirection = keyPressed;
            }
            
            if (keyPressed.equals(KeyCode.LEFT)) {
                currentDirection = keyPressed;
            }
            
            if (keyPressed.equals(KeyCode.UP)) {
                currentDirection = keyPressed;
            }
            
            if (keyPressed.equals(KeyCode.DOWN)) {
                currentDirection = keyPressed;
            }
            
        });
        
        startLoop();
    }
    
    public void startLoop() {
        //Timeline timeline = new Timeline();
        KeyFrame keyFrame = new KeyFrame(Duration.millis(200), e -> {
            Integer positionX = snake.getPositionX();
            Integer positionY = snake.getPositionY();
            
            if (KeyCode.RIGHT.equals(currentDirection)) {
                positionX = positionX + Config.SQUARE_SIZE;
            }
            
            if (KeyCode.LEFT.equals(currentDirection)) {
                positionX = positionX - Config.SQUARE_SIZE;
            }
            
            if (KeyCode.UP.equals(currentDirection)) {
                positionY = positionY - Config.SQUARE_SIZE;
            }
            
            if (KeyCode.DOWN.equals(currentDirection)) {
                positionY = positionY + Config.SQUARE_SIZE;
            }
            
            
            if (positionX < 0 ||
                positionX > Config.WIDTH - Config.SQUARE_SIZE ||
                positionY < 0 ||
                positionY > Config.HEIGHT - Config.SQUARE_SIZE) {
                gameOver();
                
            } else {
                
                if (positionX.equals(food.getPositionX()) && positionY.equals(food.getPositionY())) {
                    food.setRandomPosition();
                }
                    
                this.snake.setPosition(positionX, positionY);
            }
            
            
        });
        
        this.timeline.getKeyFrames().add(keyFrame);
        this.timeline.setCycleCount(Integer.MAX_VALUE);
        this.timeline.play();
        
    }
    
    public void gameOver() {
        this.timeline.stop();
        this.currentDirection = null;
        this.snake.die();
        this.scenario.showGameOver(this);
    }
    
}
