package eu.h2020.helios_social.core.context;

import androidx.annotation.NonNull;

/**
 * This class is a compound context defined by two other contexts and the context is active when
 * both the related contexts are active (AND operation). The class extends the base class Context.<br/>
 *
 * The context active value is updated using the setActive method. The current value of the context
 * can always be checked using the isActive method of the context.</br>
 *
 * If the application needs to track the
 * changes in the active value of the context then the application should implement also
 * the ContextListener interface {@see eu.h2020.helios_social.core.context.ContextListener} and
 * register the context for the listener.
 */
public class ContextAnd extends Context implements ContextListener {

    private final Context contextA;
    private final Context contextB;

    /**
     * Creates an AndContext
     * @param name the name of the context
     * @param contextA the first context
     * @param contextB the second context
     */
    public ContextAnd(String name, @NonNull Context contextA, @NonNull Context contextB) {
        this(null, name, contextA, contextB);
    }

    /**
     * Creates an AndContext
     * @param id the identifier of the context
     * @param name the name of the context
     * @param contextA the first context
     * @param contextB the second context
     */
    public ContextAnd(String id, String name, @NonNull Context contextA, @NonNull Context contextB) {
        super(id, name, contextA.isActive() && contextB.isActive());
        this.contextA = contextA;
        this.contextB = contextB;
        contextA.registerContextListener(this);
        contextB.registerContextListener(this);
    }

    /**
     * Returns the first context of ContextOr
     * @return the contextA
     */
    public Context getContextA() {
        return contextA;
    }

    /**
     * Returns the second context of ContextOr
     * @return the contextB
     */
    public Context getContextB() {
        return contextB;
    }

    @Override
    public void contextChanged(boolean active) {
        setActive(contextA.isActive() && contextB.isActive());
    }

}
