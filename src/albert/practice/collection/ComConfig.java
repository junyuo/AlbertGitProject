package albert.practice.collection;

import java.io.Serializable;
import java.sql.Timestamp;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class ComConfig implements Serializable {

    private static final long serialVersionUID = 1L;

    private ComConfigPK id;

    private String configValue;

    private Timestamp updateDate;

    private String userId;

}
