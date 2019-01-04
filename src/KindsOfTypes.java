public class KindsOfTypes implements Comparable{

    @Override
    public int compareTo(Object o) {
        return 0;
    }

    interface myInterface {
        void anInterfaceFunction();
    }

    interface myOtherInterface extends myInterface {
        void anExtendedInterfaceFunct();

    }

    public class MyClass implements myOtherInterface {

        @Override
        public void anInterfaceFunction() {
            System.out.println("anInterfaceFunction called");
        }

        @Override
        public void anExtendedInterfaceFunct() {
            System.out.println("anExtendedInterfaceFunct called");

        }
    }

    protected  MyClass getMyClass() {
        return new MyClass();
    }

}
