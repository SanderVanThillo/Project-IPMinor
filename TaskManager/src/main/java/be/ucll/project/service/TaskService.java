package be.ucll.project.service;

import be.ucll.project.dto.SubTaskDTO;
import be.ucll.project.dto.TaskDTO;

import java.util.List;

public interface TaskService {

    List<TaskDTO> getTasks();
    TaskDTO getTask(Long id);
    void addTask(TaskDTO taskDTO);
    void updateTask(TaskDTO taskDTO);
    void addSubTask(Long id, SubTaskDTO subTaskDTO);
}
