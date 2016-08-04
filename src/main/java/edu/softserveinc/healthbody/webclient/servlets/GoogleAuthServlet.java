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

import javax.servlet.ServletException;
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
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		PrintWriter out = response.getWriter();
		String rn = System.lineSeparator();

		try {
			// get code
			String code = request.getParameter("code");
			// format parameters to post
			String urlParameters = "code=" + code
					+ "&client_id=48524677967-juniqolaio06efre3m3q7774097q50u8.apps.googleusercontent.com"
					+ "&client_secret=KBpMscuWOZc43u-4KKpwbE5T" + "&redirect_uri=http://localhost:8080/HealthBody-WebClient/GoogleAuthServ"
					+ "&grant_type=authorization_code";

			// post parameters
			URL url = new URL("https://accounts.google.com/o/oauth2/token");
			URLConnection urlConn = url.openConnection();
			urlConn.setDoOutput(true);
			OutputStreamWriter writer = new OutputStreamWriter(urlConn.getOutputStream());
			writer.write(urlParameters);
			writer.flush();

			// get output in outputString
			String line, outputString = "";
			BufferedReader reader = new BufferedReader(new InputStreamReader(urlConn.getInputStream()));
			while ((line = reader.readLine()) != null) {
				outputString += line;
			}
			log.info(outputString + rn);

			// get Access Token
			JsonObject json = new JsonParser().parse(outputString).getAsJsonObject();
			String access_token = json.get("access_token").getAsString();
			log.info(access_token + rn);

			// get User Info
			url = new URL("https://www.googleapis.com/oauth2/v1/userinfo?access_token=" + access_token);
			urlConn = url.openConnection();
			GooglePojo data = new Gson().fromJson(new InputStreamReader(urlConn.getInputStream(), StandardCharsets.UTF_8), GooglePojo.class);
			log.info(data.toString() + rn);
			writer.close();
			reader.close();

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
			gg.setName("Name group number 1");
			gg.setCount("0");
			gg.setDescriptions("");
			gg.setScoreGroup("");

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
			userDTO.setRoleName("user");
			userDTO.setStatus(null);
			userDTO.setScore("0");
			userDTO.getGroups().add(gg);
			userDTO.setIsDisabled("false");
			log.info(userDTO.toString());

			// work with base
			HealthBodyService service = new HealthBodyServiceImplService().getHealthBodyServiceImplPort();
				if (service.getUserByLogin(login) == null) {
					service.createUser(userDTO);
					UserDTO ud = service.getUserByLogin(login);
					out.append(login + ", wellcome! You've singed up HealthBody!" + rn);
					out.flush();
				} else {
					UserDTO ud = service.getUserByLogin(login);
					out.append(login + ", wellcome HealthBody!");
					out.flush();
				}

		} catch (IOException e) {
			log.error("IOException catched" + e);
			return;
		}
	}

	public String getGoogleGender(String a) {
		String b = null;
		if ("male".equalsIgnoreCase(a))
			b = "m";
		else if ("female".equalsIgnoreCase(a))
			b = "w";
		else
			b = "o";
		return b;
	}
}