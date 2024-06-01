package httpHandler;

import java.sql.SQLException;
import spark.Request;
import spark.Response; 

import main.java.com.example.server.controllers.PostController;

public class PostHandler {
    private final PostController postController ;

    public PostHandler() throws SQLException
    {
        postController = new PostController(); 
    }


    public Object handleGetPosts (Response) {

    }
}
