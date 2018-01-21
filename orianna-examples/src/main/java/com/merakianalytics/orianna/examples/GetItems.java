package com.merakianalytics.orianna.examples;

import com.merakianalytics.orianna.types.common.Region;
import com.merakianalytics.orianna.types.core.staticdata.Item;
import com.merakianalytics.orianna.types.core.staticdata.Items;

public class GetItems {
    public static void main(final String[] args) {
    		final Items items = Items.withRegion(Region.NORTH_AMERICA).get();
    		for(Item item : items) {
    			System.out.println(item.getName() + " " + item.getId());
    		}
    		
    		final Item dagger = Item.named("Dagger").withRegion(Region.NORTH_AMERICA).get();
    		System.out.println(dagger.getName() + " " + dagger.getId());
    }
}