package edu.bsuir.onlineshop.web.mapper;

import edu.bsuir.onlineshop.domain.entity.User;
import edu.bsuir.onlineshop.web.model.UserModel;
import org.mapstruct.Mapper;

@Mapper
public interface UserMapper {

    UserModel mapToModel(User user);

    User mapToEntity(UserModel userModel);

}
