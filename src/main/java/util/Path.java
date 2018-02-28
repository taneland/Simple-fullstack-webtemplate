package util;

public class Path {

    public static class Web {
        public static final String HOME = "/";
        public static final String CONTACT = "/contact/";
        public static final String BLOG = "/blog/";
        public static final String LOGIN = "/login/";
        public static final String LOGOUT = "/logout/";
        public static final String ADMIN_ADD_BLOG_POST = "/admin/add-blog-post/";
        public static final String ADMIN_HOME = "/admin/";
        public static final String ADMIN_BLOG = "/admin/blog/";

    }
    public static class Template {
        public static final String INDEX = "logged_out/index";
        public static final String CONTACT = "logged_out/contact";

        public static final String ERROR = "logged_out/error";
        public static final String BLOG = "logged_out/blog";


        public static final String ADMIN_HOME = "logged_in/admin/admin";
        public static final String ADMIN_BLOG = "logged_in/admin/blog";
        public static final String ADMIN_ERROR = "logged_in/admin/error";
    }
}
