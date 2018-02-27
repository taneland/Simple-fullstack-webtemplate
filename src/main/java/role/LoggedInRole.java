package role;

public enum LoggedInRole {

    ADMIN("Admin"),
    USER("User");

    private String roleName;

    LoggedInRole(String roleName) {
        this.roleName = roleName;
    }

    public String getRoleName() {
        return roleName;
    }

}
