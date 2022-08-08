package snake.game;

import javafx.util.Duration;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.scene.input.KeyCode;

public class EventLoop {
        
    private Scenario scenario;
    private KeyCode currentDirection;
    private Snake snake;
    private Timeline timeline;
    private Food food;
    
    public EventLoop(Scenario scenario, Snake snake, Food food) {
        this.scenario = scenario;
        this.snake = snake;
        this.food = food;
        this.scenario.setKeyPressed(e -> {
            KeyCode keyPressed = e.getCode();
            
            if(keyPressed.equals(KeyCode.RIGHT) && !KeyCode.LEFT.equals(currentDirection)){
                currentDirection = keyPressed;
            }

            if(keyPressed.equals(KeyCode.LEFT)&& !KeyCode.RIGHT.equals(currentDirection)){
                currentDirection = keyPressed;
            }

            if(keyPressed.equals(KeyCode.UP) && !KeyCode.DOWN.equals(currentDirection)){
                currentDirection = keyPressed;
            }

            if(keyPressed.equals(KeyCode.DOWN)&& !KeyCode.UP.equals(currentDirection)){
                currentDirection = keyPressed;
            }
            
        });
        
        startLoop();
    }
    
    public void startLoop() {
        this.timeline = new Timeline();
        KeyFrame keyFrame = new KeyFrame(Duration.millis(200), e -> {
            Integer positionX = this.snake.getPositionX();
            Integer positionY = this.snake.getPositionY();
            
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
                positionY > Config.HEIGHT - Config.SQUARE_SIZE ||
                this.snake.checkCollision(positionX, positionY)) {
                gameOver();
                
            } else {
                
                if (positionX.equals(this.food.getPositionX()) && positionY.equals(this.food.getPositionY())) {
                    this.food.setRandomPosition();
                    this.snake.eat(this.scenario);
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
