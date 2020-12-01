package sr.view;

import javafx.scene.Node;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import sr.main.AppMain;

public class GCase implements GObject {
    private Rectangle rectangle;

    @SuppressWarnings("IntegerDivisionInFloatingPointContext")
    GCase(int id) {
        double width = AppMain.STAGE_WIDTH() / 9;
        double height = AppMain.STAGE_HEIGHT() / 9;

        rectangle = new Rectangle((id%9) * width+1, (id/9) * height, width-2, height-2);
    }


    @Override
    public Node getFxModel() {
        return rectangle;
    }
}
