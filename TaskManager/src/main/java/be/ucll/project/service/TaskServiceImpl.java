package be.ucll.project.service;

import be.ucll.project.domain.SubTask;
import be.ucll.project.domain.Task;
import be.ucll.project.dto.SubTaskDTO;
import be.ucll.project.dto.TaskDTO;
import be.ucll.project.repo.SubTaskRepository;
import be.ucll.project.repo.TaskRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class TaskServiceImpl implements TaskService {
    private final TaskRepository repository;
    private final SubTaskRepository subRepository;

    @Autowired
    public TaskServiceImpl(TaskRepository repository, SubTaskRepository subRepository) {
        this.repository = repository;
        this.subRepository = subRepository;
    }

    @Override
    public List<TaskDTO> getTasks() {
        return repository.findAll().stream().map(task -> {
            TaskDTO taskDTO = new TaskDTO();
            taskDTO.setId(task.getId());
            taskDTO.setTitle(task.getTitle());
            taskDTO.setDescription(task.getDescription());
            taskDTO.setDeadline(task.getDeadline());
            return taskDTO;
        }).collect(Collectors.toList());
    }

    @Override
    public TaskDTO getTask(Long id) {
        Task task = repository.getOne(id);
        if(task != null){
            TaskDTO taskDTO = new TaskDTO();
            taskDTO.setId(task.getId());
            taskDTO.setTitle(task.getTitle());
            taskDTO.setDescription(task.getDescription());
            taskDTO.setDeadline(task.getDeadline());
            taskDTO.setSubTasks(task.getSubTasks().stream().map(subTask -> {
                SubTaskDTO dto = new SubTaskDTO();
                dto.setTitle(subTask.getTitle());
                dto.setDescription(subTask.getDescription());
                dto.setId(subTask.getId());
                return dto;
            }).collect(Collectors.toList()));
            return taskDTO;
        }
        else throw new IllegalArgumentException("Task does not exist!");
    }

    @Override
    public void addTask(TaskDTO taskDTO) {
        Task task = new Task();
        task.setTitle(taskDTO.getTitle());
        task.setDescription(taskDTO.getDescription());
        task.setDeadline(taskDTO.getDeadline());
        repository.save(task);
    }

    @Override
    public void updateTask(TaskDTO taskDTO) {
        Task task = repository.getOne(taskDTO.getId());
        task.setTitle(taskDTO.getTitle());
        task.setDescription(taskDTO.getDescription());
        task.setDeadline(taskDTO.getDeadline());
        repository.save(task);
    }

    @Override
    public void addSubTask(Long id, SubTaskDTO subTaskDTO) {
        Task task = repository.getOne(id);
        SubTask subTask = new SubTask();
        subTask.setTitle(subTaskDTO.getTitle());
        subTask.setDescription(subTaskDTO.getDescription());
        subTask.setTask(task);

        subRepository.save(subTask);
        task.addSubTask(subTask);
        repository.save(task);
    }
}
