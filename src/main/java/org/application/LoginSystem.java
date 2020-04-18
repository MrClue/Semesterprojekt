package org.application;

import java.util.ArrayList;
import java.util.List;

public class LoginSystem {


    List<Role> list = new ArrayList<>();

    public boolean login(String username, String password){
        list.add(new Role("yusaf", "tyk", 1, 1));
        list.add(new Role("villy", "tyk", 1, 1));
        boolean valid = false;
        for (int i = 0; i < list.size() ; i++) {
            if (list.get(i).getName().compareTo(username) == 0 && list.get(i).getPassword().compareTo(password) == 0){
                valid = true;
            }
        }
        return valid;
    }

}
