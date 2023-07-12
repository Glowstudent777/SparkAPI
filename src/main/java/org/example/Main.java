package org.example;

import org.eclipse.jetty.http.HttpStatus;
import spark.Spark;

import static spark.Spark.*;

public class Main {
    public static void main(String[] args) {
        port(8000);

        Spark.before((req, res) -> {
            String path = req.pathInfo();
            if (path.endsWith("/"))
                res.redirect(path.substring(0, path.length() - 1));
        });

        path("/api", () -> {
            get("", (req, res) -> HttpStatus.OK_200);
        });
    }
}
