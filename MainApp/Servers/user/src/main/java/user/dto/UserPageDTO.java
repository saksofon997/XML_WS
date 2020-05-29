package user.dto;

import lombok.Data;
import org.springframework.data.domain.Page;
import user.model.User;

import java.util.ArrayList;
import java.util.List;

@Data
public class UserPageDTO {

    private List<UserDTO> content;
    private int pageNo;
    private int totalPages;

    public UserPageDTO() {
        content = new ArrayList<UserDTO>();
    }
}
