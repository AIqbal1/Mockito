package com.spring.maven.mockito.dummy;

import static org.junit.Assert.*;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;

import org.junit.Test;

import com.spring.maven.mockito.dummy.DummyStudent;
import com.spring.maven.mockito.dummy.Grades;
import com.spring.maven.mockito.dummy.Marks;
import com.spring.maven.mockito.dummy.Teacher;

public class TeacherTest {

	@Test 
	public void when_marks_above_seventy_five_percent_returns_very_good() {
		DummyStudent dummyStudent = new DummyStudent();
		
		Marks inEnglish = new Marks(dummyStudent, "English002", new BigDecimal("81.00"));
		Marks inMath = new Marks(dummyStudent, "Math005", new BigDecimal("97.00"));
		Marks inHistory = new Marks(dummyStudent, "History007", new BigDecimal("79.00"));
		
		List<Marks> marks = Arrays.asList(inHistory, inMath, inEnglish);
		Grades grade = new Teacher().generateGrade(marks);
		
		assertEquals(Grades.VeryGood, grade);
	}
	
	
}
