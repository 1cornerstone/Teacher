package sample;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.stage.Stage;

public class Main extends Application {
    BorderPane mainboard;
    teach teach = new teach();

    @Override
    public void start(Stage primaryStage) throws Exception {

        mainboard = new BorderPane();
        mainboard.setId("mainpanel");
        mainboard.setCenter(splash());

        Scene scene = new Scene(mainboard, 1200, 700);
        String css = this.getClass().getResource("/sample/css/style.css").toExternalForm();
        scene.setUserAgentStylesheet(css);

        primaryStage.setScene(scene);
        primaryStage.setTitle("PROJECT 405");
        primaryStage.setResizable(false);
        primaryStage.show();
    }

    Label top() {

        Label appname = new Label("\t\t NAME: NAME OF THE STUDENT \n\t\t MATRIC NO: 14???? \n\t\t COURSE: CSE 405" +
                " \n\t\t PROJECT TITLE: LEARN TO WRITE FIGURES.  ");
        appname.setFont(Font.font("Arial", 22));
        appname.setAlignment(Pos.CENTER_LEFT);
        appname.setId("title");
        appname.setPadding(new Insets(60, 10, 12, 21));


        return appname;
    }

    ImageView image() {

        String imagepath = this.getClass().getResource("/sample/css/learn.jpg").toExternalForm();

        Image image = new Image(imagepath);
        ImageView imageView = new ImageView(image);
        imageView.setId("imageview");
        imageView.setFitHeight(200);
        imageView.setFitWidth(200);
        imageView.setPreserveRatio(true);

        return imageView;
    }

    Button proceed() {

        Button proceed = new Button("PROCEED");
        proceed.setPrefSize(200, 35);
        proceed.setId("proceed");
        proceed.setAlignment(Pos.CENTER);
        proceed.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {

                mainboard.setCenter(teach.center());
                mainboard.setBottom(teach.bottombutton());
                mainboard.setLeft(teach.number());
                mainboard.setTop(teach.title());


            }
        });

        return proceed;
    }

    VBox splash() {
        VBox vBox = new VBox(image(), top(), proceed());
        vBox.setAlignment(Pos.CENTER);
        return vBox;
    }


    public static void main(String[] args) {
        launch(args);
    }


}
