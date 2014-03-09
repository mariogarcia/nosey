package core

import java.util.regex.Matcher
import groovy.util.slurpersupport.NodeChild
import groovy.util.slurpersupport.GPathResult

/**
 *
 *
 */
class GPathResultFinderExtension {

    static final FINDER_ALL_REGEX = /findAll([\w]{1,})Where$/

    /**
     * This will add this "methodMissing" implementation to all NodeChild objects
     *
     * @param thisObject The object its behavior is going to be extended
     * @param name The name of the missing method
     * @param args The arguments of the missing method
     * @returns The result of the query in case it is a finder, otherwise we delegate to the super
     * type
     */
    static Object methodMissing(final NodeChild thisObject, String name, args) {
        if (!buildMatcher(name).matches()) {
            return thisObject.super.methodMissing(name,args)
        }

        return resolveAllFrom(thisObject, resolveElement(name), args)

    }

    /**
     * This method builds a matcher using the method name we need to match and the
     * general regular expression on top
     *
     * @param finderName The name of the method we want to know if matches the regular expression
     * @return an instance of java.util.Matcher
     */
    private static Matcher buildMatcher(final String finderName) {
        return finderName =~ FINDER_ALL_REGEX
    }

    /**
     * This method applies the criteria passed to the "findAllXXXWhere" method as a Closure
     */
    private static resolveAllFrom(NodeChild nodeChild, String elementToLookFor, args) {
        return nodeChild.'**'.
            findAll { el -> el.name().toUpperCase() == elementToLookFor.toUpperCase() }.
            findAll(args.first())
    }

    /**
     * This method gets the type of element we are looking for.
     *
     * @param The method name
     * @returns the part of the regular expression that represents the element we are looking for
     */
    private static String resolveElement(final String finderName) {
        return buildMatcher(finderName)[0][1]
    }

}
