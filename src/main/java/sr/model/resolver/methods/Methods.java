package sr.model.resolver.methods;

public enum Methods {
    UNIQUE_POSSIBILITY(new UniquePossibility()),
    EXCLUSION(new Exclusion()),
    EXCLUSIVE_PAIR_ONE_NUMBER(new ExclusivePairOneNumber()),
    EXCLUSIVE_PAIR_TWO_NUMBERS(new ExclusivePairTwoNumbers());

    private Method method;

    Methods(Method method) {
        this.method = method;
    }

    public Method getMethodInstance() {
        return method;
    }
}
