package bo.custom.impl;

import bo.custom.ApplicationUserBo;
import dao.DaoFactory;
import dao.DaoType;
import dao.custom.ApplicationUserDao;
import dto.ApplicationUserDto;
import entity.ApplicationUser;

import java.io.IOException;
import java.sql.SQLException;

public class ApplicationUserBoImpl implements ApplicationUserBo {
    private ApplicationUserDao  applicationUserDao = DaoFactory.getInstance().getDao(DaoType.APPLICATION_USER);
    @Override
    public boolean signup(ApplicationUserDto dto) throws SQLException, IOException, ClassNotFoundException {
        return applicationUserDao.create(
                new ApplicationUser(
                        dto.getEmail(), dto.getFullName(), dto.getPassword()
                )
        );
    }

    @Override
    public ApplicationUserDto login(String email) throws SQLException, IOException, ClassNotFoundException {
        ApplicationUser selectedUser = applicationUserDao.findByEmail(email);
        if(selectedUser == null){
            return null;
        }
        return new ApplicationUserDto(
                selectedUser.getEmail(),
                selectedUser.getFullName(),
                selectedUser.getPassword()
        );
    }
}
