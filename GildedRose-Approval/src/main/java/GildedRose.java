import java.util.ArrayList;
import java.util.List;

import com.thoughtworks.xstream.XStream;


public class GildedRose {

    private List<Item> items = null;
    public String externalCallParams;

    public List<Item> getItems() {
        return items;
    }

    /**
     * @param args
     */
    public static void main(String[] args) {
        final GildedRose gildedRose = new GildedRose();
        gildedRose.createItems();
        gildedRose.updateQuality();
    }

    public void createItems() {
        System.out.println("OMGHAI!");
        items = new ArrayList<Item>();
        items.add(new Item("+5 Dexterity Vest", 10, 20));
        items.add(new Item("Aged Brie", 2, 0));
        items.add(new Item("Elixir of the Mongoose", 5, 7));
        items.add(new Item("Sulfuras, Hand of Ragnaros", 0, 80));
        items.add(new Item("Backstage passes to a TAFKAL80ETC concert", 15, 20));
        items.add(new Item("Conjured Mana Cake", 3, 6));
    }


    public void updateQuality() {
        for (Item item : items) {
            if ("Sulfuras, Hand of Ragnaros".equals(item.getName())) {
                continue;
            } else if ("Aged Brie".equals(item.getName())) {
                increaseQuantity(item);
                if (isExpired(item)) {
                    increaseQuantity(item);
                }
                decreaseSellIn(item);
            } else if ("Backstage passes to a TAFKAL80ETC concert".equals(item.getName())) {
                increaseQuantity(item);
                if (item.getSellIn() < 11) {
                    increaseQuantity(item);
                }
                if (item.getSellIn() < 6) {
                    increaseQuantity(item);
                }
                if (isExpired(item)) {
                    item.setQuality(0);
                }
                decreaseSellIn(item);
            } else {
                decreaseQuantity(item);
                if (isExpired(item)) {
                    decreaseQuantity(item);
                }
                decreaseSellIn(item);
            }
            externalCall(item);
        }
    }

    private boolean isExpired(Item item) {
        return item.getSellIn() <= 0;
    }

    private void decreaseQuantity(Item item) {
        if (item.getQuality() > 0) {
            item.setQuality(item.getQuality() - 1);
        }
    }

    private void decreaseSellIn(Item item) {
        item.setSellIn(item.getSellIn() - 1);
    }

    private void increaseQuantity(Item item) {
        if (item.getQuality() < 50) {
            item.setQuality(item.getQuality() + 1);
        }
    }

    private void externalCall(Item item) {
        final GildedRoseService gildedRoseService = new GildedRoseService();
        if (item.getQuality() < 2) {
            int nbGoods = 5;
            gildedRoseService.orderGoods(item, nbGoods);
            //Serialize side effects
            final XStream xStream = new XStream();
            externalCallParams = xStream.toXML(item);
            externalCallParams.concat(xStream.toXML(nbGoods));
        }
    }

}