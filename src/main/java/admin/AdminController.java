package admin;

import role.LoggedInRole;
import spark.Request;
import spark.Response;
import spark.Route;
import util.Path;

import java.util.HashMap;
import java.util.Map;

import static login.LoginController.ATTR_NAME;
import static login.LoginController.ATTR_ROLE;
import static login.LoginController.isAdmin;
import static util.ViewUtil.render;

public class AdminController {

    public static Route serveAdminHomePage = (Request request, Response response) -> {
        if (isAdmin(request)) {
            Map<String, Object> model = new HashMap<>();
            model.put("page_title", "Web-Template");
            model.put("text_title", "Välkommen admin");
            model.put("home_link", Path.Web.ADMIN_HOME);
            model.put(ATTR_ROLE, LoggedInRole.ADMIN.getRoleName());
            model.put(ATTR_NAME, request.session().attribute(ATTR_NAME));
            return render(model, Path.Template.ADMIN_HOME);
        } else {
            response.redirect(Path.Web.LOGIN);
            return null;
        }
    };
}
