package be.ucll.project.model.service;

import be.ucll.project.model.dto.SubTaskDTO;
import be.ucll.project.model.dto.TaskDTO;

import java.util.List;

public interface TaskService {

    List<TaskDTO> getTasks();
    TaskDTO getTask(Long id);
    TaskDTO addTask(TaskDTO taskDTO);
    TaskDTO updateTask(TaskDTO taskDTO);
    SubTaskDTO addSubTask(Long id, SubTaskDTO subTaskDTO);
}
