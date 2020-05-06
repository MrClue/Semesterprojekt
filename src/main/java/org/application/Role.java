package org.application;

public class Role {
    private String username = null;

    @Override
    public String toString() {
        return "Role{" +
                "username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", authorityLevel=" + authorityLevel +
                ", ID=" + ID +
                '}';
    }

    private String password = null;
    private int authorityLevel;
    private int ID ;

    public Role() {}

    public Role(String username, String password, int authorityLevel, int ID) {
        this.username = username;
        this.password = password;
        this.authorityLevel = authorityLevel;
        this.ID = ID;
    }

    public String getName() {
        return this.username;
    }

    public void setName(String name)  {
        this.username = name;
    }

    public String getPassword() {
        return this.password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getAuthLvl() {
        return this.authorityLevel;
    }

    public void setAuthLvl(int authLevel) {
        this.authorityLevel = authLevel;
    }

    public int getID() {
        return this.ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

}
