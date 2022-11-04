package com.nixalevel.cli;

import com.nixalevel.command.Command;
import com.nixalevel.command.CommandFactory;
import com.nixalevel.request.InputDTO;
import com.nixalevel.exceptions.LSException;
import com.nixalevel.model.ColorHistory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.*;

public class CLI {

    private static final Logger log = LoggerFactory.getLogger(CLI.class);

    private final CommandFactory commandFactory;

    List<String> colorList;

    public CLI(CommandFactory commandFactory) {
        this.commandFactory = commandFactory;
        colorList = new ArrayList<>();
    }

    public void run() throws LSException {
        var scanner = new Scanner(System.in);
        System.out.println("Input light nickname:");
        String label = scanner.nextLine();
        System.out.println("Input color list");
        String consoleColorList = scanner.nextLine();
        colorList = Arrays.asList(consoleColorList.split(" "));
        System.out.println("Input interval:");
        long switchInterval = scanner.nextLong();
        scanner.nextLine();
        System.out.println("Input number of switch:");
        long switchNumber = scanner.nextLong();
        scanner.nextLine();
        Command<Map<String, List<ColorHistory>>> command = commandFactory.userInput(
                new InputDTO(label, colorList, switchInterval, switchNumber));
        Map<String, List<ColorHistory>> enter = command.execute();
        outColorHistory(enter);
        StringBuilder result = getInput(enter);
        System.out.println(result);
    }

    private static StringBuilder getInput(Map<String, List<ColorHistory>> input) {
        Set<String> inputSet = input.keySet();
        String key = inputSet.stream()
                .findFirst()
                .orElse(null);
        List<ColorHistory> colorHistory = input.get(key);
        StringBuilder result = new StringBuilder("Light ");
        result.append(key).append(" color switch ").append(colorHistory.get(0).getOldColor().getName());
        for (ColorHistory chist : colorHistory) {
            result.append("' => '").append(chist.getNewColor().getName());
        }
        result.append("'\n");
        return result;
    }

    private static void outColorHistory(Map<String, List<ColorHistory>> input) {
        Set<String> keySetLabel = input.keySet();
        String keyLabel = keySetLabel.stream()
                .findFirst()
                .orElse(null);
        for (ColorHistory colorHistory : input.get(keyLabel)) {
            log.info("'{}' change color '{}' to '{}' at {}", colorHistory.getLight().getLabel(), colorHistory.getOldColor().getName(), colorHistory.getNewColor().getName(), colorHistory.getChangedAt());
        }
    }

}
