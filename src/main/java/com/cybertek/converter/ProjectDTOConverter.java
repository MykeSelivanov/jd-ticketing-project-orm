package com.cybertek.converter;

import com.cybertek.dto.ProjectDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.ConfigurationPropertiesBinding;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
@ConfigurationPropertiesBinding
public class ProjectDTOConverter<ProjectService> implements Converter<String, ProjectDTO> {
    @Override
    public ProjectDTO convert(String s) {
        return null;
    }


//    ProjectService projectService;

//    @Autowired
//    public ProjectDTOConverter(ProjectService projectService) {
//        this.projectService = projectService;
//    }
//
//    @Override
//    public ProjectDTO convert(String source) {
//        return projectService.findById(source);
//    }
}