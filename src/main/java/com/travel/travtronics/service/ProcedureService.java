package com.travel.travtronics.service;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import jakarta.persistence.EntityManager;
import jakarta.persistence.ParameterMode;
import jakarta.persistence.StoredProcedureQuery;

@Service
public class ProcedureService {

	EntityManager entityManager;

	public ProcedureService(@Qualifier("srmEntityManager") EntityManager entityManager) {

		this.entityManager = entityManager;;
	}

	public String generateProductSequenceNumber(String sqModule, String prefix, int length) {

		StoredProcedureQuery procedureQuery = entityManager.createStoredProcedureQuery("sequence_number_generator");

		procedureQuery.registerStoredProcedureParameter("sequence_module_in", String.class, ParameterMode.IN);
		procedureQuery.registerStoredProcedureParameter("sequence_prefix_in", String.class, ParameterMode.IN);
		procedureQuery.registerStoredProcedureParameter("number_length", Integer.class, ParameterMode.IN);

		procedureQuery.setParameter("sequence_module_in", sqModule);
		procedureQuery.setParameter("sequence_prefix_in", prefix);
		procedureQuery.setParameter("number_length", length);

		return (String) procedureQuery.getSingleResult();

	}

}
