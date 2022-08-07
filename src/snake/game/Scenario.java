package snake.game;

import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.input.KeyEvent;
import javafx.stage.Stage;

public class Scenario {
    
    private Scene scene;
    private Group root = new Group();
    private Snake snake;
    
    public Scenario(Stage primaryStage, Snake snake, Food food) {
        this.scene = new Scene(root, Config.WIDTH, Config.HEIGHT);
        
        primaryStage.setScene(scene);
        primaryStage.setTitle("Snake Game");
        primaryStage.show();
        
        this.snake = snake;
        
        root.getChildren().add(snake.getHead());
        root.getChildren().add(food.getFood());
    }
    
    public void setKeyPressed(EventHandler<? super KeyEvent> action) {
        this.scene.setOnKeyPressed(action);
    }
    
    public void showGameOver(EventLoop eventLoop) {
        TryAgainButton tryAgainButton = new TryAgainButton(e -> {
            clean();
            this.root.getChildren().add(this.snake.reset());
            eventLoop.startLoop();
        });
        
        this.root.getChildren().add(tryAgainButton);
    }
    
    private void clean() {
        this.root.getChildren().remove(0, this.root.getChildren().size());
    }
    
}
