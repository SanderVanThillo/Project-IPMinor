package be.ucll.project.domain;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;

@Entity
public class SubTask {
    @Id
    @GeneratedValue
    private Long id;

    @NotEmpty
    private String title, description;

    @ManyToOne
    private Task task;

    public SubTask() {
    }

    public SubTask(String title, String description, Task task) {
        this.title = title;
        this.description = description;
        this.task = task;
    }

    public SubTask(Long id, String title, String description, Task task) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.task = task;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Task getTask() {
        return task;
    }

    public void setTask(Task task) {
        this.task = task;
    }
}
