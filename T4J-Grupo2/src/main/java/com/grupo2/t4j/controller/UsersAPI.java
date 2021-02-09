package com.grupo2.t4j.controller;

import com.grupo2.t4j.api.UsersAPIAdapter;
import com.grupo2.t4j.model.Email;
import com.grupo2.t4j.model.Password;
import org.json.JSONObject;

import java.io.Serializable;

public class UsersAPI implements Serializable {

    UsersAPIAdapter usersAPIAdapter;

    public UsersAPI() {
        String app_key = "IBD0DEHBDID62EB1EAZBEoA95E3cB5BD5135d01F0FqE6eDDoD4yDEX05RFEF19q9BY04KBE03A919hAFM06";
        usersAPIAdapter = new UsersAPIAdapter(app_key);
    }

    public boolean login(String user_id, Password password) {
        return usersAPIAdapter.login(user_id, password);
    }

    public boolean logout() {
        return usersAPIAdapter.logout();
    }

    public String getEmail() {
        String session = usersAPIAdapter.getSession();
        JSONObject bodyJSON = new JSONObject(session);
        return bodyJSON.getString("email");
    }

    public boolean registerUserWithRoles(String username, Email email, Password password, String rolenames) {
        return usersAPIAdapter.registerUserWithRoles(username, email, password, rolenames);
    }

    public String getContext() {
        return usersAPIAdapter.getContext();
    }

    public String getRole() {
        String session = usersAPIAdapter.getSession();
        JSONObject bodyJSON = new JSONObject(session);
        return bodyJSON.getString("rolenames");
    }


}
