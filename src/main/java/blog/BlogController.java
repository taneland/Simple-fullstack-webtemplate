package blog;

import data.BlogPost;
import database.Database;
import database.DatabaseHandler;
import spark.Request;
import spark.Response;
import spark.Route;
import util.Path;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static login.LoginController.isAdmin;
import static login.LoginController.isLoggedIn;
import static util.RedirectUtil.redirectToRoleHome;
import static util.ViewUtil.render;

public class BlogController {

    public static Route serveBlogPage = (Request request, Response response) -> {
        boolean loggedIn = isLoggedIn(request);
        if (loggedIn) {
            redirectToRoleHome(request, response);
            return null;
        }

        Map<String, Object> model = new HashMap<>();
        Database db = DatabaseHandler.getDatabase();
        List<BlogPost> blogPosts = db.getSelector().getAllBlogPosts();
        model.put("is_logged_in", loggedIn);
        model.put("all_blog_posts", blogPosts);
        model.put("page_title", "Title");
        return render(model, Path.Template.BLOG);
    };



}
