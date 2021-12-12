package fhtw;

public class Todo {

// id, description, name,

    private String id;
    private String description;
 //   private String name;
    private Integer statusId;

    public String getId() {
        return id;
    }

    public String getDescription() {
        return description;
    }

/*
    public String getName() {
        return name;
    }
*/

    public void setId(String id) {
        this.id = id;
    }

    public void setDescription(String description) {
        this.description = description;
    }

/*    public void setName(String name) {
        this.name = name;
    }*/

    public Integer getStatusId() {
        return statusId;
    }

    public void setStatusId(Integer statusId) {
        this.statusId = statusId;
    }

}
