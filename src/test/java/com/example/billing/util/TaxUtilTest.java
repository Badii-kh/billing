package com.example.billing.util;

import com.example.billing.utils.TaxUtil;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class TaxUtilTest {

	
	@Test
	void roundToHigherCent_test_decimal_change() {
		assertEquals(27.70, TaxUtil.roundToHigherCent(27.678));
	}

	@Test
	void roundToHigherCent_test_unit_change() {
		assertEquals(20.05, TaxUtil.roundToHigherCent(20.03));
	}

	@Test
	void roundToHigherCent_test_unchange() {
		assertEquals(21, TaxUtil.roundToHigherCent(21));
	}
}
