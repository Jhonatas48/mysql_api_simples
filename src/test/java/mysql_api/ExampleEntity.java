package mysql_api;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.OffsetDateTime;
import java.time.OffsetTime;

public class ExampleEntity {
    private int id;
    private String name;
    //private LocalDateTime birthdate; 
    //private LocalTime birthdate;
    //private OffsetTime birthdate;
   // private LocalDateTime birthdate;
    private OffsetDateTime birthdate;
    
    @Override
	public String toString() {
		return "ExampleEntity [id=" + id + ", name=" + name + ", birthdate=" + birthdate + "]";
	}
    
    // Getters e Setters (ou métodos de acesso) para os campos da tabela

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

	
    
}
