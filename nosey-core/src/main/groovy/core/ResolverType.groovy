package core

enum ResolverType {

    WHERE(/findAll($TERM_TO_FIND)Where$/, FindWhereResolver),
    FINDBY(/findAll($TERM_TO_FIND)By($CONDITIONS)$/, FindByResolver)

    private static final String TERM_TO_FIND = /[\w]{1,}/
    private static final String CONDITIONS = /[\w]{1,}/

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
