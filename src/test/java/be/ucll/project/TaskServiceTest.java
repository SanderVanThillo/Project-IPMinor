package be.ucll.project;

import be.ucll.project.model.dto.SubTaskDTO;
import be.ucll.project.model.dto.TaskDTO;
import be.ucll.project.model.entity.SubTask;
import be.ucll.project.model.service.TaskService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest
public class TaskServiceTest {
    @Autowired
    private TaskService taskService;

    @Test
    public void testGetTasks() {
        // setup
        TaskDTO taskDTO = new TaskDTO();
        taskDTO.setTitle("Test");
        taskDTO.setDescription("Test task description");
        taskDTO.setDeadline(LocalDateTime.of(2020, 04, 04, 20, 0));
        taskService.addTask(taskDTO);

        // method to be tested
        List<TaskDTO> tasks = taskService.getTasks();

        // checks
        assertNotNull(tasks);
        assertFalse(tasks.isEmpty());
        assertEquals(1, tasks.size());
        TaskDTO task = tasks.get(0);
        assertNotNull(task);
    }

    @Test
    public void testGetTask() {
        // setup
        TaskDTO taskDTO = new TaskDTO();
        taskDTO.setTitle("Test");
        taskDTO.setDescription("Test task description");
        taskDTO.setDeadline(LocalDateTime.of(2020, 04, 04, 20, 0));
        taskService.addTask(taskDTO);

        // method to be tested
        TaskDTO task = taskService.getTask(2L);

        // checks
        assertNotNull(task);
        assertEquals("Test", task.getTitle());
        assertEquals("Test task description", task.getDescription());
        assertEquals(2L, task.getId());
    }

    @Test
    public void testUpdateTask() {
        // setup
        TaskDTO taskDTO = new TaskDTO();
        taskDTO.setTitle("Test");
        taskDTO.setDescription("Test task description");
        taskService.addTask(taskDTO);
    }
}
