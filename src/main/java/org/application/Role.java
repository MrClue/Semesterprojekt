package org.application;

public class Role {
    private final String username, password;
    private final int authorityLevel;
    private final int ID;

    @Override
    public String toString() {
        return "Role{" +
                "username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", authorityLevel=" + authorityLevel +
                ", ID=" + ID +
                '}';
    }

    public Role(String username, String password, int authorityLevel, int ID) {
        this.username = username;
        this.password = password;
        this.authorityLevel = authorityLevel;
        this.ID = ID;
    }

    public String getName() {
        return this.username;
    }

    public String getPassword() {
        return this.password;
    }

    public int getAuthLvl() {
        return this.authorityLevel;
    }

}
