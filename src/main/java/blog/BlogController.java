package blog;

import spark.Request;
import spark.Response;
import spark.Route;
import util.Path;

import java.util.HashMap;
import java.util.Map;

import static login.LoginController.isLoggedIn;
import static util.RedirectUtil.redirectToRoleHome;
import static util.ViewUtil.render;

public class BlogController {

    public static Route serveBlogPage = (Request request, Response response) -> {
        if (isLoggedIn(request)) {
            redirectToRoleHome(request, response);
            return null;
        }

        Map<String, Object> model = new HashMap<>();
        model.put("page_title", "Title");
        return render(model, Path.Template.BLOG);
    };

}
