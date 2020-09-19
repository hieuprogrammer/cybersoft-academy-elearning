package edu.cybersoft.elearning.service.impl;

import edu.cybersoft.elearning.domain.model.Target;
import edu.cybersoft.elearning.dto.mapper.TargetMapper;
import edu.cybersoft.elearning.dto.model.TargetDto;
import edu.cybersoft.elearning.repository.TargetRepository;
import edu.cybersoft.elearning.service.TargetService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

@Service
@Transactional(rollbackOn = Exception.class)
public class TargetServiceImpl implements TargetService {
    private final TargetRepository targetRepository;

    @Autowired
    public TargetServiceImpl(TargetRepository targetRepository) {
        this.targetRepository = targetRepository;
    }

    @Override
    public void add(TargetDto targetDto) {
        this.targetRepository.save(TargetMapper.toTarget(targetDto));
    }

    @Override
    public List<TargetDto> findAll() {
        List<Target> targets = this.targetRepository.findAll();
        List<TargetDto> targetDtos = new ArrayList<TargetDto>();
        for (Target target : targets) {
            targetDtos.add(TargetMapper.toTargetDto(target));
        }
        return targetDtos;
    }

    @Override
    public TargetDto findById(Long id) {
        return TargetMapper.toTargetDto(this.targetRepository.findById(id).get());
    }

    @Override
    public void update(TargetDto targetDto) {
        Target target = this.targetRepository.findById(targetDto.getId()).get();
        target.setTitle(targetDto.getTitle());
        this.targetRepository.save(target);
    }

    @Override
    public void delete(Long id) {
        this.targetRepository.deleteById(id);
    }
}