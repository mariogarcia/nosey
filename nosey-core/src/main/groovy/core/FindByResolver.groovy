package core

import groovy.util.slurpersupport.NodeChild

/**
 * This class tries to resolve expressions such as "findAllBookByAuthorAndYear('Someone', 2012)"
 *
 */
class FindByResolver extends BaseResolver {

    private static final Closure<Condition> CONDITION_FILTER = { String f , Condition c -> f.contains(c.name()) ? c : null }
    private static final String REGEX_AND_OR = /And|Or/

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

    }

    /**
     * Returns all conditions derived from the method name expression
     *
     * @param methodName
     * @returns a list of instances of type Condition
     */
    private List<Condition> resolveConditions(final String methodName) {
        String term = buildMatcher(methodName)[0][2]
        String query = buildMatcher(methodName)[0][3]
        List<Map<String,Condition>> fragments = query.split(REGEX_AND_OR).collect { fragment ->
            Condition c = Condition.findResult(CONDITION_FILTER.curry(fragment))
            String f = fragment - c?.name()

            return [term: f , condition: c ]
        }

        return fragment
    }

}
