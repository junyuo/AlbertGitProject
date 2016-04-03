package albert.practice.xstream.beans;

import java.io.Serializable;

import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;

public class Root implements Serializable {

	private static final long serialVersionUID = 1L;

	private Attributes Attributes;

	public Attributes getAttributes() {
		return Attributes;
	}

	public void setAttributes(Attributes Attributes) {
		this.Attributes = Attributes;
	}

	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this, ToStringStyle.MULTI_LINE_STYLE);
	}
}
