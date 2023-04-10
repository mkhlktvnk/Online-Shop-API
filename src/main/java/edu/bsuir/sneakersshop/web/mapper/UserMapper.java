package edu.bsuir.sneakersshop.web.mapper;

import edu.bsuir.sneakersshop.domain.entity.User;
import edu.bsuir.sneakersshop.web.model.UserModel;
import org.mapstruct.Mapper;

@Mapper
public interface UserMapper {
    UserModel mapToModel(User user);

    User mapToEntity(UserModel userModel);
}
