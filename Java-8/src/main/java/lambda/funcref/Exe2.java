package lambda.funcref;

interface ItemCreatorBlankConstruct {
    Item getItem();
}

interface ItemCreatorParamConstruct {
    Item getItem(int id, String name, double price);
}

public class Exe2 {
    public static void main(String[] args) {
        ItemCreatorBlankConstruct creator = () -> new Item();
        Item item = creator.getItem();

        ItemCreatorBlankConstruct creator2 = Item::new;
        Item item2 = creator2.getItem();

        ItemCreatorParamConstruct creator3 = Item::new;
        Item item3 = creator3.getItem(112, "鼠标", 135.99);
    }
}