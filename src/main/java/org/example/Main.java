package org.example;

import org.example.server.manager.DataBaseManager;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {
        DataBaseManager.connectToDataBase();
        DataBaseManager.insertUser("test_user", "123");
        DataBaseManager.getUsers();
        }
    }
