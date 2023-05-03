package com.heb.assessment;

import com.heb.assessment.exception.CartException;
import com.heb.assessment.model.complex.ItemsAndCoupons;
import com.heb.assessment.model.item.ItemsList;
import com.heb.assessment.model.receipt.ReceiptTotals;
import com.heb.assessment.service.ReceiptService;
import com.heb.assessment.utils.FileUtil;
import com.heb.assessment.utils.MapperUtil;
import org.junit.Assert;
import org.junit.Before;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

@SpringBootTest
@RunWith(SpringJUnit4ClassRunner.class)
class AssessmentApplicationTests {
	@Autowired
	private ReceiptService receiptService;

	private ItemsAndCoupons testItemsAndCoupons;
	private ItemsList testItemsAndCouponsOneList;
	private ItemsList testItemsAndCouponsTwoList;
	private ItemsList testItemsAndCouponsThreeList;
	private ItemsList testItemsAndCouponsFourList;
	private Path workingDir;

	//Unit tests
	@Test
	void testFeatureOne() throws CartException, IOException {
		String testJson = Files.readString(Path.of("", "src/main/resources").resolve("test_json.json"));
		ItemsAndCoupons testItemsAndCoupons = MapperUtil.deserializeItemsAndCoupons(testJson);

		String resultFile = Files.readString(Path.of("", "src/main/resources").resolve("test_feature_one_json.json"));
		ReceiptTotals testItemsAndCouponsList =  MapperUtil.deserializeReceiptTotalsList(resultFile);

		Assert.assertEquals("These should be equal: ", receiptService.calculateFeatureOneReceipt(testItemsAndCoupons), testItemsAndCouponsList);
	}

	@Test
	void testFeatureTwo() throws CartException, IOException {
		String testJson = Files.readString(Path.of("", "src/main/resources").resolve("test_json.json"));
		ItemsAndCoupons testItemsAndCoupons = MapperUtil.deserializeItemsAndCoupons(testJson);

		String resultFile = Files.readString(Path.of("", "src/main/resources").resolve("test_feature_two_json.json"));
		ReceiptTotals testItemsAndCouponsList =  MapperUtil.deserializeReceiptTotalsList(resultFile);

		Assert.assertEquals("These should be equal: ", receiptService.calculateFeatureTwoReceipt(testItemsAndCoupons), testItemsAndCouponsList);
	}

	@Test
	void testFeatureThree() throws CartException, IOException {
		String testJson = Files.readString(Path.of("", "src/main/resources").resolve("test_json.json"));
		ItemsAndCoupons testItemsAndCoupons = MapperUtil.deserializeItemsAndCoupons(testJson);

		String resultFile = Files.readString(Path.of("", "src/main/resources").resolve("test_feature_three_json.json"));
		ReceiptTotals testItemsAndCouponsList =  MapperUtil.deserializeReceiptTotalsList(resultFile);

		Assert.assertEquals("These should be equal: ", receiptService.calculateFeatureThreeReceipt(testItemsAndCoupons), testItemsAndCouponsList);
	}

	@Test
	void testFeatureFour() throws CartException, IOException {
		String testJson = Files.readString(Path.of("", "src/main/resources").resolve("test_json.json"));
		ItemsAndCoupons testItemsAndCoupons = MapperUtil.deserializeItemsAndCoupons(testJson);

		String resultFile = Files.readString(Path.of("", "src/main/resources").resolve("test_feature_four_json.json"));
		ReceiptTotals testItemsAndCouponsList =  MapperUtil.deserializeReceiptTotalsList(resultFile);

		Assert.assertEquals("These should be equal: ", receiptService.calculateFeatureFourReceipt(testItemsAndCoupons), testItemsAndCouponsList);
	}
}
