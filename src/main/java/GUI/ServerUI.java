package GUI;

import javafx.application.Application;
import javafx.fxml.FXML;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import server.ServerService;

public class ServerUI extends Application {
    private TextArea textArea = new TextArea();
    private TextField port = new TextField();
    @Override
    public void start(Stage primaryStage) throws Exception {

        // TODO Auto-generated method stub
        primaryStage.setTitle("server");
        VBox vbox = new VBox();
        HBox hbox = new HBox();
        vbox.setSpacing(50);
        hbox.setSpacing(20);
        hbox.setAlignment(Pos.CENTER);
        textArea.setMinSize(400,400);
        port.setText("enter the port number,eg:8080");
        port.setMinSize(300,10);
        VBox.setMargin(textArea, new Insets(10, 10, 10, 10));
        vbox.setAlignment(Pos.CENTER);
        Scene scene = new Scene(vbox, 600, 600);
        primaryStage.setScene(scene);
        primaryStage.show();

        //Defining the Submit button
        Button start = new Button("Start!");
        GridPane.setConstraints(start, 1, 0);
        start.setOnAction(value ->  {
            System.out.println("Server started");
            ServerService ss = new ServerService(textArea,port);
            //need to start the serverservice
            ss.start();
        });
        hbox.getChildren().addAll(port,start);
        vbox.getChildren().addAll(hbox,textArea);
    }

    public static void main(String[] args) {

        Application.launch(args);
    }

}
