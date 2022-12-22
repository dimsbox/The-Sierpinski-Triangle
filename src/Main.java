import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import java.util.Random;
//import static javafx.application.Application.launch;

public class Main extends Application {

    Circle apexTop;
    Circle apexLeft;
    Circle apexRight;

    int count = 10000;

    public static void main(String[] args) {

        launch(args);
    }

    @Override
    public void start(Stage stage) throws Exception {

        Text text = new Text(20,50,"Случайным образом выбирается вершина треугольника (аттрактор), к которой из начальной точки, \n" +
                "находящейся в любом месте внутри треугольника, строится отрезок, по середине этого отрезка \n" +
                "ставится следующая точка, для нее случайно выбирается аттрактор, и т.д. Постороено из " + count + " точек");

        Circle point1 = new Circle();
        point1.setCenterX(640.0f);
        point1.setCenterY(100.0f);
        point1.setRadius(1.0f);
        apexTop = point1;

        Circle point2 = new Circle();
        point2.setCenterX(340.0f);
        point2.setCenterY(620.0f);
        point2.setRadius(1.0f);
        apexLeft = point2;

        Circle point3 = new Circle();
        point3.setCenterX(940.0f);
        point3.setCenterY(620.0f);
        point3.setRadius(1.0f);
        apexRight = point3;

        Group root = new Group();
        root.getChildren().add(text);
        root.getChildren().add(apexTop);
        root.getChildren().add(apexLeft);
        root.getChildren().add(apexRight);

        Circle nextPoint = apexTop;
        Random random = new Random();
        int r;

        for (int i=0;i<count;i++) {
            r = random.nextInt(3);
            if (r==0) {
                root.getChildren().add(nextPoint = toTopApex(nextPoint));
            } else if (r==1) {
                root.getChildren().add(nextPoint = toLeftApex(nextPoint));
            } else if (r==2) {
                root.getChildren().add(nextPoint = toRightApex(nextPoint));
            }
        }

        Scene scene = new Scene(root,1280,720, Color.WHITE);

        stage.setScene(scene);
        stage.setTitle("The Sierpinski Triangle");
        stage.setFullScreen(false);
        stage.show();
    }

    private Circle toLeftApex(Circle point) {
        double x = (point.getCenterX() - apexLeft.getCenterX()) / 2 + apexLeft.getCenterX();
        double y = (apexLeft.getCenterY() - point.getCenterY()) / 2 + point.getCenterY();
        return new Circle(x,y,1);
    }
    private Circle toRightApex(Circle point) {
        double x = (apexRight.getCenterX() - point.getCenterX()) / 2 + point.getCenterX();
        double y = (apexRight.getCenterY() - point.getCenterY()) / 2 + point.getCenterY();
        return new Circle(x,y,1);
    }
    private Circle toTopApex(Circle point) {
        double x;
        if (point.getCenterX()<= apexTop.getCenterX()) {
            x = (apexTop.getCenterX() - point.getCenterX()) / 2 + point.getCenterX();
        } else {
            x = (point.getCenterX() - apexTop.getCenterX()) / 2 + apexTop.getCenterX();
        }
        double y = (apexTop.getCenterY() - point.getCenterY()) / 2 + point.getCenterY();
        return new Circle(x,y,1);
    }
}
