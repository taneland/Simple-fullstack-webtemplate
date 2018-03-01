package experiments;

import data.BlogPost;
import database.Database;
import database.DatabaseHandler;
import spark.Request;
import spark.Response;
import spark.Route;
import util.Path;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static login.LoginController.isLoggedIn;
import static util.RedirectUtil.redirectToRoleHome;
import static util.ViewUtil.render;

public class ExperimentsController {


    public static Route serveExperimentsPage = (Request request, Response response) -> {
        boolean loggedIn = isLoggedIn(request);
        if (loggedIn) {
            redirectToRoleHome(request, response);
            return null;
        }

        Map<String, Object> model = new HashMap<>();
        model.put("page_title", "Title");
        return render(model, Path.Template.EXPERIMENTS);
    };
}
