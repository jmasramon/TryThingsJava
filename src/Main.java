import java.util.function.Supplier;

public class Main {
/*
    public static Supplier<String> whereEquals(final String field, final String value) {
        return () -> field + "=" + value;
    }

    public static Supplier<String> whereEquals(final String field, final Boolean value) {
        return () -> field + "=" + value;
    }

    public static Supplier<String> whereEquals(final String field, final Number value) {
        return () -> field + "=" + value;
    }

    public static Supplier<String> whereEquals(final String field, final Enum value) {
        return whereEquals(field, value.toString());
    }
*/

    public static <T> Supplier<String> whereEquals(final String field, final T value) {
        return () -> field + "=" + value;
    }

    private static <T> void addNullableEqualsFilter (final T value) {
        if (value != null) {
         System.out.println("not null");
        }
    }

    private enum myEnum {
        HOLA,
        LOLA
    }

    public static void main(String[] args) {
        Supplier<String> clauseBuilder = whereEquals("string_field", "string_value");
        System.out.println(clauseBuilder.get());
        clauseBuilder = whereEquals("string_field", true);
        System.out.println(clauseBuilder.get());
        clauseBuilder = whereEquals("string_field", 32);
        System.out.println(clauseBuilder.get());
        clauseBuilder = whereEquals("string_field", myEnum.HOLA);
        System.out.println(clauseBuilder.get());

        addNullableEqualsFilter("not null string");
        addNullableEqualsFilter(3);
        addNullableEqualsFilter(myEnum.HOLA);
        addNullableEqualsFilter(true);


        int i = 10;
        int[] ii = {1,2,3};


        KindsOfTypes kidsOfTypes = new KindsOfTypes();
        KindsOfTypes.MyClass myClass = kidsOfTypes.getMyClass();
        myClass.anInterfaceFunction();
        myClass.anExtendedInterfaceFunct();

        KindsOfTypes anotherKindsOfTypes = new KindsOfTypes();
//        assert kidsOfTypes == anotherKindsOfTypes;
        assert true;
        assert false;


        System.out.println((kidsOfTypes.equals(anotherKindsOfTypes)) ?
                "equal" :
                "not equal");

        System.out.println((kidsOfTypes == anotherKindsOfTypes) ?
                "equal" :
                "not equal");
        System.out.println((kidsOfTypes.compareTo(anotherKindsOfTypes) == 0) ?
                "equal" :
                "not equal");
    }
}


