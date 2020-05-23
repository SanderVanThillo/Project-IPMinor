package be.ucll.project;

import be.ucll.project.model.dto.SubTaskDTO;
import be.ucll.project.model.dto.TaskDTO;
import be.ucll.project.model.service.TaskService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@Transactional
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
        TaskDTO taskDTO1 = taskService.addTask(taskDTO);

        // method to be tested
        TaskDTO task = taskService.getTask(taskDTO1.getId());

        // checks
        assertNotNull(task);
        assertEquals("Test", task.getTitle());
        assertEquals("Test task description", task.getDescription());
        assertEquals(LocalDateTime.of(2020,04,04,20,0), task.getDeadline());
        assertEquals(taskDTO1.getId(), task.getId());
    }

    @Test
    public void testUpdateTask() {
        // setup
        TaskDTO taskDTO = new TaskDTO();
        taskDTO.setTitle("Test");
        taskDTO.setDescription("Test task description");
        taskDTO.setDeadline(LocalDateTime.of(2020, 04, 04, 20, 0));
        TaskDTO taskDTO1 = taskService.addTask(taskDTO);

        taskDTO1.setTitle("Updated test");
        taskDTO1.setDescription("Updated test task description");
        taskDTO1.setDeadline(LocalDateTime.of(2021,05,05,21,1));

        //method to be tested
        taskService.updateTask(taskDTO1);

        //checks
        TaskDTO task = taskService.getTask(taskDTO1.getId());
        assertNotNull(task);
        assertEquals("Updated test", task.getTitle());
        assertEquals("Updated test task description", task.getDescription());
        assertEquals(LocalDateTime.of(2021,05,05,21,1), task.getDeadline());
        assertEquals(taskDTO1.getId(), task.getId());
    }

    @Test
    public void testAddSubTask() {
        // setup
        TaskDTO taskDTO = new TaskDTO();
        taskDTO.setTitle("Test Title");
        taskDTO.setDescription("Test task description");
        taskDTO.setDeadline(LocalDateTime.of(2020, 04, 04, 20, 0));
        TaskDTO taskDTO1 = taskService.addTask(taskDTO);

        SubTaskDTO subTaskDTO = new SubTaskDTO();
        subTaskDTO.setTitle("Test Sub Title");
        subTaskDTO.setDescription("Test Subtask description");

        // method to be tested
        taskService.addSubTask(taskDTO1.getId(),subTaskDTO);

        // checks
        TaskDTO task = taskService.getTask(taskDTO1.getId());
        assertNotNull(taskDTO1.getSubTasks());

        SubTaskDTO subTask = task.getSubTasks().get(0);
        assertNotNull(subTask);
        assertEquals("Test Sub Title", subTask.getTitle());
        assertEquals("Test Subtask description", subTask.getDescription());
    }
}
