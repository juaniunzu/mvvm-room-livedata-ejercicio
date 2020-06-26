package com.example.mvvm_livedata_room;


import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

//tableName nos deja elegir el nombre de la tabla que se creara
//por defecto crea el nombre con el nombre de la java class
@Entity(tableName = "note_table")
public class Note {

    @PrimaryKey(autoGenerate = true)
    private Integer id;
    private String title;
    private String description;

    //con ColumnInfo le cambiamos el nombre a la columna, por defecto toma el nombre del atributo
    @ColumnInfo(name = "priority_column")
    private Integer priority;

    public Note() {
    }

    public Note(String title, String description, Integer priority) {
        this.title = title;
        this.description = description;
        this.priority = priority;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
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

    public Integer getPriority() {
        return priority;
    }

    public void setPriority(Integer priority) {
        this.priority = priority;
    }
}
