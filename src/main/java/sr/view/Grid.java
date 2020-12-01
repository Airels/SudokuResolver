package sr.view;

import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.util.ArrayList;
import java.util.List;

public class Grid {
    private Group root;
    private List<GCase> cases;

    public Grid(Group root) {
        this.root = root;
        cases = new ArrayList<>();
    }

    public void init() {
        for (int i = 0; i < 81; i++) {
            GCase gCase = new GCase(i);
            cases.add(gCase);
            addNode(gCase.getFxModel());
        }
    }

    public void addNode(Node node) {
        root.getChildren().add(node);
    }
}
