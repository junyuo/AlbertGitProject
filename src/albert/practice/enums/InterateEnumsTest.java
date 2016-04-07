package albert.practice.enums;

import java.util.ArrayList;
import java.util.List;

public class InterateEnumsTest {

    public static void main(String[] args) {

        List<ServiceType> serviceTypes = new ArrayList<ServiceType>();

        for (ServiceTypeEnum serviceType : ServiceTypeEnum.values()) {
            ServiceType bean = new ServiceType(serviceType.getType(), serviceType.getName());
            serviceTypes.add(bean);
        }

        System.out.println(serviceTypes.toString());
    }
}
