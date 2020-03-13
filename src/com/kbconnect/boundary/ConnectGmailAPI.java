package com.kbconnect.boundary;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.security.GeneralSecurityException;
import java.util.Arrays;
import java.util.List;
import javax.mail.MessagingException;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import com.google.api.client.auth.oauth2.Credential;
import com.google.api.client.extensions.java6.auth.oauth2.AuthorizationCodeInstalledApp;
import com.google.api.client.extensions.jetty.auth.oauth2.LocalServerReceiver;
import com.google.api.client.googleapis.auth.oauth2.GoogleAuthorizationCodeFlow;
import com.google.api.client.googleapis.auth.oauth2.GoogleClientSecrets;
import com.google.api.client.googleapis.javanet.GoogleNetHttpTransport;
import com.google.api.client.http.HttpTransport;
import com.google.api.client.json.JsonFactory;
import com.google.api.client.json.jackson2.JacksonFactory;
import com.google.api.client.util.store.FileDataStoreFactory;
import com.google.api.services.gmail.GmailScopes;
import com.kbconnect.entity.GmailCredential;

public class ConnectGmailAPI {

	GmailCredential credential;

	/**
	 * connectGmailService method is to get GmailCredentila through authorizing by auth2 server to get token
	 * before processing  connectGamilService method, 
	 * using particular gmail address to get clientId and so no by Gmail API console. 
	 * 
	 * 
	 * @return GmailCredential 
	 * @throws GeneralSecurityException
	 * @throws IOException
	 * @throws ParseException
	 */
	@SuppressWarnings("unused")
	public GmailCredential connectGmaiService() throws GeneralSecurityException, IOException, ParseException {

		final java.util.logging.Logger buggyLogger = java.util.logging.Logger
				.getLogger(FileDataStoreFactory.class.getName());
		buggyLogger.setLevel(java.util.logging.Level.SEVERE);
		// initialize context as httpTransport
		HttpTransport httpTransport = GoogleNetHttpTransport.newTrustedTransport();

		final String APPLICATION_NAME = "kbconnect";
		final JsonFactory JSON_FACTORY = JacksonFactory.getDefaultInstance();
		final String TOKENS_DIRECTORY_PATH = "tokens";
		final String CREDENTIALS_FILE_PATH = "tokens/kbconnect_credential.json";
		// define the variables for credential
		String clientId;
		String clientSecret;
		String hostEmailAddress = "csis3275kbconnect@gmail.com";
		/**
		 * kbconnet admin gmail address information: first name: kbconnect; last name:
		 * csis3275 password="kbconnect3275";
		 */

		File f = new File(CREDENTIALS_FILE_PATH);
		if (f.exists()) {
			System.out.println("json file is existed");
		}
		// read json file and get String data
		// JSON parser object to read and parser file
		JSONParser jsonParser = new JSONParser();
		FileReader readJson = new FileReader(CREDENTIALS_FILE_PATH);

		// instantiate a JSONObject
		JSONObject jsonObject = new JSONObject();
		// parse readfile to JSONObject
		jsonObject = (JSONObject) jsonParser.parse(readJson);
		// inside a JSONObject IF it is WebApplication, the key is web , otherwise it is "installed"
		JSONObject currCredential = (JSONObject) jsonObject.get("installed");
		// get values by keys from the inside JSONObject
		clientId = currCredential.get("client_id").toString();
		clientSecret = currCredential.get("client_secret").toString();

		/**
		 * Global instance of the scopes required by this connectGmailAPI. If modifying
		 * these scopes, delete your previously saved tokens/ folder.
		 */

		List<String> SCOPES = Arrays.asList(GmailScopes.MAIL_GOOGLE_COM);

		// try to get token
		InputStream in = new FileInputStream(CREDENTIALS_FILE_PATH);

		// InputStream in= GmailTEST.class.getResourceAsStream(CREDENTIALS_FILE_PATH);
		System.out.println(in.toString());

		if (in == null) {
			throw new FileNotFoundException("Resource not found: " + CREDENTIALS_FILE_PATH);
		}

		// GoogleCredential credential = GoogleCredential.fromStream(new
		// FileInputStream("tokens/kbconnect_credential.json"))
		GoogleClientSecrets clientSecrets = GoogleClientSecrets.load(JSON_FACTORY, new InputStreamReader(in));
		in.close();

		// Build flow and trigger user authorization request.
		GoogleAuthorizationCodeFlow flow = new GoogleAuthorizationCodeFlow.Builder(httpTransport, JSON_FACTORY,
				clientSecrets, SCOPES)
						.setDataStoreFactory(new FileDataStoreFactory(new java.io.File(TOKENS_DIRECTORY_PATH)))
						.setAccessType("offline").build();

		LocalServerReceiver receiver = new LocalServerReceiver.Builder().setPort(8887).build();

		Credential cred = new AuthorizationCodeInstalledApp(flow, receiver).authorize("user");

		System.out.println(cred.getAccessToken() + "this access token");
		System.out.println(cred.getRefreshToken() + "this refreshToken");

		GmailCredential crediential = new GmailCredential();
		crediential.setAccessToken(cred.getAccessToken());
		crediential.setRefreshToken(cred.getRefreshToken());
		crediential.setClientId(clientId);
		crediential.setUserEmail(hostEmailAddress);
		crediential.setClientSecret(clientSecret);

		return crediential;

	}
	
	

}

