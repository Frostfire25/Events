package co.antiqu.vibeevents.Util;

public enum  Permissions {

    PERM("permissions.admin");

    private String s;

    Permissions(String s) {
        this.s = s;
    }

    public String getPermission() {
        return s;
    }
}
