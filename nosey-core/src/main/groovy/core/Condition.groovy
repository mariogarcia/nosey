package core

/**
 * This enumerator holds all possible search criterias
 */
enum Condition {

    GreaterThan         ({ a, b -> a > b }),
    GreaterThanEquals   ({ a, b -> a >= b }),
    LessThan            ({ a, b -> a < b }),
    LessThanEquals      ({ a, b -> a <= b }),
    Like                ({ a, b -> "$a".startsWith("$b")}),
    ILike               ({ a, b -> "${a?.toUpperCase()}".startsWith("${b.toUpperCase()}")}),
    InList              ({ a, b -> a in b }),
    Between             ({ a, b, c -> b <= a && a >= c}),
    InRange             ({ a, b -> a in b }),
    IsNull              ({ a -> a == null }),
    IsNotNull           ({ a -> a != null }),
    IsEmpty             ({ a -> "$a".trim().isEmpty() }),
    IsNotEmpty          ({ a -> !"$a".trim().isEmpty() }),
    Equal               ({ a, b -> a == b }),
    NotEqual            ({ a, b -> a != b })

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
