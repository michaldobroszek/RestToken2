package com.michal;

import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import javax.ws.rs.core.Response;

import org.springframework.stereotype.Service;

@Service("exampleService")
public class ServiceImpl implements IService {

	Map<UUID, Calendar> userMap = new HashMap<UUID, Calendar>();
	Map<Integer, Book> bookMap = new HashMap<Integer, Book>();

	@Override
	public User getLogin(String l, String h) {
		User user = new User();
		user.setLogin(l);
		user.setPassword(h);

		Calendar calendar = Calendar.getInstance();

		userMap.put(user.getToken(), calendar);

		return user;

	}

	public Boolean checkToken(UUID token) {

		Calendar calendarNow = Calendar.getInstance();

		int minutes = calendarNow.get(Calendar.MINUTE);
		int seconds = calendarNow.get(Calendar.SECOND);

		Calendar timeLogin = userMap.get(token);

		long difrence = (timeLogin.getTimeInMillis())
				- (calendarNow.getTimeInMillis());

		if (difrence > -60000)
			return true;
		else
			return false;
	}

	@Override
	public Response writeSomething(UUID token) {
		if (checkToken(token) == true)
			return Response.ok("logged").build();
		else
			return Response.ok(invalidToken()).build();
	}

	@Override
	public Response addBook(int id, String title, String author, UUID token) {
		if (checkToken(token) == true) {
			Book book = new Book();
			book.setBookId(id);
			book.setTitle(title);
			book.setAuthor(author);

			bookMap.put(id, book);

			return Response.ok(book).build();

		} else
			return Response.ok(invalidToken()).build();
	}

	public String invalidToken() {

		return "ups token is invalid";
	}

	@Override
	public Response getBooksById(int id, UUID token) {
		if (checkToken(token) == true) {

			return Response.ok(bookMap.get(id)).build();

		} else

			return Response.ok(invalidToken()).build();
	}

	@Override
	public Response getAllBook(UUID token) {
		if (checkToken(token) == true) {

			return Response.ok(bookMap).build();

		} else
			return Response.ok(invalidToken()).build();

	}
}