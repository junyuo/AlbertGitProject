package albert.practice.xstream.beans;

import java.io.Serializable;
import java.util.List;

import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;

public class ProposalNums implements Serializable {

	private static final long serialVersionUID = 1L;

	private String ProposalNum;
	private List<ProposalNums> ProposalNumList;

	public String getProposalNum() {
		return ProposalNum;
	}

	public void setProposalNum(String proposalNum) {
		ProposalNum = proposalNum;
	}

	public List<ProposalNums> getProposalNumList() {
		return ProposalNumList;
	}

	public void setProposalNumList(List<ProposalNums> proposalNumList) {
		ProposalNumList = proposalNumList;
	}

	public ProposalNums(String proposalNum) {
		super();
		ProposalNum = proposalNum;
	}

	public ProposalNums() {
		super();
	}

	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this, ToStringStyle.MULTI_LINE_STYLE);
	}

}
