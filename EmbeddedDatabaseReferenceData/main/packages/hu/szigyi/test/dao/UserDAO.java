package hu.szigyi.test.dao;

import hu.szigyi.test.domain.User;

public interface UserDAO {

	User findById(int id);

}