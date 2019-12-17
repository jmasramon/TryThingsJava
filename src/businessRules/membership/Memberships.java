package businessRules.membership;

public enum Memberships {
    STANDARD,
    PREMIUM,
    GOLD,
    PLATINUM;

    private static Memberships[] vals = values();

    public Memberships next()
    {
        return vals[(this.ordinal()+1) % vals.length];
    }}
