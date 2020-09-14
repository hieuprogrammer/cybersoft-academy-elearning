package edu.cybersoft.elearning.dto.mapper;

import edu.cybersoft.elearning.domain.model.Target;
import edu.cybersoft.elearning.dto.model.TargetDto;

public class TargetMapper {
    public static TargetDto toTargetDto(Target target) {
        return new TargetDto(target.getId(), target.getTitle(), target.getCourse().getTitle());
    }

    public static Target toTarget(TargetDto targetDto) {
        Target target = new Target();
        target.setId(targetDto.getId());
        target.setTitle(targetDto.getTitle());
        return target;
    }
}