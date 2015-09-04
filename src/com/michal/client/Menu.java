package com.michal.client;

import java.io.IOException;
import java.util.Scanner;
import java.util.UUID;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;
import org.codehaus.jackson.map.ObjectMapper;

import com.michal.User;

public class Menu {

	Scanner scanner = new Scanner(System.in);

	private UUID tempToken;

	DefaultHttpClient httpClient = new DefaultHttpClient();

	public void wyswietlMenu() {
		String str = null;
		int wybor = 0;

		do {

			System.out.println("Wybierz opcje");
			System.out.println("(1) Zaloguj sie");
			System.out.println("(2) Sprawdz czy jestes zalogowany");
			System.out.println("(3) Dodaj Ksiazke");
			System.out.println("(4) wypisz ksiazki");
			try {

				str = scanner.nextLine();
				wybor = Integer.parseInt(str);

				switch (wybor) {

				case 1:
					logowanie();
					break;
				case 2:
					czyZalogowany(getTempToken());
					break;
				case 3:
					dodajKsiazke(getTempToken());
					break;
				case 4:
					wypiszKsiazki(getTempToken());
					break;
				}

			} catch (Exception e) {
			}

		} while (wybor != 5);

	}

	private void wypiszKsiazki(UUID tempToken2) throws ClientProtocolException, IOException {
		
		HttpGet getRequest = new HttpGet(
				"http://localhost:8080/RestToken/log/books/all/"+getTempToken());

		getRequest.addHeader("accept", "application/json");

		HttpResponse response = httpClient.execute(getRequest);

		int statusCode = response.getStatusLine().getStatusCode();
		if (statusCode != 200) {
			throw new RuntimeException("Failed with HTTP error code : "
					+ statusCode);
		}

		HttpEntity httpEntity = response.getEntity();
		String apiOutput = EntityUtils.toString(httpEntity);

		System.out.println(apiOutput);
	}

	private void dodajKsiazke(UUID tempToken2) throws ClientProtocolException, IOException {
		System.out.println("podaj id: ");
		String id = scanner.nextLine();
		System.out.println("podaj tytul: ");
		String tytul = scanner.nextLine();
		System.out.println("podaj autora: ");
		String autor = scanner.nextLine();
		
		HttpGet getRequest = new HttpGet(
				"http://localhost:8080/RestToken/log/books/"+id+"/"+tytul+"/"+autor+"/"+getTempToken());

		getRequest.addHeader("accept", "application/json");

		HttpResponse response = httpClient.execute(getRequest);

		int statusCode = response.getStatusLine().getStatusCode();
		if (statusCode != 200) {
			throw new RuntimeException("Failed with HTTP error code : "
					+ statusCode);
		}

		HttpEntity httpEntity = response.getEntity();
		String apiOutput = EntityUtils.toString(httpEntity);

		System.out.println(apiOutput);
		
		
	}

	private void czyZalogowany(UUID tempToken2) throws ClientProtocolException, IOException {
	
			HttpGet getRequest = new HttpGet(
					"http://localhost:8080/RestToken/log/login/"+getTempToken());

			getRequest.addHeader("accept", "application/json");

			HttpResponse response = httpClient.execute(getRequest);

			int statusCode = response.getStatusLine().getStatusCode();
			if (statusCode != 200) {
				throw new RuntimeException("Failed with HTTP error code : "
						+ statusCode);
			}

			HttpEntity httpEntity = response.getEntity();
			String apiOutput = EntityUtils.toString(httpEntity);

			System.out.println(apiOutput);

		
	
		
	}

	private void logowanie() throws ClientProtocolException, IOException {
		
		System.out.println("Enter Login: ");
		String login = scanner.nextLine();
		System.out.println("Enter Password: ");
		String password = scanner.nextLine();
		
		

			HttpGet getRequest = new HttpGet(
					"http://localhost:8080/RestToken/log/" + login + "/"
							+ password);

			getRequest.addHeader("accept", "application/json");

			HttpResponse response = httpClient.execute(getRequest);

			int statusCode = response.getStatusLine().getStatusCode();
			if (statusCode != 200) {
				throw new RuntimeException("Failed with HTTP error code : "
						+ statusCode);
			}

			HttpEntity httpEntity = response.getEntity();
			String apiOutput = EntityUtils.toString(httpEntity);

			System.out.println(apiOutput);
			
			ObjectMapper objectMapper = new ObjectMapper();

			User user = objectMapper.readValue(apiOutput, User.class);
			
			setTempToken(user.getToken());
		
		
	
		
	}

	public UUID getTempToken() {
		return tempToken;
	}

	public void setTempToken(UUID tempToken) {
		this.tempToken = tempToken;
	}

}
