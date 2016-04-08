package albert.practice.collection;

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class ComConfigPK implements Serializable {
    private static final long serialVersionUID = 1L;

    private String serviceType;

    private String configKey;
}
