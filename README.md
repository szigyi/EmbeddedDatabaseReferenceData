# EmbeddedDatabaseReferenceData
A lib which inserts reference data into the embedded database before the test method runs

## Example
```java
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:hu/szigyi/test/config/application-context.xml" })
public class UserDAOImplTest {
	
	@Rule
	@Autowired
	public RefDataRule refDataRule;

	@Autowired
	private UserDAOImpl userDAOImpl;
	
	@Test
	@RefData("INSERT INTO User (id, name) VALUES (45, 'Szigyi')")
	public void shouldFindUserById_whenValidIdIsGiven() {
		int id = 45;
		String name = "Szigyi";
		
		User user = userDAOImpl.findById(id);
		
		assertThat(user.getName(), is(equalTo(name)));
	}
}
```
