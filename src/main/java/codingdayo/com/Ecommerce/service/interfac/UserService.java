package codingdayo.com.Ecommerce.service.interfac;

import codingdayo.com.Ecommerce.dto.LoginRequest;
import codingdayo.com.Ecommerce.dto.Response;
import codingdayo.com.Ecommerce.dto.UserDto;
import codingdayo.com.Ecommerce.entity.User;

public interface UserService {

    Response registerUser(UserDto registrationRequest);

    Response loginUser(LoginRequest loginRequest);

    Response getAllUsers();

    User getLoginUser();

    Response getUserInfoAndOrderHistory();

}
