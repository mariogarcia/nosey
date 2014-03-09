package core

enum ResolverType {

    WHERE(/findAll([\w]{1,})Where$/, FindWhereResolver),
    FINDBY(/findAll([\w]{1,})By$/, FindByResolver)

    String regex
    Class<Resolver> resolverClazz

    ResolverType(String regex, Class<Resolver> resolverClazz) {
       this.regex = regex
       this.resolverClazz = resolverClazz
    }

    Resolver getResolverInstance() {
        return resolverClazz.newInstance()
    }

}
