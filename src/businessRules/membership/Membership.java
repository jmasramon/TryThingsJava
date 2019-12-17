package businessRules.membership;

public class Membership {
    boolean     isActive   = false;
    Memberships membership = Memberships.STANDARD;

    public void activate() {
        isActive = true;
        System.out.println("Membership activated");
    }

    public void upgrade() {
        if (isActive) {
            membership = membership.next();
        }
    }
}
