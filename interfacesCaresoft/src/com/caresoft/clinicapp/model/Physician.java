package com.caresoft.clinicapp.model;

import java.util.ArrayList;
import java.util.Date;

import com.caresoft.clinicapp.UsuarioCompatibleConHIPAA;

public class Physician extends Usuario implements UsuarioCompatibleConHIPAA {

	private ArrayList<String> patientNotes;
	
	public Physician(Integer id) {
		super(id);
	}
	
	public void newPatientNotes(String notes, String patientName, Date date) {
		String report = String.format(
	            "Fecha y hora de envío: %s \n", date);
	        report += String.format("Reportado por ID: %s\n", this.id);
	        report += String.format("Nombre del paciente: %s\n", patientName);
	        report += String.format("Notas: %s \n", notes);
	        this.patientNotes.add(report);
	}

	@Override
	public boolean assignPin(int pin) {
		if (String.valueOf(pin).length() == 4) {
			return true;
		}
		return false;
	}

	@Override
	public boolean accessAuthorized(Integer confirmedAuthID) {
		if (this.getId() == confirmedAuthID) {
			return true;
		}
		return false;
	}

	public ArrayList<String> getPatientNotes() {
		return patientNotes;
	}

	public void setPatientNotes(ArrayList<String> patientNotes) {
		this.patientNotes = patientNotes;
	}
	
}
