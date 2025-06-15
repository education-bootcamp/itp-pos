package bo.custom;

import dto.ApplicationUserDto;

import java.io.IOException;
import java.sql.SQLException;

public interface ApplicationUserBo {
    public boolean signup(ApplicationUserDto dto) throws SQLException, IOException, ClassNotFoundException;
}
