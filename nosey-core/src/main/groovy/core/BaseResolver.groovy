package core

import java.util.regex.Matcher

abstract class BaseResolver implements Resolver {

    ResolverType type

    BaseResolver(ResolverType type) {
        this.type = type
    }

    /**
     * This method gets the type of element we are looking for.
     *
     * @param The method name
     * @returns the part of the regular expression that represents the element we are looking for
     */
    String resolveElement(final String finderName) {
        return buildMatcher(finderName)[0][1]
    }

    /**
     * This method builds a matcher using the method name we need to match and the
     * general regular expression on top
     *
     * @param finderName The name of the method we want to know if matches the regular expression
     * @return an instance of java.util.Matcher
     */
    Matcher buildMatcher(final String finderName) {
        return finderName =~ type.regex
    }

}
