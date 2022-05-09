package com.example.billing.util;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import com.example.billing.utils.TaxUtil;

public class TaxUtilTest {

	
	@Test
	void roundToHigherCent_test_decimal_change() {
		assertEquals(TaxUtil.roundToHigherCent(27.678), 27.70);
	}

	@Test
	void roundToHigherCent_test_unit_change() {
		assertEquals(TaxUtil.roundToHigherCent(20.03), 20.05);
	}

	@Test
	void roundToHigherCent_test_unchange() {
		assertEquals(TaxUtil.roundToHigherCent(21), 21);
	}
}
