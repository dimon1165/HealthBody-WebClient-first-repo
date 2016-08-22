package edu.softserveinc.healthbody.webclient.servlets;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.URL;
import java.net.URLConnection;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import edu.softserveinc.healthbody.webclient.constants.GoogleConstants;
import edu.softserveinc.healthbody.webclient.healthbody.webservice.HealthBodyService;
import edu.softserveinc.healthbody.webclient.healthbody.webservice.HealthBodyServiceImplService;
import edu.softserveinc.healthbody.webclient.healthbody.webservice.UserDTO;
import edu.softserveinc.healthbody.webclient.models.ExeptionResponse;
import edu.softserveinc.healthbody.webclient.utils.EmailSender;
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

		// get code
		String code = request.getParameter("code");
		// format parameters to post
		String urlParameters = "code=" + code + "&client_id=" + GoogleConstants.CLIENT_ID + "&client_secret="
				+ GoogleConstants.CLIENT_SECRET + "&redirect_uri=" + GoogleConstants.REDIRECT_URI + "&grant_type="
				+ GoogleConstants.GRANT_TYPE;

		try {
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

			// form UserDTO
			String email = data.getEmail();
			String login = email.substring(0, email.indexOf("@")).toString();

			HealthBodyService service = new HealthBodyServiceImplService().getHealthBodyServiceImplPort();
			if (service.getUserByLogin(login) == null) {
				UserDTO userDTO = makeNewUser(data);
				userDTO.setIdUser(UUID.randomUUID().toString());
				userDTO.setPassword(access_token.substring(0, 15));
				service.createUser(userDTO);
				EmailSender emailSender = EmailSender.getInstance();
						emailSender.setParameters("Health Body Service Registration","Dear "
						+ userDTO.getFirstname() 
						+ GoogleConstants.SUCSESFULL_REGISTRATION_EMALE,
						email);
				Thread thread = new Thread(emailSender);
				thread.start();
			} else {
				UserDTO userDTO = service.getUserByLogin(login);
				userDTO.setPassword(access_token.substring(0, 15));
				service.updateUser(userDTO);
			}
			List<GrantedAuthority> authorities = new ArrayList<GrantedAuthority>();
			if ("admin".equals(service.getUserByLogin(login).getRoleName())) {
				authorities.add(new SimpleGrantedAuthority("ROLE_ADMIN"));
			}
			authorities.add(new SimpleGrantedAuthority("ROLE_USER"));

			Authentication authentication = new UsernamePasswordAuthenticationToken(login,
					service.getUserByLogin(login).getPassword(), authorities);
			SecurityContextHolder.getContext().setAuthentication(authentication);
			
			response.sendRedirect("main.html");

		} catch (IOException e) {
			log.error("IOException catched" + e);
			return;
		}

		finally {
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

	public UserDTO makeNewUser(GoogleUser data) {
		// form UserDTO
		String email = data.getEmail();
		String login = email.substring(0, email.indexOf("@")).toString();
		String firstname = data.getGiven_name();
		String lastname = data.getFamily_name();
		String photoURL = data.getPicture();
		String gender = data.getGender();
		HealthBodyService service = new HealthBodyServiceImplService().getHealthBodyServiceImplPort();
		UserDTO userDTO = new UserDTO();

		userDTO.setLogin(login);
		userDTO.setFirstname(firstname);
		userDTO.setLastname(lastname);
		userDTO.setPassword("");
		userDTO.setEmail(email);
		userDTO.setAge("0");
		userDTO.setWeight("0.0");
		userDTO.setGender(gender);
		userDTO.setPhotoURL(photoURL);
		userDTO.setRoleName(GoogleConstants.DEFAULT_ROLE_NAME);
		userDTO.setStatus(null);
		userDTO.setScore("0");
		if (userDTO.getGroups().isEmpty()) {
			userDTO.getGroups().add(service.getGroupByName(GoogleConstants.DEFAULT_GROUP_NAME));
		}
		userDTO.setIsDisabled(GoogleConstants.DEFAULT_USER_DISABLED);
		log.info(userDTO.toString());
		return userDTO;
	}

	@Override
	protected void service(HttpServletRequest arg0, HttpServletResponse arg1) throws ServletException, IOException {
		try {
			super.service(arg0, arg1);
		} catch (Exception e) {

			ExeptionResponse responsObject = new ExeptionResponse(e.getMessage(), 400);
			PrintWriter out = arg1.getWriter();
			out.print(responsObject.getMessage());
			out.flush();
		}
	}
}
