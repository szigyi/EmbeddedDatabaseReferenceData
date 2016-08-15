package hu.szigyi.test.rule;

import java.util.List;

import org.junit.rules.TestRule;
import org.junit.runner.Description;
import org.junit.runners.model.Statement;
import org.springframework.jdbc.core.JdbcTemplate;

import hu.szigyi.test.annotation.RefDataAnnotationProcessor;

public class RefDataRule implements TestRule {
	
	private RefDataAnnotationProcessor refDataAnnotationProcessor;
	
	private JdbcTemplate jdbcTemplate;
	
	public RefDataRule() {
	}

	public RefDataRule(RefDataAnnotationProcessor annotationProcessor, JdbcTemplate jdbcTemplate) {
		this.refDataAnnotationProcessor = annotationProcessor;
		this.jdbcTemplate = jdbcTemplate;
	}

	@Override
	public Statement apply(Statement base, Description description) {
		return new Statement() {
			
			@Override
			public void evaluate() throws Throwable {
				Class<?> testClass = description.getTestClass();
				List<String> refs = refDataAnnotationProcessor.process(testClass);
				
				for (String ref : refs) {
					jdbcTemplate.execute(ref);
				}
				
				base.evaluate();
			}
		};
	}

	public void setRefDataAnnotationProcessor(RefDataAnnotationProcessor refDataAnnotationProcessor) {
		this.refDataAnnotationProcessor = refDataAnnotationProcessor;
	}

	public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}
}
