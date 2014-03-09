package core

import groovy.util.slurpersupport.NodeChild

class FindByResolver extends BaseResolver {

    FindByResolver(String regex) {
       super(ResolverType.FINDBY)
    }

    List<NodeChild> resolve(NodeChild base, String methodName, Object... args) {

    }
}
