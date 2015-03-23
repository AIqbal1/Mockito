package com.spring.maven.mockito.fake;

import java.util.List;

import com.spring.maven.mockito.dummy.Student;

public interface StudentDao {
	public void batchUpdate(List<Student> students);
}
