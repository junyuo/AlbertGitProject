package albert.practice.designpattern.factory;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class AppleJuice implements Juice {

    @Override
    public void makeJuice() {
      log.debug("making APPLE juice.");
    }

    @Override
    public void deliverJuice() {
        log.debug("deliver APPLE juice to customer.");
    }

}
