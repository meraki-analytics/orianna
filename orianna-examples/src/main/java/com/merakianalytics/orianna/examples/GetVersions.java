package com.merakianalytics.orianna.examples;

import com.merakianalytics.orianna.types.common.Region;
import com.merakianalytics.orianna.types.core.staticdata.Versions;

public class GetVersions {
    public static void main(final String[] args) {
    		final Versions versions = Versions.withRegion(Region.NORTH_AMERICA).get();
    		for(String version : versions) {
    			System.out.println(version);
    		}
    }
}