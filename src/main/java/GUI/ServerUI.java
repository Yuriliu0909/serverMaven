package GUI;

import javafx.application.Application;
import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;
import server.ServerService;

public class ServerUI extends Application {
    private TextArea textArea = new TextArea();
    private TextField port = new TextField();
    @Override
    public void start(Stage primaryStage) throws Exception {

        // TODO Auto-generated method stub
        primaryStage.setTitle("server");

        HBox hbox = new HBox();
        Scene scene = new Scene(hbox, 200, 100);
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


        //Defining the Clear button
        Button close = new Button("close");
        GridPane.setConstraints(close, 1, 1);


        hbox.getChildren().addAll(textArea,port,start,close);
    }

    public static void main(String[] args) {

        Application.launch(args);
    }

}
