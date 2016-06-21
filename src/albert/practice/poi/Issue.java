package albert.practice.poi;

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class Issue implements Serializable {
    /**
    * 
    */
    private static final long serialVersionUID = 1L;

    private Integer id;
    private String subject;
    private String status;
    private String priority;
    private String notes;
}
