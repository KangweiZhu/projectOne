package projectone;

public class MemberDatabase {
    public static final int NOT_FOUND = -1;
    private static final int INCREMENT = 4;
    private Member[] mlist;
    private int size;

    public MemberDatabase() {
        mlist = new Member[INCREMENT];
        size = 0;
    }

    private int find(Member member) {
        for (int i = 0; i < size; i++) {
            if (mlist[i].equals(member)) {
                return i;
            }
        }
        return NOT_FOUND;
    }

    public int contains(Member member) {
        return find(member);
    }

    private void grow() {
        Member[] temp = new Member[mlist.length + INCREMENT];
        for (int i = 0; i < mlist.length; i++) {
            temp[i] = mlist[i];
        }
        mlist = temp;
    }

    public Member returnMember(int index) {
        return mlist[index];
    }

    public int getSize() {
        return size;
    }

    public boolean add(Member member) {
        if (find(member) != NOT_FOUND) {
            return false;
        }
        mlist[size] = member;
        size++;
        if (size == mlist.length) {
            grow();
        }
        return true;
    }

    public boolean remove(Member member) {
        int index = find(member);
        if (index == NOT_FOUND) {
            return false;
        } else {
            for (int i = index; (i + 1) < size; i++) {
                mlist[i] = mlist[i + 1];
            }
            mlist[size] = null;
            size--;
        }
        return true;
    }

    public void print(Member[] members) {
        if (members.length == 0) {
            System.out.println("Member database is empty!");
        } else {
            for (int i = 0; i < members.length; i++) {
                System.out.println(members[i].toString());
            }
            System.out.println("-end of list-");
            System.out.println();
        }
    }

    public void print() {
        if (size == 0) {
            System.out.println("Member database is empty!");
        } else {
            for (int i = 0; i < size; i++) {
                System.out.println(mlist[i].toString());
            }
            System.out.println("-end of list-");
            System.out.println();
        }
    }
    public void printSchedule() {
        for (int i = 0; i < size; i++) {
            System.out.print("\t\t");
            System.out.println(mlist[i].toString());
        }
    }

    public void printByCounty() {
        if (size == 0) {
            print();
        } else {
            Member[] copyOfMlist = new Member[size];
            copyArray(copyOfMlist,size);
            System.out.println();
            System.out.println("-list of members sorted by county and zipcode-");
            for (int i = size - 1; i > 0; i--) {
                for (int j = 0; j < i; j++) {
                    if (copyOfMlist[j].getLocation().compareLocation(copyOfMlist[j + 1]) > 0) {
                        Member temp = copyOfMlist[j];
                        copyOfMlist[j] = copyOfMlist[j + 1];
                        copyOfMlist[j + 1] = temp;
                    }
                }
            }
            print(copyOfMlist);
        }
    }

    public void copyArray(Member[] members, int limit){
        for (int i = 0; i < limit; i++) {
            members[i] = mlist[i];
        }
    }

    public void printByExpirationDate() {
        if (size == 0) {
            print();
        } else {
            Member[] copyOfMlist = new Member[size];
            copyArray(copyOfMlist,size);
            System.out.println();
            System.out.println("-list of members sorted by membership expiration date-");
            for (int i = size - 1; i > 0; i--) {
                for (int j = 0; j < i; j++) {
                    if (copyOfMlist[j].getExpire().compareTo(copyOfMlist[j + 1].getExpire()) > 0) {
                        Member temp = copyOfMlist[j];
                        copyOfMlist[j] = copyOfMlist[j + 1];
                        copyOfMlist[j + 1] = temp;
                    }
                }
            }
            print(copyOfMlist);
        }
    }

    public void printByName() {
        if (size == 0) {
            print();
        } else {
            Member[] copyOfMlist = new Member[size];
            copyArray(copyOfMlist,size);
            System.out.println();
            System.out.println("-list of members sorted by name, and last name-");
            for (int i = size - 1; i > 0; i--) {
                for (int j = 0; j < i; j++) {
                    if (copyOfMlist[j].compareTo(copyOfMlist[j + 1]) > 0) {
                        Member temp = copyOfMlist[j];
                        copyOfMlist[j] = copyOfMlist[j + 1];
                        copyOfMlist[j + 1] = temp;
                    }
                }
            }
            print(copyOfMlist);
        }
    }

}