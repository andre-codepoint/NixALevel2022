package com.nixalevel.command;

import com.nixalevel.request.InputDTO;
import com.nixalevel.model.ColorHistory;
import org.hibernate.SessionFactory;

import java.util.List;
import java.util.Map;

public class CommandFactory {
    private final SessionFactory sessionFactory;
    public CommandFactory(SessionFactory session) {
        this.sessionFactory = session;
    }
    public Command<Map<String, List<ColorHistory>>> userInput(InputDTO context) {
        return new LSHistoryCommand(sessionFactory, context);
    }
}
