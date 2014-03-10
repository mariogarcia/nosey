package core

/**
 * This enumerator holds all possible search criterias
 */
enum Condition {

    GREATER_THAN            ({ a, b -> a > b }),
    GREATER_THAN_EQUALS     ({ a, b -> a >= b }),
    LESS_THAN               ({ a, b -> a < b }),
    LESS_THAN_EQUALS        ({ a, b -> a <= b }),
    LIKE                    ({ a, b -> "$a".startsWith("$b")}),
    ILIKE                   ({ a, b -> "${a?.toUpperCase()}".startsWith("${b.toUpperCase()}")}),
    IN_LIST                 ({ a, b -> a in b }),
    BETWEEN                 ({ a, b, c -> b <= a && a >= c}),
    IN_RANGE                ({ a, b -> a in b }),
    IS_NULL                 ({ a -> a == null }),
    IS_NOT_NULL             ({ a -> a != null }),
    IS_EMPTY                ({ a -> "$a".trim().isEmpty() }),
    IS_NOT_EMPTY            ({ a -> !"$a".trim().isEmpty() }),
    EQUAL                   ({ a, b -> a == b }),
    NOT_EQUAL               ({ a, b -> a != b }),
    AND                     ({ a, b -> a && b }),
    OR                      ({ a, b -> a || b })

    Closure<Boolean> expression

    /**
     * This constructors receives a closure. This closure will be used later on
     * to evaluate the condition
     *
     * @param expression
     */
    Condition(Closure<Boolean> expression) {
        this.expression = expression
    }

}
