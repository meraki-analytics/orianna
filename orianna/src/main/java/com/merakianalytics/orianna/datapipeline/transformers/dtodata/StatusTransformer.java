package com.merakianalytics.orianna.datapipeline.transformers.dtodata;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.joda.time.DateTime;
import org.joda.time.format.DateTimeFormatter;
import org.joda.time.format.ISODateTimeFormat;

import com.merakianalytics.datapipelines.PipelineContext;
import com.merakianalytics.datapipelines.transformers.AbstractDataTransformer;
import com.merakianalytics.datapipelines.transformers.Transform;
import com.merakianalytics.orianna.types.common.Platform;
import com.merakianalytics.orianna.types.data.status.Incident;
import com.merakianalytics.orianna.types.data.status.Message;
import com.merakianalytics.orianna.types.data.status.Service;
import com.merakianalytics.orianna.types.data.status.ShardStatus;
import com.merakianalytics.orianna.types.data.status.Translation;

public class StatusTransformer extends AbstractDataTransformer {
    final DateTimeFormatter FORMATTER = ISODateTimeFormat.dateTime();

    @Transform(from = com.merakianalytics.orianna.types.dto.status.Incident.class, to = Incident.class)
    public Incident transform(final com.merakianalytics.orianna.types.dto.status.Incident item, final PipelineContext context) {
        final Incident incident = new Incident();
        incident.setActive(item.isActive());
        incident.setCreated(DateTime.parse(item.getCreated_at(), FORMATTER));
        incident.setId(item.getId());
        final List<Message> updates = new ArrayList<>(item.getUpdates().size());
        for(final com.merakianalytics.orianna.types.dto.status.Message update : item.getUpdates()) {
            updates.add(transform(update, context));
        }
        incident.setUpdates(updates);
        return incident;
    }

    @Transform(from = com.merakianalytics.orianna.types.dto.status.Message.class, to = Message.class)
    public Message transform(final com.merakianalytics.orianna.types.dto.status.Message item, final PipelineContext context) {
        final Message message = new Message();
        message.setAuthor(item.getAuthor());
        message.setContent(item.getContent());
        message.setCreated(DateTime.parse(item.getCreated_at(), FORMATTER));
        message.setId(item.getId());
        message.setSeverity(item.getSeverity());
        final Map<String, Translation> translations = new HashMap<>();
        for(final com.merakianalytics.orianna.types.dto.status.Translation translation : item.getTranslations()) {
            translations.put(translation.getLocale(), transform(translation, context));
        }
        message.setTranslations(translations);
        message.setUpdated(DateTime.parse(item.getUpdated_at(), FORMATTER));
        return message;
    }

    @Transform(from = com.merakianalytics.orianna.types.dto.status.Service.class, to = Service.class)
    public Service transform(final com.merakianalytics.orianna.types.dto.status.Service item, final PipelineContext context) {
        final Service service = new Service();
        final List<Incident> incidents = new ArrayList<>(item.getIncidents().size());
        for(final com.merakianalytics.orianna.types.dto.status.Incident incident : item.getIncidents()) {
            incidents.add(transform(incident, context));
        }
        service.setIncidents(incidents);
        service.setName(item.getName());
        service.setStatus(item.getStatus());
        return service;
    }

    @Transform(from = com.merakianalytics.orianna.types.dto.status.ShardStatus.class, to = ShardStatus.class)
    public ShardStatus transform(final com.merakianalytics.orianna.types.dto.status.ShardStatus item, final PipelineContext context) {
        final ShardStatus status = new ShardStatus();
        status.setHostname(item.getHostname());
        status.setLocales(new ArrayList<>(item.getLocales()));
        status.setName(item.getName());
        status.setPlatform(item.getRegion_tag().toUpperCase());
        final List<Service> services = new ArrayList<>(item.getServices().size());
        for(final com.merakianalytics.orianna.types.dto.status.Service service : item.getServices()) {
            services.add(transform(service, context));
        }
        status.setServices(services);
        return status;
    }

    @Transform(from = com.merakianalytics.orianna.types.dto.status.Translation.class, to = Translation.class)
    public Translation transform(final com.merakianalytics.orianna.types.dto.status.Translation item, final PipelineContext context) {
        final Translation translation = new Translation();
        translation.setContent(item.getContent());
        translation.setLocale(item.getLocale());
        translation.setUpdated(DateTime.parse(item.getUpdated_at(), FORMATTER));
        return translation;
    }

    @Transform(from = Incident.class, to = com.merakianalytics.orianna.types.dto.status.Incident.class)
    public com.merakianalytics.orianna.types.dto.status.Incident transform(final Incident item, final PipelineContext context) {
        final com.merakianalytics.orianna.types.dto.status.Incident incident = new com.merakianalytics.orianna.types.dto.status.Incident();
        incident.setActive(item.isActive());
        incident.setCreated_at(item.getCreated().toString(FORMATTER));
        incident.setId(item.getId());
        final List<com.merakianalytics.orianna.types.dto.status.Message> updates = new ArrayList<>(item.getUpdates().size());
        for(final Message update : item.getUpdates()) {
            updates.add(transform(update, context));
        }
        incident.setUpdates(updates);
        return incident;
    }

    @Transform(from = Message.class, to = com.merakianalytics.orianna.types.dto.status.Message.class)
    public com.merakianalytics.orianna.types.dto.status.Message transform(final Message item, final PipelineContext context) {
        final com.merakianalytics.orianna.types.dto.status.Message message = new com.merakianalytics.orianna.types.dto.status.Message();
        message.setAuthor(item.getAuthor());
        message.setContent(item.getContent());
        message.setCreated_at(item.getCreated().toString(FORMATTER));
        message.setId(item.getId());
        message.setSeverity(item.getSeverity());
        final List<com.merakianalytics.orianna.types.dto.status.Translation> translations = new ArrayList<>(item.getTranslations().size());
        for(final Translation translation : item.getTranslations().values()) {
            translations.add(transform(translation, context));
        }
        message.setTranslations(translations);
        message.setUpdated_at(item.getUpdated().toString(FORMATTER));
        return message;
    }

    @Transform(from = Service.class, to = com.merakianalytics.orianna.types.dto.status.Service.class)
    public com.merakianalytics.orianna.types.dto.status.Service transform(final Service item, final PipelineContext context) {
        final com.merakianalytics.orianna.types.dto.status.Service service = new com.merakianalytics.orianna.types.dto.status.Service();
        final List<com.merakianalytics.orianna.types.dto.status.Incident> incidents = new ArrayList<>(item.getIncidents().size());
        for(final Incident incident : item.getIncidents()) {
            incidents.add(transform(incident, context));
        }
        service.setIncidents(incidents);
        service.setName(item.getName());
        service.setSlug(item.getName().toLowerCase());
        service.setStatus(item.getStatus());
        return service;
    }

    @Transform(from = ShardStatus.class, to = com.merakianalytics.orianna.types.dto.status.ShardStatus.class)
    public com.merakianalytics.orianna.types.dto.status.ShardStatus transform(final ShardStatus item, final PipelineContext context) {
        final com.merakianalytics.orianna.types.dto.status.ShardStatus status = new com.merakianalytics.orianna.types.dto.status.ShardStatus();
        status.setHostname(item.getHostname());
        status.setLocales(new ArrayList<>(item.getLocales()));
        status.setName(item.getName());
        status.setRegion_tag(item.getPlatform().toLowerCase());
        status.setSlug(Platform.withTag(item.getPlatform()).getRegion().getTag().toLowerCase());
        final List<com.merakianalytics.orianna.types.dto.status.Service> services = new ArrayList<>(item.getServices().size());
        for(final Service service : item.getServices()) {
            services.add(transform(service, context));
        }
        status.setServices(services);
        return status;
    }

    @Transform(from = Translation.class, to = com.merakianalytics.orianna.types.dto.status.Translation.class)
    public com.merakianalytics.orianna.types.dto.status.Translation transform(final Translation item, final PipelineContext context) {
        final com.merakianalytics.orianna.types.dto.status.Translation translation = new com.merakianalytics.orianna.types.dto.status.Translation();
        translation.setContent(item.getContent());
        translation.setLocale(item.getLocale());
        translation.setUpdated_at(item.getUpdated().toString(FORMATTER));
        return translation;
    }
}
