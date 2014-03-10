package core

import groovy.util.slurpersupport.NodeChild

/**
 * This class tries to resolve expressions such as "findAllBookByAuthorAndYear('Someone', 2012)"
 *
 */
class FindByResolver extends BaseResolver {

    /**
     * Default constructor
     */
    FindByResolver() {
       super(ResolverType.FINDBY)
    }

    List<NodeChild> resolve(NodeChild base, String methodName, Object... args) {
        String requestedName = resolveElement(methodName)

    }

}
