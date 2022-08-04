package snake.game;

import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Scenario {
    
    private Scene scene;
    private Group root = new Group();
    private Snake snake;
    
    public Scenario(Stage primaryStage, Snake snake) {
        this.scene = new Scene(root, Config.WIDTH, Config.HEIGHT);
        
        primaryStage.setScene(scene);
        primaryStage.setTitle("Snake Game");
        primaryStage.show();
        
        this.snake = snake;
        
        root.getChildren().add(snake.getHead());
    }   
}
