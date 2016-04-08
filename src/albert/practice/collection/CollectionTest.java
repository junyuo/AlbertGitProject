package albert.practice.collection;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import lombok.extern.slf4j.Slf4j;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.collections.Predicate;
import org.apache.commons.lang.StringUtils;

@Slf4j
public class CollectionTest {

    public static void main(String[] args) throws ComConfigException {

        // prepare test data for input collection
        CollectionTest test = new CollectionTest();
        ComConfig vo1 = test.generateComConfig1();
        ComConfig vo2 = test.generateComConfig2();

        List<ComConfig> input = new ArrayList<ComConfig>();
        input.add(vo1);
        input.add(vo2);
        log.debug("input = " + input.toString());

        // output collection for filtered data
        List<ComConfig> output = new ArrayList<ComConfig>();

        test.checkConfigKey("smtp1", input, output);

        log.debug("output = " + output.toString());
    }

    private ComConfig generateComConfig1() {
        ComConfigPK pk = new ComConfigPK("EMAIL", "smtp");
        Timestamp updateDate = new Timestamp(Calendar.getInstance().getTime().getTime());
        ComConfig config = new ComConfig(pk, "192.168.30.67", updateDate, "test");
        return config;
    }

    private ComConfig generateComConfig2() {
        ComConfigPK pk = new ComConfigPK("EMAIL", "port");
        Timestamp updateDate = new Timestamp(Calendar.getInstance().getTime().getTime());
        ComConfig config = new ComConfig(pk, "10025", updateDate, "test");
        return config;
    }

    public void checkConfigKey(final String configKey, List<ComConfig> input, List<ComConfig> output)
            throws ComConfigException {
        List<ComConfig> comConfigs = findConfigKey(configKey, input, output);
        if (CollectionUtils.isEmpty(comConfigs)) {
            throw new ComConfigException("在COM_CONFIG中找不到 " + configKey + " 此key值的資料");
        } else {
            ComConfig comConfig = comConfigs.get(0);
            if (StringUtils.isEmpty(comConfig.getConfigValue())) {
                throw new ComConfigException("key 值為空");
            }
        }
    }

    private List<ComConfig> findConfigKey(final String configKey, List<ComConfig> input,
            List<ComConfig> output) {

        CollectionUtils.select(input, new Predicate() {
            @Override
            public boolean evaluate(Object object) {
                ComConfig vo = (ComConfig) object;
                return vo.getId().getConfigKey().equals(configKey);
            }
        }, output);

        return output;
    }

}
