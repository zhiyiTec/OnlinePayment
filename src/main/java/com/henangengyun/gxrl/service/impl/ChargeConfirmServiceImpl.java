package com.henangengyun.gxrl.service.impl;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.henangengyun.gxrl.mapper.ChargeConfirmMapper;
import com.henangengyun.gxrl.po.ChargeInfo;
import com.henangengyun.gxrl.po.ConfirmInfo;
import com.henangengyun.gxrl.po.SeasonFee;
import com.henangengyun.gxrl.service.ChargeConfirmService;
import com.henangengyun.gxrl.service.ChargeInfoService;
import com.henangengyun.gxrl.service.ChargeRecordService;
import com.henangengyun.gxrl.service.SeasonFeeService;

@Service
public class ChargeConfirmServiceImpl implements ChargeConfirmService {
	private static Logger log = LogManager.getLogger(ChargeConfirmServiceImpl.class.getName());

	@Autowired
	ChargeRecordService chargeRecordService;

	@Autowired
	SeasonFeeService seasonFeeService;

	@Autowired
	ChargeInfoService chargeInfoService;

	@Autowired
	ChargeConfirmMapper chargeConfirmMapper;

	/**
	 * 1、调用缴费查询，计算总费用，滞纳金 2、校对金额 3、缴费成功后插入新的缴费记录(有可能多条记录) 4、记录本地日志
	 */
	public void ChargeConfirm(ConfirmInfo confirmInfo) throws ParseException, IOException {
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS");
		// 1、调用缴费查询
		List<SeasonFee> seasonFees = seasonFeeService.getSeasonFeeByNo(confirmInfo.getNo());
		List<ChargeInfo> chargeInfos = chargeInfoService.getChargeInfoByNo(confirmInfo.getNo());
		// 计算滞纳金
		for (SeasonFee seasonFee : seasonFees) {
			seasonFeeService.countDelayFee(seasonFee);
			seasonFeeService.countOwedFee(seasonFee);
		}
		// 计算总金额
		for (ChargeInfo chargeInfo : chargeInfos) {
			chargeInfoService.countTotalOwedFee(seasonFees, chargeInfo);
		}

		log.info("微信支付订单号" + confirmInfo.getTransaction_id());
		log.info("用户编号" + confirmInfo.getNo());
		log.info("订单金额" + confirmInfo.getTotal_fee());
		log.info("现金支付金额" + confirmInfo.getCash_fee());
		log.info("应缴总金额" + chargeInfoService.returnTotalFee(seasonFees));
		log.info("===========================================================");
		// 2、校对金额
		if (Math.abs(confirmInfo.getTotal_fee() - confirmInfo.getCash_fee()) < 1e-6) {

			if (Math.abs(confirmInfo.getCash_fee() - chargeInfoService.returnTotalFee(seasonFees)) < 1e-6) {
				// 3、判断本次缴费是否包含多个季度欠费，若是，则遍历
				for (SeasonFee getSeason : seasonFees) {

					if (!getSeason.getState().equals("2") && !getSeason.getState().equals("5")) {
						try {
							insertChargeRecord(getSeason.getHeatingSeason(), confirmInfo.getNo(),
									getSeason.getOwedFee(), getSeason.getDelayFee(),
									formatter.format(confirmInfo.getTime_end()).toString(),
									confirmInfo.getTransaction_id(), getSeason.getId(), getSeason.getFeeId());
							log.info("插入成功! SeasonFeeId:" + getSeason.getFeeId());
							log.info("===========================================================");
						} catch (Exception e) {
							e.printStackTrace();
							log.error("Season：" + getSeason.getHeatingSeason() + "\nClientId：" + getSeason.getId()
									+ "\nClientNo：" + confirmInfo.getNo() + "\nPayedFee：" + getSeason.getOwedFee()
									+ "\nDelayFee：" + getSeason.getDelayFee() + "\nPayedTime："
									+ formatter.format(confirmInfo.getTime_end()).toString() + "\nSeasonFeeId："
									+ getSeason.getFeeId() + "\nStreamNo：" + confirmInfo.getTransaction_id());
							log.info("===========================================================");
						}
					}
				}
			} else {
				log.error("微信支付订单号" + confirmInfo.getTransaction_id());
				log.error("支付金额与需缴总金额不符！");
				log.info("===========================================================");
			}
		} else {
			log.warn("微信支付订单号" + confirmInfo.getTransaction_id());
			log.warn("支付金额与订单金额不符！");
			log.info("===========================================================");
		}
	}

	public void insertChargeRecord(String Season, String No, float cash_fee, float delayFee, String time_end,
			String transaction_id, long ClientId, long FeeId) {
		String ClientNo = No;
		chargeConfirmMapper.insertChargeRecord(Season, ClientId, ClientNo, cash_fee, delayFee, time_end, FeeId,
				transaction_id);
	}
}
