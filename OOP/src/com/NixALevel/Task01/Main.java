package com.NixALevel.Task01;

public class Main {
    public static void main(String[] args) {
        Group group = new Group("KH421B", 15);
        System.out.println("List of group");
        group.printGroup();
        System.out.println("---using downcast----");
        group.printContractStudent_usingDowncast();
        System.out.println("---using downcast and override method----");
        group.printContractStudent_usingOverrideMethod();
    }
}
