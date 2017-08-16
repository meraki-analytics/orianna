package com.merakianalytics.orianna.types.core.staticdata;

import com.merakianalytics.orianna.Orianna;
import com.merakianalytics.orianna.types.common.Platform;

public class Item {
    public static Item withId(final int id) {
        return withId(id, Orianna.getSettings().getDefaultPlatform(), Orianna.getSettings().getCurrentVersion(),
            Orianna.getSettings().getDefaultPlatform().getDefaultLocale());
    }

    public static Item withId(final int id, final Platform platform) {
        return withId(id, platform, Orianna.getSettings().getCurrentVersion(), platform.getDefaultLocale());
    }

    public static Item withId(final int id, final Platform platform, final String version) {
        return withId(id, platform, version, platform.getDefaultLocale());
    }

    public static Item withId(final int id, final Platform platform, final String version, final String locale) {
        return new Item(); // TODO
    }
}
