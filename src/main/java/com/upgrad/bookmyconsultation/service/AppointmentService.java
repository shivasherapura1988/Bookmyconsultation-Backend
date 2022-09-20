package com.upgrad.bookmyconsultation.service;

import com.upgrad.bookmyconsultation.entity.Appointment;
import com.upgrad.bookmyconsultation.exception.InvalidInputException;
import com.upgrad.bookmyconsultation.exception.ResourceUnAvailableException;
import com.upgrad.bookmyconsultation.exception.SlotUnavailableException;
import com.upgrad.bookmyconsultation.repository.AppointmentRepository;
import com.upgrad.bookmyconsultation.repository.UserRepository;
import com.upgrad.bookmyconsultation.util.ValidationUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AppointmentService {

	
	
	//mark it autowired
	@Autowired
	//create an instance of AppointmentRepository called appointmentRepository
	private AppointmentRepository appointmentRepository;

	@Autowired
	private UserRepository userRepository;


	//create a method name appointment with the return type of String and parameter of type Appointment
	public String appointment(Appointment appointment) throws SlotUnavailableException, InvalidInputException {
		//declare exceptions 'SlotUnavailableException' and 'InvalidInputException'

		//validate the appointment details using the validate method from ValidationUtils class
		ValidationUtils.validate(appointment);
		//find if an appointment exists with the same doctor for the same date and time
		//if the appointment exists throw the SlotUnavailableException
		Appointment exisitngAppointment = appointmentRepository.findByDoctorIdAndTimeSlotAndAppointmentDate(
				appointment.getDoctorId(), appointment.getAppointmentDate(), appointment.getTimeSlot());
		if (exisitngAppointment != null) {
			throw new SlotUnavailableException();
		}
		//save the appointment details to the database
		Appointment savedAppointment = appointmentRepository.save(appointment);
		//return the appointment id
		return savedAppointment.getAppointmentId();
	}
	


	//create a method getAppointment of type Appointment with a parameter name appointmentId of type String
	public Appointment getAppointment(String appointmentId) {
		//Use the appointmentid to get the appointment details

		//if the appointment exists return the appointment
		//else throw ResourceUnAvailableException
		//tip: use Optional.ofNullable(). Use orElseThrow() method when Optional.ofNullable() throws NULL
		return Optional.ofNullable(appointmentRepository.findById(appointmentId)).get().orElseThrow
				(ResourceUnAvailableException::new);
	}
	
	public List<Appointment> getAppointmentsForUser(String userId) {
		return appointmentRepository.findByUserId(userId);
	}
}
