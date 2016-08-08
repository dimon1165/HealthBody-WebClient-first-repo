package edu.softserveinc.healthbody.webclient.servlets;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.URL;
import java.net.URLConnection;
import java.nio.charset.StandardCharsets;
import java.util.UUID;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import edu.softserveinc.healthbody.webclient.api.GroupDTO;
import edu.softserveinc.healthbody.webclient.api.HealthBodyService;
import edu.softserveinc.healthbody.webclient.api.HealthBodyServiceImplService;
import edu.softserveinc.healthbody.webclient.api.UserDTO;
import edu.softserveinc.healthbody.webclient.constants.GoogleConstants;
import lombok.extern.slf4j.Slf4j;

@WebServlet("/GoogleAuthServ")
@Slf4j
public class GoogleAuthServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public GoogleAuthServlet() {
		super();
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) {
		String rn = System.lineSeparator();
		OutputStreamWriter writer = null;
		BufferedReader reader = null;

		try {
			PrintWriter out = response.getWriter();
			// get code
			String code = request.getParameter("code");
			// format parameters to post
			String urlParameters = "code=" + code + "&client_id=" + GoogleConstants.CLIENT_ID + "&client_secret="
					+ GoogleConstants.CLIENT_SECRET + "&redirect_uri=" + GoogleConstants.REDIRECT_URI + "&grant_type="
					+ GoogleConstants.GRANT_TYPE;

			// post parameters
			URL url = new URL(GoogleConstants.TOKEN_URL);
			URLConnection urlConn = url.openConnection();
			urlConn.setDoOutput(true);
			writer = new OutputStreamWriter(urlConn.getOutputStream());
			writer.write(urlParameters);
			writer.flush();

			// get output in outputString
			String line, outputString = "";
			reader = new BufferedReader(new InputStreamReader(urlConn.getInputStream()));
			while ((line = reader.readLine()) != null) {
				outputString += line;
			}
			log.info(outputString + rn);

			// get Access Token
			JsonObject json = new JsonParser().parse(outputString).getAsJsonObject();
			String access_token = json.get("access_token").getAsString();
			log.info(access_token + rn);

			// get User Info
			url = new URL(GoogleConstants.USERINFO_URL + access_token);
			urlConn = url.openConnection();
			GoogleUser data = new Gson().fromJson(
					new InputStreamReader(urlConn.getInputStream(), StandardCharsets.UTF_8), GoogleUser.class);
			log.info(data.toString() + rn);

			// form UserDTO
			String email = data.getEmail();
			String login = email.substring(0, email.indexOf("@")).toString();
			String firstname = data.getGiven_name();
			String lastname = data.getFamily_name();
			String photoURL = data.getPicture();
			String fullgender = data.getGender();
			String gender = getGoogleGender(fullgender);

			GroupDTO gg = new GroupDTO();
			gg.setIdGroup(UUID.randomUUID().toString());
			gg.setName(GoogleConstants.DEFAULT_GROUP_NAME);
			gg.setCount("0");
			gg.setDescriptions("");
			gg.setScoreGroup("");
			gg.setStatus("");

			UserDTO userDTO = new UserDTO();
			userDTO.setIdUser(UUID.randomUUID().toString());
			userDTO.setLogin(login);
			userDTO.setPassword(null);
			userDTO.setFirstname(firstname);
			userDTO.setLastname(lastname);
			userDTO.setEmail(email);
			userDTO.setAge("0");
			userDTO.setWeight("0.0");
			userDTO.setGender(gender);
			userDTO.setPhotoURL(photoURL);
			userDTO.setRoleName(GoogleConstants.DEFAULT_ROLE_NAME);
			userDTO.setStatus(null);
			userDTO.setScore("0");
			userDTO.getGroups().add(gg);
			userDTO.setIsDisabled(GoogleConstants.DEFAULT_USER_DISABLED);
			log.info(userDTO.toString());

			// work with base
			HealthBodyService service = new HealthBodyServiceImplService().getHealthBodyServiceImplPort();
			if (service.getUserByLogin(login) == null) {
				service.createUser(userDTO);
				// UserDTO ud = service.getUserByLogin(login);
				out.append(login + GoogleConstants.GREATING_NOT_REGISTRED + rn);
				out.flush();
			} else {
				// UserDTO ud = service.getUserByLogin(login);
				out.append(login + GoogleConstants.GREATING_ALREADY_REGISTRED);
				out.flush();
			}

		} catch (IOException e) {
			log.error("IOException catched" + e);
			return;
		} finally {
			if (writer != null && reader != null) {
				try {
					writer.close();
					reader.close();
				} catch (IOException e) {
					log.error("IOException catched" + e);
					return;
				}

			}
		}
	}

	public String getGoogleGender(String a) {
		String b = null;
		if ("male".equalsIgnoreCase(a))
			b = GoogleConstants.GOOGLE_MAIL_GENDER;
		else if ("female".equalsIgnoreCase(a))
			b = GoogleConstants.GOOGLE_FEMALE_GENDER;
		else
			b = GoogleConstants.GOOGLE_OTHER_GENDER;
		return b;
	}
}