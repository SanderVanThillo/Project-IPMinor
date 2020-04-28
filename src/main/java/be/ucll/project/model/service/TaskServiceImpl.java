package be.ucll.project.model.service;

import be.ucll.project.model.entity.SubTask;
import be.ucll.project.model.entity.Task;
import be.ucll.project.model.dto.SubTaskDTO;
import be.ucll.project.model.dto.TaskDTO;
import be.ucll.project.model.repo.SubTaskRepository;
import be.ucll.project.model.repo.TaskRepository;
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
        return repository.findAll().stream().map(this::convertTask).collect(Collectors.toList());
    }

    @Override
    public TaskDTO getTask(Long id) {
        return convertTask(getTaskEntity(id));
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
        Task task = getTaskEntity(taskDTO.getId());
        task.setTitle(taskDTO.getTitle());
        task.setDescription(taskDTO.getDescription());
        task.setDeadline(taskDTO.getDeadline());
        repository.save(task);
    }

    @Override
    public SubTaskDTO addSubTask(Long id, SubTaskDTO subTaskDTO) {
        Task task = getTaskEntity(id);
        SubTask subTask = new SubTask();
        subTask.setTitle(subTaskDTO.getTitle());
        subTask.setDescription(subTaskDTO.getDescription());
        subTask.setTask(task);

        subRepository.save(subTask);
        task.addSubTask(subTask);
        repository.save(task);
        return convertSubTask(subTask);
    }

    private TaskDTO convertTask(Task task) {
        TaskDTO dto = new TaskDTO();
        dto.setId(task.getId());
        dto.setTitle(task.getTitle());
        dto.setDescription(task.getDescription());
        dto.setDeadline(task.getDeadline());
        dto.setSubTasks(task.getSubTasks().stream().map(this::convertSubTask).collect(Collectors.toList()));
        return dto;
    }

    private  SubTaskDTO convertSubTask(SubTask subTask) {
        SubTaskDTO dto = new SubTaskDTO();
        dto.setTitle(subTask.getTitle());
        dto.setDescription(subTask.getDescription());
        dto.setId(subTask.getId());
        return dto;
    }

    private Task getTaskEntity(Long id){
        Task task = repository.getOne(id);
        if(task != null){
            return task;
        }
        throw new IllegalArgumentException("Task doesn't exist.");
    }

}
