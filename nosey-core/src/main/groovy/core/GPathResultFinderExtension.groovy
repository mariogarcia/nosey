package core

import groovy.util.slurpersupport.NodeChild

/**
 *
 *
 */
class GPathResultFinderExtension {

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
        return ResolverType.values().
            find({ String methodName, ResolverType type -> methodName ==~ type.regex }.curry(name)).
            resolverInstance.
            resolve(thisObject, name, args) ?: thisObject.super.methodMissing(name, args)
    }

}
