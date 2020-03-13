package com.kbconnect.boundary;


	
	
	import java.io.ByteArrayOutputStream;
	import java.io.IOException;
	import java.util.Properties;
	import javax.mail.MessagingException;
	import javax.mail.Session;
	import javax.mail.internet.InternetAddress;
	import javax.mail.internet.MimeMessage;
	import com.google.api.client.auth.oauth2.Credential;
	import com.google.api.client.googleapis.auth.oauth2.GoogleCredential;
	import com.google.api.client.http.HttpTransport;
	import com.google.api.client.json.JsonFactory;
	import com.google.api.client.json.jackson2.JacksonFactory;
	import com.google.api.client.util.Base64;
	import com.google.api.services.gmail.Gmail;
	import com.google.api.services.gmail.model.Message;
    import com.kbconnect.entity.GmailCredential;

	public class GmailServiceImplement {

		private static String APPLICATION_NAME = "kbconnect";

		private static JsonFactory JSON_FACTORY = JacksonFactory.getDefaultInstance();

		private HttpTransport httpTransport;
		private GmailCredential gmailCredentials;

		// Constructor
		public GmailServiceImplement() {

		}
		
		public void setHttpTransport(HttpTransport httpTransport) {
			this.httpTransport= httpTransport;
		};
		
		public void setGmailCredential(GmailCredential gmailCredentials) {
			this.gmailCredentials= gmailCredentials;
		}

		public boolean sendMessage(String recipientAddress, String subject, String body)
				throws MessagingException, IOException {
			Message message = createMessageWithEmail(
					createEmail(recipientAddress, gmailCredentials.getUserEmail(), subject, body));

			return createGmail().users().messages().send(gmailCredentials.getUserEmail(), message).execute().getLabelIds()
					.contains("SENT");
		}

		private Gmail createGmail() {
			Credential credential = authorize();
			return new Gmail.Builder(this.httpTransport, JSON_FACTORY, credential).setApplicationName(APPLICATION_NAME)
					.build();
		}

		private MimeMessage createEmail(String to, String from, String subject, String bodyText) throws MessagingException {
			MimeMessage email = new MimeMessage(Session.getDefaultInstance(new Properties(), null));
			email.setFrom(new InternetAddress(from));
			email.addRecipient(javax.mail.Message.RecipientType.TO, new InternetAddress(to));
			email.setSubject(subject);
			email.setText(bodyText);
			return email;
		}

		private Message createMessageWithEmail(MimeMessage emailContent) throws MessagingException, IOException {
			ByteArrayOutputStream buffer = new ByteArrayOutputStream();
			emailContent.writeTo(buffer);

			return new Message().setRaw(Base64.encodeBase64URLSafeString(buffer.toByteArray()));
		}

		private Credential authorize() {
			return new GoogleCredential.Builder().setTransport(httpTransport).setJsonFactory(JSON_FACTORY)
					.setClientSecrets(gmailCredentials.getClientId(), gmailCredentials.getClientSecret()).build()
					.setAccessToken(gmailCredentials.getAccessToken()).setRefreshToken(gmailCredentials.getRefreshToken());
		}
	}

	
	

