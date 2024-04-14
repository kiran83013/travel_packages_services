package com.travel.travtronics.converter;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.stream.Collectors;

public class ItenarySrConverter {
	public static DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");

	/*
	 * generate dates between two check-in dates which includes startDate and
	 * endDate
	 */
	public static List<LocalDate> generateDatesBetwenTwoCheckinDates(LocalDate startDate, LocalDate endDate) {
		return startDate.datesUntil(endDate.plusDays(1)).sorted().collect(Collectors.toList());
	}
}
