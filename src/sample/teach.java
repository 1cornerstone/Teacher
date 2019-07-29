package sample;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

import javafx.scene.control.Button;
import javafx.scene.text.Font;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.concurrent.atomic.LongAccumulator;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class teach {

    long number = 0;

    Label text, label,sample = new Label();
    Button back, next, nexts, backs;
    ArrayList<String> write;
    int listcounter = 0;


    HBox bottombutton() {

        next = new Button("NEXT");
        next.setId("proceed");

        next.setAlignment(Pos.CENTER);
        next.setPrefSize(200, 15);
        next.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                listcounter = 0;
                write = null;
                number += 1;
                text.setText(String.valueOf(number));
                changeboard();

                if (number == 0) {
                    back.setVisible(false);
                } else {
                    back.setVisible(true);
                }


            }
        });

        back = new Button("BACK");
        back.setId("proceed");
        back.setVisible(false);
        back.setAlignment(Pos.CENTER);
        back.setPrefSize(200, 15);
        back.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                number -= 1;
                write = null;
                listcounter = 0;

                if (number == 0) {
                    back.setVisible(false);
                    text.setText(String.valueOf(number));

                } else {
                    text.setText(String.valueOf(number));

                }
                changeboard();
            }
        });


        HBox hBox = new HBox(back, next);
        hBox.setAlignment(Pos.TOP_LEFT);
        hBox.setSpacing(12);


        return hBox;
    }

    HBox title(){

        Label label = new Label("TEACHER");
        label.setAlignment(Pos.CENTER);
        label.setId("title");

        HBox box  = new HBox(label);
        box.setAlignment(Pos.CENTER);
        return box;
    }

    HBox gotonum() {

        TextField startfrom = new TextField();
        startfrom.setPromptText("Start Learning from where");
        startfrom.setId("field");
        startfrom.setPrefWidth(250);
        startfrom.setPrefHeight(30);


        Button start = new Button("GO TO");
        start.setId("proceed");
        start.setPrefSize(100, 15);
        start.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                listcounter = 0;
                if (!regrexNum(startfrom.getText().toString().trim())){

                    startfrom.setText("");
                    startfrom.setPromptText("PLS ENTER DIGIT");

                }else if (!isEmpty(startfrom)) {

                    number = Long.parseLong(startfrom.getText().toString());
                    text.setText(String.valueOf(number));

                    if (number <= 0) {

                        back.setVisible(false);
                        number = 0;
                        text.setText(String.valueOf(number));

                    } else {
                        back.setVisible(true);

                        text.setText(String.valueOf(number));


                    }


                }
                sample.setText(String.valueOf(text.getText().toString().charAt(0)));

                changeboard();
            }
        });

        HBox box = new HBox(startfrom, start);
        box.setSpacing(10);
        box.setPadding(new Insets(80, 10, 10, 10));
        return box;
    }

    VBox number() {

        text = new Label();
        text.setText(String.valueOf(number));
        text.setAlignment(Pos.CENTER_RIGHT);
        text.setFont(new Font(70));
        text.setWrapText(true);

        text.setPrefWidth(350);
        text.setId("number");

        VBox vBox = new VBox(gotonum(), text);
        vBox.setAlignment(Pos.CENTER);
        vBox.setPadding(new Insets(10, 10, 10, 10));

        return vBox;
    }

    BorderPane center() {

        BorderPane centernode = new BorderPane();

        nexts = new Button("NEXT STEP");
        nexts.setId("proceed");
        nexts.setVisible(false);
        nexts.setAlignment(Pos.CENTER);
        nexts.setPrefSize(200, 15);
        nexts.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                listcounter++;

                if (listcounter < write.size()) {
                    String num = String.valueOf(listcounter);

                    label.setText(write.get(listcounter));
                    backs.setVisible(true);
                    sample.setText(String.valueOf(text.getText().toString().charAt(listcounter)));

                } else {
                    listcounter--;
                    nexts.setVisible(false);
                    sample.setText(String.valueOf(text.getText().toString().charAt(listcounter)));

                }


            }
        });


        backs = new Button("BACK");
        backs.setId("proceed");
        backs.setAlignment(Pos.CENTER);
        backs.setVisible(false);
        backs.setPrefSize(200, 15);
        backs.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                listcounter--;

                if (listcounter >= 0) {
                    String num = String.valueOf(listcounter);

                    label.setText(write.get(listcounter));
                    nexts.setVisible(true);
                    sample.setText(String.valueOf(text.getText().toString().charAt(listcounter)));

                } else {
                    listcounter++;
                    backs.setVisible(false);
                    sample.setText(String.valueOf(text.getText().toString().charAt(listcounter)));

                }

            }
        });

        HBox hBox = new HBox(backs, nexts);
        hBox.setAlignment(Pos.CENTER);
        hBox.setSpacing(12);

        sample.setAlignment(Pos.CENTER);
        sample.setId("sample");
        sample.setPrefWidth(500);


        HBox box = new HBox(sample);
        box.setAlignment(Pos.CENTER);

        centernode.setTop(box);
        centernode.setCenter(centerboard());
        centernode.setBottom(hBox);
        return centernode;
    }

    Boolean isEmpty(TextField data) {

        return data.getText().contentEquals("") || data.getText().isEmpty();

    }

    String array(String number) {


        HashMap<String, String> map = new HashMap<>();
        map.put("0", "Start on top \nand round you go.");
        map.put("1", "Go Straight down \nand then you are done.");
        map.put("2", "Make a candy cane \nand then a shoe.");
        map.put("3", "Around a tree \n Around a tree.");
        map.put("4", "Down across \n and down some more.");
        map.put("5", "take a dive\nSwin around.");
        map.put("6", "Slide down and\nTo pick up sticks.");
        map.put("7", "Straight across and\nDown from heaven.");
        map.put("8", "make a 5 but don`t just wait come back up.");
        map.put("9", "Make a loop and then a line.");
        map.put("welcome", "LEARN TO WRITE FIGURE");


        return map.get(number);
    }

    Label centerboard() {


        label = new Label();
        label.setWrapText(true);
        label.setText(array("0"));
        label.setId("board");
        label.setPrefWidth(500);
        sample.setText("0");


        return label;

    }

    void changeboard() {

        String data = text.getText().toString();
        long num = Long.parseLong(text.getText().toString());


        if (num > 9) {
            write = new ArrayList<>();

            for (int i = 0; i < data.length(); i++) {

                write.add(array(String.valueOf(data.charAt(i))));

            }

            label.setText(write.get(0));
            nexts.setVisible(true);
            sample.setText(String.valueOf(text.getText().toString().charAt(0)));


        } else {
            sample.setText(text.getText().toString());
            label.setText(array(text.getText().toString()));
            nexts.setVisible(false);
            backs.setVisible(false);
        }

    }

    boolean regrexNum(String data) {

        String regrex = "[0-9]*";

        Pattern pattern = Pattern.compile(regrex);

        Matcher matcher = pattern.matcher(data);
        if (matcher.matches()) {
            return true;
        } else {
            return false;
        }

    }



}
