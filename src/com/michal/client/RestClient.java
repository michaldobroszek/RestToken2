package com.michal.client;

import java.util.Scanner;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;
import org.codehaus.jackson.map.ObjectMapper;

import com.michal.User;

public class RestClient {

	public static void main(String[] args) throws Exception {
		
		
		Menu menu = new Menu();
		
		menu.wyswietlMenu();
	}

}
