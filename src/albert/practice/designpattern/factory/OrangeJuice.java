package albert.practice.designpattern.factory;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class OrangeJuice implements Juice {

    @Override
    public void makeJuice() {
        log.debug("making ORANGE juice.");
    }

    @Override
    public void deliverJuice() {
        log.debug("deliver ORANGE juice to customer.");        
    }

}
