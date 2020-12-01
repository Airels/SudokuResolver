package sr.main;

import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import sr.controller.JavaFXController;
import sr.view.Grid;

public class AppMain extends Application {

    public static final double
            WIDTH = 800,
            HEIGHT = 800;

    private static double STAGE_WIDTH, STAGE_HEIGHT;


    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("Sudoku Resolver");
        Group root = new Group();

        primaryStage.setScene(new Scene(root, WIDTH, HEIGHT));
        primaryStage.setResizable(false);

        primaryStage.setOnCloseRequest(e -> System.exit(0));
        primaryStage.addEventHandler(KeyEvent.KEY_PRESSED, JavaFXController.getKeyEventHandler());
        primaryStage.addEventHandler(MouseEvent.MOUSE_PRESSED, JavaFXController.getMouseEventHandler());

        STAGE_WIDTH = primaryStage.getScene().getWidth();
        STAGE_HEIGHT = primaryStage.getScene().getHeight();

        Grid grid = new Grid(root);
        grid.init();

        primaryStage.show();
    }

    public static double STAGE_HEIGHT() {
        return STAGE_HEIGHT;
    }

    public static double STAGE_WIDTH() {
        return STAGE_WIDTH;
    }
}
