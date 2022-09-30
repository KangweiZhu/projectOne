package projectone;

/**
 * The MemberDatabase is the class that will store the Member object. It is a sequenceList datastructures.
 * <p>
 * author: Michael Israel, Kangwei Zhu
 */
public class MemberDatabase {
    public static final int NOT_FOUND = -1;
    private static final int INCREMENT = 4;
    private Member[] mlist;
    private int size; // number of current Member objects that store in this database

    /**
     * Initialize a newly created MemberDatabase object.
     * Initialize MemberDatabase's Member array mlist to an empty array with size 4. And set the number of members to 0.
     */
    public MemberDatabase() {
        mlist = new Member[INCREMENT];
        size = 0;
    }

    /**
     * Find whether a Member object exists in this MemberDatabase object.
     *
     * @param member a specific Member object.
     * @return Index of this Member in mlist if this Member does exist. If it not exists, return -1.
     */
    private int find(Member member) {
        for (int i = 0; i < size; i++) {
            if (mlist[i].equals(member)) {
                return i;
            }
        }
        return NOT_FOUND;
    }

    /**
     * Check whether the MemberDataBase contains a specific Member object.
     *
     * @param member a specific Member object.
     * @return Index of this Member in mlist if this Member does exist. If it not exists, return -1.
     */
    public int contains(Member member) {
        return find(member);
    }

    /**
     * Increase the amount of Member object that the database could store by 4.
     */
    private void grow() {
        Member[] temp = new Member[mlist.length + INCREMENT];
        for (int i = 0; i < mlist.length; i++) {
            temp[i] = mlist[i];
        }
        mlist = temp;
    }

    /**
     * Get the Member object in database through using its index in mlist array.
     *
     * @param index The index of this Member object in mlist.
     * @return Member object.
     */
    public Member returnMember(int index) {
        return mlist[index];
    }

    /**
     * get the number of Member object that currently stored in this database.
     *
     * @return number of Member object stored in this database.
     */
    public int getSize() {
        return size;
    }

    /**
     * Add a Member object into the database.
     * To successfully add a Member object, the database must not contain this Member object.
     *
     * @param member Member object that going to be added.
     * @return true if this Member is add successfully, false if this Member object has already been added.
     */
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

    /**
     * Remove a Member object in database.
     * To remove this Member object, the database must have this object.
     *
     * @param member Member object that going to be removed.
     * @return true if this Member has been successfully removed, false otherwise.
     */
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

    /**
     * Print all the Member objects that are currently stored in an array
     *
     * @param members The array that contains Member objects
     */
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

    /**
     * Print all the Member objects that are currently stored in database
     */
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

    /**
     * Print all the Member objects that are currently stored in database. Notice that this method is used for printing
     * the all the members in one specific fitness database.
     */
    public void printSchedule() {
        for (int i = 0; i < size; i++) {
            System.out.print("\t\t");
            System.out.println(mlist[i].toString());
        }
    }

    /**
     * Sort the database Member objects by county name, if county name is equal, then sort by zipcode.
     */
    public void printByCounty() {
        if (size == 0) {
            print();
        } else {
            Member[] copyOfMlist = new Member[size];
            copyArray(copyOfMlist, size);
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

    /**
     * Copy the instance variable mlist array into a new array
     *
     * @param members array that store Member objects
     * @param limit   the cutoff of the range of copy
     */
    public void copyArray(Member[] members, int limit) {
        for (int i = 0; i < limit; i++) {
            members[i] = mlist[i];
        }
    }

    /**
     * Sort the database Member objects by its expiration date.
     */
    public void printByExpirationDate() {
        if (size == 0) {
            print();
        } else {
            Member[] copyOfMlist = new Member[size];
            copyArray(copyOfMlist, size);
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

    /**
     * Sort the database Member objects by its first name and last name. First check first name, then check last name.
     */
    public void printByName() {
        if (size == 0) {
            print();
        } else {
            Member[] copyOfMlist = new Member[size];
            copyArray(copyOfMlist, size);
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