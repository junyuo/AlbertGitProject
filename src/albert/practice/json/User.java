package albert.practice.json;

import java.io.Serializable;

import lombok.Data;
import lombok.ToString;

@Data
@ToString
public class User implements Serializable {
    private static final long serialVersionUID = 1L;

    private String id;
    private String login;
    private String mail;
}
