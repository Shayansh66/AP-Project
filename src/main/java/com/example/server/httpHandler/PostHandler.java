package main.java.com.example.server.httpHandler;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import main.java.com.example.server.controllers.PostController;
import main.java.com.example.server.utils.JwtAuth;
import io.jsonwebtoken.Claims;

import java.io.IOException;
import java.io.OutputStream;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.HashMap;
import java.util.Map;

public class PostHandler implements HttpHandler {
}
