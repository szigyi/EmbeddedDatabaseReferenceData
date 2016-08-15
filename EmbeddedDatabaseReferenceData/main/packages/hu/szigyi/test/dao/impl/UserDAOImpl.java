package hu.szigyi.test.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.core.ResultSetExtractor;

import hu.szigyi.test.dao.UserDAO;
import hu.szigyi.test.domain.User;

public class UserDAOImpl implements UserDAO {
	
	private static final String FIND_BY_ID = "SELECT id, name FROM szigyi.User WHERE id = ?";
	
	private JdbcTemplate jdbcTemplate;

	@Override
	public User findById(int id) {
		return jdbcTemplate.query(new PreparedStatementCreator() {
			
			@Override
			public PreparedStatement createPreparedStatement(Connection con) throws SQLException {
				PreparedStatement ps = con.prepareStatement(FIND_BY_ID);
				ps.setInt(1, id);
				return ps;
			}
		}, new ResultSetExtractor<User>() {

			@Override
			public User extractData(ResultSet rs) throws SQLException, DataAccessException {
				int id = rs.getInt("id");
				String name = rs.getString("name");
				User user = new User(id, name);
				return user;
			}
		});
	}

	public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}
}
