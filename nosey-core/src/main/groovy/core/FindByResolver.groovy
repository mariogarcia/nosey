package core

import groovy.util.slurpersupport.NodeChild

/**
 * This class tries to resolve expressions such as "findAllBookByAuthorAndYear('Someone', 2012)"
 *
 */
class FindByResolver extends BaseResolver {

    private static class Fn {

        private final NodeChild base
        private Closure constraints

        private Fn() {}
        private Fn(NodeChild base) {
            this.base = base
        }

        static Fn from(final NodeChild base) {
            return new Fn(base)
        }

        Fn filter (Closure<Boolean>... filters) {
            this.constraints =  { fs, e -> fs*.call(e).every { x -> x } }.curry(filters as List)
            return this
        }

        List getResult() {
            return { pred, col -> col.findAll(pred) }.curry(constraints).call(base.'**')
        }

    }

    /**
     * Default constructor
     */
    FindByResolver() {
       super(ResolverType.FINDBY)
    }

    /**
     * Buildin the GPath expression
     */
    List<NodeChild> resolve(NodeChild base, String methodName, Object... args) {
        return getResult(base, resolveConditions(methodName))
    }

    /**
     * Returns all matches for the node name and the conditions provided
     *
     * @param base The root node we start from
     * @conditions All search conditions that restrict the query
     * @returns a list of NodeChild instances fullfilling the criteria
     */
    private List<NodeChild> getResult(final NodeChild base, final List<Condition> conditions) {
        return Fn.from(base).filter(conditions).result
    }

    /**
     * Returns all conditions derived from the method name expression
     *
     * @param methodName
     * @returns a list of instances of type Condition
     */
    private List<Condition> resolveConditions(final String methodName) {
        String rawConditions = buildMatcher(methodName)[0][2]
        String nodeNameToSearch = resolveElement(methodName)

    }

}
