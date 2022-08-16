package com.NixALevel.Task01;

import java.util.Random;

public class Group {
    private String groupNumber;

    public String getGroupNumber() {
        return groupNumber;
    }

    public void setGroupNumber(String groupNumber) {
        this.groupNumber = groupNumber;
    }

    private final Student[] students;

    public Group(String groupNumber, int n) {
        this.groupNumber = groupNumber;
        this.students = new Student[n];
        for (int i = 0; i < n; i++) {
            int random = new Random().nextInt(2);

            if (random == 0) {
                students[i] = new Student("StName" + i, 17 + new Random().nextInt(5));
            } else {
                students[i] = new ContractStudent("StName" + i, 17 + new Random().nextInt(5), 33000);
            }
        }
    }

    public void printContractStudent() {
        for (Student student : students
        ) {
            if (student instanceof ContractStudent) {
                System.out.println(this.groupNumber + " " + student.getName() + " " + ((ContractStudent) student).getValueOfEducation());
            }
        }
    }

    public void printGroup() {
        for (Student student : students
        ) {
            if (student instanceof ContractStudent) {
                System.out.println(this.groupNumber + " " + student.getName() + " " + ((ContractStudent) student).getValueOfEducation());
            }
            else {
                System.out.println(this.groupNumber + " " + student.getName() + " " + "0.0");
            }
        }
    }
    public void printContractStudent_usingOverrideMethod() {
        for (Student student : students
        ) {
            if (student instanceof ContractStudent) {
                System.out.println(this.groupNumber + " " + student.getName() + " " + ((ContractStudent) student).getValueOfEducation());
            }
        }
    }
}
