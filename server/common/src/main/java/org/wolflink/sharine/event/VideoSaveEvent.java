package org.wolflink.sharine.event;

import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.cloud.bus.event.PathDestinationFactory;
import org.springframework.cloud.bus.event.RemoteApplicationEvent;
import org.wolflink.sharine.entity.Video;

@Getter
@NoArgsConstructor
public class VideoSaveEvent extends RemoteApplicationEvent {
    private Video video;
    private Long categoryId;

    public VideoSaveEvent(Object source,String originService, String destinationService,Video video,Long categoryId) {
        super(source,originService,new PathDestinationFactory().getDestination(destinationService));
        this.video = video;
        this.categoryId = categoryId;
    }

}
