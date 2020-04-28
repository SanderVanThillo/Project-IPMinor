package be.ucll.project.rest.controller;


import be.ucll.project.model.dto.SubTaskDTO;
import be.ucll.project.model.dto.TaskDTO;
import be.ucll.project.model.service.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api")
public class TaskRestController {
    private final TaskService taskService;

    @Autowired
    public TaskRestController(TaskService taskService) { this.taskService = taskService; }

    @GetMapping("/tasks")
    @ResponseBody
    public List<TaskDTO> getTasks() {
        return taskService.getTasks();
    }

    @GetMapping("tasks/{id}")
    @ResponseBody
    public TaskDTO getTask(@PathVariable("id") Long id){
        return taskService.getTask(id);
    }

    @PostMapping("tasks")
    public void addNewTask(@RequestBody @Valid TaskDTO taskDTO){
        taskService.addTask(taskDTO);
    }

    @PutMapping("tasks/edit/{id}")
    public void updateTask(@RequestBody @Valid TaskDTO taskDTO, @PathVariable("id") Long id){
        taskService.updateTask(taskDTO);
    }

    @PostMapping("tasks/{id}/sub/create")
    public void addSubTask(@PathVariable("id") Long id, @RequestBody @Valid SubTaskDTO subTaskDTO) {
        taskService.addSubTask(id, subTaskDTO);
    }
}
