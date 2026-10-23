
package com.scc;

public class Main {

    public static void main(String[] args) {

        DatabaseConnection.initialize();

        ScenarioMenu menu = new ScenarioMenu();

        menu.start();
    }
}
