package lambda.funcref;

import java.util.ArrayList;

public class RemoveIf {
    public static void main(String[] args) {
        ArrayList<Item> items = new ArrayList<>();
        items.add(new Item(11, "小牙刷", 12.05 ));
        items.add(new Item(5, "日本马桶盖", 999.05 ));
        items.add(new Item(7, "格力空调", 888.88 ));
        items.add(new Item(17, "肥皂", 2.00 ));
        items.add(new Item(9, "冰箱", 4200.00 ));

        items.removeIf(ele -> ele.getId() == 7);

        //通过 foreach 遍历，查看是否已经删除
        items.forEach(System.out::println);
    }
}
