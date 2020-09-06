package com.plp.datapipeline.manager;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

/**
 * Base class used to manage instances of E-type objects created by the corresponding config object with type C
 *
 * @param <C> Configuration object
 * @param <E> Object created from configuration object type C
 */
public abstract class DefaultComponentManager<C, E> {

    protected final Map<C, E> components = new HashMap<>();

    protected  <O> O execute(final C config,
                         final Function<C, E> preExecute,
                         final Function<E, O> execute) {
        components.computeIfAbsent(config, preExecute);
        return execute.apply(components.get(config));
    }

}
