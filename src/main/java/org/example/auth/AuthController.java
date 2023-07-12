package org.example.auth;

import org.eclipse.jetty.http.HttpStatus;
import spark.Filter;
import spark.Request;
import spark.Response;
import static spark.Spark.*;

public class AuthController {
    private static final String AUTH_TOKEN = "YOUR_AUTH_TOKEN";

    public static class HandleAuthentication implements Filter {
        @Override
        public void handle(Request req, Response res) {
            String authHeader = req.headers("Authorization");
            if (authHeader == null || !authHeader.startsWith("Bearer ")) {
                res.status(HttpStatus.UNAUTHORIZED_401);
                halt(401, "Unauthorized");
            }

            String authToken = authHeader.substring(7).trim(); // Extract the token after "Bearer " and trim any whitespace
            if (!AUTH_TOKEN.equals(authToken)) {
                res.status(HttpStatus.UNAUTHORIZED_401);
                halt(401, "Unauthorized");
            }
        }
    }
}
