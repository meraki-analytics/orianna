package com.merakianalytics.orianna.datapipeline.transformers.dtodata;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import com.merakianalytics.datapipelines.PipelineContext;
import com.merakianalytics.datapipelines.transformers.AbstractDataTransformer;
import com.merakianalytics.datapipelines.transformers.Transform;
import com.merakianalytics.orianna.types.data.runes.RunePage;
import com.merakianalytics.orianna.types.data.runes.RunePages;
import com.merakianalytics.orianna.types.dto.runes.RuneSlot;

public class RunesTransformer extends AbstractDataTransformer {
    @Transform(from = com.merakianalytics.orianna.types.dto.runes.RunePage.class, to = RunePage.class)
    public RunePage transform(final com.merakianalytics.orianna.types.dto.runes.RunePage item, final PipelineContext context) {
        final RunePage page = new RunePage();
        final Map<Integer, Integer> slots = new HashMap<>();
        for(final RuneSlot slot : item.getSlots()) {
            slots.put(slot.getRuneSlotId(), slot.getRuneId());
            Integer count = page.get(slot.getRuneId());
            count = count == null ? 1 : count + 1;
            page.put(slot.getRuneSlotId(), count);
        }
        page.setSlots(slots);
        page.setActive(item.isCurrent());
        page.setId(item.getId());
        page.setName(item.getName());
        return page;
    }

    @Transform(from = com.merakianalytics.orianna.types.dto.runes.RunePages.class, to = RunePages.class)
    public RunePages transform(final com.merakianalytics.orianna.types.dto.runes.RunePages item, final PipelineContext context) {
        final RunePages pages = new RunePages(item.getPages().size());
        for(final com.merakianalytics.orianna.types.dto.runes.RunePage page : item.getPages()) {
            pages.add(transform(page, context));
        }
        pages.setPlatform(item.getPlatform());
        pages.setSummonerId(item.getSummonerId());
        return pages;
    }

    @Transform(from = RunePage.class, to = com.merakianalytics.orianna.types.dto.runes.RunePage.class)
    public com.merakianalytics.orianna.types.dto.runes.RunePage transform(final RunePage item, final PipelineContext context) {
        final com.merakianalytics.orianna.types.dto.runes.RunePage page = new com.merakianalytics.orianna.types.dto.runes.RunePage();
        final Set<RuneSlot> slots = new HashSet<>();
        for(final Integer slotId : item.getSlots().keySet()) {
            final RuneSlot slot = new RuneSlot();
            slot.setRuneId(item.getSlots().get(slotId));
            slot.setRuneSlotId(slotId);
            slots.add(slot);
        }
        page.setSlots(slots);
        page.setCurrent(item.isActive());
        page.setId(item.getId());
        page.setName(item.getName());
        return page;
    }

    @Transform(from = RunePages.class, to = com.merakianalytics.orianna.types.dto.runes.RunePages.class)
    public com.merakianalytics.orianna.types.dto.runes.RunePages transform(final RunePages item, final PipelineContext context) {
        final com.merakianalytics.orianna.types.dto.runes.RunePages pages = new com.merakianalytics.orianna.types.dto.runes.RunePages();
        final Set<com.merakianalytics.orianna.types.dto.runes.RunePage> pgs = new HashSet<>();
        for(final RunePage page : item) {
            pgs.add(transform(page, context));
        }
        pages.setPages(pgs);
        pages.setPlatform(item.getPlatform());
        pages.setSummonerId(item.getSummonerId());
        return pages;
    }
}
