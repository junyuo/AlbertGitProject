package albert.practice.xstream.beans;

import java.io.Serializable;
import java.util.List;

import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;

public class PolicyNumbers implements Serializable {

    /**
     * 
     */
    private static final long serialVersionUID = 1L;

    private String PolicyNumber;
    private List<PolicyNumbers> PolicyNumberList;

    public List<PolicyNumbers> getPolicyNumberList() {
        return PolicyNumberList;
    }

    public void setPolicyNumberList(List<PolicyNumbers> policyNumberList) {
        PolicyNumberList = policyNumberList;
    }

    public PolicyNumbers(String policyNumber) {
        this.PolicyNumber = policyNumber;
    }

    public String getPolicyNumber() {
        return PolicyNumber;
    }

    public void setPolicyNumber(String policyNumber) {
        this.PolicyNumber = policyNumber;
    }

    public PolicyNumbers() {
        super();
        // TODO Auto-generated constructor stub
    }

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this, ToStringStyle.MULTI_LINE_STYLE);
    }
}
