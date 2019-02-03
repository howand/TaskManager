package com.bsd.taskmanager.service.exception;

public class MandatoryFieldException extends RuntimeException {

	private static final long serialVersionUID = 7613130487040053964L;

	public MandatoryFieldException(String mandatoryField) {
		super("Mandatory field not supplied: " + mandatoryField);
	}
}
