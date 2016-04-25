package ox;

import com.google.common.base.Throwables;
import com.google.inject.Inject;
import com.google.inject.Injector;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;

import java.lang.reflect.InvocationTargetException;

/**
 * Created by huseyn on 22/04/2016.
 */
@Slf4j
public final class BaseUtils {


    private BaseUtils() {
    }

    /** */
    @Inject
    @Getter
    private static Injector injector;

    /**
     * Returns the appropriate instance for the given injection type; equivalent to {@code
     * getProvider(type).get()}. When feasible, avoid using this method, in favor of having Guice
     * inject your dependencies ahead of time.
     *
     * @throws com.google.inject.ConfigurationException if this injector cannot find or create the provider.
     * @throws com.google.inject.ProvisionException     if there was a runtime failure while providing an instance.
     */
    public static <T> T getInjectedInstance(Class<T> type) {
        return injector.getInstance(type);
    }

    public static <C> C createClassInstance(Class<C> cls, boolean throwException) {
        try {
            return cls.getConstructor().newInstance();
        } catch (InstantiationException | IllegalAccessException | NoSuchMethodException | InvocationTargetException e) {
            log.error("Failed to create new instance of {}", cls.getName(), e);
            if (throwException) {
                throw Throwables.propagate(e);
            }
            return null;
        }
    }
}
