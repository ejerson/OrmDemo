package edu.cnm.deepdive.demo.ormdemo.entities;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;
import java.sql.Timestamp;


// connects my class to a specific table in my database
@DatabaseTable(tableName = "STUDENT")
// student class corresponds with STUDENT table
public class Student {

  // generatedId = true, auto increments an id
  @DatabaseField(columnName = "STUDENT_ID", generatedId = true)
  private int id;

  // will get the current time and use it as a timestamp
  @DatabaseField(columnName = "CREATED", columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP",
  format = "yyyy-MM-dd HH:mm:ss", canBeNull = false, readOnly = true)
  private Timestamp created;

  @DatabaseField(columnName = "NAME", canBeNull = false)
  private String name;

  public int getId() {
    return id;
  }

  public Timestamp getCreated() {
    return created;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }
}
