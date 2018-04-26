package org.softuni.ruk.terminal;

import org.softuni.ruk.controllers.*;
import org.softuni.ruk.io.interfaces.ConsoleIO;
import org.softuni.ruk.io.interfaces.FileIO;
import org.springframework.beans.factory.ListableBeanFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;

import java.io.IOException;
import java.util.Map;

import static org.softuni.ruk.utils.Config.*;

@Component
@Transactional
public class Terminal implements CommandLineRunner {

    private Map<String, Object> controllers;
    private ConsoleIO consoleIO;
    private FileIO fileIO;

    @Autowired
    public Terminal(ListableBeanFactory listableBeanFactory, ConsoleIO consoleIO, FileIO fileIO) {
        this.controllers = listableBeanFactory.getBeansWithAnnotation(Controller.class);
        this.consoleIO = consoleIO;
        this.fileIO = fileIO;
    }

    @Override
    public void run(String... args) throws Exception {

        importFromFiles();


        System.out.println("test");
    }

    private void importFromFiles() throws IOException {
        // import Branches from json
        this.consoleIO.write(
                ((BranchController) this.controllers.get("BranchController"))
                        .importDataFromJSON(this.fileIO.read(BRANCHES_IMPORT_JSON)));

        // import employees from json
        this.consoleIO.write(
                ((EmployeeController) this.controllers.get("EmployeeController"))
                        .importDataFromJSON(this.fileIO.read(EMPLOYEES_IMPORT_JSON)));
        // import clients from json
        this.consoleIO.write(
                ((ClientsController) this.controllers.get("ClientsController"))
                        .importDataFromJSON(this.fileIO.read(CLIENTS_IMPORT_JSON)));

        // import bank accounts from xml
        this.consoleIO.write(
                ((BankAccountController) this.controllers.get("BankAccountController"))
                        .importDataFromXML(this.fileIO.read(BANK_ACCOUNTS_IMPORT_XML)));

        // import cards from xml
        this.consoleIO.write(
                ((CardController) this.controllers.get("CardController"))
                        .importDataFromXML(this.fileIO.read(CARDS_IMPORT_XML)));

    }

}
