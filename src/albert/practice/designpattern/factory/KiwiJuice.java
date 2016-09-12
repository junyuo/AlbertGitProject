package albert.practice.designpattern.factory;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class KiwiJuice implements Juice {

    @Override
    public void makeJuice() {
        log.debug("making KIWI juice.");
    }

    @Override
    public void deliverJuice() {
        log.debug("deliver KIWI juice to customer.");        
    }

}
