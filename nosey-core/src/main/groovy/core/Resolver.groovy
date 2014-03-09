package core

import groovy.util.slurpersupport.NodeChild

interface Resolver {
    List<NodeChild> resolve(NodeChild nodeChild, String methodName, Object... args)
}
