package org.example.model;

public class User {
    private String name;
    private String password;
    //private Boolean isOnline;

    public User(String name, String password) {
        this.password = password;
        this.name = name;
    }

//    public void setOnline(Boolean online) {
//        isOnline = online;
//    }
//
//    public Boolean getOnline() {
//        return isOnline;
//    }

    public String getPassword() {
        return password;
    }

    public String getName() {
        return name;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof User){
            User u = (User) obj;
            return this.name.equals(u.name);
        }
        return false;
    }

    @Override
    public int hashCode() {
        return name.hashCode();
    }

    @Override
    public String toString() {
        return name;
    }
}
