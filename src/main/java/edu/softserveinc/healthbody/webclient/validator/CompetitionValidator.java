package edu.softserveinc.healthbody.webclient.validator;

import java.time.LocalTime;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;
import edu.softserveinc.healthbody.webclient.healthbody.webservice.CompetitionDTO;
import edu.softserveinc.healthbody.webclient.healthbody.webservice.HealthBodyService;
import edu.softserveinc.healthbody.webclient.healthbody.webservice.HealthBodyServiceImplService;

@Component
public class CompetitionValidator implements Validator {

	public boolean supports(Class<?> clazz) {
		return CompetitionDTO.class.isAssignableFrom(clazz);
	}
	
	public void validate(Object target, Errors errors) {
		CompetitionDTO competitionDTO = (CompetitionDTO) target;
		
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "name", "name.empty", "Please enter a competition name");
        String competitionName = competitionDTO.getName();
        if (competitionName.length() > 20 && competitionName.length() < 2) {
                errors.rejectValue("name", "name.tooLong", "Please enter value betwen than or equal 2-20 characters");
        }
        
        HealthBodyServiceImplService healthBody = new HealthBodyServiceImplService();
        HealthBodyService service = healthBody.getHealthBodyServiceImplPort();
        if (service.getCompetitionViewByName(competitionName) != null) {
                errors.rejectValue("name", "name.incorrect", "Competition name already exist");
        }

        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "description", "description.empty", "Please enter a competition description");
		
        
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "startDate", "startDate.empty", "Please enter a competition startDate");
		
		
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "finishDate", "finishDate.empty", "Please enter a competition finishDate");

		LocalTime startDate = LocalTime.parse(competitionDTO.getStartDate());
		LocalTime finishDate = LocalTime.parse(competitionDTO.getFinishDate());

		if (startDate.isAfter(finishDate)) {
			errors.rejectValue("startDate", "startDate.incorrect", "Please enter correct start and finish dates");
		}

	}
}