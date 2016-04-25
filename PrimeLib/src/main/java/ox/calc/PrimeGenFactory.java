package ox.calc;

import com.google.common.base.Preconditions;
import lombok.extern.slf4j.Slf4j;
import ox.BaseUtils;


import java.util.HashMap;
import java.util.Map;
@Slf4j
public class PrimeGenFactory {

    final Map<String, IPrimeGen> generatorInstances = new HashMap<>();
    final Map<String, Class<? extends IPrimeGen>> generatorClasses = new HashMap<>();


    public void register(String name, Class<? extends IPrimeGen> generator) {
        generatorClasses.put(name, generator);
    }


    public IPrimeGen getPrimeGenerator(String name) {
        IPrimeGen iPrimeGen = generatorInstances.get(name);
        if (iPrimeGen == null) {
            final Class<? extends IPrimeGen> iPrimeGenClass = generatorClasses.get(name);
            Preconditions.checkNotNull(iPrimeGenClass, "This class is not registered");
            iPrimeGen = BaseUtils.createClassInstance(iPrimeGenClass, true);
            generatorInstances.put(name, iPrimeGen);
            log.info("Generated generator class for "+name);
        }

        return iPrimeGen;
    }

}

