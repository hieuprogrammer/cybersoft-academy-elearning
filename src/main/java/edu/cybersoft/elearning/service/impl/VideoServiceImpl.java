package edu.cybersoft.elearning.service.impl;

import edu.cybersoft.elearning.domain.model.Video;
import edu.cybersoft.elearning.dto.mapper.VideoMapper;
import edu.cybersoft.elearning.dto.model.VideoDto;
import edu.cybersoft.elearning.repo.VideoRepository;
import edu.cybersoft.elearning.service.VideoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

@Service
@Transactional(rollbackOn = Exception.class)
public class VideoServiceImpl implements VideoService {
    private final VideoRepository videoRepository;

    @Autowired
    public VideoServiceImpl(VideoRepository videoRepository) {
        this.videoRepository = videoRepository;
    }

    @Override
    public void add(VideoDto videoDto) {
        this.videoRepository.save(VideoMapper.toVideo(videoDto));
    }

    @Override
    public List<VideoDto> findAll() {
        List<Video> videos = this.videoRepository.findAll();
        List<VideoDto> videoDtos = new ArrayList<VideoDto>();
        for (Video video : videos) {
            videoDtos.add(VideoMapper.toVideoDto(video));
        }
        return videoDtos;
    }

    @Override
    public VideoDto findById(Long id) {
        return VideoMapper.toVideoDto(this.videoRepository.findById(id).get());
    }

    @Override
    public void update(VideoDto videoDto) {
        Video video = this.videoRepository.findById(videoDto.getId()).get();
        video.setTitle(videoDto.getTitle());
        video.setUrl(videoDto.getUrl());
        video.setTimeCount(videoDto.getTimeCount());
        this.videoRepository.save(video);
    }

    @Override
    public void delete(Long id) {
        this.videoRepository.deleteById(id);
    }
}