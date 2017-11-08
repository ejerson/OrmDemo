package edu.cnm.deepdive.demo.ormdemo.helpers;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import com.j256.ormlite.android.apptools.OrmLiteSqliteOpenHelper;
import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.support.ConnectionSource;
import com.j256.ormlite.table.TableUtils;
import edu.cnm.deepdive.demo.ormdemo.entities.Student;
import java.sql.SQLException;

public class OrmHelper extends OrmLiteSqliteOpenHelper{

  private static final String DATABASE_NAME = "students.db";
  private static final int DATABASE_VERSION = 1;

  //Data access object (Daos are parametarized by entity type <Student> and the data type of the primary key <Integer>)
  private Dao<Student, Integer> studentDao = null;

  public OrmHelper(Context context) {
    super(context, DATABASE_NAME, null, DATABASE_VERSION);
  }

  @Override
  public void onCreate(SQLiteDatabase database, ConnectionSource connectionSource) {
    // student.class is a class object, which gives code access to a world information about the class
    try {
      TableUtils.createTable(connectionSource, Student.class);
      populateDatabase();

    } catch (SQLException e) {
      // RunTimeException is not a checked exception
      throw new RuntimeException(e);
    }


  }

  @Override
  public void onUpgrade(SQLiteDatabase database, ConnectionSource connectionSource, int oldVersion,
      int newVersion) {

  }

  // Think of an entities type as a class
  @Override
  public void close() {
    // any Dao reference must be set to null to make sure Garbage collection has access to the object
    studentDao = null;
    super.close();
  }

  //Method to handle my Dao
  // synchronized manages multiple threads that are accessing this method, one at a time is the rule
  public synchronized Dao<Student, Integer> getStudentDao() throws SQLException {
    if(studentDao == null) {
      studentDao = getDao(Student.class);
    }
    return studentDao;
  }

  private void populateDatabase() throws SQLException {
    // a lot of deserialization depends on a no parameter constructor
    Student student = new Student();
    student.setName("Mortimer Snerd");

    getStudentDao().create(student);
    //data access object?

    student = new Student();
    student.setName("Charlie McCarthy");
    getStudentDao().create(student);

  }

}
