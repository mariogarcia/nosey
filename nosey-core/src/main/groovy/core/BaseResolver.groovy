package core


abstract class BaseResolver implements Resolver {

    ResolverType type

    BaseResolver(ResolverType type) {
        this.type = type
    }

}
