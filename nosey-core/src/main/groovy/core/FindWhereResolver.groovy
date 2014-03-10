package core

import groovy.util.slurpersupport.NodeChild

/**
 * This class tries to resolve expressions such as "findAllBookWhere { book -> book.author.text() == 'Someone' }
 *
 */
class FindWhereResolver extends BaseResolver {

    /**
     * Default constructor
     */
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

}
