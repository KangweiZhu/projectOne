public class FitnessClass {
    private String instructorName;
    private String fitnessClassName;
    private Time classTime;
    private final int NOTFOUND = -1;
    private final MemberDatabase studentsList = new MemberDatabase();
    private MemberDatabase memberDb;
    private FitnessClass[] fitnessClasses;

    public FitnessClass() {

    }

    public FitnessClass(String fitnessClassName, String instructorName, Time classTime, MemberDatabase memberDb, FitnessClass[] fitnessClasses) {
        this.fitnessClassName = fitnessClassName;
        this.instructorName = instructorName;
        this.classTime = classTime;
        this.memberDb = memberDb;
        this.fitnessClasses = fitnessClasses;
    }

    public MemberDatabase getStudentsList() {
        return studentsList;
    }

    public String getInstructor() {
        return instructorName;
    }

    public void setInstructor(String instructorName) {
        this.instructorName = instructorName;
    }

    public String getFitnessClassName() {
        return fitnessClassName;
    }

    public void setFitnessClassName(String fitnessClassName) {
        this.fitnessClassName = fitnessClassName;
    }

    public Time getTime() {
        return classTime;
    }

    public void setTime(Time classTime) {
        this.classTime = classTime;
    }

    public void printSchedule() {
        System.out.println(fitnessClassName + " - " + instructorName + " " + classTime.getDateTime());
        if (studentsList.getSize() != 0) {
            System.out.println("\t ** participants **");
        }
        studentsList.printSchedule();
    }

    public boolean isRegistered(Member member) {
        return studentsList.contains(member) != NOTFOUND;
    }

    public boolean isExpired(Member member) {
        return member.getExpire().compareTo(new Date()) < 0;
    }

//    public boolean isTimeConflict(String className, FitnessClass[] fitnessClasses, Member member) {
//        Time[] times = new Time[fitnessClasses.length];
//        int count = 0;
//        int j = 0;
//
//        for (int i = 0; i < fitnessClasses.length; i++) {
//            if (fitnessClasses[i].getStudentsList().contains(member) != NOTFOUND) {
//                times[count] = fitnessClasses[i].getTime();
//                if((fitnessClasses[i]).equals(times[i].valueOf(className)) && times are different){
//                    return true;
//                }
//                count++;
//            }
//        }
////        if (count != 0) {
////            System.out.println("                                    count: " + count);
////            for (int i = 0; i < 3; i++) {
////                for (int j = 1; j < count; j++) {
////                    System.out.println("                                    time@: "+i+" " + times[i].getDateTime());
////                    System.out.println("                                    time2: "+j+" " + times[j].getDateTime());
////                    if (times[i].getDateTime().equals(times[j].getDateTime()) && !times[i].valueOf(className).equals(times[j].valueOf(className))) {
////                        return true;
////                    }
////                }
////            }
////        }
//        return false;
//    }

    private boolean isTimeConflict(String className, FitnessClass[] fitnessClasses, Member member) {
        int index = 0;
        String[] times = new String[fitnessClasses.length];
        String time = " ";
        for (int i = 0; i < fitnessClasses.length; i++) {
            if ((fitnessClasses[i].getFitnessClassName()).equalsIgnoreCase(className)) {
                time = fitnessClasses[i].getTime().getDateTime();
            }
        }
        for (int i = 0; i < fitnessClasses.length; i++) {
            if ((fitnessClasses[i].getFitnessClassName()).equalsIgnoreCase(className)) {
                continue;
            }
            if (fitnessClasses[i].getStudentsList().contains(member) != NOTFOUND) {
                if (time.equalsIgnoreCase(fitnessClasses[i].getTime().getDateTime())) {
                    return true;
                }
            }
        }
        return false;
    }

    private boolean isClassExist(String className) {
        for (int i = 0; i < fitnessClasses.length; i++) {
            if (className.equalsIgnoreCase(fitnessClasses[i].getFitnessClassName())) {
                return true;
            }
        }
        return false;
    }

    public boolean checkIn(Member member, String className, FitnessClass[] fitnessClasses, MemberDatabase memberDb) {
        if (!isExpired(member)) {
            if (!isRegistered(member)) {
                if (member.getDob().isValidDob()) {
                    if (!isTimeConflict(className, fitnessClasses, member)) {
                        System.out.println(member.getFname() + " " + member.getLname() + " checked in " + className);
                        studentsList.add(member);
                        return true;
                    } else {
                        System.out.println(className + " time conflict -- " +
                                member.getFname() + " " + member.getLname() + " has already checked in " + className);
                    }
                }
            } else {
                System.out.println(member.getFname() + " " + member.getLname() + " has already checked in " + className);
            }
        } else {
            System.out.println(member.getFname() + " " + member.getLname() + " " + member.getDob().toString() + " membership has expired.");
        }
        return false;
    }

    public boolean drop(Member member, MemberDatabase memberDb) {
        if (member.getDob().isValidDob()) {
            if (studentsList.contains(member) != NOTFOUND) {
                studentsList.remove(member);
                System.out.println(member.getFname() + " " + member.getLname() + " dropped " + fitnessClassName);
                return true;
            } else {
                System.out.println(member.getFname() + " " + member.getLname() + " is not a participant in " + fitnessClassName);
            }
        }
        return false;
    }
}