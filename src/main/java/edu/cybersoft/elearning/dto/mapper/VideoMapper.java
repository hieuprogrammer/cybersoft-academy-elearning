package edu.cybersoft.elearning.dto.mapper;

import edu.cybersoft.elearning.domain.model.Video;
import edu.cybersoft.elearning.dto.model.VideoDto;

public class VideoMapper {
    public static VideoDto toVideoDto(Video video) {
        return new VideoDto(
                video.getId(),
                video.getTitle(),
                video.getUrl(),
                video.getTimeCount(),
                video.getCourse().getTitle());
    }

    public static Video toVideo(VideoDto videoDto) {
        Video video = new Video();
        video.setId(videoDto.getId());
        video.setTitle(videoDto.getTitle());
        video.setUrl(videoDto.getUrl());
        video.setTimeCount(videoDto.getTimeCount());
        return video;
    }
}