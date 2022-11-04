package com.nixalevel.logic;

import com.nixalevel.cli.CLI;
import com.nixalevel.command.CommandFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class LSApp {

    private static final Logger log = LoggerFactory.getLogger(LSApp.class);

    public static void main(String[] args) {

        System.exit(new LSApp().run());

    }

    public int run() {
        try (var serviceRegistry = new StandardServiceRegistryBuilder()
                .configure()
                .build();
            var sessionFactory = new Configuration().buildSessionFactory(serviceRegistry)) {var commandFactory = new CommandFactory(sessionFactory);
            var cli = new CLI(commandFactory);
            cli.run();
        } catch (Exception e) {
            log.error("Error during user interaction", e);
            return -1;
        }
        return 0;
    }
}