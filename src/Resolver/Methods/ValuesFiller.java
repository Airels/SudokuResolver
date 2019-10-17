package Resolver.Methods;

import Structures.Structure;

import java.util.List;

public class ValuesFiller {

    public static boolean Resolve(ResolvingMethod resolvingMethod, List<Structure> structures) {
        switch (resolvingMethod) {
            case UNIQUE_POSSIBILITY:
                return new UniquePossibility(structures).resolve();
            case EXCLUSION:
                return new Exclusion(structures).resolve();
            case EXCLUSIVE_PAIR_ONE_NUMBER:
                return new ExclusivePairOneNumber(structures).resolve();
            case EXCLUSIVE_PAIR_TWO_NUMBERS:
                return new ExclusivePairTwoNumbers(structures).resolve();
            default:
                throw new IllegalArgumentException(resolvingMethod + " ISN'T A VALID RESOLVING METHOD");
        }
    }
}
