package albert.practice.designpattern.factory;

public class JuiceFactory {

    public Juice getJuice(FruitEnum fruitEnum) {
        Juice juice = null;
        if (FruitEnum.APPLE == fruitEnum) {
            juice = new AppleJuice();
        } else if (FruitEnum.ORANGE == fruitEnum) {
            juice = new OrangeJuice();
        } else if (FruitEnum.KIWI == fruitEnum) {
            juice = new KiwiJuice();
        }
        return juice;
    }

}
