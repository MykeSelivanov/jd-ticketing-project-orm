package com.cybertek.implementation;

import com.cybertek.dto.TaskDTO;
import com.cybertek.entity.Task;
import com.cybertek.enums.Status;
import com.cybertek.mapper.TaskMapper;
import com.cybertek.repository.TaskRepository;
import com.cybertek.service.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class TaskServiceImpl implements TaskService {

    TaskRepository taskRepository;
    TaskMapper taskMapper;

    @Autowired
    public TaskServiceImpl(TaskRepository taskRepository, TaskMapper taskMapper) {
        this.taskRepository = taskRepository;
        this.taskMapper = taskMapper;
    }

    @Override
    public TaskDTO findById(Long id) {
        Optional<Task> taskEntity = taskRepository.findById(id);
        if (taskEntity.isPresent()) {
            return taskMapper.convertToDTO(taskEntity.get());
        }
        return null;
    }

    @Override
    public List<TaskDTO> listAllTasks() {
        List<Task> taskEntitiesList = taskRepository.findAll();
//      return taskEntitiesList.stream().map(taskEntity -> { return taskMapper.convertToDTO(taskEntity);}).collect(Collectors.toList());
        return taskEntitiesList.stream().map(taskMapper::convertToDTO).collect(Collectors.toList());
    }

    @Override
    public Task save(TaskDTO dto) {
        dto.setTaskStatus(Status.OPEN);
        dto.setAssignedDate(LocalDate.now());
        Task taskEntity = taskMapper.convertToEntity(dto);
        return taskRepository.save(taskEntity);
    }

    @Override
    public void update(TaskDTO dto) {
        Optional<Task> taskEntity = taskRepository.findById(dto.getId());
        Task convertedTask = taskMapper.convertToEntity(dto);
        if (taskEntity.isPresent()){
            convertedTask.setId(taskEntity.get().getId());
            convertedTask.setTaskStatus(taskEntity.get().getTaskStatus());
            convertedTask.setAssignedDate(taskEntity.get().getAssignedDate());
            taskRepository.save(convertedTask);
        }
    }

    @Override
    public void delete(Long id) {
        Optional<Task> taskEntity = taskRepository.findById(id);
        if(taskEntity.isPresent()){
            taskEntity.get().setIsDeleted(true);
            taskRepository.save(taskEntity.get());
        }

    }
}
