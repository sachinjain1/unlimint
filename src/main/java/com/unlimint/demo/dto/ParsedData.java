package com.unlimint.demo.dto;

import java.util.concurrent.atomic.AtomicInteger;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

@JsonInclude(value = Include.NON_NULL)
public class ParsedData {

	private static final AtomicInteger count = new AtomicInteger(0);
	private Integer id;

	private Long orderId;

	private Double amount;

	private String comment;

	private String filename;

	private Integer line;

	private String result;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Long getOrderId() {
		return orderId;
	}

	public void setOrderId(Long orderId) {
		this.orderId = orderId;
	}

	public Double getAmount() {
		return amount;
	}

	public void setAmount(Double amount) {
		this.amount = amount;
	}

	public String getComment() {
		return comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}

	public String getFilename() {
		return filename;
	}

	public void setFilename(String filename) {
		this.filename = filename;
	}

	public Integer getLine() {
		return line;
	}

	public void setLine(Integer line) {
		this.line = line;
	}

	public String getResult() {
		return result;
	}

	public void setResult(String result) {
		this.result = result;
	}

	public ParsedData(Long orderId, Double amount, String comment, String filename, Integer line, String result) {
		super();
		this.orderId = orderId;
		this.amount = amount;
		this.comment = comment;
		this.filename = filename;
		this.line = line;
		this.result = result;
		this.id = count.incrementAndGet();
	}

	@Override
	public String toString() {
		return "ParsedData [id=" + id + ", orderId=" + orderId + ", amount=" + amount + ", comment=" + comment
				+ ", filename=" + filename + ", line=" + line + ", result=" + result + "]";
	}
}
