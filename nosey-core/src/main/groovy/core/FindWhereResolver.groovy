package core

import java.util.regex.Matcher
import groovy.util.slurpersupport.NodeChild

class FindWhereResolver extends BaseResolver {

    FindWhereResolver() {
        super(ResolverType.WHERE)
    }

    List<NodeChild> resolve(final NodeChild base, String methodName, Object... args) {
        Closure<Boolean> baseCriteria = { name, el -> el.name().toUpperCase() == name.toUpperCase() }
        String requestedName = resolveElement(methodName)

        return base.'**'.
            findAll(baseCriteria.curry(requestedName)).
            findAll(args.first())
    }

    /**
     * This method gets the type of element we are looking for.
     *
     * @param The method name
     * @returns the part of the regular expression that represents the element we are looking for
     */
    private String resolveElement(final String finderName) {
        return buildMatcher(finderName)[0][1]
    }

    /**
     * This method builds a matcher using the method name we need to match and the
     * general regular expression on top
     *
     * @param finderName The name of the method we want to know if matches the regular expression
     * @return an instance of java.util.Matcher
     */
    private Matcher buildMatcher(final String finderName) {
        return finderName =~ type.regex
    }


}
