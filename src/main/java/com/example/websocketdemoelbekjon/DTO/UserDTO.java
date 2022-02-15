package com.example.websocketdemoelbekjon.DTO;



import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserDTO {

    private  String firstname;
    private String lastname;
    private String username;
    private String email;
    private List<Integer> roleListId;

}
