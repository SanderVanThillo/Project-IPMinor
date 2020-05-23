package be.ucll.project.controller;

import be.ucll.project.model.dto.SubTaskDTO;
import be.ucll.project.model.dto.TaskDTO;
import be.ucll.project.model.service.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Controller
@RequestMapping("/tasks")
public class TaskController {
    private final TaskService taskService;

    @Autowired
    public TaskController(TaskService taskService){
        this.taskService = taskService;
    }

    @GetMapping
    public String getTasks(Model model) {
        model.addAttribute("tasks", taskService.getTasks());
        return "tasks";
    }

    @GetMapping("/{id}")
    public String getTask(Model model, @PathVariable("id") Long id){
        model.addAttribute("task", taskService.getTask(id));
        return "taskDetail";
    }

    @GetMapping("/new")
    public String getTaskForm(Model model){
        model.addAttribute("taskDTO", new TaskDTO());
        return "addTask";
    }

    @PostMapping
    public String addNewTask(@ModelAttribute @Valid TaskDTO taskDTO, BindingResult bindingResult){
        if(bindingResult.hasErrors()){
            return "addTask";
        }
        taskService.addTask(taskDTO);
        return "redirect:/tasks";
    }

    @GetMapping("/edit/{id}")
    public String getEditForm(Model model, @PathVariable("id") Long id){
        model.addAttribute("taskDTO", taskService.getTask(id));
        return "editTask";
    }

    @PostMapping("/edit/{id}")
    public String updateTask(@ModelAttribute @Valid TaskDTO taskDTO, @PathVariable("id") Long id, BindingResult bindingResult){
        if(bindingResult.hasErrors()){
            return "editTask";
        }
        taskService.updateTask(taskDTO);
        return "redirect:/tasks/" + id;
    }

    @GetMapping("/{id}/sub/create")
    public String getSubTaskForm(Model model, @PathVariable("id") Long taskId) {
        model.addAttribute("taskId", taskId);
        model.addAttribute("subTaskDTO", new SubTaskDTO());
        return "addSubTask";
    }

    @PostMapping("/{id}/sub/create")
    public String addSubTask(@PathVariable("id") Long id, @ModelAttribute @Valid SubTaskDTO subTaskDTO, BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) {
            model.addAttribute("taskId", id);
            return "addSubTask";
        }
        taskService.addSubTask(id, subTaskDTO);
        return "redirect:/tasks/" + id;
    }
}
