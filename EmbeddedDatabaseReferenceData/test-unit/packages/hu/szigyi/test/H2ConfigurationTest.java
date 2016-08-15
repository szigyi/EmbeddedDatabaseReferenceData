package hu.szigyi.test;

import javax.sql.DataSource;

import org.hamcrest.MatcherAssert;
import org.hamcrest.Matchers;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:hu/szigyi/test/config/application-context.xml" })
public class H2ConfigurationTest {
	
	@Autowired
	private DataSource dataSource;
	
	@Test
	public void sanityCheck() {
		MatcherAssert.assertThat(dataSource, Matchers.notNullValue());
	}
}
