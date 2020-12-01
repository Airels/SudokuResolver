package sr.controller;

import javafx.event.EventHandler;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;

public class JavaFXController {
    public static EventHandler<? super MouseEvent> getMouseEventHandler() {
        return (EventHandler<MouseEvent>) event -> {
            switch (event.getButton()) {
                case PRIMARY:
                    System.out.println("PRIMARY");
                    break;
                case SECONDARY:
                    System.out.println("SECONDARY");
                    break;
                case MIDDLE:
                    System.out.println("MIDDLE");
                    break;
            }
        };
    }

    public static EventHandler<? super KeyEvent> getKeyEventHandler() {
        return (EventHandler<KeyEvent>) event -> {
            System.out.println(event.getCode());
        };
    }
}
