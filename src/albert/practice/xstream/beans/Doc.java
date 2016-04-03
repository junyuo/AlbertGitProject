package albert.practice.xstream.beans;

import java.io.Serializable;

import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;

public class Doc implements Serializable {

	private static final long serialVersionUID = 1L;

	// 訂單編號
	private String regId;

	// 要保人ID
	private String ownerId;

	// 保單號碼
	private String policyNumber;

	// 被保人 ID
	private String insuredId;

	// 表單代碼
	private String formId;

	/**
	 * Gets the reg id.
	 * 
	 * @return the reg id
	 */
	public String getRegId() {
		return regId;
	}

	/**
	 * Sets the reg id.
	 * 
	 * @param regId
	 *            the new reg id
	 */
	public void setRegId(String regId) {
		this.regId = regId;
	}

	/**
	 * Gets the owner id.
	 * 
	 * @return the owner id
	 */
	public String getOwnerId() {
		return ownerId;
	}

	/**
	 * Sets the owner id.
	 * 
	 * @param ownerId
	 *            the new owner id
	 */
	public void setOwnerId(String ownerId) {
		this.ownerId = ownerId;
	}

	/**
	 * Gets the policy number.
	 * 
	 * @return the policy number
	 */
	public String getPolicyNumber() {
		return policyNumber;
	}

	/**
	 * Sets the policy number.
	 * 
	 * @param policyNumber
	 *            the new policy number
	 */
	public void setPolicyNumber(String policyNumber) {
		this.policyNumber = policyNumber;
	}

	/**
	 * Gets the insured id.
	 * 
	 * @return the insured id
	 */
	public String getInsuredId() {
		return insuredId;
	}

	/**
	 * Sets the insured id.
	 * 
	 * @param insuredId
	 *            the new insured id
	 */
	public void setInsuredId(String insuredId) {
		this.insuredId = insuredId;
	}

	/**
	 * Gets the form id.
	 * 
	 * @return the form id
	 */
	public String getFormId() {
		return formId;
	}

	/**
	 * Sets the form id.
	 * 
	 * @param formId
	 *            the new form id
	 */
	public void setFormId(String formId) {
		this.formId = formId;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this, ToStringStyle.MULTI_LINE_STYLE);
	}

}
