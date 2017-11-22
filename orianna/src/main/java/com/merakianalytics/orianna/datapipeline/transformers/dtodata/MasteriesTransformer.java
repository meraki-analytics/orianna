package com.merakianalytics.orianna.datapipeline.transformers.dtodata;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import com.merakianalytics.datapipelines.PipelineContext;
import com.merakianalytics.datapipelines.transformers.AbstractDataTransformer;
import com.merakianalytics.datapipelines.transformers.Transform;
import com.merakianalytics.orianna.types.data.masteries.MasteryPage;
import com.merakianalytics.orianna.types.data.masteries.MasteryPages;
import com.merakianalytics.orianna.types.dto.masteries.Mastery;

public class MasteriesTransformer extends AbstractDataTransformer {
    @Transform(from = com.merakianalytics.orianna.types.dto.masteries.MasteryPage.class, to = MasteryPage.class)
    public MasteryPage transform(final com.merakianalytics.orianna.types.dto.masteries.MasteryPage item, final PipelineContext context) {
        final MasteryPage page = new MasteryPage();
        for(final Mastery mastery : item.getMasteries()) {
            page.put(mastery.getId(), mastery.getRank());
        }
        page.setActive(item.isCurrent());
        page.setId(item.getId());
        page.setName(item.getName());
        return page;
    }

    @Transform(from = com.merakianalytics.orianna.types.dto.masteries.MasteryPages.class, to = MasteryPages.class)
    public MasteryPages transform(final com.merakianalytics.orianna.types.dto.masteries.MasteryPages item, final PipelineContext context) {
        final MasteryPages pages = new MasteryPages(item.getPages().size());
        for(final com.merakianalytics.orianna.types.dto.masteries.MasteryPage page : item.getPages()) {
            pages.add(transform(page, context));
        }
        pages.setPlatform(item.getPlatform());
        pages.setSummonerId(item.getSummonerId());
        return pages;
    }

    @Transform(from = MasteryPage.class, to = com.merakianalytics.orianna.types.dto.masteries.MasteryPage.class)
    public com.merakianalytics.orianna.types.dto.masteries.MasteryPage transform(final MasteryPage item, final PipelineContext context) {
        final com.merakianalytics.orianna.types.dto.masteries.MasteryPage page = new com.merakianalytics.orianna.types.dto.masteries.MasteryPage();
        final List<Mastery> masteries = new ArrayList<>(item.size());
        for(final Integer id : item.keySet()) {
            final Mastery mastery = new Mastery();
            mastery.setId(id);
            mastery.setRank(item.get(id));
            masteries.add(mastery);
        }
        page.setCurrent(item.isActive());
        page.setId(item.getId());
        page.setName(item.getName());
        return page;
    }

    @Transform(from = MasteryPages.class, to = com.merakianalytics.orianna.types.dto.masteries.MasteryPages.class)
    public com.merakianalytics.orianna.types.dto.masteries.MasteryPages transform(final MasteryPages item, final PipelineContext context) {
        final com.merakianalytics.orianna.types.dto.masteries.MasteryPages pages = new com.merakianalytics.orianna.types.dto.masteries.MasteryPages();
        final Set<com.merakianalytics.orianna.types.dto.masteries.MasteryPage> pgs = new HashSet<>();
        for(final MasteryPage page : item) {
            pgs.add(transform(page, context));
        }
        pages.setPlatform(item.getPlatform());
        pages.setSummonerId(item.getSummonerId());
        return pages;
    }
}
