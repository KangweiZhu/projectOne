package projectone;

import java.util.Scanner;

public class GymManager {

    private final int ISNOTFOUND = -1;
    private final int INDEX_OF_COMMAND = 0;
    private final int INDEX_OF_FIRST_CLASS = 0;
    private final int INDEX_OF_CLASS_NAME = 1;
    private final int INDEX_OF_SECOND_CLASS = 1;
    private final int INDEX_OF_FIRSTNAME = 1;
    private final int INDEX_OF_LASTNAME = 2;
    private final int INDEX_OF_THIRD_CLASS = 2;
    private final int INDEX_OF_DOB = 3;
    private final int MAXCLASS_SIZE = 3;
    private final int INDEX_OF_EXPIRATION_DATE = 4;
    private final int INDEX_OF_LOCATION = 5;


    Scanner sc = new Scanner(System.in);
    MemberDatabase memberDB = new MemberDatabase();
    FitnessClass[] fitnessClasses = new FitnessClass[MAXCLASS_SIZE];

    public void run() {
        System.out.println("Gym Manager running...”");
        String command;
        String[] cmdLine;
        addClasses();
        while (sc.hasNext()) {
            command = sc.nextLine();
            cmdLine = command.split("\\s");
            if (command.length() == 0) {
                System.out.println();
                continue;
            }
            switch (cmdLine[0]) {
                case "A" -> A(cmdLine);
                case "R" -> R(cmdLine);
                case "P" -> P();
                case "PC" -> PC();
                case "PN" -> PN();
                case "PD" -> PD();
                case "S" -> S();
                case "C" -> C(cmdLine);
                case "D" -> D(cmdLine);
                case "Q" -> Q();
                default -> System.out.println(cmdLine[0] + " is an invalid command!");
            }
        }
    }

    private void addClasses() {
        fitnessClasses[INDEX_OF_FIRST_CLASS] = new FitnessClass("Pilates", "Jennifer", Time.Pilates, memberDB, fitnessClasses);
        fitnessClasses[INDEX_OF_SECOND_CLASS] = new FitnessClass("Spinning", "Denise", Time.Spinning, memberDB, fitnessClasses);
        fitnessClasses[INDEX_OF_THIRD_CLASS] = new FitnessClass("Cardio", "Kim", Time.Cardio, memberDB, fitnessClasses);
    }

    private void A(String[] cmdLine) {             //add a member
        String newLocation = cmdLine[INDEX_OF_LOCATION].toUpperCase();
        Date dob = new Date(cmdLine[INDEX_OF_DOB]);
        Date expireDate = new Date(cmdLine[INDEX_OF_EXPIRATION_DATE]);
        if (dob.isValidDob() && expireDate.isValidExpiration() && isValidLocation(newLocation)) {
            Member member = new Member(cmdLine[INDEX_OF_FIRSTNAME], cmdLine[INDEX_OF_LASTNAME], dob, expireDate, Location.valueOf(newLocation));
            if (memberDB.add(member)) {
                System.out.println(member.getFname() + " " + member.getLname() + " added");
            } else {
                System.out.println(member.getFname() + " " + member.getLname() + " already exist in database.");
            }
        }
    }//        A April March 3/31/1990 6/30/2023 Piscataway

    private void R(String[] cmdLine) {             //remove a member
        Date date = new Date(cmdLine[INDEX_OF_DOB]);
        Member member = new Member(cmdLine[INDEX_OF_FIRSTNAME], cmdLine[INDEX_OF_LASTNAME], date);

        if (memberDB.remove(member)) {  //found
            System.out.println(member.getFname() + " " + member.getLname() + " removed.");
        } else {
            System.out.println(member.getFname() + " " + member.getLname() + " is not in the database.");
        }
    }

    private void P() {
        if (memberDB.getSize() == 0) {
            memberDB.print();
        } else {
            System.out.println();
            System.out.println("-list of members-");
            memberDB.print();
        }
    }

    private void PC() {
        memberDB.printByCounty();
    }

    private void PN() {
        memberDB.printByName();
    }

    private void PD() {
        memberDB.printByExpirationDate();
    }

    private void S() {
        System.out.println();
        System.out.println("-Fitness Classes-");
        for (int i = 0; i < fitnessClasses.length; i++) {
            fitnessClasses[i].printSchedule();
        }
        System.out.println();
    }

    private void C(String[] cmdLine) {
        String fName = cmdLine[INDEX_OF_FIRSTNAME + 1];
        String lName = cmdLine[INDEX_OF_LASTNAME + 1];
        String className = cmdLine[INDEX_OF_CLASS_NAME];
        Date dob = new Date(cmdLine[INDEX_OF_DOB + 1]);
        Member findMember = new Member(fName, lName, dob);
        if (memberDB.contains(findMember) != ISNOTFOUND) {
            findMember = memberDB.returnMember(memberDB.contains(findMember));
            for (int i = 0; i < fitnessClasses.length; i++) {
//                System.out.println("                                    Tested: " + fitnessClasses[i].getFitnessClassName() + " i: "+ i);
                if ((fitnessClasses[i].getFitnessClassName()).equalsIgnoreCase(className)) {
                    fitnessClasses[i].checkIn(findMember, className, fitnessClasses, memberDB);
                    return;
                }
            }
            System.out.println(className + " class does not exist");
        } else {
            System.out.println(fName + " " + lName + " " + dob.toString() + " is not in the database.");
        }
    }

    private void D(String[] cmdLine) {
        String className = cmdLine[INDEX_OF_CLASS_NAME];
        String fName = cmdLine[INDEX_OF_FIRSTNAME + 1];
        String lName = cmdLine[INDEX_OF_LASTNAME + 1];
        Date dob = new Date(cmdLine[INDEX_OF_DOB + 1]);
        Member member = new Member(fName, lName, dob);
        for (int i = 0; i < fitnessClasses.length; i++) {
            if (fitnessClasses[i].getFitnessClassName().equalsIgnoreCase(className)) {
                fitnessClasses[i].drop(new Member(fName, lName, dob), memberDB);
                return;
            }
        }
        System.out.println(className + " class does not exist");
    }

    private void Q() {
        System.out.println("Gym Manager terminated.");
        System.exit(0);
//        sc.close();
    }

    private void doPrint() {
        memberDB.print();
    }

    private boolean isValidLocation(String loc) {
        for (Location location : Location.values()) {
            if (location.toString().equalsIgnoreCase(loc)) {
                return true;
            }
        }
        System.out.println(loc + ": invalid location!");
        return false;
    }
}