package com.caresoft.clinicapp.model;

import java.util.ArrayList;
import java.util.Date;

import com.caresoft.clinicapp.AdminCompatibleConHIPAA;
import com.caresoft.clinicapp.UsuarioCompatibleConHIPAA;

public class UsuarioAdmin extends Usuario implements UsuarioCompatibleConHIPAA, AdminCompatibleConHIPAA {

    private Integer employeeID;
    private String role;
    private ArrayList<String> securityIncidents;
	
	public UsuarioAdmin(Integer id, String role) {
		super(id);
		this.employeeID = id; // ? No se usa
		this.role = role;
		this.securityIncidents = new ArrayList<String>();
	}

	@Override
	public boolean assignPin(int pin) {
		if (String.valueOf(pin).length() >= 6) {
			return true;
		}
		return false;
	}

	@Override
	public boolean accessAuthorized(Integer confirmedAuthID) {
		if (this.id == confirmedAuthID) {
			return true;
		} else {
			this.authIncident();
			return false;
		}
	}
	
	@Override
	public ArrayList<String> reportSecurityIncidents() {
		return this.getSecurityIncidents();
	}
	
    public void newIncident(String notes) {
        String report = String.format(
            "Fecha y hora de env�o: %s \n, Reportado por ID: %s\n Notas: %s \n", 
            new Date(), this.id, notes
        );
        securityIncidents.add(report);
    }
    
    public void authIncident() {
        String report = String.format(
            "Fecha y hora de env�o: %s \n, ID: %s\n Notas: %s \n", 
            new Date(), this.id, "FALL� EL INTENTO DE AUTORIZACI�N PARA ESTE USUARIO"
        );
        securityIncidents.add(report);
    }

	public Integer getEmployeeID() {
		return employeeID;
	}

	public void setEmployeeID(Integer employeeID) {
		this.employeeID = employeeID;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	public ArrayList<String> getSecurityIncidents() {
		return securityIncidents;
	}

	public void setSecurityIncidents(ArrayList<String> securityIncidents) {
		this.securityIncidents = securityIncidents;
	}
	
}
