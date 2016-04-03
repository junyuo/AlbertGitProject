package albert.practice.xstream.beans;

import java.io.Serializable;
import java.util.List;

import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;

public class Attributes implements Serializable {

	private static final long serialVersionUID = 1L;

	private String WorkLoad;

	private String DetailDocCat;

	private String ScanDate;

	private String ScanTime;

	private String StockingNumber;

	private String CaseId;

	private List<PolicyNumbers> PolicyNumbers;

	private List<ProposalNums> ProposalNums;

	public String getWorkLoad() {
		return WorkLoad;
	}

	public void setWorkLoad(String WorkLoad) {
		this.WorkLoad = WorkLoad;
	}

	public String getDetailDocCat() {
		return DetailDocCat;
	}

	public void setDetailDocCat(String DetailDocCat) {
		this.DetailDocCat = DetailDocCat;
	}

	public String getStockingNumber() {
		return StockingNumber;
	}

	public void setStockingNumber(String StockingNumber) {
		this.StockingNumber = StockingNumber;
	}

	public List<PolicyNumbers> getPolicyNumbers() {
		return PolicyNumbers;
	}

	public void setPolicyNumbers(List<PolicyNumbers> policyNumbers) {
		PolicyNumbers = policyNumbers;
	}

	public String getCaseId() {
		return CaseId;
	}

	public void setCaseId(String CaseId) {
		this.CaseId = CaseId;
	}

	public String getScanDate() {
		return ScanDate;
	}

	public void setScanDate(String ScanDate) {
		this.ScanDate = ScanDate;
	}

	public String getScanTime() {
		return ScanTime;
	}

	public void setScanTime(String ScanTime) {
		this.ScanTime = ScanTime;
	}

	public List<ProposalNums> getProposalNums() {
		return ProposalNums;
	}

	public void setProposalNums(List<ProposalNums> proposalNums) {
		ProposalNums = proposalNums;
	}

	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this, ToStringStyle.MULTI_LINE_STYLE);
	}
}
