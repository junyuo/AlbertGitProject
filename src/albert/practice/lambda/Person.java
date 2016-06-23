package albert.practice.lambda;

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class Person implements Serializable{

    /**
     * 
     */
    private static final long serialVersionUID = 1L;
    
    private String name;
    private Gender gender;
    private int age;
    private String email;
    
}
