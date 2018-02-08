package util;

import java.util.HashMap;
import java.util.Map;

import role.LoggedInRole;
import spark.ModelAndView;
import spark.Request;
import spark.Response;
import spark.Route;
import spark.template.thymeleaf.ThymeleafTemplateEngine;

import static login.LoginController.*;

public class ViewUtil {

    public static String render(Map<String, Object> model, String templatePath) {
        return new ThymeleafTemplateEngine().render(new ModelAndView(model, templatePath));
    }

    public static Route notFound = (Request request, Response response) ->
            displayErrorPage(request, "404 Not Found", "Kunde tyvärr ej hitta sidan du söker");
    public static Route internalServerError = (Request request, Response response) ->
            displayErrorPage(request, "500 Internal Server Error", "Något gick fel");

    private static String displayErrorPage(Request request, String title, String message) {
        Map<String, Object> model = new HashMap<>();
        model.put("page_title", "VFU-portal SOCIONOM");
        model.put("text_title", title);
        model.put("text_content", message);
        if (isLoggedIn(request)) {
            model.put(ATTR_NAME, request.session().attribute(ATTR_NAME));
            if (isAdmin(request)) {
                model.put(ATTR_ROLE, LoggedInRole.ADMIN.getRoleName());
                model.put("home_link", Path.Web.ADMIN_HOME);
                return render(model, Path.Template.ADMIN_ERROR);
            }
        }

        return render(model, Path.Template.ERROR);
    }
}