package com.dp.view;

import com.dp.service.Service;
import com.dp.view.command.Command;
import com.dp.view.command.ExitCommand;
import com.dp.view.command.PredictCommand;
import com.dp.view.command.SaveCommand;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class View {

    Map<String, Command> commands = new HashMap<>();

    Service service;

    private void initializeCommands() {
        commands.put("0", new ExitCommand("0", "Exit."));
        commands.put("1", new PredictCommand("1", "Generate predictive report.", service));
        commands.put("2", new SaveCommand("2", "Save reports.", service));
    }

    public View(Service service) {
        this.service = service;
        initializeCommands();
    }

    public void run(){
        Scanner scanner = new Scanner(System.in);
        while (true) {
            showMenu();
            String key = scanner.nextLine();
            Command requestedCommand = commands.get(key);
            if (requestedCommand != null){
                requestedCommand.execute();
            }
            else {
                System.out.println("Invalid option!");
            }
        }
    }

    public void showMenu(){
        this.commands.entrySet().stream().forEach(
                item -> {System.out.println(item.getKey() + ". " + item.getValue().getText());}
        );
        System.out.println("Enter your command: >> ");
    }
}
