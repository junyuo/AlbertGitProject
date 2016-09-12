package albert.practice.designpattern.factory;

//http://www.tutorialspoint.com/design_pattern/factory_pattern.htm
public class JuiceStore {

    public static void main(String[] args) {
//        JuiceStore test = new JuiceStore();
//        test.processOrder(FruitEnum.KIWI);
//        test.processOrder(FruitEnum.APPLE);
//        test.processOrder(FruitEnum.ORANGE);
        
        JuiceFactory factory = new JuiceFactory();
        
        Juice apple = factory.getJuice(FruitEnum.APPLE);
        apple.makeJuice();
        apple.deliverJuice();
        
        Juice orange = factory.getJuice(FruitEnum.ORANGE);
        orange.makeJuice();
        orange.deliverJuice();
        
        Juice kiwi = factory.getJuice(FruitEnum.KIWI);
        kiwi.makeJuice();
        kiwi.deliverJuice();
    }

    public void processOrder(FruitEnum fruit) {
        Juice juice = null;
        if (FruitEnum.APPLE == fruit) {
            juice = new AppleJuice();
        } else if (FruitEnum.ORANGE == fruit) {
            juice = new OrangeJuice();
        } else if (FruitEnum.KIWI == fruit) {
            juice = new KiwiJuice();
        }
        juice.makeJuice();
        juice.deliverJuice();
    }

}
