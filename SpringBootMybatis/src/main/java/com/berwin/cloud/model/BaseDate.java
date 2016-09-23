package com.berwin.cloud.model;

import java.io.Serializable;

public class BaseDate implements Serializable {
	private static final long serialVersionUID = -4317757503801152058L;
	
	private String tx_id;
	private String tx_data;
	private String tx_data_name;

	public String getTx_id() {
		return tx_id;
	}

	public void setTx_id(String tx_id) {
		this.tx_id = tx_id;
	}

	public String getTx_data() {
		return tx_data;
	}

	public void setTx_data(String tx_data) {
		this.tx_data = tx_data;
	}

	public String getTx_data_name() {
		return tx_data_name;
	}

	public void setTx_data_name(String tx_data_name) {
		this.tx_data_name = tx_data_name;
	}

	@Override
	public String toString() {
		return "BaseDate [tx_id=" + tx_id + ", tx_data=" + tx_data + ", tx_data_name=" + tx_data_name + "]";
	}

}
