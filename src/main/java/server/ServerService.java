package server;

import GUI.ServerUI;
import javafx.concurrent.Service;
import javafx.concurrent.Task;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

import java.io.IOException;

public class ServerService extends Service<ServerUI> {
    private TextArea textArea;
    private TextField port;

    public ServerService(TextArea textArea, TextField port) {
        this.textArea = textArea;
        this.port = port;
    }

    @Override
    protected Task<ServerUI> createTask() {
        // TODO Auto-generated method stub

        return new Task<>() {
            @Override
            protected ServerUI call() throws IOException {
                //start server
                ThreadedServer server=new ThreadedServer(textArea, port);
                server.listen();
                return null;
            }
        };

    }

}