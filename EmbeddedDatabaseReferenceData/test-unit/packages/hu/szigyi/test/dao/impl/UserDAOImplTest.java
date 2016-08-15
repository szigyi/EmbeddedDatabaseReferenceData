package hu.szigyi.test.dao.impl;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import hu.szigyi.test.domain.User;
import hu.szigyi.test.rule.RefDataRule;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:hu/szigyi/test/config/application-context.xml" })
public class UserDAOImplTest {
	
	@Rule
	@Autowired
	public RefDataRule refDataRule;

	@Autowired
	private UserDAOImpl userDAOImpl;
	
	@Test
	public void shouldFindUserById_whenValidIdIsGiven() {
		int id = 45;
		String name = "Szigyi";
		
		User user = userDAOImpl.findById(id);
		
		assertThat(user.getName(), is(equalTo(name)));
	}
}
