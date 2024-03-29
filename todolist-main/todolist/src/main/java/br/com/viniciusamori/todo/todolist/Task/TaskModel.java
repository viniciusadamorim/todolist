package br.com.viniciusamori.todo.todolist.Task;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;
import java.util.UUID;

@Data
@Entity(name = "tb_tasks")
public class TaskModel {

    @Id
    @GeneratedValue(generator = "UUID")
    private UUID id;
    private String description;
    @Column(length = 50)
    private String tittle;
    private LocalDateTime startAt;
    private LocalDateTime endAt;
    private String priority;
    private UUID idUser;

    public void setTittle(String tittle) throws Exception {
        if (tittle.length() > 50 ){
            throw new Exception("O Campo Tittle deve conter no máximo 50 caracteres");
        }
        this.tittle = tittle;
    }

    @CreationTimestamp
    private LocalDateTime localDateTime;

}
